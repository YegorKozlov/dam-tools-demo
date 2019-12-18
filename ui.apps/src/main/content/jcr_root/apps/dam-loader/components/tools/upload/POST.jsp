<%@include file="/libs/foundation/global.jsp" %><%
%><%@page session="false"
          import="
    javax.jcr.Node,
    javax.jcr.NodeIterator,
	javax.jcr.RepositoryException,
	org.apache.sling.api.request.*,
	java.util.*,
	java.io.*,
	com.day.cq.dam.api.*" 
%><div style="font-size:11px; white-space: pre-wrap; font-family:'Courier New', Courier, monospace"><%

   String destPath = slingRequest.getParameter("destPath");	  
   out.println("destPath: " + destPath);	
   for (RequestParameter param : slingRequest.getRequestParameterList()) {
        if(!param.isFormField()) {
            InputStream stream = param.getInputStream();
            out.println(param);
            out.println("===========================");

            AssetManager assetMgr = resourceResolver.adaptTo(AssetManager.class);
            Asset asset = assetMgr.createAsset(destPath + "/" + param.getFileName(), stream, param.getContentType(), true);
            out.println("asset created: " + asset.getPath());


        }
    }
%></div>