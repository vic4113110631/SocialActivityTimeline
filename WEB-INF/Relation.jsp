<%@ page contentType = "text/html" pageEncoding = "UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import = "java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta http-equiv = "Content-Type" content = "text/html; charset = UTF-8">
        <title>Relation</title>
		
		<style>
			text {
			  font: 10px sans-serif;
			}
		</style>
		<script type = "text/javascript" src = "//d3js.org/d3.v3.min.js"></script>
    </head>
	
	<body>
		<div id = "relationChart"></div><hr>
		<div>
			<table>
				<tr>
					<td>Who</td>
					<td>Events</td>
					<td>Total</td>
				</tr>
				<c:forEach var = "key" items = "${TableKeySet}">
					<tr>
						<td>${key}</td>
						<td>
							<br>
							<c:forEach var = "event" varStatus = "theCount" items = "${RelationTable[key]}">
								${event.name}<br>
								<c:set var = "total" value = "theCount" scope = "request" />
							</c:forEach>
						</td>
						<td>${total}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>
	
	<script>
		var diameter = 960,
			format = d3.format(",d"),
			color = d3.scale.category20c();

		var bubble = d3.layout.pack()
			.sort(null)
			.size([diameter, diameter])
			.padding(1.5);

		var svg = d3.select("#relationChart").append("svg")
			.attr("width", diameter)
			.attr("height", diameter)
			.attr("class", "bubble");

		d3.json("flare.json", function(error, root) {
			if (error) throw error;

			var node = svg.selectAll(".node")
				.data(bubble.nodes(classes(root))
				.filter(function(d) { return !d.children; }))
				.enter().append("g")
				.attr("class", "node")
				.attr("transform", function(d) { return "translate(" + d.x + "," + d.y + ")"; });

			node.append("title")
				.text(function(d) { return d.className + ": " + format(d.value); });

			node.append("circle")
				.attr("r", function(d) { return d.r; })
				.style("fill", function(d) { return color(d.packageName); });

			node.append("text")
				.attr("dy", ".3em")
				.style("text-anchor", "middle")
				.text(function(d) { return d.className.substring(0, d.r / 3); });
		});

		// Returns a flattened hierarchy containing all leaf nodes under the root.
		function classes(root) {
			var classes = [];
			
			function recurse(name, node) {
				if (node.children) node.children.forEach(function(child) { recurse(node.name, child); });
				else classes.push({packageName: name, className: node.name, value: node.size});
			}
		  
			recurse(null, root);
			return {children: classes};
		}

		d3.select(self.frameElement).style("height", diameter + "px");
	</script>
</html>