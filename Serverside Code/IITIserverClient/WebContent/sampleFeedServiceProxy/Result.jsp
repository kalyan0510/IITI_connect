<%@page contentType="text/html;charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<HTML>
<HEAD>
<TITLE>Result</TITLE>
</HEAD>
<BODY>
<H1>Result</H1>

<jsp:useBean id="sampleFeedServiceProxyid" scope="session" class="webService.FeedServiceProxy" />
<%
if (request.getParameter("endpoint") != null && request.getParameter("endpoint").length() > 0)
sampleFeedServiceProxyid.setEndpoint(request.getParameter("endpoint"));
%>

<%
String method = request.getParameter("method");
int methodID = 0;
if (method == null) methodID = -1;

if(methodID != -1) methodID = Integer.parseInt(method);
boolean gotMethod = false;

try {
switch (methodID){ 
case 2:
        gotMethod = true;
        java.lang.String getEndpoint2mtemp = sampleFeedServiceProxyid.getEndpoint();
if(getEndpoint2mtemp == null){
%>
<%=getEndpoint2mtemp %>
<%
}else{
        String tempResultreturnp3 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getEndpoint2mtemp));
        %>
        <%= tempResultreturnp3 %>
        <%
}
break;
case 5:
        gotMethod = true;
        String endpoint_0id=  request.getParameter("endpoint8");
            java.lang.String endpoint_0idTemp = null;
        if(!endpoint_0id.equals("")){
         endpoint_0idTemp  = endpoint_0id;
        }
        sampleFeedServiceProxyid.setEndpoint(endpoint_0idTemp);
break;
case 10:
        gotMethod = true;
        webService.FeedService getFeedService10mtemp = sampleFeedServiceProxyid.getFeedService();
if(getFeedService10mtemp == null){
%>
<%=getFeedService10mtemp %>
<%
}else{
%>
<TABLE>
<TR>
<TD COLSPAN="3" ALIGN="LEFT">returnp:</TD>
</TABLE>
<%
}
break;
case 19:
        gotMethod = true;
        String address_1id=  request.getParameter("address22");
            java.lang.String address_1idTemp = null;
        if(!address_1id.equals("")){
         address_1idTemp  = address_1id;
        }
        String mac_2id=  request.getParameter("mac24");
            java.lang.String mac_2idTemp = null;
        if(!mac_2id.equals("")){
         mac_2idTemp  = mac_2id;
        }
        String reg_id_3id=  request.getParameter("reg_id26");
        int reg_id_3idTemp  = Integer.parseInt(reg_id_3id);
        boolean setAddress19mtemp = sampleFeedServiceProxyid.setAddress(address_1idTemp,mac_2idTemp,reg_id_3idTemp);
        String tempResultreturnp20 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(setAddress19mtemp));
        %>
        <%= tempResultreturnp20 %>
        <%
break;
case 28:
        gotMethod = true;
        String username_4id=  request.getParameter("username31");
            java.lang.String username_4idTemp = null;
        if(!username_4id.equals("")){
         username_4idTemp  = username_4id;
        }
        String password_5id=  request.getParameter("password33");
            java.lang.String password_5idTemp = null;
        if(!password_5id.equals("")){
         password_5idTemp  = password_5id;
        }
        java.lang.String login28mtemp = sampleFeedServiceProxyid.login(username_4idTemp,password_5idTemp);
if(login28mtemp == null){
%>
<%=login28mtemp %>
<%
}else{
        String tempResultreturnp29 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(login28mtemp));
        %>
        <%= tempResultreturnp29 %>
        <%
}
break;
case 35:
        gotMethod = true;
        java.lang.String getWarning35mtemp = sampleFeedServiceProxyid.getWarning();
if(getWarning35mtemp == null){
%>
<%=getWarning35mtemp %>
<%
}else{
        String tempResultreturnp36 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getWarning35mtemp));
        %>
        <%= tempResultreturnp36 %>
        <%
}
break;
case 38:
        gotMethod = true;
        String entity_6id=  request.getParameter("entity41");
            java.lang.String entity_6idTemp = null;
        if(!entity_6id.equals("")){
         entity_6idTemp  = entity_6id;
        }
        String value_7id=  request.getParameter("value43");
            java.lang.String value_7idTemp = null;
        if(!value_7id.equals("")){
         value_7idTemp  = value_7id;
        }
        java.lang.String forgotpassword38mtemp = sampleFeedServiceProxyid.forgotpassword(entity_6idTemp,value_7idTemp);
