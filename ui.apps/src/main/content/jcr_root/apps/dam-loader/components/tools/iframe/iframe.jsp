<%@include file="/libs/granite/ui/global.jsp" %><%
%><%@page session="false"
          import="java.util.Iterator,
                  com.adobe.granite.ui.components.AttrBuilder,
                  com.adobe.granite.ui.components.Config,
                  com.adobe.granite.ui.components.Tag,
                  com.adobe.granite.ui.components.Value" %><%
Tag tag = cmp.consumeTag();
AttrBuilder attrs = tag.getAttrs();
cmp.populateCommonAttrs(attrs);
Config cfg = cmp.getConfig();
attrs.add("name", cfg.get("granite:id", String.class));

%> 

<iframe <%= attrs.build() %> style="width:100%; height: 100%; min-height:400px; border: 1px solid #888888; overflow: scroll; background-color: white;">
</iframe>
