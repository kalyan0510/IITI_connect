/**
 * GetMessage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webService;

public class GetMessage  implements java.io.Serializable {
    private int reg;

    private java.lang.String authentic;

    public GetMessage() {
    }

    public GetMessage(
           int reg,
           java.lang.String authentic) {
           this.reg = reg;
           this.authentic = authentic;
    }


    /**
     * Gets the reg value for this GetMessage.
     * 
     * @return reg
     */
    public int getReg() {
        return reg;
    }


    /**
     * Sets the reg value for this GetMessage.
     * 
     * @param reg
     */
    public void setReg(int reg) {
        this.reg = reg;
    }


    /**
     * Gets the authentic value for this GetMessage.
     * 
     * @return authentic
     */
    public java.lang.String getAuthentic() {
        return authentic;
    }


    /**
     * Sets the authentic value for this GetMessage.
     * 
     * @param authentic
     */
    public void setAuthentic(java.lang.String authentic) {
        this.authentic = authentic;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetMessage)) return false;
        GetMessage other = (GetMessage) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.reg == other.getReg() &&
            ((this.authentic==null && other.getAuthentic()==null) || 
             (this.authentic!=null &&
              this.authentic.equals(other.getAuthentic())));
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
        _hashCode += getReg();
        if (getAuthentic() != null) {
            _hashCode += getAuthentic().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetMessage.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webService", ">getMessage"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reg");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webService", "reg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authentic");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webService", "authentic"));
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
