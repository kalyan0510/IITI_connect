/**
 * FeedServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webService;

public class FeedServiceServiceLocator extends org.apache.axis.client.Service implements webService.FeedServiceService {

    public FeedServiceServiceLocator() {
    }


    public FeedServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public FeedServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for FeedService
    private java.lang.String FeedService_address = "http://localhost/IITIserver/services/FeedService";

    public java.lang.String getFeedServiceAddress() {
        return FeedService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String FeedServiceWSDDServiceName = "FeedService";

    public java.lang.String getFeedServiceWSDDServiceName() {
        return FeedServiceWSDDServiceName;
    }

    public void setFeedServiceWSDDServiceName(java.lang.String name) {
        FeedServiceWSDDServiceName = name;
    }

    public webService.FeedService getFeedService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(FeedService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getFeedService(endpoint);
    }

    public webService.FeedService getFeedService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            webService.FeedServiceSoapBindingStub _stub = new webService.FeedServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getFeedServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setFeedServiceEndpointAddress(java.lang.String address) {
        FeedService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (webService.FeedService.class.isAssignableFrom(serviceEndpointInterface)) {
                webService.FeedServiceSoapBindingStub _stub = new webService.FeedServiceSoapBindingStub(new java.net.URL(FeedService_address), this);
                _stub.setPortName(getFeedServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("FeedService".equals(inputPortName)) {
            return getFeedService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://webService", "FeedServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://webService", "FeedService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("FeedService".equals(portName)) {
            setFeedServiceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
