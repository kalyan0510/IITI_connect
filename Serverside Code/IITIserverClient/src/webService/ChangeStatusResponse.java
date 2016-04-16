/**
 * ChangeStatusResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webService;

public class ChangeStatusResponse  implements java.io.Serializable {
    private java.lang.String changeStatusReturn;

    public ChangeStatusResponse() {
    }

    public ChangeStatusResponse(
           java.lang.String changeStatusReturn) {
           this.changeStatusReturn = changeStatusReturn;
    }


    /**
     * Gets the changeStatusReturn value for this ChangeStatusResponse.
     * 
     * @return changeStatusReturn
     */
    public java.lang.String getChangeStatusReturn() {
        return changeStatusReturn;
    }


    /**
     * Sets the changeStatusReturn value for this ChangeStatusResponse.
     * 
     * @param changeStatusReturn
     */
    public void setChangeStatusReturn(java.lang.String changeStatusReturn) {
        this.changeStatusReturn = changeStatusReturn;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ChangeStatusResponse)) return false;
        ChangeStatusResponse other = (ChangeStatusResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.changeStatusReturn==null && other.getChangeStatusReturn()==null) || 
             (this.changeStatusReturn!=null &&
              this.changeStatusReturn.equals(other.getChangeStatusReturn())));
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
        if (getChangeStatusReturn() != null) {
            _hashCode += getChangeStatusReturn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ChangeStatusResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webService", ">changeStatusResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("changeStatusReturn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webService", "changeStatusReturn"));
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