if(forgotpassword38mtemp == null){
%>
<%=forgotpassword38mtemp %>
<%
}else{
        String tempResultreturnp39 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(forgotpassword38mtemp));
        %>
        <%= tempResultreturnp39 %>
        <%
}
break;
case 45:
        gotMethod = true;
        String reg_id_8id=  request.getParameter("reg_id48");
        int reg_id_8idTemp  = Integer.parseInt(reg_id_8id);
        String oldpass_9id=  request.getParameter("oldpass50");
            java.lang.String oldpass_9idTemp = null;
        if(!oldpass_9id.equals("")){
         oldpass_9idTemp  = oldpass_9id;
        }
        String newpass_10id=  request.getParameter("newpass52");
            java.lang.String newpass_10idTemp = null;
        if(!newpass_10id.equals("")){
         newpass_10idTemp  = newpass_10id;
        }
        java.lang.String changepassword45mtemp = sampleFeedServiceProxyid.changepassword(reg_id_8idTemp,oldpass_9idTemp,newpass_10idTemp);
if(changepassword45mtemp == null){
%>
<%=changepassword45mtemp %>
<%
}else{
        String tempResultreturnp46 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(changepassword45mtemp));
        %>
        <%= tempResultreturnp46 %>
        <%
}
break;
case 54:
        gotMethod = true;
        String reg_id_11id=  request.getParameter("reg_id57");
        int reg_id_11idTemp  = Integer.parseInt(reg_id_11id);
        String x_12id=  request.getParameter("x59");
            java.lang.String x_12idTemp = null;
        if(!x_12id.equals("")){
         x_12idTemp  = x_12id;
        }
        String y_13id=  request.getParameter("y61");
            java.lang.String y_13idTemp = null;
        if(!y_13id.equals("")){
         y_13idTemp  = y_13id;
        }
        boolean changedp54mtemp = sampleFeedServiceProxyid.changedp(reg_id_11idTemp,x_12idTemp,y_13idTemp);
        String tempResultreturnp55 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(changedp54mtemp));
        %>
        <%= tempResultreturnp55 %>
        <%
break;
case 63:
        gotMethod = true;
        String reg_id_14id=  request.getParameter("reg_id66");
        int reg_id_14idTemp  = Integer.parseInt(reg_id_14id);
        java.lang.String getdp63mtemp = sampleFeedServiceProxyid.getdp(reg_id_14idTemp);
if(getdp63mtemp == null){
%>
<%=getdp63mtemp %>
<%
}else{
        String tempResultreturnp64 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getdp63mtemp));
        %>
        <%= tempResultreturnp64 %>
        <%
}
break;
case 68:
        gotMethod = true;
        String reg_id_15id=  request.getParameter("reg_id71");
        int reg_id_15idTemp  = Integer.parseInt(reg_id_15id);
        java.lang.String geticon68mtemp = sampleFeedServiceProxyid.geticon(reg_id_15idTemp);
if(geticon68mtemp == null){
%>
<%=geticon68mtemp %>
<%
}else{
        String tempResultreturnp69 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(geticon68mtemp));
        %>
        <%= tempResultreturnp69 %>
        <%
}
break;
case 73:
        gotMethod = true;
        String reg_id_16id=  request.getParameter("reg_id76");
        int reg_id_16idTemp  = Integer.parseInt(reg_id_16id);
        java.lang.String getuser73mtemp = sampleFeedServiceProxyid.getuser(reg_id_16idTemp);
if(getuser73mtemp == null){
%>
<%=getuser73mtemp %>
<%
}else{
        String tempResultreturnp74 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getuser73mtemp));
        %>
        <%= tempResultreturnp74 %>
        <%
}
break;
case 78:
        gotMethod = true;
        String reg_id_17id=  request.getParameter("reg_id81");
        int reg_id_17idTemp  = Integer.parseInt(reg_id_17id);
        String message_18id=  request.getParameter("message83");
            java.lang.String message_18idTemp = null;
        if(!message_18id.equals("")){
         message_18idTemp  = message_18id;
        }
        String type_19id=  request.getParameter("type85");
            java.lang.String type_19idTemp = null;
        if(!type_19id.equals("")){
         type_19idTemp  = type_19id;
        }
        java.lang.String setWarning78mtemp = sampleFeedServiceProxyid.setWarning(reg_id_17idTemp,message_18idTemp,type_19idTemp);
