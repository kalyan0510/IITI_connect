/**
 * Changepassword.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webService;

public class Changepassword  implements java.io.Serializable {
    private int reg_id;

    private java.lang.String oldpass;

    private java.lang.String newpass;

    public Changepassword() {
    }

    public Changepassword(
           int reg_id,
           java.lang.String oldpass,
           java.lang.String newpass) {
           this.reg_id = reg_id;
           this.oldpass = oldpass;
           this.newpass = newpass;
    }


    /**
     * Gets the reg_id value for this Changepassword.
     * 
     * @return reg_id
     */
    public int getReg_id() {
        return reg_id;
    }


    /**
     * Sets the reg_id value for this Changepassword.
     * 
     * @param reg_id
     */
    public void setReg_id(int reg_id) {
        this.reg_id = reg_id;
    }


    /**
     * Gets the oldpass value for this Changepassword.
     * 
     * @return oldpass
     */
    public java.lang.String getOldpass() {
        return oldpass;
    }


    /**
     * Sets the oldpass value for this Changepassword.
     * 
     * @param oldpass
     */
    public void setOldpass(java.lang.String oldpass) {
        this.oldpass = oldpass;
    }


    /**
     * Gets the newpass value for this Changepassword.
     * 
     * @return newpass
     */
    public java.lang.String getNewpass() {
        return newpass;
    }


    /**
     * Sets the newpass value for this Changepassword.
     * 
     * @param newpass
     */
    public void setNewpass(java.lang.String newpass) {
        this.newpass = newpass;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Changepassword)) return false;
        Changepassword other = (Changepassword) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.reg_id == other.getReg_id() &&
            ((this.oldpass==null && other.getOldpass()==null) || 
             (this.oldpass!=null &&
              this.oldpass.equals(other.getOldpass()))) &&
            ((this.newpass==null && other.getNewpass()==null) || 
             (this.newpass!=null &&
              this.newpass.equals(other.getNewpass())));
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
        _hashCode += getReg_id();
        if (getOldpass() != null) {
            _hashCode += getOldpass().hashCode();
        }
        if (getNewpass() != null) {
            _hashCode += getNewpass().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Changepassword.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webService", ">changepassword"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reg_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webService", "Reg_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("oldpass");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webService", "oldpass"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("newpass");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webService", "newpass"));
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
