<%@ page contentType = "text/html" pageEncoding = "UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import = "java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta http-equiv = "Content-Type" content = "text/html; charset = UTF-8">
        <title>活動社群</title>
		
		<style>
			text { font: 10px sans-serif; }
		</style>
		<script src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
		<script src="http://cytoscape.github.io/cytoscape.js/api/cytoscape.js-latest/cytoscape.min.js"></script>
		<script src="https://cdn.rawgit.com/cpettitt/dagre/v0.7.4/dist/dagre.min.js"></script>
		<script src="https://cdn.rawgit.com/cytoscape/cytoscape.js-dagre/1.1.2/cytoscape-dagre.js"></script>
		

    	<style>
			#relationChart {
				width: 500px;
				height: 500px;

			}
		</style>
	</head>

	<body>
		<div id = "relationChart"></div><hr>
		<script>
			$(function(){
				var cy = window.cy = cytoscape(${jsonStrg});
			});
		</script>
		<div>
			<table>
				<tr>
					<td>Who</td>
					<td>Intersection Events</td>
					<td>NonIntersection Events</td>
					<td>Total Events</td>
				</tr>
				<c:forEach var = "element" items = "${relArray}">
					<tr>
						<td>${element.who}</td>
						<td>
							&nbsp
							<c:forEach var = "event" items = "${element.intersection}">
								${event.name}&nbsp
							</c:forEach>
						</td>
						<td>
							&nbsp
							<c:forEach var = "event" items = "${element.nointersection}">
								${event.name}&nbsp
							</c:forEach>
						</td>
						<td>${element.totalScore}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>
</html>