if(setWarning78mtemp == null){
%>
<%=setWarning78mtemp %>
<%
}else{
        String tempResultreturnp79 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(setWarning78mtemp));
        %>
        <%= tempResultreturnp79 %>
        <%
}
break;
case 87:
        gotMethod = true;
        java.lang.String isWarnignGenerated87mtemp = sampleFeedServiceProxyid.isWarnignGenerated();
if(isWarnignGenerated87mtemp == null){
%>
<%=isWarnignGenerated87mtemp %>
<%
}else{
        String tempResultreturnp88 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(isWarnignGenerated87mtemp));
        %>
        <%= tempResultreturnp88 %>
        <%
}
break;
case 90:
        gotMethod = true;
        String reg_id_20id=  request.getParameter("reg_id93");
        int reg_id_20idTemp  = Integer.parseInt(reg_id_20id);
        String status_21id=  request.getParameter("status95");
            java.lang.String status_21idTemp = null;
        if(!status_21id.equals("")){
         status_21idTemp  = status_21id;
        }
        java.lang.String changeStatus90mtemp = sampleFeedServiceProxyid.changeStatus(reg_id_20idTemp,status_21idTemp);
if(changeStatus90mtemp == null){
%>
<%=changeStatus90mtemp %>
<%
}else{
        String tempResultreturnp91 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(changeStatus90mtemp));
        %>
        <%= tempResultreturnp91 %>
        <%
}
break;
case 97:
        gotMethod = true;
        sampleFeedServiceProxyid.clearWarning();
break;
case 100:
        gotMethod = true;
        String reg_id_22id=  request.getParameter("reg_id103");
        int reg_id_22idTemp  = Integer.parseInt(reg_id_22id);
        String message_23id=  request.getParameter("message105");
            java.lang.String message_23idTemp = null;
        if(!message_23id.equals("")){
         message_23idTemp  = message_23id;
        }
        String type_24id=  request.getParameter("type107");
            java.lang.String type_24idTemp = null;
        if(!type_24id.equals("")){
         type_24idTemp  = type_24id;
        }
        java.lang.String setSOS100mtemp = sampleFeedServiceProxyid.setSOS(reg_id_22idTemp,message_23idTemp,type_24idTemp);
if(setSOS100mtemp == null){
%>
<%=setSOS100mtemp %>
<%
}else{
        String tempResultreturnp101 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(setSOS100mtemp));
        %>
        <%= tempResultreturnp101 %>
        <%
}
break;
case 109:
        gotMethod = true;
        java.lang.String getSOS109mtemp = sampleFeedServiceProxyid.getSOS();
if(getSOS109mtemp == null){
%>
<%=getSOS109mtemp %>
<%
}else{
        String tempResultreturnp110 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getSOS109mtemp));
        %>
        <%= tempResultreturnp110 %>
        <%
}
break;
case 112:
        gotMethod = true;
        java.lang.String isSOSGenerated112mtemp = sampleFeedServiceProxyid.isSOSGenerated();
if(isSOSGenerated112mtemp == null){
%>
<%=isSOSGenerated112mtemp %>
<%
}else{
        String tempResultreturnp113 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(isSOSGenerated112mtemp));
        %>
        <%= tempResultreturnp113 %>
        <%
}
break;
case 115:
        gotMethod = true;
        sampleFeedServiceProxyid.clearSOS();
break;
case 118:
        gotMethod = true;
        String mac_25id=  request.getParameter("mac121");
            java.lang.String mac_25idTemp = null;
        if(!mac_25id.equals("")){
         mac_25idTemp  = mac_25id;
        }
        boolean wasMacSet118mtemp = sampleFeedServiceProxyid.wasMacSet(mac_25idTemp);
        String tempResultreturnp119 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(wasMacSet118mtemp));
        %>
        <%= tempResultreturnp119 %>
        <%
break;
case 123:
        gotMethod = true;
        java.lang.String timenow123mtemp = sampleFeedServiceProxyid.timenow();
if(timenow123mtemp == null){
%>
<%=timenow123mtemp %>
<%
}else{
        String tempResultreturnp124 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(timenow123mtemp));
        %>
        <%= tempResultreturnp124 %>
        <%
}
break;
case 126:
        gotMethod = true;
        int loadusers126mtemp = sampleFeedServiceProxyid.loadusers();
        String tempResultreturnp127 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(loadusers126mtemp));
        %>
        <%= tempResultreturnp127 %>
        <%
break;
case 129:
        gotMethod = true;
        java.lang.String getusersforsearch129mtemp = sampleFeedServiceProxyid.getusersforsearch();
