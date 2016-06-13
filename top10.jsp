<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.model.*" %>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>template</title>
		<link rel="stylesheet" type="text/css" href="index.css">
	</head>
	<body>
	<!--ArrayList<Event> list = (ArrayList<Event>)request.getAttribute("top10");-->
	<!-- d3.js graph--->
	<!--title,url,value-->
		<div id="root">
			<svg width="100%" height="600px" viewBox="0 0 800 600" preserveAspectRatio="xMidYMid">
			</svg>
		</div>

		<script type="text/javascript" src="//d3js.org/d3.v3.min.js"></script>
		<script type="text/javascript">
			d3.csv("top10.csv", function(data) {
				var dataobj = { children: data };
				var pack = d3.layout.pack();
				pack = pack.padding(2).size([800,600]).sort(function(a,b) { return b.value - a.value; });
				var nodes = pack.nodes(dataobj);
				nodes = nodes.filter(function(it) { return it.parent; });
				var color = d3.scale.category20();
				d3.select("svg")
					.selectAll("circle")                 // 建立 circle 的 Selection
					.data(nodes)                         // 綁定 selection 與資料
					.enter()                             // 對於任何沒被對應而落單的資料 ...
					.append("circle")                    // 新增一個 circle 標籤
					.attr({
						cx: function(it) { return it.x; }, // 用 x,y 當圓心
						cy: function(it) { return it.y; },
						r : function(it) { return it.r; }, // 用 r 當半徑
						fill: function(it) { return color(it.title); },
						stroke: "#444",                    // 邊框畫深灰色
				});

			d3.select("svg").selectAll("text").data(nodes).enter()
			.append("text")
			.attr({
				x: function(it) { return it.x; },
				y: function(it) { return it.y; },
				"text-anchor": "middle",                  
			}).text(function(it)  { return it.title; }); // 設定文字為title

		});
	</script>
	
	
	
	<!-- table--->
	<table>
  <tr>
    <th>排行</th>
    <th>活動名稱</th>
    <th>點擊數</th>
  </tr>
	<%
	int i =1;
		for(Event event: list){
	out.print("<tr>");
	out.print("<td>"+i+"</td>");
	i++;
	out.print("<td>"+"<a href='"+event.getUrl()+"'>"+event.getTitle()+"</a>"+"</td>");
	out.print("<td>"+event.getCTR()+"</td>");
    
    <td>Smith</td>
    <td>50</td>
	out.print("</tr>");
		}
	%>
	</table>
	</body>


	</body>
</html>