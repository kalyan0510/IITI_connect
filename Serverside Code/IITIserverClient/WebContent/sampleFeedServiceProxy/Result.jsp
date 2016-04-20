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
        java.lang.String getWarning19mtemp = sampleFeedServiceProxyid.getWarning();
if(getWarning19mtemp == null){
%>
<%=getWarning19mtemp %>
<%
}else{
        String tempResultreturnp20 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getWarning19mtemp));
        %>
        <%= tempResultreturnp20 %>
        <%
}
break;
case 22:
        gotMethod = true;
        String address_1id=  request.getParameter("address25");
            java.lang.String address_1idTemp = null;
        if(!address_1id.equals("")){
         address_1idTemp  = address_1id;
        }
        String mac_2id=  request.getParameter("mac27");
            java.lang.String mac_2idTemp = null;
        if(!mac_2id.equals("")){
         mac_2idTemp  = mac_2id;
        }
        String reg_id_3id=  request.getParameter("reg_id29");
        int reg_id_3idTemp  = Integer.parseInt(reg_id_3id);
        boolean setAddress22mtemp = sampleFeedServiceProxyid.setAddress(address_1idTemp,mac_2idTemp,reg_id_3idTemp);
        String tempResultreturnp23 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(setAddress22mtemp));
        %>
        <%= tempResultreturnp23 %>
        <%
break;
case 31:
        gotMethod = true;
        String username_4id=  request.getParameter("username34");
            java.lang.String username_4idTemp = null;
        if(!username_4id.equals("")){
         username_4idTemp  = username_4id;
        }
        String password_5id=  request.getParameter("password36");
            java.lang.String password_5idTemp = null;
        if(!password_5id.equals("")){
         password_5idTemp  = password_5id;
        }
        java.lang.String login31mtemp = sampleFeedServiceProxyid.login(username_4idTemp,password_5idTemp);
if(login31mtemp == null){
%>
<%=login31mtemp %>
<%
}else{
        String tempResultreturnp32 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(login31mtemp));
        %>
        <%= tempResultreturnp32 %>
        <%
}
break;
case 38:
        gotMethod = true;
        String username_6id=  request.getParameter("username41");
            java.lang.String username_6idTemp = null;
        if(!username_6id.equals("")){
         username_6idTemp  = username_6id;
        }
        String first_name_7id=  request.getParameter("first_name43");
            java.lang.String first_name_7idTemp = null;
        if(!first_name_7id.equals("")){
         first_name_7idTemp  = first_name_7id;
        }
        String last_name_8id=  request.getParameter("last_name45");
            java.lang.String last_name_8idTemp = null;
        if(!last_name_8id.equals("")){
         last_name_8idTemp  = last_name_8id;
        }
        String mail_9id=  request.getParameter("mail47");
            java.lang.String mail_9idTemp = null;
        if(!mail_9id.equals("")){
         mail_9idTemp  = mail_9id;
        }
        java.lang.String signup38mtemp = sampleFeedServiceProxyid.signup(username_6idTemp,first_name_7idTemp,last_name_8idTemp,mail_9idTemp);
if(signup38mtemp == null){
%>
<%=signup38mtemp %>
<%
}else{
        String tempResultreturnp39 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(signup38mtemp));
        %>
        <%= tempResultreturnp39 %>
        <%
}
break;
case 49:
        gotMethod = true;
        String reg_id_10id=  request.getParameter("reg_id52");
        int reg_id_10idTemp  = Integer.parseInt(reg_id_10id);
        String message_11id=  request.getParameter("message54");
            java.lang.String message_11idTemp = null;
        if(!message_11id.equals("")){
         message_11idTemp  = message_11id;
        }
        String type_12id=  request.getParameter("type56");
            java.lang.String type_12idTemp = null;
        if(!type_12id.equals("")){
         type_12idTemp  = type_12id;
        }
        java.lang.String setWarning49mtemp = sampleFeedServiceProxyid.setWarning(reg_id_10idTemp,message_11idTemp,type_12idTemp);
if(setWarning49mtemp == null){
%>
<%=setWarning49mtemp %>
<%
}else{
        String tempResultreturnp50 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(setWarning49mtemp));
        %>
        <%= tempResultreturnp50 %>
        <%
}
break;
case 58:
        gotMethod = true;
        int loadusers58mtemp = sampleFeedServiceProxyid.loadusers();
        String tempResultreturnp59 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(loadusers58mtemp));
        %>
        <%= tempResultreturnp59 %>
        <%
break;
case 61:
        gotMethod = true;
        String entity_13id=  request.getParameter("entity64");
            java.lang.String entity_13idTemp = null;
        if(!entity_13id.equals("")){
         entity_13idTemp  = entity_13id;
        }
        String value_14id=  request.getParameter("value66");
            java.lang.String value_14idTemp = null;
        if(!value_14id.equals("")){
         value_14idTemp  = value_14id;
        }
        java.lang.String forgotpassword61mtemp = sampleFeedServiceProxyid.forgotpassword(entity_13idTemp,value_14idTemp);
