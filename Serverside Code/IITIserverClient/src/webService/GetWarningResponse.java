/**
 * GetWarningResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webService;

public class GetWarningResponse  implements java.io.Serializable {
    private java.lang.String getWarningReturn;

    public GetWarningResponse() {
    }

    public GetWarningResponse(
           java.lang.String getWarningReturn) {
           this.getWarningReturn = getWarningReturn;
    }


    /**
     * Gets the getWarningReturn value for this GetWarningResponse.
     * 
     * @return getWarningReturn
     */
    public java.lang.String getGetWarningReturn() {
        return getWarningReturn;
    }


    /**
     * Sets the getWarningReturn value for this GetWarningResponse.
     * 
     * @param getWarningReturn
     */
    public void setGetWarningReturn(java.lang.String getWarningReturn) {
        this.getWarningReturn = getWarningReturn;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetWarningResponse)) return false;
        GetWarningResponse other = (GetWarningResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getWarningReturn==null && other.getGetWarningReturn()==null) || 
             (this.getWarningReturn!=null &&
              this.getWarningReturn.equals(other.getGetWarningReturn())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getGetWarningReturn() != null) {
            _hashCode += getGetWarningReturn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetWarningResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webService", ">getWarningResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getWarningReturn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webService", "getWarningReturn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
