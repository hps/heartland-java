<%@ page language="java" %>
<%@ page language="java" import="java.math.BigDecimal" %>
<%@ page language="java" import="java.util.List" %>
<%@ page language="java" import="com.hps.integrator.entities.HpsAddress" %>
<%@ page language="java" import="com.hps.integrator.services.*" %>
<%@ page language="java" import="com.hps.integrator.entities.altpayment.*" %>
<%@ page language="java" import="com.hps.integrator.infrastructure.HpsException" %>

<%
// Get our stuff
BigDecimal amount = (BigDecimal)session.getAttribute("Amount");
HpsBuyerData buyer = (HpsBuyerData)session.getAttribute("HpsBuyerData");
HpsPaymentData payment = (HpsPaymentData)session.getAttribute("HpsPaymentData");
HpsShippingInfo shippingInfo = (HpsShippingInfo)session.getAttribute("HpsShippingInfo");
List<HpsLineItem> lineItems = (List<HpsLineItem>)session.getAttribute("HpsLineItems");
HpsAddress address = shippingInfo.getAddress();

String payerId = request.getParameter("PayerID");
buyer.setPayerId(payerId);

// perform the sale
HpsServicesConfig _config = new HpsServicesConfig();
_config.setUserName("30360021");
_config.setPassword("$Test1234");
_config.setDeviceId(90911395);
_config.setLicenseId(20527);
_config.setSiteId(20518);
_config.setServiceUri("https://api-uat.heartlandportico.com/paymentserver.v1/PosGatewayService.asmx?wsdl");

HpsPayPalService paypalService = new HpsPayPalService(_config, true);

String suToken = request.getParameter("token");
String errorMessage = "";
HpsAltPaymentSale saleResponse = null;
try {
	saleResponse = paypalService.sale(suToken, amount, "usd", buyer, payment, shippingInfo, lineItems);
} catch (HpsException exc) {
	errorMessage = exc.getMessage();
}
%>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="SecureSubmit JSP paypal payment example.">
    <meta name="author" content="Russell Everett">
    <title>PayPal Demo</title>

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="assets/secure.submit-1.0.2.js"></script>

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-md-11">
                <h1>Order Details:</h1>
            </div>
            <div class="col-md-1">
                <a class="btn btn-success" href="index.html" role="button" style="margin-top:20px;">
                    <span class="glyphicon glyphicon-home"></span>
                    Home
                </a>
            </div>
        </div>

        <hr />
        <div class="row">
            <div class="col-md-6 info">
                <strong>Billing Info:</strong><br />
                <%=shippingInfo.getName() %><br />
                <%=address.getAddress() %><br />
                <%=address.getCity() %>, <%=address.getState() %> <%=address.getZip() %><br />
                <%=address.getCountry() %><br />
            </div>
            <div class="col-md-6 info" style="border-left: 1px solid gray;">
                <strong>Shipping Info:</strong><br />
                <%=shippingInfo.getName() %><br />
                <%=address.getAddress() %><br />
                <%=address.getCity() %>, <%=address.getState() %> <%=address.getZip() %><br />
                <%=address.getCountry() %><br />
            </div>
        </div>
        <br />

        <table class="table">
            <thead>
                <tr class="active">
                    <td width="10%"><strong>Id</strong></td>
                    <td width="60%"><strong>Name</strong></td>
                    <td width="10%"><strong>Amount</strong></td>
                    <td width="10%"><strong>Qty</strong></td>
                    <td><strong>Subtotal</strong></td>
                </tr>
            </thead>
            <tbody>
                <% for(HpsLineItem item: lineItems) { %>
                    <tr>
                        <td><%=item.getNumber() %></td>
                        <td><%=item.getName() %></td>
                        <td>$<%=item.getAmount() %></td>
                        <td><%=item.getQuantity() %></td>
                        <td>$<%=new BigDecimal(item.getAmount()).multiply(new BigDecimal(item.getQuantity())).toString() %>
                    </tr>
                <% } %>
            </tbody>
            <tfoot>
                <tr class="active">
                    <td colspan="3"></td>
                    <td>
                        Subtotal:<br />
                        Shipping:<br />
                        Tax:<br />
                        <strong>Total:</strong>
                    </td>
                    <td>
                        $<%=payment.getSubTotal() %><br />
                        $<%=payment.getShippingAmount() %><br />
                        $<%=payment.getTaxAmount() %><br />
                        <strong>$<%=amount %></strong>
                    </td>
                </tr>
            </tfoot>
        </table>
        <strong>Transaction Status:</strong>
        <% if(!errorMessage.equals("")) { %>
            <span style="color: red;">
                Failed - <%=errorMessage%>
            </span>
        <% } else { %>
            <span style="color: green;">
                Success - Transaction Id: <%=saleResponse.getTransactionID() %>
            </span>
        <% } %>
        <br /><br />
    </div>
</body>
</html>