if(forgotpassword61mtemp == null){
%>
<%=forgotpassword61mtemp %>
<%
}else{
        String tempResultreturnp62 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(forgotpassword61mtemp));
        %>
        <%= tempResultreturnp62 %>
        <%
}
break;
case 68:
        gotMethod = true;
        String reg_id_15id=  request.getParameter("reg_id71");
        int reg_id_15idTemp  = Integer.parseInt(reg_id_15id);
        String oldpass_16id=  request.getParameter("oldpass73");
            java.lang.String oldpass_16idTemp = null;
        if(!oldpass_16id.equals("")){
         oldpass_16idTemp  = oldpass_16id;
        }
        String newpass_17id=  request.getParameter("newpass75");
            java.lang.String newpass_17idTemp = null;
        if(!newpass_17id.equals("")){
         newpass_17idTemp  = newpass_17id;
        }
        java.lang.String changepassword68mtemp = sampleFeedServiceProxyid.changepassword(reg_id_15idTemp,oldpass_16idTemp,newpass_17idTemp);
if(changepassword68mtemp == null){
%>
<%=changepassword68mtemp %>
<%
}else{
        String tempResultreturnp69 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(changepassword68mtemp));
        %>
        <%= tempResultreturnp69 %>
        <%
}
break;
case 77:
        gotMethod = true;
        String mac_18id=  request.getParameter("mac80");
            java.lang.String mac_18idTemp = null;
        if(!mac_18id.equals("")){
         mac_18idTemp  = mac_18id;
        }
        boolean wasMacSet77mtemp = sampleFeedServiceProxyid.wasMacSet(mac_18idTemp);
        String tempResultreturnp78 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(wasMacSet77mtemp));
        %>
        <%= tempResultreturnp78 %>
        <%
break;
case 82:
        gotMethod = true;
        java.lang.String timenow82mtemp = sampleFeedServiceProxyid.timenow();
if(timenow82mtemp == null){
%>
<%=timenow82mtemp %>
<%
}else{
        String tempResultreturnp83 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(timenow82mtemp));
        %>
        <%= tempResultreturnp83 %>
        <%
}
break;
case 85:
        gotMethod = true;
        java.lang.String getusersforsearch85mtemp = sampleFeedServiceProxyid.getusersforsearch();
if(getusersforsearch85mtemp == null){
%>
<%=getusersforsearch85mtemp %>
<%
}else{
        String tempResultreturnp86 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getusersforsearch85mtemp));
        %>
        <%= tempResultreturnp86 %>
        <%
}
break;
case 88:
        gotMethod = true;
        String regfrom_19id=  request.getParameter("regfrom91");
        int regfrom_19idTemp  = Integer.parseInt(regfrom_19id);
        String name_20id=  request.getParameter("name93");
            java.lang.String name_20idTemp = null;
        if(!name_20id.equals("")){
         name_20idTemp  = name_20id;
        }
        String au_21id=  request.getParameter("au95");
            java.lang.String au_21idTemp = null;
        if(!au_21id.equals("")){
         au_21idTemp  = au_21id;
        }
        String regto_22id=  request.getParameter("regto97");
        int regto_22idTemp  = Integer.parseInt(regto_22id);
        String str_23id=  request.getParameter("str99");
            java.lang.String str_23idTemp = null;
        if(!str_23id.equals("")){
         str_23idTemp  = str_23id;
        }
        String img_24id=  request.getParameter("img101");
            java.lang.String img_24idTemp = null;
        if(!img_24id.equals("")){
         img_24idTemp  = img_24id;
        }
        int putmessage88mtemp = sampleFeedServiceProxyid.putmessage(regfrom_19idTemp,name_20idTemp,au_21idTemp,regto_22idTemp,str_23idTemp,img_24idTemp);
        String tempResultreturnp89 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(putmessage88mtemp));
        %>
        <%= tempResultreturnp89 %>
        <%
break;
case 103:
        gotMethod = true;
        String reg_id_25id=  request.getParameter("reg_id106");
        int reg_id_25idTemp  = Integer.parseInt(reg_id_25id);
        String x_26id=  request.getParameter("x108");
            java.lang.String x_26idTemp = null;
        if(!x_26id.equals("")){
         x_26idTemp  = x_26id;
        }
        String y_27id=  request.getParameter("y110");
            java.lang.String y_27idTemp = null;
        if(!y_27id.equals("")){
         y_27idTemp  = y_27id;
        }
        boolean changedp103mtemp = sampleFeedServiceProxyid.changedp(reg_id_25idTemp,x_26idTemp,y_27idTemp);
        String tempResultreturnp104 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(changedp103mtemp));
        %>
        <%= tempResultreturnp104 %>
        <%
