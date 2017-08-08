package com.hps.integrator.tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import com.hps.integrator.entities.HpsDirectMarketData;
import com.hps.integrator.entities.HpsEMVDataType;
import com.hps.integrator.entities.HpsTrackData;
import com.hps.integrator.entities.credit.HpsAccountVerify;
import com.hps.integrator.entities.credit.HpsAuthorization;
import com.hps.integrator.entities.credit.HpsCharge;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.HpsGatewayException;
import com.hps.integrator.infrastructure.emums.EMVchipConditionType;
import com.hps.integrator.services.HpsCreditService;
import com.hps.integrator.services.HpsServicesConfig;
import com.hps.integrator.services.fluent.HpsFluentCreditService;
import com.hps.integrator.tests.testdata.TestCardHolders;
import com.hps.integrator.tests.testdata.TestCreditCards;
import com.hps.integrator.tests.testdata.TestServicesConfig;

public class EMVTests {

	private HpsFluentCreditService creditService;

	public EMVTests() throws HpsException {
		HpsServicesConfig servicesConfig = new HpsServicesConfig();
		servicesConfig.setSecretAPIKey("skapi_cert_MTyMAQBiHVEAewvIzXVFcmUd2UcyBge_eCpaASUp0A");
		servicesConfig.setDeveloperId("012345");
		servicesConfig.setVersionNumber("0001");
		creditService = new HpsFluentCreditService(servicesConfig, true);
	}

	/* Positive Test cases */
	@Test
	public void authorize_visa_EMV() throws HpsException {

		String emvData = "9F4005F000F0A0019F02060000000025009F03060000000000009F2608D90A06501B48564E82027C005F3401019F360200029F0702FF009F"
				+ "0802008C9F0902008C9F34030403029F2701809F0D05F0400088009F0E0508000000009F0F05F0400098005F280208409F390105FFC605DC4000A8"
				+ "00FFC7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F350122950500000080005F2A0208409A031409109B02E8009F21030811539"
				+ "C01009F37045EED3A8E4F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03A400029F410400000002";

		String value = ";4761739001010036=15122011184404889?";

		HpsEMVDataType emvDataType = new HpsEMVDataType();
		emvDataType.setEmvTagData(emvData);
		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(value);

		HpsAuthorization authorizeResponse = creditService.authorize(BigDecimal.valueOf(17.06))
				.withTrackData(trackData)
				.withEMVData(emvDataType)
				.withAllowDuplicates(true)
				.execute();

		assertNotEquals(null, authorizeResponse);
		assertEquals("00", authorizeResponse.getResponseCode());
	}

	@Test
	public void charge_visa_EMV() throws HpsException {

		String emvData = "9F4005F000F0A0019F02060000000025009F03060000000000009F2608D90A06501B48564E82027C005F3401019F360200029F0702FF009F"
				+ "0802008C9F0902008C9F34030403029F2701809F0D05F0400088009F0E0508000000009F0F05F0400098005F280208409F390105FFC605DC4000A8"
				+ "00FFC7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F350122950500000080005F2A0208409A031409109B02E8009F21030811539"
				+ "C01009F37045EED3A8E4F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03A400029F410400000002";

		String value = ";4761739001010036=15122011184404889?";

		HpsEMVDataType emvDataType = new HpsEMVDataType();
		emvDataType.setEmvTagData(emvData);

		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(value);

		HpsCharge chargeResponse = creditService.charge(BigDecimal.valueOf(17.06))
				.withTrackData(trackData)
				.withEMVData(emvDataType)
				.withAllowDuplicates(true)
				.execute();

		assertNotEquals(null, chargeResponse);
		assertEquals("00", chargeResponse.getResponseCode());
	}

