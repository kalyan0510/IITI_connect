/**
 * GetSOSResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webService;

public class GetSOSResponse  implements java.io.Serializable {
    private java.lang.String getSOSReturn;

    public GetSOSResponse() {
    }

    public GetSOSResponse(
           java.lang.String getSOSReturn) {
           this.getSOSReturn = getSOSReturn;
    }


    /**
     * Gets the getSOSReturn value for this GetSOSResponse.
     * 
     * @return getSOSReturn
     */
    public java.lang.String getGetSOSReturn() {
        return getSOSReturn;
    }


    /**
     * Sets the getSOSReturn value for this GetSOSResponse.
     * 
     * @param getSOSReturn
     */
    public void setGetSOSReturn(java.lang.String getSOSReturn) {
        this.getSOSReturn = getSOSReturn;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetSOSResponse)) return false;
        GetSOSResponse other = (GetSOSResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getSOSReturn==null && other.getGetSOSReturn()==null) || 
             (this.getSOSReturn!=null &&
              this.getSOSReturn.equals(other.getGetSOSReturn())));
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
        if (getGetSOSReturn() != null) {
            _hashCode += getGetSOSReturn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetSOSResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webService", ">getSOSResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getSOSReturn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webService", "getSOSReturn"));
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
