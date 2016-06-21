<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.model.*" %>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>top10</title>
		<link rel="stylesheet" type="text/css" href="index.css">
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<script src="js/jquery.min.js"></script>
		<script src="js/skel.min.js"></script>
		<script src="js/skel-layers.min.js"></script>
		<script src="js/init.js"></script>
		<noscript>
			<link rel="stylesheet" href="css/skel.css" />
			<link rel="stylesheet" href="css/style.css" />
			<link rel="stylesheet" href="css/style-xlarge.css" />
		</noscript>
	<%
		ArrayList<Event> list = (ArrayList<Event>)request.getAttribute("top10");
	%>
	<!-- d3.js graph--->
	<!--title,url,value-->
	<link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,600,200italic,600italic&subset=latin,vietnamese' rel='stylesheet' type='text/css'>

  <script src="http://phuonghuynh.github.io/js/bower_components/jquery/dist/jquery.min.js"></script>
  <script src="http://phuonghuynh.github.io/js/bower_components/d3/d3.min.js"></script>
  <script src="http://phuonghuynh.github.io/js/bower_components/d3-transform/src/d3-transform.js"></script>
  <script src="http://phuonghuynh.github.io/js/bower_components/cafej/src/extarray.js"></script>
  <script src="http://phuonghuynh.github.io/js/bower_components/cafej/src/misc.js"></script>
  <script src="http://phuonghuynh.github.io/js/bower_components/cafej/src/micro-observer.js"></script>
  <script src="http://phuonghuynh.github.io/js/bower_components/microplugin/src/microplugin.js"></script>
  <script src="http://phuonghuynh.github.io/js/bower_components/bubble-chart/src/bubble-chart.js"></script>
 <script src="http://phuonghuynh.github.io/js/bower_components/bubble-chart/src/plugins/lines/lines.js"></script>
  <script>