	@Test
	public void verify_visa_EMV() throws HpsException {

		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(";4761739001010036=15122011184404889?");

		String emvData = "9F4005F000F0A0019F02060000000025009F03060000000000009F2608D90A06501B48564E82027C005F3401019F360200029F0702FF009F08"
				+ "02008C9F0902008C9F34030403029F2701809F0D05F0400088009F0E0508000000009F0F05F0400098005F280208409F390105FFC605DC4000A800FF"
				+ "C7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F350122950500000080005F2A0208409A031409109B02E8009F21030811539C0100"
				+ "9F37045EED3A8E4F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03A400029F410400000001";

		HpsEMVDataType emvDataType = new HpsEMVDataType();
		emvDataType.setEmvTagData(emvData);
		HpsDirectMarketData directMarketData = new HpsDirectMarketData();
		directMarketData.setInvoiceNumber("123456");

		HpsAccountVerify verifyResponse = creditService.verify()
				.withEMVData(emvDataType)
				.withTrackData(trackData)
				.execute();

		assertNotEquals(null, verifyResponse);
		assertEquals("85", verifyResponse.getResponseCode());
	}
	@Test
	public void charge_visa_EMV_ForCredit() throws HpsException {
		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());

		String emvData = "9F4005F000F0A0019F02060000000025009F03060000000000009F2608D90A06501B48564E82027C005F3401019F360200029F0702FF009F"
				+ "0802008C9F0902008C9F34030403029F2701809F0D05F0400088009F0E0508000000009F0F05F0400098005F280208409F390105FFC605DC4000A8"
				+ "00FFC7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F350122950500000080005F2A0208409A031409109B02E8009F21030811539"
				+ "C01009F37045EED3A8E4F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03A400029F410400000002";

		String value = ";4761739001010036=15122011184404889?";

		HpsEMVDataType emvDataType = new HpsEMVDataType();
		emvDataType.setEmvTagData(emvData);
		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(value);

		HpsCharge chargeResponse=service.charge(BigDecimal.valueOf(17.10), "usd", null, null, true, null, null, null, trackData, emvDataType);

