/**
 * ForgotpasswordResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webService;

public class ForgotpasswordResponse  implements java.io.Serializable {
    private java.lang.String forgotpasswordReturn;

    public ForgotpasswordResponse() {
    }

    public ForgotpasswordResponse(
           java.lang.String forgotpasswordReturn) {
           this.forgotpasswordReturn = forgotpasswordReturn;
    }


    /**
     * Gets the forgotpasswordReturn value for this ForgotpasswordResponse.
     * 
     * @return forgotpasswordReturn
     */
    public java.lang.String getForgotpasswordReturn() {
        return forgotpasswordReturn;
    }


    /**
     * Sets the forgotpasswordReturn value for this ForgotpasswordResponse.
     * 
     * @param forgotpasswordReturn
     */
    public void setForgotpasswordReturn(java.lang.String forgotpasswordReturn) {
        this.forgotpasswordReturn = forgotpasswordReturn;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ForgotpasswordResponse)) return false;
        ForgotpasswordResponse other = (ForgotpasswordResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.forgotpasswordReturn==null && other.getForgotpasswordReturn()==null) || 
             (this.forgotpasswordReturn!=null &&
              this.forgotpasswordReturn.equals(other.getForgotpasswordReturn())));
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
        if (getForgotpasswordReturn() != null) {
            _hashCode += getForgotpasswordReturn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ForgotpasswordResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webService", ">forgotpasswordResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("forgotpasswordReturn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webService", "forgotpasswordReturn"));
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
