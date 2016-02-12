<%@ page language="java" %>
<%@ page language="java" import="java.math.BigDecimal" %>
<%@ page language="java" import="java.util.List" %>
<%@ page language="java" import="java.util.ArrayList" %>
<%@ page language="java" import="com.hps.integrator.entities.HpsAddress" %>
<%@ page language="java" import="com.hps.integrator.entities.altpayment.*" %>
<%@ page language="java" import="com.hps.integrator.services.*" %>

<%
// Amount
BigDecimal amount = new BigDecimal("258.45");
session.setAttribute("Amount", amount);

// Currency
String currency = "usd";

// Create BuyerInfo
HpsBuyerData buyer = new HpsBuyerData();
buyer.setReturnUrl("http://localhost/securesubmit/paypal/charge.jsp");
buyer.setCancelUrl("http://localhost/securesubmit/paypal/index.html");
session.setAttribute("HpsBuyerData", buyer);

// Create PaymentInfo
HpsPaymentData payment = new HpsPaymentData();
payment.setSubTotal(new BigDecimal("226.57"));
payment.setShippingAmount(new BigDecimal("12.74"));
payment.setTaxAmount(new BigDecimal("19.14"));
payment.setPaymentType("Sale");
session.setAttribute("HpsPaymentData", payment);

// shipping Info
HpsShippingInfo shippingInfo = new HpsShippingInfo();
shippingInfo.setName("Russell Everett");

HpsAddress address = new HpsAddress();
address.setAddress("6860 Dallas Pkwy");
address.setCity("Plano");
address.setState("TX");
address.setZip("75024");
address.setCountry("US");

shippingInfo.setAddress(address);
session.setAttribute("HpsShippingInfo", shippingInfo);

// Line Items
List<HpsLineItem> lineItems = new ArrayList<HpsLineItem>();
HpsLineItem item1 = new HpsLineItem();
item1.setName("Blanton's Bourbon Single Barrel 750ML");
item1.setNumber("1");
item1.setAmount("37.19");
item1.setQuantity("1");
lineItems.add(item1);

HpsLineItem item2 = new HpsLineItem();
item2.setName("Pappy Van Winkle's Family Reserve 23-Year-Old Kentucky Straight Bourbon");
item2.setNumber("2");
item2.setAmount("108.99");
item2.setQuantity("1");
lineItems.add(item2);

HpsLineItem item3 = new HpsLineItem();
item3.setName("Blood Oath Bourbon Pact No. 1 750ML");
item3.setNumber("3");
item3.setAmount("80.39");
item3.setQuantity("1");
lineItems.add(item3);
session.setAttribute("HpsLineItems", lineItems);

// Create Session
HpsServicesConfig _config = new HpsServicesConfig();
_config.setUserName("30360021");
_config.setPassword("$Test1234");
_config.setDeviceId(90911395);
_config.setLicenseId(20527);
_config.setSiteId(20518);
_config.setServiceUri("https://api-uat.heartlandportico.com/paymentserver.v1/PosGatewayService.asmx?wsdl");

HpsPayPalService paypalService = new HpsPayPalService(_config, true);
HpsAltPaymentCreateSession sessionResponse = paypalService.createSession(amount, currency, buyer, payment, shippingInfo, lineItems);

String token = sessionResponse.getSessionId();
response.sendRedirect(sessionResponse.getRedirectUrl());
%>

<h3>Token Value: <%=token %>