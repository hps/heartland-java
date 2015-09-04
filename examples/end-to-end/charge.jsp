<%@ page language="java" %>
<%@ page language="java" import="java.math.BigDecimal" %>
<%@ page language="java" import="com.hps.integrator.entities.HpsAddress" %>
<%@ page language="java" import="com.hps.integrator.entities.credit.*" %>
<%@ page language="java" import="com.hps.integrator.entities.serialization.HpsToken" %>
<%@ page language="java" import="com.hps.integrator.services.*" %>
<%@ page language="java" import="com.hps.integrator.infrastructure.HpsException" %>

<%
HpsServicesConfig serviceConfig = new HpsServicesConfig();
serviceConfig.setSecretAPIKey("skapi_cert_MYl2AQAowiQAbLp5JesGKh7QFkcizOP2jcX9BrEMqQ");

// The following variables will be provided to you during certification
serviceConfig.setVersionNumber("0000");
serviceConfig.setDeveloperId("000000");

HpsCreditService creditService = new HpsCreditService(serviceConfig);

HpsAddress address = new HpsAddress();
address.setAddress(request.getParameter("Address"));
address.setCity(request.getParameter("City"));
address.setState(request.getParameter("State"));
address.setZip(request.getParameter("Zip"));
address.setCountry("United States");

HpsCardHolder validCardHolder = new HpsCardHolder();
validCardHolder.setFirstName(request.getParameter("FirstName"));
validCardHolder.setLastName(request.getParameter("LastName"));
validCardHolder.setAddress(address);
validCardHolder.setPhone(request.getParameter("PhoneNumber"));

String suToken = request.getParameter("token_value");
String errorMessage = "";
HpsCharge creditResponse = null;
try {
	creditResponse = creditService.charge(new BigDecimal("15.15"), "usd", suToken, validCardHolder, true);
} catch (HpsException exc) {
	errorMessage = exc.getMessage();
}
%>

<% if(!errorMessage.equals("")) { %>
	<h1>Transaction Failed</h1>
	<%=errorMessage%>
<% } else { %>
	<% if(creditResponse.getResponseCode().equals("00")) { %>
		<h1>Success!</h1>
		<p>Thank you, <%= request.getParameter("FirstName") %> for your order of $15.15.</p>
	<% } else { %>
		<h1>Transaction Failed</h1>
	<% } %>
	Transaction ID: <%= creditResponse.getTransactionID() %>
<% } %>
