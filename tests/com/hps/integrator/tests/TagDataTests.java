package com.hps.integrator.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.hps.integrator.entities.HpsDirectMarketData;
import com.hps.integrator.entities.HpsTagDataType;
import com.hps.integrator.entities.HpsTrackData;
import com.hps.integrator.entities.credit.HpsAccountVerify;
import com.hps.integrator.entities.credit.HpsAuthorization;
import com.hps.integrator.entities.credit.HpsCharge;
import com.hps.integrator.entities.credit.HpsCreditCard;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.HpsGatewayException;
import com.hps.integrator.infrastructure.emums.TagDataTypeTagValuesSource;
import com.hps.integrator.services.HpsCreditService;
import com.hps.integrator.services.HpsServicesConfig;
import com.hps.integrator.services.fluent.HpsFluentCreditService;
import com.hps.integrator.tests.testdata.TestServicesConfig;

public class TagDataTests {

	private HpsFluentCreditService creditService;

	public TagDataTests() throws HpsException {
		HpsServicesConfig servicesConfig = new HpsServicesConfig();
		servicesConfig.setSecretAPIKey("skapi_cert_MTyMAQBiHVEAewvIzXVFcmUd2UcyBge_eCpaASUp0A");
		servicesConfig.setDeveloperId("012345");
		servicesConfig.setVersionNumber("0001");
		creditService = new HpsFluentCreditService(servicesConfig, true);
	}
	
