package PosGateway.Exchange.Hps;

import org.ksoap2.HeaderProperty;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.transport.HttpTransportSE;
import org.ksoap2.transport.HttpsTransportSE;
import org.ksoap2.transport.Transport;

import java.util.List;

public class PosGatewayInterface {
    interface IWcfMethod {
        ExtendedSoapSerializationEnvelope CreateSoapEnvelope() throws Exception;

        Object ProcessResult(ExtendedSoapSerializationEnvelope envelope, Object result) throws Exception;
    }

    String url = "https://posgateway.cert.secureexchange.net/Hps.Exchange.PosGateway/PosGatewayService.asmx";

    int timeOut = 60000;
    public List<HeaderProperty> httpHeaders;
    public boolean enableLogging;

    IServiceEvents callback;

    public PosGatewayInterface() {}

    public PosGatewayInterface(IServiceEvents callback) {
        this.callback = callback;
    }

    public PosGatewayInterface(String url) {
        this.url = url;
    }

    public PosGatewayInterface(IServiceEvents callback, String url) {
        this.callback = callback;
        this.url = url;
    }

    public PosGatewayInterface(IServiceEvents callback, String url, int timeOut) {
        this.callback = callback;
        this.url = url;
        this.timeOut = timeOut;
    }

    protected Transport createTransport() {
        try {
            java.net.URI uri = new java.net.URI(url);
            if (uri.getScheme().equalsIgnoreCase("https")) {
                //the following doesn't seem to work when using the Google App Engine.
                //return new HttpsTransportSE(uri.getHost(), uri.getPort(), uri.getPath(), timeOut);
                return new HttpTransportSE(url, timeOut);
            } else {
                return new HttpTransportSE(url, timeOut);
            }

        } catch (java.net.URISyntaxException ignored) {}

        return null;
    }

    protected ExtendedSoapSerializationEnvelope createEnvelope() {
        return new ExtendedSoapSerializationEnvelope();
    }

    protected void sendRequest(String methodName, ExtendedSoapSerializationEnvelope envelope, Transport transport) throws Exception {
        transport.call(methodName, envelope, httpHeaders);
    }

    Object getResult(Class destObj, Object source, String resultName, ExtendedSoapSerializationEnvelope __envelope) throws Exception {
        if (source instanceof SoapPrimitive) {
            SoapPrimitive soap = (SoapPrimitive) source;
            if (soap.getName().equals(resultName)) {
                return __envelope.get(source, destObj);
            }
        } else {
            SoapObject soap = (SoapObject) source;
            if (soap.hasProperty(resultName)) {
                Object j = soap.getProperty(resultName);
                if (j == null) {
                    return null;
                }
                return __envelope.get(j, destObj);
            } else if (soap.getName().equals(resultName)) {
                return __envelope.get(source, destObj);
            }
        }

        return null;
    }

    public PosResponse DoTransaction(final PosRequest PosRequest) throws Exception {
        return (PosResponse) execute(new IWcfMethod() {
            @Override
            public ExtendedSoapSerializationEnvelope CreateSoapEnvelope() {
                ExtendedSoapSerializationEnvelope __envelope = createEnvelope();
                __envelope.addMapping("http://Hps.Exchange.PosGateway", "PosRequest", PosGateway.Exchange.Hps.PosRequest.class);
                __envelope.setOutputSoapObject(PosRequest);
                return __envelope;
            }

            @Override
            public Object ProcessResult(ExtendedSoapSerializationEnvelope __envelope, Object __result) throws Exception {
                return getResult(PosResponse.class, __result, "PosResponse", __envelope);
            }
        }, "");
    }

//    public android.os.AsyncTask DoTransactionAsync(final PosRequest PosRequest)
//    {
//        return executeAsync(new Functions.IFunc< PosResponse>() {
//            public PosResponse Func() throws Exception {
//                return DoTransaction( PosRequest);
//            }
//        });
//    }

    protected Object execute(IWcfMethod wcfMethod, String methodName) throws Exception {
        Transport __httpTransport = createTransport();
        __httpTransport.debug = enableLogging;

        ExtendedSoapSerializationEnvelope __envelope = wcfMethod.CreateSoapEnvelope();
        __envelope.implicitTypes = true;
        __envelope.skipNullProperties = true;
        __envelope.setAddAdornments(false);

        try {
            sendRequest(methodName, __envelope, __httpTransport);
        } finally {
            if (__httpTransport.debug) {
                if (__httpTransport.requestDump != null) {
                    System.out.println(__httpTransport.requestDump);
                }

                if (__httpTransport.responseDump != null) {
                    System.out.println(__httpTransport.responseDump);
                }
            }
        }
        Object __retObj = __envelope.bodyIn;
        if (__retObj instanceof org.ksoap2.SoapFault) {
            org.ksoap2.SoapFault __fault = (org.ksoap2.SoapFault) __retObj;
            throw convertToException(__fault, __envelope);
        } else {
            return wcfMethod.ProcessResult(__envelope, __retObj);
        }
    }

//    protected <T> android.os.AsyncTask  executeAsync(final Functions.IFunc< T> func)
//    {
//        return new android.os.AsyncTask< Void, Void, OperationResult< T>>()
//        {
//            @Override
//            protected void onPreExecute() {
//                callback.Starting();
//            };
//            @Override
//            protected OperationResult< T> doInBackground(Void... params) {
//                OperationResult< T> result = new OperationResult< T>();
//                try
//                {
//                    result.Result= func.Func();
//                }
//                catch(Exception ex)
//                {
//                    ex.printStackTrace();
//                    result.Exception=ex;
//                }
//                return result;
//            }
//
//            @Override
//            protected void onPostExecute(OperationResult< T> result)
//            {
//                callback.Completed(result);
//            }
//        }.execute();
//    }

    Exception convertToException(org.ksoap2.SoapFault fault, ExtendedSoapSerializationEnvelope envelope) {
        return new Exception(fault.faultstring);
    }
}