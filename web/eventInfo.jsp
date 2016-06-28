<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ page import="com.model.Event" %>
<html>
<meta charset="UTF-8">
<head>
	<!-- TEMPLATE -->
	<title>詳細資訊</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<!--[if lte IE 8]><script src="js/html5shiv.js"></script><![endif]-->
		<script src="js/jquery.min.js"></script>
		<script src="js/skel.min.js"></script>
		<script src="js/skel-layers.min.js"></script>
		<script src="js/init.js"></script>
		<noscript>
			<link rel="stylesheet" href="css/skel.css" />
			<link rel="stylesheet" href="css/style.css" />
			<link rel="stylesheet" href="css/style-xlarge.css" />
		</noscript>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

    <script src="https://www.amcharts.com/lib/3/amcharts.js"></script>
    <script src="https://www.amcharts.com/lib/3/pie.js"></script>
    <script src="https://www.amcharts.com/lib/3/themes/light.js"></script>
	
	<script src="https://www.amcharts.com/lib/3/amcharts.js"></script>
    <script src="https://www.amcharts.com/lib/3/pie.js"></script>
    <script src="https://www.amcharts.com/lib/3/themes/light.js"></script>
	<script src="https://www.amcharts.com/lib/3/amcharts.js"></script>
<script src="https://www.amcharts.com/lib/3/serial.js"></script>
<script src="https://www.amcharts.com/lib/3/themes/light.js"></script>
	<script>
        var chart = AmCharts.makeChart( "chartdiv", {
        "type": "pie",
        "theme": "light",
        "dataProvider": [ {
        "sex": "Male",
        "value": ${sexRatio[0]}
        }, {
        "sex": "Female",
        "value": ${sexRatio[1]}
        }],
        "valueField": "value",
        "titleField": "sex",
        "outlineAlpha": 0.4,
        "depth3D": 15,
        "balloonText": "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>",
        "angle": 30,
        "export": {
        "enabled": true
        }
        } );
    </script>
	<script>
	var chart = AmCharts.makeChart("charts", {
    "theme": "light",
    "type": "serial",
	"startDuration": 2,
    "dataProvider": [{
        "department": "資工",
        "total": ${department['資工']},
        "color": "#FF0F00"
    }, {
        "department": "應外",
        "total": ${department['應外']},
        "color": "#FF6600"
    }, {
        "department": "會計",
        "total": ${department['會計']},
        "color": "#FF9E01"
    }, {
        "department": "航管",
	    "total": ${department['航管']},
        "color": "#FCD202"
    }, {
        "department": "護理",
        "total": ${department['護理']},
        "color": "#F8FF01"
    }],
    "graphs": [{
        "balloonText": "[[category]]: <b>[[value]]</b>",
        "fillColorsField": "color",
        "fillAlphas": 1,
        "lineAlpha": 0.1,
        "type": "column",
        "valueField": "total"
    }],
    "depth3D": 20,
	"angle": 30,
    "chartCursor": {
        "categoryBalloonEnabled": false,
        "cursorAlpha": 0,
        "zoomable": false
    },    
    "categoryField": "department",
    "categoryAxis": {
        "gridPosition": "start",
        "labelRotation": 0
    },
    "export": {
    	"enabled": true
     }
    });
	</script>
    <style>
        #chartdiv {
			float: right;
            width		: 60%;
            height		: 435px;
            font-size	: 32px;
        }
    </style>
	<style>
	    #charts {
			float: left;
	        width		: 40%;
	        height		: 435px;
	        font-size	: 32px;
	}
	 </style>
	
	
	
	
	
	
	
    <script>
        var chart = AmCharts.makeChart( "chartdiv", {
        "type": "pie",
        "theme": "light",
        "dataProvider": [ {
        "sex": "Male",
        "value": ${sexRatio[0]}
        }, {
        "sex": "Female",
        "value": ${sexRatio[1]}
        }],
        "valueField": "value",
        "titleField": "sex",
        "outlineAlpha": 0.4,
        "depth3D": 15,
        "balloonText": "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>",
        "angle": 30,
        "export": {
        "enabled": true
        }
        } );
    </script>
    
</head>
<body style="font-family: 微軟正黑體; font-size:22px">

<!-- Header -->
<%
    Event e = (Event) request.getAttribute("event");
    out.print("<div><center><img src = ImageServlet.do?ImageName=" + e.getImgPath() + "></img></center></div>");
    out.println(e.getIntroduction());
    
%>
			<header id="header" style=" font-size:18px">
				<h1><strong><a href="index.html"></a></strong> <%=e.getName()%></h1>
				<nav id="nav">
					<ul>
						<li><a href="Index.do">首頁</a></li>
						
						<li><a href="ApplicantForm.jsp?name=${event.getName()}">報名</a></li>
					</ul>
				</nav>
			</header>


<hr size="5" align="center" noshade width="90%" color="0000ff"><br><br>
<%
    int total = (int)request.getAttribute("participatants");
    out.print("<center>目前報名人數:" + total);
%>
</center><br><br>
<br><br><br><br><br>
<div id="charts"></div>
<div id="chartdiv" >
</div>

<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<form action = "ApplicantForm.jsp">
        <input type="hidden" name="name" value="${event.getName()}">
<center><input type="submit" value="報名"></center><br><br><br><br><br>
</form>
<%request.setAttribute("event",e);%>

<!-- Footer -->
			<footer id="footer" style="background-color:#D6D6D6">
				<div class="container">
					<ul class="icons">
						<li><a href="https://www.facebook.com/" class="icon fa-facebook"></a></li>
						<li><a href="https://twitter.com/" class="icon fa-twitter"></a></li>
						<li><a href="https://plus.google.com/" class="icon fa-instagram"></a></li>
					</ul>
					<ul class="copyright">
						<li>&copy; D3js</li>
						<li>Design: <a href="http://templated.co">TEMPLATED</a></li>
						<li>Images: <a href="http://unsplash.com">Unsplash</a></li>
					</ul>
				</div>
			</footer>
</body>
</html>  