	/*  Positive Tests Cases */
	@Test
	public void authorizevisa_tagData_chip() throws HpsException {

		String tagData = "9F4005F000F0A0019F02060000000025009F03060000000000009F2608D90A06501B48564E82027C005F3401019F360200029F0702FF009F0802008C9F0902008C9F34030403029F2"
				+ "701809F0D05F0400088009F0E0508000000009F0F05F0400098005F280208409F390105FFC605DC4000A800FFC7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F350122"
				+ "950500000080005F2A0208409A031409109B02E8009F21030811539C01009F37045EED3A8E4F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03A40002"
				+ "9F410400000001";

		String value = ";4761739001010036=15122011184404889?";

		HpsTagDataType tagDataType = new HpsTagDataType();
		tagDataType.setTagData(tagData);
		tagDataType.setSource(TagDataTypeTagValuesSource.chip);

		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(value);

		HpsAuthorization authorizeResponse = creditService.authorize(BigDecimal.valueOf(17.06))
				.withAmount(new BigDecimal("50.40"))
				.withTrackData(trackData)
				.withTagData(tagDataType)
				.withAllowDuplicates(true)
				.execute();

		assertNotEquals(null, authorizeResponse);
		assertEquals("00", authorizeResponse.getResponseCode());
	}
	@Test
	public void authorize_visa_tagData_msd() throws HpsException {

		String tagData = "9F4005F000F0A0019F02060000000025009F03060000000000009F2608D90A06501B48564E82027C005F3401019F360200029F0702FF009F0802008C9F0902008C9F34030403029F2"
				+ "701809F0D05F0400088009F0E0508000000009F0F05F0400098005F280208409F390105FFC605DC4000A800FFC7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F350122"
				+ "950500000080005F2A0208409A031409109B02E8009F21030811539C01009F37045EED3A8E4F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03A40002"
				+ "9F410400000001";

		String value = ";4761739001010036=15122011184404889?";

		HpsTagDataType tagDataType = new HpsTagDataType();
		tagDataType.setTagData(tagData);
		tagDataType.setSource(TagDataTypeTagValuesSource.msd);

		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(value);

		HpsAuthorization authorizeResponse = creditService.authorize(BigDecimal.valueOf(17.06))
				.withAmount(new BigDecimal("50.40"))
				.withTrackData(trackData)
				.withTagData(tagDataType)
				.withAllowDuplicates(true)
				.execute();

		assertNotEquals(null, authorizeResponse);
		assertEquals("00", authorizeResponse.getResponseCode());
	}
	@Test
	public void charge_visa_tagData_chip() throws HpsException {

		String tagData = "9F4005F000F0A0019F02060000000025009F03060000000000009F2608D90A06501B48564E82027C005F3401019F360200029F0702FF009F0802008C9F0902008C9F34030403029F2"
				+ "701809F0D05F0400088009F0E0508000000009F0F05F0400098005F280208409F390105FFC605DC4000A800FFC7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F350122"
				+ "950500000080005F2A0208409A031409109B02E8009F21030811539C01009F37045EED3A8E4F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03A40002"
				+ "9F410400000001";

		String value = ";4761739001010036=15122011184404889?";

		HpsTagDataType tagDataType = new HpsTagDataType();
		tagDataType.setTagData(tagData);
		tagDataType.setSource(TagDataTypeTagValuesSource.chip);

		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(value);

		HpsCharge chargeResponse = creditService.charge(BigDecimal.valueOf(17.06))
				.withAmount(new BigDecimal("50.40"))
				.withTrackData(trackData)
				.withTagData(tagDataType)
				.withAllowDuplicates(true)
				.execute();

		assertNotEquals(null, chargeResponse);
		assertEquals("00", chargeResponse.getResponseCode());
	}
	@Test
	public void charge_visa_tagData_msd() throws HpsException {

		String tagData = "9F4005F000F0A0019F02060000000025009F03060000000000009F2608D90A06501B48564E82027C005F3401019F360200029F0702FF009F0802008C9F0902008C9F34030403029F2"
				+ "701809F0D05F0400088009F0E0508000000009F0F05F0400098005F280208409F390105FFC605DC4000A800FFC7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F350122"
				+ "950500000080005F2A0208409A031409109B02E8009F21030811539C01009F37045EED3A8E4F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03A40002"
				+ "9F410400000001";

		String value = ";4761739001010036=15122011184404889?";

		HpsTagDataType tagDataType = new HpsTagDataType();
		tagDataType.setTagData(tagData);
		tagDataType.setSource(TagDataTypeTagValuesSource.msd);

		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(value);

		HpsCharge chargeResponse = creditService.charge(BigDecimal.valueOf(17.06))
				.withAmount(new BigDecimal("50.40"))
				.withTrackData(trackData)
				.withTagData(tagDataType)
				.withAllowDuplicates(true)
				.execute();

		assertNotEquals(null, chargeResponse);
		assertEquals("00", chargeResponse.getResponseCode());
	}
	@Test
	public void verify_visa_tagData_chip() throws HpsException {

		String tagData = "9F4005F000F0A0019F02060000000025009F03060000000000009F2608D90A06501B48564E82027C005F3401019F360200029F0702FF009F0802008C9F0902008C9F34030403029F2"
				+ "701809F0D05F0400088009F0E0508000000009F0F05F0400098005F280208409F390105FFC605DC4000A800FFC7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F350122"
				+ "950500000080005F2A0208409A031409109B02E8009F21030811539C01009F37045EED3A8E4F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03A40002"
				+ "9F410400000001";

		String value = ";4761739001010036=15122011184404889?";

		HpsTagDataType tagDataType = new HpsTagDataType();
		tagDataType.setTagData(tagData);
		tagDataType.setSource(TagDataTypeTagValuesSource.chip);

		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(value);

		HpsAccountVerify verifyResponse = creditService.verify()
				.withTrackData(trackData)
				.withTagData(tagDataType)
				.execute();

		assertNotEquals(null, verifyResponse);
		assertEquals("85", verifyResponse.getResponseCode());
	}
	@Test
	public void verify_visa_tagData_msd() throws HpsException {

		String tagData = "9F4005F000F0A0019F02060000000025009F03060000000000009F2608D90A06501B48564E82027C005F3401019F360200029F0702FF009F0802008C9F0902008C9F34030403029F2"
				+ "701809F0D05F0400088009F0E0508000000009F0F05F0400098005F280208409F390105FFC605DC4000A800FFC7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F350122"
				+ "950500000080005F2A0208409A031409109B02E8009F21030811539C01009F37045EED3A8E4F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03A40002"
				+ "9F410400000001";

		String value = ";4761739001010036=15122011184404889?";

		HpsTagDataType tagDataType = new HpsTagDataType();
		tagDataType.setTagData(tagData);
		tagDataType.setSource(TagDataTypeTagValuesSource.msd);

		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(value);

		HpsAccountVerify verifyResponse = creditService.verify()
				.withTrackData(trackData)
				.withTagData(tagDataType)
				.execute();

		assertNotEquals(null, verifyResponse);
		assertEquals("85", verifyResponse.getResponseCode());
	}
	@Test
	public void charge_visa_tagData_ForCredit_chip() throws HpsException {

		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());

