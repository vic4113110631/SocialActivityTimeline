<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.model.*" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html"; charset = "utf-8">
        <title>Timeline</title>	
	</head>
	<body>
	
	<!--club filter-->
	<form method="GET" action="IndexServlet.do">
	<p>只想要看那些社團?</p><br>
	<INPUT TYPE="checkbox"  NAME="club" value="吉他社">吉他社<P> 
    <INPUT TYPE="checkbox"  NAME="club" value="鋼琴社">鋼琴社<P>
    <INPUT TYPE="checkbox"  NAME="club" value="國術社">國術社<P>
    <INPUT TYPE="checkbox"  NAME="club" value="籃球社">籃球社<P>
    <INPUT TYPE="checkbox"  NAME="club" value="資工系學會">資工系學會<P>
	<input type="submit" name="next" value="Submit">
	</form>
	
	<!-- create event -->
	<p><a href="CertificateServlet.do" name="h1">我要辦活動</a></p>
	<button onclick="popup()">Try it</button>
	<p id="pwd"></p>

	<script>
	function popup() {
		var pwd = prompt("請輸入社團驗證碼", "");
    
		if (pwd != null) {
			document.all("h1").innerText =
			"CertificateServlet.do?pwd=" + pwd ;
		}
	}
</script>
	
	<!-- relationship -->
	<p><a href="RelationServlet.do" name="h2">羈絆抓抓</a></p>
	<button onclick="pop2()">Try it</button>
	<p id="kwd"></p>

	<script>
	function pop2() {
		var kwd = prompt("請輸入關鍵字", "");
    
		if (kwd != null) {
			document.all("h2").innerText =
			"RelationServletServlet.do?kwd=" + kwd ;
		}
	}
</script>
	
	<!-- top 10 -->
	<a href="EventInfoServlet.do?action=top10 ">Top 10</a>
	
	<!-- make timeline -->
	<!-- 1 -->
    <link title="timeline-styles" rel="stylesheet" href="https://cdn.knightlab.com/libs/timeline3/latest/css/timeline.css">
    <!-- 2 -->
    <script src="https://cdn.knightlab.com/libs/timeline3/latest/js/timeline.js"></script>
    <div id='timeline-embed' style="width: 100%; height: 600px"></div>
    <!-- 3 -->
    <script type="text/javascript">
	var obj = JSON.parse(<%=(String)request.getAttribute("TLJsonFile")%>);						
    window.timeline = new TL.Timeline('timeline-embed', obj);
	</script>
	
	</body>
	
	
</html>