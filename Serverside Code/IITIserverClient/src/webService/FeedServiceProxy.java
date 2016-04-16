package webService;

public class FeedServiceProxy implements webService.FeedService {
  private String _endpoint = null;
  private webService.FeedService feedService = null;
  
  public FeedServiceProxy() {
    _initFeedServiceProxy();
  }
  
  public FeedServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initFeedServiceProxy();
  }
  
  private void _initFeedServiceProxy() {
    try {
      feedService = (new webService.FeedServiceServiceLocator()).getFeedService();
      if (feedService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)feedService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)feedService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (feedService != null)
      ((javax.xml.rpc.Stub)feedService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public webService.FeedService getFeedService() {
    if (feedService == null)
      _initFeedServiceProxy();
    return feedService;
  }
  
  public boolean setAddress(java.lang.String address, java.lang.String mac, int reg_id) throws java.rmi.RemoteException{
    if (feedService == null)
      _initFeedServiceProxy();
    return feedService.setAddress(address, mac, reg_id);
  }
  
  public java.lang.String login(java.lang.String username, java.lang.String password) throws java.rmi.RemoteException{
    if (feedService == null)
      _initFeedServiceProxy();
    return feedService.login(username, password);
  }
  
  public java.lang.String getWarning() throws java.rmi.RemoteException{
    if (feedService == null)
      _initFeedServiceProxy();
    return feedService.getWarning();
  }
  
  public java.lang.String forgotpassword(java.lang.String entity, java.lang.String value) throws java.rmi.RemoteException{
    if (feedService == null)
      _initFeedServiceProxy();
    return feedService.forgotpassword(entity, value);
  }
  
  public java.lang.String changepassword(int reg_id, java.lang.String oldpass, java.lang.String newpass) throws java.rmi.RemoteException{
    if (feedService == null)
      _initFeedServiceProxy();
    return feedService.changepassword(reg_id, oldpass, newpass);
  }
  
  public boolean changedp(int reg_id, java.lang.String x, java.lang.String y) throws java.rmi.RemoteException{
    if (feedService == null)
      _initFeedServiceProxy();
    return feedService.changedp(reg_id, x, y);
  }
  
  public java.lang.String getdp(int reg_id) throws java.rmi.RemoteException{
    if (feedService == null)
      _initFeedServiceProxy();
    return feedService.getdp(reg_id);
  }
  
  public java.lang.String geticon(int reg_id) throws java.rmi.RemoteException{
    if (feedService == null)
      _initFeedServiceProxy();
    return feedService.geticon(reg_id);
  }
  
  public java.lang.String getuser(int reg_id) throws java.rmi.RemoteException{
    if (feedService == null)
      _initFeedServiceProxy();
    return feedService.getuser(reg_id);
  }
  
  public java.lang.String setWarning(int reg_id, java.lang.String message, java.lang.String type) throws java.rmi.RemoteException{
    if (feedService == null)
      _initFeedServiceProxy();
    return feedService.setWarning(reg_id, message, type);
  }
  
  public java.lang.String isWarnignGenerated() throws java.rmi.RemoteException{
    if (feedService == null)
      _initFeedServiceProxy();
    return feedService.isWarnignGenerated();
  }
  
  public java.lang.String changeStatus(int reg_id, java.lang.String status) throws java.rmi.RemoteException{
    if (feedService == null)
      _initFeedServiceProxy();
    return feedService.changeStatus(reg_id, status);
  }
  
  public void clearWarning() throws java.rmi.RemoteException{
    if (feedService == null)
      _initFeedServiceProxy();
    feedService.clearWarning();
  }
  
  public java.lang.String setSOS(int reg_id, java.lang.String message, java.lang.String type) throws java.rmi.RemoteException{
    if (feedService == null)
      _initFeedServiceProxy();
    return feedService.setSOS(reg_id, message, type);
  }
  
  public java.lang.String getSOS() throws java.rmi.RemoteException{
    if (feedService == null)
      _initFeedServiceProxy();
    return feedService.getSOS();
  }
  
  public java.lang.String isSOSGenerated() throws java.rmi.RemoteException{
    if (feedService == null)
      _initFeedServiceProxy();
    return feedService.isSOSGenerated();
  }
  
  public void clearSOS() throws java.rmi.RemoteException{
    if (feedService == null)
      _initFeedServiceProxy();
    feedService.clearSOS();
  }
  
  public boolean wasMacSet(java.lang.String mac) throws java.rmi.RemoteException{
    if (feedService == null)
      _initFeedServiceProxy();
    return feedService.wasMacSet(mac);
  }
  
  public java.lang.String timenow() throws java.rmi.RemoteException{
    if (feedService == null)
      _initFeedServiceProxy();
    return feedService.timenow();
  }
  
  public int loadusers() throws java.rmi.RemoteException{
    if (feedService == null)
      _initFeedServiceProxy();
    return feedService.loadusers();
  }
  
  public java.lang.String getusersforsearch() throws java.rmi.RemoteException{
    if (feedService == null)
      _initFeedServiceProxy();
    return feedService.getusersforsearch();
  }
  
  public int putmessage(int regfrom, java.lang.String name, java.lang.String au, int regto, java.lang.String str, java.lang.String img) throws java.rmi.RemoteException{
    if (feedService == null)
      _initFeedServiceProxy();
    return feedService.putmessage(regfrom, name, au, regto, str, img);
  }
  
  public java.lang.String signup(java.lang.String username, java.lang.String first_name, java.lang.String last_name, java.lang.String mail) throws java.rmi.RemoteException{
    if (feedService == null)
      _initFeedServiceProxy();
    return feedService.signup(username, first_name, last_name, mail);
  }
  
  public java.lang.String getAddress(java.lang.String mac) throws java.rmi.RemoteException{
    if (feedService == null)
      _initFeedServiceProxy();
    return feedService.getAddress(mac);
  }
  
  public java.lang.String getMessage(int reg, java.lang.String authentic) throws java.rmi.RemoteException{
    if (feedService == null)
      _initFeedServiceProxy();
    return feedService.getMessage(reg, authentic);
  }
  
  
}