		String tagData = "9F4005F000F0A0019F02060000000025009F03060000000000009F2608D90A06501B48564E82027C005F3401019F360200029F0702FF009F0802008C9F0902008C9F34030403029F2"
				+ "701809F0D05F0400088009F0E0508000000009F0F05F0400098005F280208409F390105FFC605DC4000A800FFC7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F350122"
				+ "950500000080005F2A0208409A031409109B02E8009F21030811539C01009F37045EED3A8E4F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03A40002"
				+ "9F410400000001";

		String value = ";4761739001010036=15122011184404889?";

		HpsTagDataType tagDataType = new HpsTagDataType();
		tagDataType.setTagData(tagData);
		tagDataType.setSource(TagDataTypeTagValuesSource.chip);

		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(value);

		HpsCharge chargeResponse = service.charge(new BigDecimal("50"), "usd", null, null, true, null, null, tagDataType,
				trackData);

		assertNotEquals(null, chargeResponse);
		assertEquals("00", chargeResponse.getResponseCode());
	}
	@Test
	public void charge_visa_tagData_ForCredit_msd() throws HpsException {

		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());

		String tagData = "9F4005F000F0A0019F02060000000025009F03060000000000009F2608D90A06501B48564E82027C005F3401019F360200029F0702FF009F0802008C9F0902008C9F34030403029F2"
				+ "701809F0D05F0400088009F0E0508000000009F0F05F0400098005F280208409F390105FFC605DC4000A800FFC7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F350122"
				+ "950500000080005F2A0208409A031409109B02E8009F21030811539C01009F37045EED3A8E4F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03A40002"
				+ "9F410400000001";

		String value = ";4761739001010036=15122011184404889?";

		HpsTagDataType tagDataType = new HpsTagDataType();
		tagDataType.setTagData(tagData);
		tagDataType.setSource(TagDataTypeTagValuesSource.msd);

		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(value);

		HpsCharge chargeResponse = service.charge(new BigDecimal("50"), "usd", null, null, true, null, null, tagDataType,
				trackData);

		assertNotEquals(null, chargeResponse);
		assertEquals("00", chargeResponse.getResponseCode());
	}
	@Test
	public void verify_visa_tagData_ForCredit_chip() throws HpsException {

		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());

		String tagData = "9F4005F000F0A0019F02060000000025009F03060000000000009F2608D90A06501B48564E82027C005F3401019F360200029F0702FF009F0802008C9F0902008C9F34030403029F2"
				+ "701809F0D05F0400088009F0E0508000000009F0F05F0400098005F280208409F390105FFC605DC4000A800FFC7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F350122"
				+ "950500000080005F2A0208409A031409109B02E8009F21030811539C01009F37045EED3A8E4F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03A40002"
				+ "9F410400000001";

		String value = ";4761739001010036=15122011184404889?";

		HpsTagDataType tagDataType = new HpsTagDataType();
		tagDataType.setTagData(tagData);
		tagDataType.setSource(TagDataTypeTagValuesSource.chip);

		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(value);

		HpsAccountVerify verifyResponse = service.verify(null, null, trackData, tagDataType);

		assertNotEquals(null, verifyResponse);
		assertEquals("85", verifyResponse.getResponseCode());
	}
	@Test
	public void verify_visa_tagData_ForCredit_msd() throws HpsException {

		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());

		String tagData = "9F4005F000F0A0019F02060000000025009F03060000000000009F2608D90A06501B48564E82027C005F3401019F360200029F0702FF009F0802008C9F0902008C9F34030403029F2"
				+ "701809F0D05F0400088009F0E0508000000009F0F05F0400098005F280208409F390105FFC605DC4000A800FFC7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F350122"
				+ "950500000080005F2A0208409A031409109B02E8009F21030811539C01009F37045EED3A8E4F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03A40002"
				+ "9F410400000001";

		String value = ";4761739001010036=15122011184404889?";

		HpsTagDataType tagDataType = new HpsTagDataType();
		tagDataType.setTagData(tagData);
		tagDataType.setSource(TagDataTypeTagValuesSource.msd);

		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(value);

		HpsAccountVerify verifyResponse = service.verify(null, null, trackData, tagDataType);

		assertNotEquals(null, verifyResponse);
		assertEquals("85", verifyResponse.getResponseCode());
	}
	
	@Test
	public void charge_visa_tag_Issuer_Response_chip() throws HpsException {

		String tagData = "9F4005F000F0A0019F02060000000025009F03060000000000009F2608D90A06501B48564E82027C005F3401019F360200029F0702FF009F0802008C9F0902008C9F34030403029F2"
				+ "701809F0D05F0400088009F0E0508000000009F0F05F0400098005F280208409F390105FFC605DC4000A800FFC7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F350122"
				+ "950500000080005F2A0208409A031409109B02E8009F21030811539C01009F37045EED3A8E4F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03A40002"
				+ "9F410400000001";

		String value = ";4761739001010036=15122011184404889?";

		HpsTagDataType tagDataType = new HpsTagDataType();
		tagDataType.setTagData(tagData);
		tagDataType.setSource(TagDataTypeTagValuesSource.chip);

		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(value);

		HpsCharge chargeResponse = creditService.charge(BigDecimal.valueOf(17.06))
				.withAmount(new BigDecimal("50.40"))
				.withTrackData(trackData)
				.withTagData(tagDataType)
				.withAllowDuplicates(true)
				.execute();

		assertNotEquals(null, chargeResponse);
		assertNotEquals(null, chargeResponse.getEmvIssuerResp());
	}
	@Test
	public void charge_visa_tag_Issuer_Response_msd() throws HpsException {

		String tagData = "9F4005F000F0A0019F02060000000025009F03060000000000009F2608D90A06501B48564E82027C005F3401019F360200029F0702FF009F0802008C9F0902008C9F34030403029F2"
				+ "701809F0D05F0400088009F0E0508000000009F0F05F0400098005F280208409F390105FFC605DC4000A800FFC7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F350122"
				+ "950500000080005F2A0208409A031409109B02E8009F21030811539C01009F37045EED3A8E4F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03A40002"
				+ "9F410400000001";

		String value = ";4761739001010036=15122011184404889?";

		HpsTagDataType tagDataType = new HpsTagDataType();
		tagDataType.setTagData(tagData);
		tagDataType.setSource(TagDataTypeTagValuesSource.msd);

		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(value);

		HpsCharge chargeResponse = creditService.charge(BigDecimal.valueOf(17.06))
				.withAmount(new BigDecimal("50.40"))
				.withTrackData(trackData)
				.withTagData(tagDataType)
				.withAllowDuplicates(true)
				.execute();

		assertNotEquals(null, chargeResponse);
		assertNotEquals(null, chargeResponse.getEmvIssuerResp());
	}
	/* Negative Tests Cases */
	@Test(expected = HpsGatewayException.class)
	public void authorize_visa_InvalidTagData_chip() throws HpsException {

		String tagData = "9F4005F000F0A0019F02060000000025009F03060000000000009F2608D90A06501B48564E82027C005F3401019F360200029F0702FF009F0802008C9F0902008C9F34030403029F2"
				+ "701809F0D05F0400088009F0E0508000000009F0F05F0400098005F280208409F390105FFC605DC4000A800FFC7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F350122"
				+ "950500000080005F2A0208409A031409109B02E8009F21030811539C01009F37045EED3A8E4F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03A40002"
				+ "9F410400000001XX";

		String value = ";4761739001010036=15122011184404889?";

		HpsTagDataType tagDataType = new HpsTagDataType();
		tagDataType.setTagData(tagData);
		tagDataType.setSource(TagDataTypeTagValuesSource.chip);

		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(value);

		creditService.authorize(BigDecimal.valueOf(17.06))
		        .withAmount(new BigDecimal("50.40"))
		        .withTrackData(trackData)
				.withTagData(tagDataType)
				.withAllowDuplicates(false)
				.execute();
	}
	@Test(expected = HpsGatewayException.class)
	public void authorize_visa_InvalidTagData_msd() throws HpsException {

		String tagData = "9F4005F000F0A0019F02060000000025009F03060000000000009F2608D90A06501B48564E82027C005F3401019F360200029F0702FF009F0802008C9F0902008C9F34030403029F2"
				+ "701809F0D05F0400088009F0E0508000000009F0F05F0400098005F280208409F390105FFC605DC4000A800FFC7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F350122"
				+ "950500000080005F2A0208409A031409109B02E8009F21030811539C01009F37045EED3A8E4F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03A40002"
				+ "9F410400000001XX";

		String value = ";4761739001010036=15122011184404889?";

		HpsTagDataType tagDataType = new HpsTagDataType();
		tagDataType.setTagData(tagData);
		tagDataType.setSource(TagDataTypeTagValuesSource.msd);

		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(value);

		creditService.authorize(BigDecimal.valueOf(17.06))
		        .withAmount(new BigDecimal("50.40"))
		        .withTrackData(trackData)
				.withTagData(tagDataType)
				.withAllowDuplicates(false)
				.execute();
	}

	@Test(expected = HpsGatewayException.class)
	public void charge_visa_InvalidTagData_chip() throws HpsException {

		String tagData = "9F4005F000F0A0019F02060000000025009F03060000000000009F2608D90A06501B48564E82027C005F3401019F360200029F0702FF009F0802008C9F0902008C9F34030403029F2"
				+ "701809F0D05F0400088009F0E0508000000009F0F05F0400098005F280208409F390105FFC605DC4000A800FFC7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F350122"
				+ "950500000080005F2A0208409A031409109B02E8009F21030811539C01009F37045EED3A8E4F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03A40002"
				+ "9F410400000001XX";

		String value = ";4761739001010036=15122011184404889?";

		HpsTagDataType tagDataType = new HpsTagDataType();
		tagDataType.setTagData(tagData);
		tagDataType.setSource(TagDataTypeTagValuesSource.chip);

		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(value);

		creditService.charge(BigDecimal.valueOf(17.06))
		        .withAmount(new BigDecimal("50.40"))
		        .withTrackData(trackData)
				.withTagData(tagDataType)
				.withAllowDuplicates(false)
				.execute();
	}
	@Test(expected = HpsGatewayException.class)
	public void charge_visa_InvalidTagData_msd() throws HpsException {

		String tagData = "9F4005F000F0A0019F02060000000025009F03060000000000009F2608D90A06501B48564E82027C005F3401019F360200029F0702FF009F0802008C9F0902008C9F34030403029F2"
				+ "701809F0D05F0400088009F0E0508000000009F0F05F0400098005F280208409F390105FFC605DC4000A800FFC7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F350122"
				+ "950500000080005F2A0208409A031409109B02E8009F21030811539C01009F37045EED3A8E4F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03A40002"
				+ "9F410400000001XX";

		String value = ";4761739001010036=15122011184404889?";

		HpsTagDataType tagDataType = new HpsTagDataType();
		tagDataType.setTagData(tagData);
		tagDataType.setSource(TagDataTypeTagValuesSource.msd);

		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(value);

		creditService.charge(BigDecimal.valueOf(17.06))
		        .withAmount(new BigDecimal("50.40"))
		        .withTrackData(trackData)
				.withTagData(tagDataType)
				.withAllowDuplicates(false)
				.execute();
	}
	@Test(expected = HpsGatewayException.class)
	public void verify_visa_InvalidTagData_chip() throws HpsException {

		String tagData = "9F4005F000F0A0019F02060000000025009F03060000000000009F2608D90A06501B48564E82027C005F3401019F360200029F0702FF009F0802008C9F0902008C9F34030403029F2"
				+ "701809F0D05F0400088009F0E0508000000009F0F05F0400098005F280208409F390105FFC605DC4000A800FFC7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F350122"
				+ "950500000080005F2A0208409A031409109B02E8009F21030811539C01009F37045EED3A8E4F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03A40002"
				+ "9F410400000001XX";

		String value = ";4761739001010036=15122011184404889?";

		HpsTagDataType tagDataType = new HpsTagDataType();
		tagDataType.setTagData(tagData);
		tagDataType.setSource(TagDataTypeTagValuesSource.chip);

		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(value);

		creditService.verify()
		    .withTrackData(trackData)
		    .withTagData(tagDataType)
		    .execute();
	}
	@Test(expected = HpsGatewayException.class)
	public void verify_visa_InvalidTagData_msd() throws HpsException {

		String tagData = "9F4005F000F0A0019F02060000000025009F03060000000000009F2608D90A06501B48564E82027C005F3401019F360200029F0702FF009F0802008C9F0902008C9F34030403029F2"
				+ "701809F0D05F0400088009F0E0508000000009F0F05F0400098005F280208409F390105FFC605DC4000A800FFC7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F350122"
				+ "950500000080005F2A0208409A031409109B02E8009F21030811539C01009F37045EED3A8E4F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03A40002"
				+ "9F410400000001XX";

		String value = ";4761739001010036=15122011184404889?";

		HpsTagDataType tagDataType = new HpsTagDataType();
		tagDataType.setTagData(tagData);
		tagDataType.setSource(TagDataTypeTagValuesSource.msd);

		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(value);

		creditService.verify()
		    .withTrackData(trackData)
		    .withTagData(tagDataType)
		    .execute();
	}
	@Test(expected = HpsGatewayException.class)
	public void charge_visa_ForCredit_InvalidTagData_chip() throws HpsException {

		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());

		String tagData = "9F4005F000F0A0019F02060000000025009F03060000000000009F2608D90A06501B48564E82027C005F3401019F360200029F0702FF009F0802008C9F0902008C9F34030403029F2"
				+ "701809F0D05F0400088009F0E0508000000009F0F05F0400098005F280208409F390105FFC605DC4000A800FFC7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F350122"
				+ "950500000080005F2A0208409A031409109B02E8009F21030811539C01009F37045EED3A8E4F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03A40002"
				+ "9F410400000001XX";

		String value = ";4761739001010036=15122011184404889?";

		HpsTagDataType tagDataType = new HpsTagDataType();
		tagDataType.setTagData(tagData);
		tagDataType.setSource(TagDataTypeTagValuesSource.chip);

		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(value);

		service.charge(new BigDecimal("50"), "usd", null, null, true, null, null, tagDataType, trackData);

	}
	@Test(expected = HpsGatewayException.class)
	public void charge_visa_ForCredit_InvalidTagData_msd() throws HpsException {

		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());

		String tagData = "9F4005F000F0A0019F02060000000025009F03060000000000009F2608D90A06501B48564E82027C005F3401019F360200029F0702FF009F0802008C9F0902008C9F34030403029F2"
				+ "701809F0D05F0400088009F0E0508000000009F0F05F0400098005F280208409F390105FFC605DC4000A800FFC7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F350122"
				+ "950500000080005F2A0208409A031409109B02E8009F21030811539C01009F37045EED3A8E4F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03A40002"
				+ "9F410400000001XX";

		String value = ";4761739001010036=15122011184404889?";

		HpsTagDataType tagDataType = new HpsTagDataType();
		tagDataType.setTagData(tagData);
		tagDataType.setSource(TagDataTypeTagValuesSource.msd);

		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(value);

		service.charge(new BigDecimal("50"), "usd", null, null, true, null, null, tagDataType, trackData);

	}
	@Test(expected = HpsGatewayException.class)
	public void offlineCharge_visa_InvalidTagData_chip() throws HpsException {

		String tagData = "9F4005F000F0A0019F02060000000001219F03060000000000009F260816AC7EB8C0DFC40982027C005F3401019F360203869F0702FF009F0802008C9F0902008C8A0259319F34031"
				+ "E03009F2701409F0D05F0400088009F0E0500100000009F0F05F0400098005F280208409F390105FFC605DC4000A800FFC7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409"
				+ "F350122950500000800005F2A0208409A031409029B02E8009F21031145219C01009F3704BEBD49924F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03"
				+ "9000029F410400000001";

		String value = ";4761739001010036=15122011184404889?";

		HpsTagDataType tagDataType = new HpsTagDataType();
		tagDataType.setTagData(tagData);
		tagDataType.setSource(TagDataTypeTagValuesSource.chip);

		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(value);

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");
        
        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

		creditService.offlineCharge(BigDecimal.valueOf(17.10))
        		.withCard(card)
        		.withOfflineAuthCode("654321")
                .withAllowDuplicates(false)
                .withTagData(tagDataType)
                .execute();
        
	}
	@Test(expected = HpsGatewayException.class)
	public void offlineCharge_visa_InvalidTagData_msd() throws HpsException {

		String tagData = "9F4005F000F0A0019F02060000000001219F03060000000000009F260816AC7EB8C0DFC40982027C005F3401019F360203869F0702FF009F0802008C9F0902008C8A0259319F34031E"
				+ "03009F2701409F0D05F0400088009F0E0500100000009F0F05F0400098005F280208409F390105FFC605DC4000A800FFC7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F3"
				+ "50122950500000800005F2A0208409A031409029B02E8009F21031145219C01009F3704BEBD49924F07A00000000310109F0607A00000000310108407A00000000310109F100706010A0390"
				+ "00029F410400000001";

		String value = ";4761739001010036=15122011184404889?";

		HpsTagDataType tagDataType = new HpsTagDataType();
		tagDataType.setTagData(tagData);
		tagDataType.setSource(TagDataTypeTagValuesSource.msd);

		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(value);
		
        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");
        
        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

	    creditService.offlineCharge(BigDecimal.valueOf(17.10))
        		.withCard(card)
        		.withOfflineAuthCode("654321")
                .withAllowDuplicates(false)
                .withTagData(tagDataType)
                .execute();
	}
	@Test(expected = HpsGatewayException.class)
	public void verify_visa_ForCredit_InvalidTagData_chip() throws HpsException {

		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());

		String tagData = "9F4005F000F0A0019F02060000000025009F03060000000000009F2608D90A06501B48564E82027C005F3401019F360200029F0702FF009F0802008C9F0902008C9F34030403029F2"
				+ "701809F0D05F0400088009F0E0508000000009F0F05F0400098005F280208409F390105FFC605DC4000A800FFC7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F350122"
				+ "950500000080005F2A0208409A031409109B02E8009F21030811539C01009F37045EED3A8E4F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03A40002"
				+ "9F410400000001XX";

		String value = ";4761739001010036=15122011184404889?";

		HpsTagDataType tagDataType = new HpsTagDataType();
		tagDataType.setTagData(tagData);
		tagDataType.setSource(TagDataTypeTagValuesSource.chip);

		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(value);

		service.verify(null, null, trackData, tagDataType);
	}
	@Test(expected = HpsGatewayException.class)
	public void verify_visa_ForCredit_InvalidTagData_msd() throws HpsException {

		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());

		String tagData = "9F4005F000F0A0019F02060000000025009F03060000000000009F2608D90A06501B48564E82027C005F3401019F360200029F0702FF009F0802008C9F0902008C9F34030403029F2"
				+ "701809F0D05F0400088009F0E0508000000009F0F05F0400098005F280208409F390105FFC605DC4000A800FFC7050010000000FFC805DC4004F8009F3303E0B8C89F1A0208409F350122"
				+ "950500000080005F2A0208409A031409109B02E8009F21030811539C01009F37045EED3A8E4F07A00000000310109F0607A00000000310108407A00000000310109F100706010A03A40002"
				+ "9F410400000001XX";

		String value = ";4761739001010036=15122011184404889?";

		HpsTagDataType tagDataType = new HpsTagDataType();
		tagDataType.setTagData(tagData);
		tagDataType.setSource(TagDataTypeTagValuesSource.msd);

		HpsTrackData trackData = new HpsTrackData();
		trackData.setValue(value);

		service.verify(null, null, trackData, tagDataType);
	}
	
	/* Ended Negative Tests Cases */
}
