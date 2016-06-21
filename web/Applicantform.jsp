<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ page import = "com.model.Sex" %>
<%@ page import="com.model.Event" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%    pageContext.setAttribute("sexEnum", Sex.values()); %>

<html>
    <head>
		<title>Applicant Form</title>
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
		
	    
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	</head>

	<body style="font-family: 微軟正黑體; font-size:18px">
	<!-- Header -->
			<header id="header">
				<h1><strong>請填寫表單</strong></h1>
			
			</header>
	
	
	
	<!-- Form -->
	<section>
	
	<div class="row uniform 50%" >
	<div class="6u 12u$(xsmall)" >
		<form method="GET" action="Applicant.do" class = "form-horizontal" role = "form" >
			<div class = "form-group"><label class = "col-sm-3 control-label">名子</label>
				<div class = "col-sm-9">
					<input type="text" name = "name" placeholder = "ex : 林一尾" class="form-control">
				</div>
			</div>
			<div class = "form-group"><label class = "col-sm-3 control-label">學系</label>
				<div class = "col-sm-9">
					<input type="text" name = "grade" placeholder="ex : 資工" class="form-control">
				</div>
			</div>
			<div class = "form-group"><label class = "col-sm-3 control-label">學號</label>
				<div class = "col-sm-9">
					<input type="text" name = "number" placeholder="ex : 00257001" class="form-control">
				</div>
			</div>

			<div class = "form-group"><label class = "col-sm-3 control-label">性別</label>
				<div class = "col-sm-9">
					<select name = "sex" class="form-control">
						<c:forEach var = "sex" items = "${sexEnum}">
							<option value = "${sex}">${sex}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<input type="hidden" name="eventName" value="${param.name}">
				<ul class="actions">
					<center><li><input type="submit" value="寄出" class="special" /></li></center>
				</ul>
		</form>
	</div>
	</div>
	</section>
	</body>
</html>