<%@include file="/apps/natgeo/global.jsp" %><%
%><%@page import="com.day.cq.wcm.api.WCMMode,
                  com.nationalgeographic.cq.pagetypes.util.NgsObject"%>
<%
    //initialize global variables to be used by all pages here
    NgsObject currentNgsPage = currentPage.adaptTo(NgsObject.class);
    request.setAttribute("currentNgsPage",currentNgsPage);
    
    boolean ngsIsWCMDisable = (WCMMode.fromRequest(request) == WCMMode.DISABLED || WCMMode.PREVIEW == WCMMode.fromRequest(request));
	request.setAttribute("ngsIsWCMDisable",ngsIsWCMDisable);
%>