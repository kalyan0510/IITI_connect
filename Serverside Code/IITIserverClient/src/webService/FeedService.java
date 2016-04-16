/**
 * FeedService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webService;

public interface FeedService extends java.rmi.Remote {
    public boolean setAddress(java.lang.String address, java.lang.String mac, int reg_id) throws java.rmi.RemoteException;
    public java.lang.String login(java.lang.String username, java.lang.String password) throws java.rmi.RemoteException;
    public java.lang.String getWarning() throws java.rmi.RemoteException;
    public java.lang.String forgotpassword(java.lang.String entity, java.lang.String value) throws java.rmi.RemoteException;
    public java.lang.String changepassword(int reg_id, java.lang.String oldpass, java.lang.String newpass) throws java.rmi.RemoteException;
    public boolean changedp(int reg_id, java.lang.String x, java.lang.String y) throws java.rmi.RemoteException;
    public java.lang.String getdp(int reg_id) throws java.rmi.RemoteException;
    public java.lang.String geticon(int reg_id) throws java.rmi.RemoteException;
    public java.lang.String getuser(int reg_id) throws java.rmi.RemoteException;
    public java.lang.String setWarning(int reg_id, java.lang.String message, java.lang.String type) throws java.rmi.RemoteException;
    public java.lang.String isWarnignGenerated() throws java.rmi.RemoteException;
    public java.lang.String changeStatus(int reg_id, java.lang.String status) throws java.rmi.RemoteException;
    public void clearWarning() throws java.rmi.RemoteException;
    public java.lang.String setSOS(int reg_id, java.lang.String message, java.lang.String type) throws java.rmi.RemoteException;
    public java.lang.String getSOS() throws java.rmi.RemoteException;
    public java.lang.String isSOSGenerated() throws java.rmi.RemoteException;
    public void clearSOS() throws java.rmi.RemoteException;
    public boolean wasMacSet(java.lang.String mac) throws java.rmi.RemoteException;
    public java.lang.String timenow() throws java.rmi.RemoteException;
    public int loadusers() throws java.rmi.RemoteException;
    public java.lang.String getusersforsearch() throws java.rmi.RemoteException;
    public int putmessage(int regfrom, java.lang.String name, java.lang.String au, int regto, java.lang.String str, java.lang.String img) throws java.rmi.RemoteException;
    public java.lang.String signup(java.lang.String username, java.lang.String first_name, java.lang.String last_name, java.lang.String mail) throws java.rmi.RemoteException;
    public java.lang.String getAddress(java.lang.String mac) throws java.rmi.RemoteException;
    public java.lang.String getMessage(int reg, java.lang.String authentic) throws java.rmi.RemoteException;
}
