<%@page contentType="text/html" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
	<head>
	
	<title>活動抓抓</title>	
		<meta http-equiv="Content-Type" content="text/html"; charset = "utf-8">
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
		<!--時間軸排版-->
		<style>
			.tl-text {
				font-family: 微軟正黑體;
			}
		</style>
	</head>
	
	<body class="landing" style="font-family: 微軟正黑體;">
	<!-- Header -->
			<header id="header" class="alt">
				<h1><strong><a href="第七組.pptx">Produced</a></strong> by team 7 </h1>
				<nav id="nav">
					<ul>
					
						<!-- top 10 -->
						<li><a href="EventInfo.do?action=top10 ">Top 10</a></li>
						
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
			
	<!-- Banner -->
			<section id="banner">
				<a href = "Index.do"><h2 style = "background-color: black; padding: 15px;">活動抓抓</h2></a>
				<p>English Name Here</p>
				<p>- 活動串聯你我他 彼此距離不下線 -</p>
			</section>		
			
	<!--club filter-->
	<!--運動比賽->沙灘排球、健身比賽
	音樂藝術->海洋之星、相聲瓦舍、畢業演唱會
	休閒娛樂->資工之夜、淨灘活動、棉花糖情人節
	升學就業->企業實習說明會、履歷撰寫教學演講-->
	
	<section>
		
		<form method="get" action="Index.do" style = "background-color: lightgray; padding: 50px; padding-bottom: 65px;" >
			<br><div class="row uniform 100%">
				
				<div class="12u$ 12u$(xlarge)" align="center">
					<h3>活動分類</h3>
					<input type="checkbox" id="priority-low" name="club" value="運動比賽">
					<label for="priority-low">運動比賽</label>
					<input type="checkbox" id="priority-normal" name="club" value="音樂藝術">
					<label for="priority-normal">音樂藝術</label>
					<input type="checkbox" id="priority-high" name="club" value="休閒娛樂">
					<label for="priority-high">休閒娛樂</label>
					<input type="checkbox" id="priority-extraHigh" name="club" value="升學就業">
					<label for="priority-extraHigh">升學就業</label><br>
					<input type="submit" value="篩選" class="special" />
				</div>
			</div>
		</form>
	</section>
	
	
	<!-- make timeline -->
	<!-- 1 -->
    <link title="timeline-styles" rel="stylesheet" href="https://cdn.knightlab.com/libs/timeline3/latest/css/timeline.css">
    <!-- 2 -->
    <script src="https://cdn.knightlab.com/libs/timeline3/latest/js/timeline.js"></script>
    <div id='timeline-embed' style="width: 100%; height: 900px"></div>
    <!-- 3 -->
    <script type="text/javascript">
	var obj = <%= (String)request.getAttribute("TLJsonFile") %>;
		
		var additionalOptions = {
            start_at_end: true,
            timenav_height: 200
          }
		  
    window.timeline = new TL.Timeline('timeline-embed', obj,additionalOptions);
	</script>
	
	
	
	
	
	<!-- top10 -->
				<section id="four" class="wrapper style3 special">
					<div class="container">
						<header class="major">
							<h2>羈絆抓抓</h2>
							<p>還在茫茫人海中追尋那個他/她嗎?</p>
							<p>讓我們幫你找出與你志趣相投的人吧!</p>
						</header>
						<ul class="actions">
							<li><a href="#" name="h2"class="button special big" onclick="pop2()">Here We Go !</a></li>
						</ul>
					</div>
				</section>
				
	<!-- Editors -->
				<section id="three" class="wrapper style2 special">
					<div class="container">
						<header class="major special">
							<h2>編輯精選</h2>
							<p>話題活動!只怕你不來</p>
						</header>
						<div class="feature-grid">
							<div class="feature">
								<div class="image rounded"><img src="images/iot.jpg" alt="" /></div>
								<div class="content">
									<header>
										<h4>物聯網實物教學平台演講</h4>
										<p>2016/6/24</p>
									</header>
									<p>各位同學好 資工系特邀請飆機器人_普特企業王經理前來介紹雲端物聯網(IOT)實物教學平台 時間：105/6/24(五)9:20~17:00 地點：205教室</p>
								</div>
							</div>
							<div class="feature">
								<div class="image rounded"><img src="images/aief.jpg" alt="" /></div>
								<div class="content">
									<header>
										<h4>協助公告『翻譯工讀生及義工』招募活動</h4>
										<p>2016/6/29</p>
									</header>
									<p>美國教育基金會每年舉辦兩次美國教育展，展覽期間招募及培訓對美國教育文化有興趣、有熱情的在校學生擔任翻譯工讀生及義工。教育展的翻譯及義工是臺灣同學與美國校方的溝通橋樑。</p>
								</div>
							</div>
							<div class="feature">
								<div class="image rounded"><img src="images/17129.jpg" alt="" /></div>
								<div class="content">
									<header>
										<h4>相聲瓦舍《賣橘子的》</h4>
										<p>2016/6/14</p>
									</header>
									<p>遊走紫湖畔，遇見馮翊綱的浪漫情懷；登上五行山，撞見宋少卿的恣意江湖；躲進松林裡，窺見黃士偉的風情萬種。一部集結《西遊記》唐僧師徒、《水滸傳》梁山好漢以及《三國演義》群雄豪傑的相聲作品。</p>
								</div>
							</div>
							<div class="feature">
								<div class="image rounded"><img src="images/123.jpg" alt="" /></div>
								<div class="content">
									<header>
										<h4>孤女的願望-柳依蘭傳奇</h4>
										<p>2016/4/28</p>
									</header>
									<p>圖書館1F，如果你對於柳依蘭老師想要進一步了解，千萬不要錯過專題講座。蕭瓊瑞老師略帶沙啞嗓音，搭配優美的音樂，將柳依蘭的傳奇，深深印入你的腦中，絕對要聽一次的演講，千萬不要錯過。(開放旁聽~)</p>
								</div>
							</div>
						</div>
					</div>
				</section>
	
				
				
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