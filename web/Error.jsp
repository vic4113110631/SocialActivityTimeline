<%@ page contentType = "text/html" pageEncoding = "UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import = "java.util.*" %>

<html>
    <head>
        <meta http-equiv = "Content-Type" content = "text/html; charset = UTF-8">
        <title>Certification Error</title>
		
		<script></script>
    </head>
    <body>
		<p>Wrong Password~</p>
		<% response.setHeader("Refresh", "1;url=Index.do"); %>
    </body>
</html>