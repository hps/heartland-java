package com.hps.integrator.applepay.ecv1;


import java.security.cert.X509Certificate;
import java.security.interfaces.ECPrivateKey;

public class KeyStoreEntities {

    // <editor-fold desc="private variables">
    private final X509Certificate mCertificate;

    private final ECPrivateKey mPrivateKey;
    // </editor-fold>

    public KeyStoreEntities(X509Certificate certificate, ECPrivateKey privateKey)
    {
        this.mCertificate = certificate;
        this.mPrivateKey = privateKey;
    }

    public X509Certificate getCertificate()
    {
        return this.mCertificate;
    }

    public ECPrivateKey getPrivateKey()
    {
        return this.mPrivateKey;
    }
}