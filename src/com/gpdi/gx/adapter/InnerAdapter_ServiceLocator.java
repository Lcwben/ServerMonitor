package com.gpdi.gx.adapter;

/**
 * InnerAdapter_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */



public class InnerAdapter_ServiceLocator extends org.apache.axis.client.Service implements InnerAdapter_Service {

    public InnerAdapter_ServiceLocator() {
    }


    public InnerAdapter_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public InnerAdapter_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for InnerAdapter
    private java.lang.String InnerAdapter_address = "http://10.201.37.41:7601/adapter/services/InnerAdapter";

    public java.lang.String getInnerAdapterAddress() {
        return InnerAdapter_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String InnerAdapterWSDDServiceName = "InnerAdapter";

    public java.lang.String getInnerAdapterWSDDServiceName() {
        return InnerAdapterWSDDServiceName;
    }

    public void setInnerAdapterWSDDServiceName(java.lang.String name) {
        InnerAdapterWSDDServiceName = name;
    }

    public InnerAdapterImpl getInnerAdapter() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(InnerAdapter_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getInnerAdapter(endpoint);
    }

    public InnerAdapterImpl getInnerAdapter(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            InnerAdapterSoapBindingStub _stub = new InnerAdapterSoapBindingStub(portAddress, this);
            _stub.setPortName(getInnerAdapterWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setInnerAdapterEndpointAddress(java.lang.String address) {
        InnerAdapter_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (InnerAdapterImpl.class.isAssignableFrom(serviceEndpointInterface)) {
                InnerAdapterSoapBindingStub _stub = new InnerAdapterSoapBindingStub(new java.net.URL(InnerAdapter_address), this);
                _stub.setPortName(getInnerAdapterWSDDServiceName());
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
        if ("InnerAdapter".equals(inputPortName)) {
            return getInnerAdapter();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://10.201.37.41:7601/adapter/services/InnerAdapter", "InnerAdapter");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://10.201.37.41:7601/adapter/services/InnerAdapter", "InnerAdapter"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("InnerAdapter".equals(portName)) {
            setInnerAdapterEndpointAddress(address);
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