break;
case 112:
        gotMethod = true;
        String reg_id_28id=  request.getParameter("reg_id115");
        int reg_id_28idTemp  = Integer.parseInt(reg_id_28id);
        java.lang.String getdp112mtemp = sampleFeedServiceProxyid.getdp(reg_id_28idTemp);
if(getdp112mtemp == null){
%>
<%=getdp112mtemp %>
<%
}else{
        String tempResultreturnp113 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getdp112mtemp));
        %>
        <%= tempResultreturnp113 %>
        <%
}
break;
case 117:
        gotMethod = true;
        String reg_id_29id=  request.getParameter("reg_id120");
        int reg_id_29idTemp  = Integer.parseInt(reg_id_29id);
        java.lang.String geticon117mtemp = sampleFeedServiceProxyid.geticon(reg_id_29idTemp);
if(geticon117mtemp == null){
%>
<%=geticon117mtemp %>
<%
}else{
        String tempResultreturnp118 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(geticon117mtemp));
        %>
        <%= tempResultreturnp118 %>
        <%
}
break;
case 122:
        gotMethod = true;
        String reg_id_30id=  request.getParameter("reg_id125");
        int reg_id_30idTemp  = Integer.parseInt(reg_id_30id);
        java.lang.String getuser122mtemp = sampleFeedServiceProxyid.getuser(reg_id_30idTemp);
if(getuser122mtemp == null){
%>
<%=getuser122mtemp %>
<%
}else{
        String tempResultreturnp123 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getuser122mtemp));
        %>
        <%= tempResultreturnp123 %>
        <%
}
break;
case 127:
        gotMethod = true;
        java.lang.String isWarnignGenerated127mtemp = sampleFeedServiceProxyid.isWarnignGenerated();
if(isWarnignGenerated127mtemp == null){
%>
<%=isWarnignGenerated127mtemp %>
<%
}else{
        String tempResultreturnp128 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(isWarnignGenerated127mtemp));
        %>
        <%= tempResultreturnp128 %>
        <%
}
break;
case 130:
        gotMethod = true;
        String reg_id_31id=  request.getParameter("reg_id133");
        int reg_id_31idTemp  = Integer.parseInt(reg_id_31id);
        String status_32id=  request.getParameter("status135");
            java.lang.String status_32idTemp = null;
        if(!status_32id.equals("")){
         status_32idTemp  = status_32id;
        }
        java.lang.String changeStatus130mtemp = sampleFeedServiceProxyid.changeStatus(reg_id_31idTemp,status_32idTemp);
if(changeStatus130mtemp == null){
%>
<%=changeStatus130mtemp %>
<%
}else{
        String tempResultreturnp131 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(changeStatus130mtemp));
        %>
        <%= tempResultreturnp131 %>
        <%
}
break;
case 137:
        gotMethod = true;
        sampleFeedServiceProxyid.clearWarning();
break;
case 140:
        gotMethod = true;
        String reg_id_33id=  request.getParameter("reg_id143");
        int reg_id_33idTemp  = Integer.parseInt(reg_id_33id);
        String message_34id=  request.getParameter("message145");
            java.lang.String message_34idTemp = null;
        if(!message_34id.equals("")){
         message_34idTemp  = message_34id;
        }
        String type_35id=  request.getParameter("type147");
            java.lang.String type_35idTemp = null;
        if(!type_35id.equals("")){
         type_35idTemp  = type_35id;
        }
        java.lang.String setSOS140mtemp = sampleFeedServiceProxyid.setSOS(reg_id_33idTemp,message_34idTemp,type_35idTemp);
if(setSOS140mtemp == null){
%>
<%=setSOS140mtemp %>
<%
}else{
        String tempResultreturnp141 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(setSOS140mtemp));
        %>
        <%= tempResultreturnp141 %>
        <%
}
break;
case 149:
        gotMethod = true;
        java.lang.String getSOS149mtemp = sampleFeedServiceProxyid.getSOS();
if(getSOS149mtemp == null){
%>
<%=getSOS149mtemp %>
<%
}else{
        String tempResultreturnp150 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getSOS149mtemp));
        %>
        <%= tempResultreturnp150 %>
        <%
}
break;
case 152:
        gotMethod = true;
        java.lang.String isSOSGenerated152mtemp = sampleFeedServiceProxyid.isSOSGenerated();
if(isSOSGenerated152mtemp == null){
%>
<%=isSOSGenerated152mtemp %>
<%
}else{
        String tempResultreturnp153 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(isSOSGenerated152mtemp));
        %>
        <%= tempResultreturnp153 %>
        <%
}
break;
case 155:
        gotMethod = true;
        sampleFeedServiceProxyid.clearSOS();
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