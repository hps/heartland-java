package com.hps.integrator.applepay.ecv1;

import com.hps.integrator.infrastructure.HpsException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.*;
import java.util.Enumeration;

public class DecryptService
{
    // <editor-fold desc="private variables">
    private final byte[] iv = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };

    private final KeyStoreEntities mKeyStoreEntities;
    // </editor-fold>

    public DecryptService(String privateKeyFilePath, String privateKeyPassword) throws HpsException
    {
        //this.mKeyStoreEntities = PKCSUtils.extractEntities(privateKeyFilePath, privateKeyPassword);
        /**
         * Java has a minimum requirement for keystore password lengths.  Utilities like KeyChain will
         * allow you to specify less but then you will receive an obscure java error when trying to
         * load the keystore.  Check for it here and throw a meaningful error
         */
        if(privateKeyPassword.length() < 6 )
        {
            throw new HpsException("Password must be 6 or more characters");
        }

        X509Certificate certificate;
        ECPrivateKey privateKey;

        try
        {
            KeyStore store = KeyStore.getInstance("PKCS12");

            FileInputStream fs = new FileInputStream(privateKeyFilePath);

            char[] pwd = privateKeyPassword.toCharArray();

            store.load(fs, pwd);

            Enumeration<String> aliases = store.aliases();

            String alias = null;

            /**
             * This logic assumes there is only one key in the keystore.  If
             * there are multiple then need to add the ability to pass in desired
             * alias.  Leaving that out for now to keep it simple
             */
            if(store.size() > 1)
            {
                throw new HpsException("KeyStore contains more than one entry.");
            }

            while (aliases.hasMoreElements()) {
                alias = aliases.nextElement();
            }

            privateKey = (ECPrivateKey) store.getKey(alias, pwd);
            certificate = (X509Certificate)store.getCertificate(alias);

        }
        catch (KeyStoreException e) { throw new HpsException(e.getMessage(), e); }
        catch (NoSuchAlgorithmException e) { throw new HpsException(e.getMessage(), e); }
        catch (UnrecoverableKeyException e) { throw new HpsException(e.getMessage(), e); }
        catch (IOException e) { throw new HpsException(e.getMessage(), e); }
        catch (CertificateException e) { throw new HpsException(e.getMessage(), e); }

        this.mKeyStoreEntities = new KeyStoreEntities(certificate, privateKey);
    }

    public PaymentData decrypt(PaymentToken token) throws HpsException
    {
        Security.addProvider(new BouncyCastleProvider());

        PaymentData result = null;

        try
        {
            final byte[] keyData = Base64.decode(token.getHeader().getEphemeralPublicKey());

//            ECPublicKey ephemeralPublicKey = new ECPublicKeyImpl(keyData);

            X509EncodedKeySpec ks = new X509EncodedKeySpec(keyData);
            KeyFactory kf = KeyFactory.getInstance("EC");
            ECPublicKey ephemeralPublicKey = (ECPublicKey)kf.generatePublic(ks);

            KeyAgreement aKeyAgree = KeyAgreement.getInstance("ECDH");
            aKeyAgree.init(mKeyStoreEntities.getPrivateKey());
            aKeyAgree.doPhase(ephemeralPublicKey, true);

            /** Verify Signature */
            /*if(token.isValid(mKeyStoreEntities.getPrivateKey()) == false)
            {
                throw new HpsException("PKPaymentToken Signature is invalid!");
            }*/

            byte[] sharedSecret = aKeyAgree.generateSecret();
            byte[] algorithmId = ((char)0x0D + "id-aes256-GCM").getBytes("ASCII");
            byte[] partyUInfo = "Apple".getBytes("ASCII");
            byte[] partyVInfo = extractMerchantIdFromCertificateOid("1.2.840.113635.100.6.32");

            byte[] otherInfo = new byte[algorithmId.length + partyUInfo.length + partyVInfo.length];
            System.arraycopy(algorithmId, 0, otherInfo, 0, algorithmId.length);
            System.arraycopy(partyUInfo, 0, otherInfo, algorithmId.length, partyUInfo.length);
            System.arraycopy(partyVInfo, 0, otherInfo, algorithmId.length + partyUInfo.length, partyVInfo.length);

            byte[] keyMaterial = kdf(sharedSecret, otherInfo);

            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding", "BC");

            cipher.init(
                    Cipher.DECRYPT_MODE,
                    new SecretKeySpec(keyMaterial, "AES"),
                    new IvParameterSpec(iv)
            );

            byte[] encryptedData = Base64.decode(token.getData());
            byte[] decryptedData = cipher.doFinal(encryptedData);

            result = new PaymentData(new String(decryptedData, "ASCII"));
        }
        catch (NoSuchProviderException e) { throw new HpsException(e.getMessage(), e); }
        catch (NoSuchPaddingException e) { throw new HpsException(e.getMessage(), e); }
        catch (IllegalBlockSizeException e) { throw new HpsException(e.getMessage(), e); }
        catch (BadPaddingException e) { throw new HpsException(e.getMessage(), e); }
        catch (InvalidAlgorithmParameterException e) { throw new HpsException(e.getMessage(), e); }
        catch (InvalidKeyException e) { throw new HpsException(e.getMessage(), e); }
        catch (NoSuchAlgorithmException e) { throw new HpsException(e.getMessage(), e); }
        catch (UnsupportedEncodingException e) { throw new HpsException(e.getMessage(), e); }
        catch (InvalidKeySpecException e) { throw new HpsException(e.getMessage(), e); }

        return result;
    }

    protected byte[] kdf(byte[] z, byte[] otherInfo) throws NoSuchAlgorithmException
    {
        Digest digest = new SHA256Digest();

        byte[] result = new byte[digest.getDigestSize()];

        digest.update((byte)(1 >> 24));
        digest.update((byte)(1 >> 16));
        digest.update((byte)(1 >> 8));
        digest.update((byte)1);
        digest.update(z, 0, z.length);
        digest.update(otherInfo, 0, otherInfo.length);
        digest.doFinal(result, 0);

        return result;
    }

    protected byte[] extractMerchantIdFromCertificateOid(String oid)
            throws UnsupportedEncodingException
    {
        X509Certificate cert = this.mKeyStoreEntities.getCertificate();
        byte[] merchantIdentifierTlv = cert.getExtensionValue(oid);
        byte[] merchantIdentifier = new byte[64];
        System.arraycopy(merchantIdentifierTlv, 4, merchantIdentifier, 0, 64);

        return Hex.decode(merchantIdentifier);
    }
}