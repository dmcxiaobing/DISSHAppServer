<%--
  Created by IntelliJ IDEA.
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <%--<%=basePath%>这是设置基础路径的,basePath为变量，简单的静态网页的话你设置比如：<base href="http://www.GOOGLE.com">，
    那你下面的href属性就会以你上面设的为基准，
    <%=basePath%> == http://localhost:80/
    如：<a href="http://www.GOOGLE.com/xxx.htm"></a>你现在就只需要写<a href="xxx.htm"></a>--%>
    <base href="<%=basePath%>">
  </head>
  <title></title>
  <body>
  </body>
</html>