		assertNotEquals(null, chargeResponse);
		assertEquals("00", chargeResponse.getResponseCode());
	}
	@Test
	public void verify_visa_EMV_ForCredit() throws HpsException {
		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());

		String emvData = "9F4005F000F0A0019F02060000000025009F03060000000000009F2608D90A06501B48564E82027C005F3401019F360200029F0702FF009F"
				+ "0802008C9F0902008C9F34030403029F2701809F0D05F0400088009F0E0508000000009F0F05F0400098005F280208409F390105FFC605DC4000A8"
				+ "00FFC7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F350122950500000080005F2A0208409A031409109B02E8009F21030811539"
				+ "C01009F37045EED3A8E4F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03A400029F410400000002";

		String value = ";4761739001010036=15122011184404889?";

		HpsEMVDataType emvDataType = new HpsEMVDataType();
		emvDataType.setEmvTagData(emvData);

		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(value);

		HpsAccountVerify verifyResponse = service.verify(null, null, trackData, null, emvDataType);

		assertNotEquals(null, verifyResponse);
		assertEquals("85", verifyResponse.getResponseCode());
	}

	@Test
	public void authorize_visa_EMV_Issuer_Response_is_Present() throws HpsException {

		String emvData = "9F4005F000F0A0019F02060000000025009F03060000000000009F2608D90A06501B48564E82027C005F3401019F360200029F0702FF009F"
				+ "0802008C9F0902008C9F34030403029F2701809F0D05F0400088009F0E0508000000009F0F05F0400098005F280208409F390105FFC605DC4000A8"
				+ "00FFC7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F350122950500000080005F2A0208409A031409109B02E8009F21030811539"
				+ "C01009F37045EED3A8E4F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03A400029F410400000002";

		String value = ";4761739001010036=15122011184404889?";

		HpsEMVDataType emvDataType = new HpsEMVDataType();
		emvDataType.setEmvTagData(emvData);
		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(value);

		HpsAuthorization authorizeResponse = creditService.authorize(BigDecimal.valueOf(17.06))
				.withTrackData(trackData)
				.withEMVData(emvDataType)
				.withAllowDuplicates(true)
				.execute();

		assertNotEquals(null, authorizeResponse);
		assertNotEquals(null, authorizeResponse.getEmvIssuerResp());
	}

	@Test
	public void charge_visa_EMV_Issuer_Response_is_Present() throws HpsException {

		String emvData = "9F4005F000F0A0019F02060000000025009F03060000000000009F2608D90A06501B48564E82027C005F3401019F360200029F0702FF009F"
				+ "0802008C9F0902008C9F34030403029F2701809F0D05F0400088009F0E0508000000009F0F05F0400098005F280208409F390105FFC605DC4000A8"
				+ "00FFC7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F350122950500000080005F2A0208409A031409109B02E8009F21030811539"
				+ "C01009F37045EED3A8E4F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03A400029F410400000002";

		String value = ";4761739001010036=15122011184404889?";

		HpsEMVDataType emvDataType = new HpsEMVDataType();
		emvDataType.setEmvTagData(emvData);

		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(value);

		HpsCharge chargeResponse = creditService.charge(BigDecimal.valueOf(17.06))
				.withTrackData(trackData)
				.withEMVData(emvDataType)
				.withAllowDuplicates(true)
				.execute();

		assertNotEquals(null, chargeResponse);
		assertNotEquals(null, chargeResponse.getEmvIssuerResp());
	}

	@Test
	public void verify_visa_EMV_Issuer_Response_is_Present() throws HpsException {

		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(";4761739001010036=15122011184404889?");

		String emvData = "9F4005F000F0A0019F02060000000025009F03060000000000009F2608D90A06501B48564E82027C005F3401019F360200029F0702FF009F08"
				+ "02008C9F0902008C9F34030403029F2701809F0D05F0400088009F0E0508000000009F0F05F0400098005F280208409F390105FFC605DC4000A800FF"
				+ "C7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F350122950500000080005F2A0208409A031409109B02E8009F21030811539C0100"
				+ "9F37045EED3A8E4F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03A400029F410400000001";

		HpsEMVDataType emvDataType = new HpsEMVDataType();
		emvDataType.setEmvTagData(emvData);
		HpsDirectMarketData directMarketData = new HpsDirectMarketData();
		directMarketData.setInvoiceNumber("123456");

		HpsAccountVerify verifyResponse = creditService.verify()
				.withEMVData(emvDataType)
				.withTrackData(trackData)
				.execute();

		assertNotEquals(null, verifyResponse);
		assertNotEquals(null, verifyResponse.getEmvIssuerResp());
	}

	@Test
	public void verify_visa_EMV_Issuer_Response_is_Present_ForCredit() throws HpsException {

		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());

		String emvData = "9F4005F000F0A0019F02060000000025009F03060000000000009F2608D90A06501B48564E82027C005F3401019F360200029F0702FF009F"
				+ "0802008C9F0902008C9F34030403029F2701809F0D05F0400088009F0E0508000000009F0F05F0400098005F280208409F390105FFC605DC4000A8"
				+ "00FFC7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F350122950500000080005F2A0208409A031409109B02E8009F21030811539"
				+ "C01009F37045EED3A8E4F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03A400029F410400000002";

		String value = ";4761739001010036=15122011184404889?";

		HpsEMVDataType emvDataType = new HpsEMVDataType();
		emvDataType.setEmvTagData(emvData);

		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(value);

		HpsAccountVerify verifyResponse = service.verify(null, null, trackData, null, emvDataType);

		assertNotEquals(null, verifyResponse);
		assertNotEquals(null, verifyResponse.getEmvIssuerResp());
	}
	@Test
	public void charge_visa_EMV_Issuer_Response_is_Present_ForCredit() throws HpsException {
		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());

		String emvData = "9F4005F000F0A0019F02060000000025009F03060000000000009F2608D90A06501B48564E82027C005F3401019F360200029F0702FF009F"
				+ "0802008C9F0902008C9F34030403029F2701809F0D05F0400088009F0E0508000000009F0F05F0400098005F280208409F390105FFC605DC4000A8"
				+ "00FFC7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F350122950500000080005F2A0208409A031409109B02E8009F21030811539"
				+ "C01009F37045EED3A8E4F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03A400029F410400000002";

		String value = ";4761739001010036=15122011184404889?";

		HpsEMVDataType emvDataType = new HpsEMVDataType();
		emvDataType.setEmvTagData(emvData);
		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(value);

		HpsCharge chargeResponse=service.charge(BigDecimal.valueOf(17.10), "usd", null, null, true, null, null, null, trackData, emvDataType);

		assertNotEquals(null, chargeResponse);
		assertNotEquals(null, chargeResponse.getEmvIssuerResp());
	}

	/* Negative Test cases */
	@Test(expected = HpsGatewayException.class)
	public void authorize_visa_EMV_Prev_Chip_Card_Read_Failed() throws HpsException {

		String emvData = "9F4005F000F0A0019F02060000000025009F03060000000000009F2608D90A06501B48564E82027C005F3401019F360200029F0702FF009F"
				+ "0802008C9F0902008C9F34030403029F2701809F0D05F0400088009F0E0508000000009F0F05F0400098005F280208409F390105FFC605DC4000A8"
				+ "00FFC7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F350122950500000080005F2A0208409A031409109B02E8009F21030811539"
				+ "C01009F37045EED3A8E4F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03A400029F410400000002";

		String value = ";4761739001010036=15122011184404889?";

		HpsEMVDataType emvDataType = new HpsEMVDataType();
		emvDataType.setEmvTagData(emvData);
		emvDataType.setEmvChipCondition(EMVchipConditionType.CHIP_FAILED_PREV_FAILED);
		emvDataType.setEmvChipConditionSpecified(false);
		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(value);

		creditService.authorize(BigDecimal.valueOf(17.06)).withTrackData(trackData).withEMVData(emvDataType)
				.withAllowDuplicates(true).execute();
	}

	@Test(expected = HpsGatewayException.class)
	public void charge_visa_EMV_Prev_Chip_Card_Read_Failed() throws HpsException {

		String emvData = "9F4005F000F0A0019F02060000000025009F03060000000000009F2608D90A06501B48564E82027C005F3401019F360200029F0702FF009F"
				+ "0802008C9F0902008C9F34030403029F2701809F0D05F0400088009F0E0508000000009F0F05F0400098005F280208409F390105FFC605DC4000A8"
				+ "00FFC7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F350122950500000080005F2A0208409A031409109B02E8009F21030811539"
				+ "C01009F37045EED3A8E4F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03A400029F410400000002";

		String value = ";4761739001010036=15122011184404889?";

		HpsEMVDataType emvDataType = new HpsEMVDataType();
		emvDataType.setEmvTagData(emvData);
		emvDataType.setEmvChipCondition(EMVchipConditionType.CHIP_FAILED_PREV_FAILED);
		emvDataType.setEmvChipConditionSpecified(false);
		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(value);

		creditService.charge(BigDecimal.valueOf(17.06))
	          	.withTrackData(trackData)
		        .withEMVData(emvDataType)
				.withAllowDuplicates(true)
				.execute();
	}

	@Test(expected = HpsGatewayException.class)
	public void verify_visa_EMV_Prev_Chip_Card_Read_Failed() throws HpsException {

		String emvData = "9F4005F000F0A0019F02060000000025009F03060000000000009F2608D90A06501B48564E82027C005F3401019F360200029F0702FF009F"
				+ "0802008C9F0902008C9F34030403029F2701809F0D05F0400088009F0E0508000000009F0F05F0400098005F280208409F390105FFC605DC4000A8"
				+ "00FFC7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F350122950500000080005F2A0208409A031409109B02E8009F21030811539"
				+ "C01009F37045EED3A8E4F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03A400029F410400000002";

		String value = ";4761739001010036=15122011184404889?";

		HpsEMVDataType emvDataType = new HpsEMVDataType();
		emvDataType.setEmvTagData(emvData);
		emvDataType.setEmvChipCondition(EMVchipConditionType.CHIP_FAILED_PREV_FAILED);
		emvDataType.setEmvChipConditionSpecified(false);

		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(value);

		creditService.verify()
				.withTrackData(trackData)
				.withEMVData(emvDataType)
				.execute();
	}
	
	@Test(expected = HpsGatewayException.class)
	public void authorize_visa_invalid_EMVData() throws HpsException {

		String emvData = "9F4005F000F0A0019F02060000000025009F03060000000000009F2608D90A06501B48564E82027C005F3401019F360200029F0702FF009F"
				+ "0802008C9F0902008C9F34030403029F2701809F0D05F0400088009F0E0508000000009F0F05F0400098005F280208409F390105FFC605DC4000A8"
				+ "00FFC7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F350122950500000080005F2A0208409A031409109B02E8009F21030811539"
				+ "C01009F37045EED3A8E4F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03A400029F410400000002XX";

		String value = ";4761739001010036=15122011184404889?";

		HpsEMVDataType emvDataType = new HpsEMVDataType();
		emvDataType.setEmvTagData(emvData);
		emvDataType.setEmvChipConditionSpecified(false);

		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(value);

		creditService.authorize(BigDecimal.valueOf(17.06))
				.withTrackData(trackData)
				.withEMVData(emvDataType)
				.withAllowDuplicates(true)
				.execute();
	}

	@Test(expected = HpsGatewayException.class)
	public void charge_visa_invalid_EMVData() throws HpsException {

		String emvData = "9F4005F000F0A0019F02060000000025009F03060000000000009F2608D90A06501B48564E82027C005F3401019F360200029F0702FF009F"
				+ "0802008C9F0902008C9F34030403029F2701809F0D05F0400088009F0E0508000000009F0F05F0400098005F280208409F390105FFC605DC4000A8"
				+ "00FFC7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F350122950500000080005F2A0208409A031409109B02E8009F21030811539"
				+ "C01009F37045EED3A8E4F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03A400029F410400000002XX";

		String value = ";4761739001010036=15122011184404889?";

		HpsEMVDataType emvDataType = new HpsEMVDataType();
		emvDataType.setEmvTagData(emvData);
		emvDataType.setEmvChipConditionSpecified(false);

		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(value);

		creditService.charge(BigDecimal.valueOf(17.06))
				.withTrackData(trackData)
				.withEMVData(emvDataType)
				.withAllowDuplicates(true)
				.execute();
	}

	@Test(expected = HpsGatewayException.class)
	public void verify_visa_invalid_EMVData() throws HpsException {

		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(";4761739001010036=15122011184404889?");

		String emvData = "9F4005F000F0A0019F02060000000025009F03060000000000009F2608D90A06501B48564E82027C005F3401019F360200029F0702FF009F08"
				+ "02008C9F0902008C9F34030403029F2701809F0D05F0400088009F0E0508000000009F0F05F0400098005F280208409F390105FFC605DC4000A800FF"
				+ "C7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F350122950500000080005F2A0208409A031409109B02E8009F21030811539C0100"
				+ "9F37045EED3A8E4F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03A400029F410400000001XX";

		HpsEMVDataType emvDataType = new HpsEMVDataType();
		emvDataType.setEmvTagData(emvData);
		emvDataType.setEmvChipConditionSpecified(false);

		creditService.verify()
				.withEMVData(emvDataType)
				.withTrackData(trackData)
				.execute();
	}
	@Test(expected = HpsException.class)
	public void offlineCharge_visa_invalid_EMVData() throws HpsException {

		String emvData = "9F4005F000F0A0019F02060000000025009F03060000000000009F2608D90A06501B48564E82027C005F3401019F360200029F0702FF009F"
				+ "0802008C9F0902008C9F34030403029F2701809F0D05F0400088009F0E0508000000009F0F05F0400098005F280208409F390105FFC605DC4000A8"
				+ "00FFC7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F350122950500000080005F2A0208409A031409109B02E8009F21030811539"
				+ "C01009F37045EED3A8E4F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03A400029F410400000002XXXXYYY";

		String value = ";4761739001010036=15122011184404889?";

		HpsEMVDataType emvDataType = new HpsEMVDataType();
		emvDataType.setEmvTagData(emvData);
		emvDataType.setEmvChipConditionSpecified(false);

		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(value);
        
		creditService.offlineCharge(BigDecimal.valueOf(17.10))
                .withAllowDuplicates(true)
                .withEMVData(emvDataType)
                .withTrackData(trackData)
                .execute();
	}
	
	@Test(expected = HpsGatewayException.class)
	public void verify_visa_invalid_EMVData_ForCredit() throws HpsException {
		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());

		String emvData = "9F4005F000F0A0019F02060000000025009F03060000000000009F2608D90A06501B48564E82027C005F3401019F360200029F0702FF009F"
				+ "0802008C9F0902008C9F34030403029F2701809F0D05F0400088009F0E0508000000009F0F05F0400098005F280208409F390105FFC605DC4000A8"
				+ "00FFC7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F350122950500000080005F2A0208409A031409109B02E8009F21030811539"
				+ "C01009F37045EED3A8E4F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03A400029F410400000002XX0000X";

		String value = ";4761739001010036=15122011184404889?";

		HpsEMVDataType emvDataType = new HpsEMVDataType();
		emvDataType.setEmvTagData(emvData);
		emvDataType.setEmvChipConditionSpecified(false);

		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(value);

		service.verify(null, null, trackData, null, emvDataType);
	}
	
	@Test(expected = HpsGatewayException.class)
	public void authorize_visa_EMV_ForCredit() throws HpsException {
		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());

		String emvData = "9F4005F000F0A0019F02060000000025009F03060000000000009F2608D90A06501B48564E82027C005F3401019F360200029F0702FF009F"
				+ "0802008C9F0902008C9F34030403029F2701809F0D05F0400088009F0E0508000000009F0F05F0400098005F280208409F390105FFC605DC4000A8"
				+ "00FFC7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F350122950500000080005F2A0208409A031409109B02E8009F21030811539"
				+ "C01009F37045EED3A8E4F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03A400029F410400000002XXXXXX000";

		String value = ";4761739001010036=15122011184404889?";

		HpsEMVDataType emvDataType = new HpsEMVDataType();
		emvDataType.setEmvTagData(emvData);
		emvDataType.setEmvChipConditionSpecified(false);

		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(value);

		service.authorize(new BigDecimal("50"), "usd", TestCreditCards.validAmex(), TestCardHolders.validCardHolder(), true, null, null, trackData, null, emvDataType);
		
	}
	@Test(expected = HpsGatewayException.class)
	public void charge_visa_invalid_EMVData_ForCredit() throws HpsException {
		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());

		String emvData = "9F4005F000F0A0019F02060000000025009F03060000000000009F2608D90A06501B48564E82027C005F3401019F360200029F0702FF009F"
				+ "0802008C9F0902008C9F34030403029F2701809F0D05F0400088009F0E0508000000009F0F05F0400098005F280208409F390105FFC605DC4000A8"
				+ "00FFC7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F350122950500000080005F2A0208409A031409109B02E8009F21030811539"
				+ "C01009F37045EED3A8E4F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03A400029F410400000002XX00XX";

		String value = ";4761739001010036=15122011184404889?";

		HpsEMVDataType emvDataType = new HpsEMVDataType();
		emvDataType.setEmvTagData(emvData);
		emvDataType.setEmvChipConditionSpecified(false);

		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(value);

		service.charge(new BigDecimal("50"), "usd", TestCreditCards.validVisa(), TestCardHolders.validCardHolder(), true, null, null, null, null, emvDataType);
	}
	
}