d3.svg.BubbleChart.define("lines", function (options) {
  var self = this;

  self.setup = (function () {
    var original = self.setup;
    return function () {
      var fn = original.apply(this, arguments);
      var node = self.getNodes();
      $.each(options.format, function (i, f) {
        node.append("text")
          .classed(f.classed)
          .style(f.style)
          .attr(f.attr)
          .text(function (d) {
            return getText(d,f);
          });
      });
      return fn;
    };
  })();
  function getText(d,f){
     if(d.item[f.textField])
        return d.item[f.textField];
      else 
        return f.textField(d.item);
  }
  self.reset = (function (node) {
    var original = self.reset;
    return function (node) {
      var fn = original.apply(this, arguments);
      $.each(options.format, function (i, f) {
        var tNode = d3.select(node.selectAll("text")[0][i]);
        tNode.classed(f.classed).text(function (d) {
            return getText(d,f);
        })
          .transition().duration(self.getOptions().transitDuration)
          .style(f.style)
          .attr(f.attr);
      });
      return fn;
    };
  })();

  self.moveToCentral = (function (node) {
    var original = self.moveToCentral;
    return function (node) {
      var fn = original.apply(this, arguments);
      $.each(options.centralFormat, function (i, f) {
        var tNode = d3.select(node.selectAll("text")[0][i]);
        tNode.transition().duration(self.getOptions().transitDuration)
          .style(f.style)
          .attr(f.attr);
        f.classed !== undefined && tNode.classed(f.classed);
        f.textField !== undefined && tNode.text(function (d) {
          return getText(d,f);
        });
      });
      return fn;
    };
  })();
});
</script>
  
  <script>
 $(document).ready(function () {
  /**
 * central-click.js
 */
d3.svg.BubbleChart.define("central-click", function (options) {
  var self = this;

  self.setup = (function (node) {
    var original = self.setup;
    return function (node) {
      var fn = original.apply(this, arguments);
      self.event.on("click", function(node) {
        //here you will get the data you want
        console.log(node.data()[0].item)
      });
      return fn;
    };
  })();

  self.reset = (function (node) {
    var original = self.reset;
    return function (node) {
      var fn = original.apply(this, arguments);
      node.select("text.central-click").remove();
      return fn;
    };
  })();

  self.moveToCentral = (function (node) {
    var original = self.moveToCentral;
    return function (node) {
      var fn = original.apply(this, arguments);
      var transition = self.getTransition().centralNode;
      transition.each("end", function() {
        node.append("text").classed({"central-click": true})
          .attr(options.attr)
          .style(options.style)
          .attr("x", function (d) {return d.cx;})
          .attr("y", function (d) {return d.cy;})
          .text(options.text)
          .style("opacity", 0).transition().duration(self.getOptions().transitDuration / 2).style("opacity", "0.8");
      });
      return fn;
    };
  })();
});
  var bubbleChart = new d3.svg.BubbleChart({
    supportResponsive: true,
    size: 600,
    innerRadius: 600 / 3.5,
    radiusMin: 50,
    data: {
     items: [
        {
          name: "1",
          position: "<%=list.get(0).getName()%>",
          contact_info: {
            phone: "<%=list.get(0).getCTR()%>",
          },
        },
        {
          name: "2",
          position: "<%=list.get(1).getName()%>",
          contact_info: {
            phone: "<%=list.get(1).getCTR()%>",
          },
        },
        {
          name: "3",
          position: "<%=list.get(2).getName()%>",
          contact_info: {
            phone: "<%=list.get(2).getCTR()%>",
          },
        },
		{
          name: "4",
          position: "<%=list.get(3).getName()%>",
          contact_info: {
            phone: "<%=list.get(3).getCTR()%>",
          },
        },
		 {
          name: "5",
          position: "<%=list.get(4).getName()%>",
          contact_info: {
            phone: "<%=list.get(4).getCTR()%>",
          },
        },
		 {
          name: "6",
          position: "<%=list.get(5).getName()%>",
          contact_info: {
            phone: "<%=list.get(5).getCTR()%>",
          },
        },
		 {
          name: "7",
          position: "<%=list.get(6).getName()%>",
          contact_info: {
            phone: "<%=list.get(6).getCTR()%>",
          },
        },
		 {
          name: "8",
          position: "<%=list.get(7).getName()%>",
          contact_info: {
            phone: "<%=list.get(7).getCTR()%>",
          },
        },
		 {
          name: "9",
          position: "<%=list.get(8).getName()%>",
          contact_info: {
            phone: "<%=list.get(8).getCTR()%>",
          },
        },
		 {
          name: "10",
          position: "<%=list.get(9).getName()%>",
          contact_info: {
            phone: "<%=list.get(9).getCTR()%>",
          },
        },
      ],
      eval: function (item) {
        console.log(item); 
        return item.contact_info.phone;
        
      },
      myFn: function (item) {console.log('item'); return item.contact_info.phone;},
      classed: function (item) {return item.name.split(" ").join("");}
    },
    plugins: [
      {
        name: "central-click",
        options: {
          text: "(See more detail)",
          style: {
            "font-size": "12px",
            "font-style": "italic",
            "font-family": "Source Sans Pro, sans-serif",
            //"font-weight": "700",
            "text-anchor": "middle",
            "fill": "white"
          },
          attr: {dy: "65px"},
          centralClick: function(d) {
            console.log(d,"clickeds")
            alert("Here is more details!!");
          }
        }
      },
      {
        name: "lines",
        options: {
          format: [
            {// Line #0
              textField: "position",
              classed: {position: true},
              style: {
                "font-size": "8px",
                "font-family": "Source Sans Pro, sans-serif",
                "text-anchor": "middle",
                fill: "white"
              },
              attr: {
                dy: "0px",
                x: function (d) {return d.cx;},
                y: function (d) {return d.cy;}
              }
            },
            {// Line #1 // THE OTHER 2 LINES WORKS, BUT THIS ONE IS NOT!!!
              textField: function(d){console.log(d);return d.contact_info.phone},
              classed: {contact_info : {phone: true}},
              style: {
                "font-size": "14px",
                "font-family": "Source Sans Pro, sans-serif",
                "text-anchor": "middle",
                fill: "red"
              },
              attr: {
                dy: "20px",
                x: function (d) {return d.cx;},
                y: function (d) {return d.cy;}
              }
            },
            {// Line #2
              textField: "name",
              classed: {name: true},
              style: {
                "font-size": "14px",
                "font-family": "Source Sans Pro, sans-serif",
                "text-anchor": "middle",
                fill: "white"
              },
              attr: {
                dy: "30px",
                x: function (d) {return d.cx;},
                y: function (d) {return d.cy;}
              }
            }
          ],
          centralFormat: [
            {// Line #0
              style: {"font-size": "20px"},
              attr: {}
            },
            {// Line #1
              style: {"font-size": "30px"},
              attr: {dy: "40px"}
            },
            {// Line #2
              style: {"font-size": "20px"},
              attr: {dy: "80px"}
            }
          ]
        }
      }]
  });
});

  </script>
  
   <style>
    .bubbleChart {
      min-height: 600px;
      max-height: 1000px;
	  min-width: 600px;
      max-width: 1300px;
      margin: 0 auto;
    }
    .bubbleChart svg{
      background: #000000;
    }
  </style>
  </head>
	<body class="landing" style="font-family: 微軟正黑體;background: #000000">
	<header id="header" class="alt">
				<h1><strong><a href="#">Top 10</a></strong> by team 7 </h1>
				<nav id="nav">
					<ul>
					
						<!-- top 10 -->
						<li><a href="Index.do">首頁</a></li>
						
						<!-- create event -->
						<li><a href="#" name="h1" onclick="popup()">我要辦活動</a></li>
							<script>
							function popup() {
								var pwd = prompt("請輸入社團驗證碼", "");
							
								if (pwd != null) {
									document.all("h1").href =
									"Certificate.do?pwd=" + pwd ;
								}
							}
							</script>
					
						<!-- relationship --
						<li><a href="RelationServlet.do" name="h2" onclick="pop2()">羈絆抓抓</a></li>-->

							<script>
							function pop2() {
								var kwd = prompt("請輸入關鍵字", "");
								
								if (kwd != null) {
									document.all("h2").href =
									"Relation.do?kwd=" + kwd ;
								}
							}
							</script>

					</ul>
				</nav>
			</header>
	</head>
	<div class="bubbleChart" style="height:1200px">
	</div>
	<!-- table--->
	<div class="table-wrapper" style="background: #FFFFFF;font-size:22pt;height=1500px">
	<table align = "center">
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
			out.print("<td>"+"<a href='eventInfo.do?id="+event.getId()+"'>"+event.getName()+"</a>"+"</td>");
			out.print("<td>"+event.getCTR()+"</td>");
			out.print("</tr>");
		}
	%>
	</table>
	</div>
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