if(getusersforsearch129mtemp == null){
%>
<%=getusersforsearch129mtemp %>
<%
}else{
        String tempResultreturnp130 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getusersforsearch129mtemp));
        %>
        <%= tempResultreturnp130 %>
        <%
}
break;
case 132:
        gotMethod = true;
        String regfrom_26id=  request.getParameter("regfrom135");
        int regfrom_26idTemp  = Integer.parseInt(regfrom_26id);
        String name_27id=  request.getParameter("name137");
            java.lang.String name_27idTemp = null;
        if(!name_27id.equals("")){
         name_27idTemp  = name_27id;
        }
        String au_28id=  request.getParameter("au139");
            java.lang.String au_28idTemp = null;
        if(!au_28id.equals("")){
         au_28idTemp  = au_28id;
        }
        String regto_29id=  request.getParameter("regto141");
        int regto_29idTemp  = Integer.parseInt(regto_29id);
        String str_30id=  request.getParameter("str143");
            java.lang.String str_30idTemp = null;
        if(!str_30id.equals("")){
         str_30idTemp  = str_30id;
        }
        String img_31id=  request.getParameter("img145");
            java.lang.String img_31idTemp = null;
        if(!img_31id.equals("")){
         img_31idTemp  = img_31id;
        }
        int putmessage132mtemp = sampleFeedServiceProxyid.putmessage(regfrom_26idTemp,name_27idTemp,au_28idTemp,regto_29idTemp,str_30idTemp,img_31idTemp);
        String tempResultreturnp133 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(putmessage132mtemp));
        %>
        <%= tempResultreturnp133 %>
        <%
break;
case 147:
        gotMethod = true;
        String username_32id=  request.getParameter("username150");
            java.lang.String username_32idTemp = null;
        if(!username_32id.equals("")){
         username_32idTemp  = username_32id;
        }
        String first_name_33id=  request.getParameter("first_name152");
            java.lang.String first_name_33idTemp = null;
        if(!first_name_33id.equals("")){
         first_name_33idTemp  = first_name_33id;
        }
        String last_name_34id=  request.getParameter("last_name154");
            java.lang.String last_name_34idTemp = null;
        if(!last_name_34id.equals("")){
         last_name_34idTemp  = last_name_34id;
        }
        String mail_35id=  request.getParameter("mail156");
            java.lang.String mail_35idTemp = null;
        if(!mail_35id.equals("")){
         mail_35idTemp  = mail_35id;
        }
        java.lang.String signup147mtemp = sampleFeedServiceProxyid.signup(username_32idTemp,first_name_33idTemp,last_name_34idTemp,mail_35idTemp);
if(signup147mtemp == null){
%>
<%=signup147mtemp %>
<%
}else{
        String tempResultreturnp148 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(signup147mtemp));
        %>
        <%= tempResultreturnp148 %>
        <%
}
break;
case 158:
        gotMethod = true;
        String mac_36id=  request.getParameter("mac161");
            java.lang.String mac_36idTemp = null;
        if(!mac_36id.equals("")){
         mac_36idTemp  = mac_36id;
        }
        java.lang.String getAddress158mtemp = sampleFeedServiceProxyid.getAddress(mac_36idTemp);
if(getAddress158mtemp == null){
%>
<%=getAddress158mtemp %>
<%
}else{
        String tempResultreturnp159 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getAddress158mtemp));
        %>
        <%= tempResultreturnp159 %>
        <%
}
break;
case 163:
        gotMethod = true;
        String reg_37id=  request.getParameter("reg166");
        int reg_37idTemp  = Integer.parseInt(reg_37id);
        String authentic_38id=  request.getParameter("authentic168");
            java.lang.String authentic_38idTemp = null;
        if(!authentic_38id.equals("")){
         authentic_38idTemp  = authentic_38id;
        }
        java.lang.String getMessage163mtemp = sampleFeedServiceProxyid.getMessage(reg_37idTemp,authentic_38idTemp);
if(getMessage163mtemp == null){
%>
<%=getMessage163mtemp %>
<%
}else{
        String tempResultreturnp164 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getMessage163mtemp));
        %>
        <%= tempResultreturnp164 %>
        <%
}
break;
}
} catch (Exception e) { 
%>
Exception: <%= org.eclipse.jst.ws.util.JspUtils.markup(e.toString()) %>
Message: <%= org.eclipse.jst.ws.util.JspUtils.markup(e.getMessage()) %>
<%
return;
}
if(!gotMethod){
%>
result: N/A
<%
}
%>
</BODY>
</HTML>