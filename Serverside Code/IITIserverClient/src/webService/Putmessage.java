/**
 * Putmessage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webService;

public class Putmessage  implements java.io.Serializable {
    private int regfrom;

    private java.lang.String au;

    private int regto;

    private java.lang.String str;

    private byte[] img;

    public Putmessage() {
    }

    public Putmessage(
           int regfrom,
           java.lang.String au,
           int regto,
           java.lang.String str,
           byte[] img) {
           this.regfrom = regfrom;
           this.au = au;
           this.regto = regto;
           this.str = str;
           this.img = img;
    }


    /**
     * Gets the regfrom value for this Putmessage.
     * 
     * @return regfrom
     */
    public int getRegfrom() {
        return regfrom;
    }


    /**
     * Sets the regfrom value for this Putmessage.
     * 
     * @param regfrom
     */
    public void setRegfrom(int regfrom) {
        this.regfrom = regfrom;
    }


    /**
     * Gets the au value for this Putmessage.
     * 
     * @return au
     */
    public java.lang.String getAu() {
        return au;
    }


    /**
     * Sets the au value for this Putmessage.
     * 
     * @param au
     */
    public void setAu(java.lang.String au) {
        this.au = au;
    }


    /**
     * Gets the regto value for this Putmessage.
     * 
     * @return regto
     */
    public int getRegto() {
        return regto;
    }


    /**
     * Sets the regto value for this Putmessage.
     * 
     * @param regto
     */
    public void setRegto(int regto) {
        this.regto = regto;
    }


    /**
     * Gets the str value for this Putmessage.
     * 
     * @return str
     */
    public java.lang.String getStr() {
        return str;
    }


    /**
     * Sets the str value for this Putmessage.
     * 
     * @param str
     */
    public void setStr(java.lang.String str) {
        this.str = str;
    }


    /**
     * Gets the img value for this Putmessage.
     * 
     * @return img
     */
    public byte[] getImg() {
        return img;
    }


    /**
     * Sets the img value for this Putmessage.
     * 
     * @param img
     */
    public void setImg(byte[] img) {
        this.img = img;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Putmessage)) return false;
        Putmessage other = (Putmessage) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.regfrom == other.getRegfrom() &&
            ((this.au==null && other.getAu()==null) || 
             (this.au!=null &&
              this.au.equals(other.getAu()))) &&
            this.regto == other.getRegto() &&
            ((this.str==null && other.getStr()==null) || 
             (this.str!=null &&
              this.str.equals(other.getStr()))) &&
            ((this.img==null && other.getImg()==null) || 
             (this.img!=null &&
              java.util.Arrays.equals(this.img, other.getImg())));
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
        _hashCode += getRegfrom();
        if (getAu() != null) {
            _hashCode += getAu().hashCode();
        }
        _hashCode += getRegto();
        if (getStr() != null) {
            _hashCode += getStr().hashCode();
        }
        if (getImg() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getImg());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getImg(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Putmessage.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webService", ">putmessage"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("regfrom");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webService", "regfrom"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("au");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webService", "au"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("regto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webService", "regto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("str");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webService", "str"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("img");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webService", "img"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
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
