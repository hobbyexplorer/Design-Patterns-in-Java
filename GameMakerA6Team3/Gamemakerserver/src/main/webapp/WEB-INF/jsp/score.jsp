
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
<meta charset="utf-8">
<title>Scores</title>

<link rel="stylesheet" href="../css/style.css" />

<!--[if IE 6]>
            <style>body { behavior: url("../css/ie6-hover-fix.htc"); }</style>
            <link rel="stylesheet" href="../css/ie6.css" />
        <![endif]-->
<!--[if IE 7]>
            <link rel="stylesheet" href="../css/ie7.css" />
        <![endif]-->
<!--[if IE 8]>
            <link rel="stylesheet" href="../css/ie8.css" />
        <![endif]-->

</head>

<body>
	<div id="wrap">
		<div id="header">
			<h1>
				<a href="home"> <img src="../images/head_image.png" alt=""
					width="960" height="245" />
				</a>
			</h1>
			<ul id="nav">
				<li id="nav-home"><a href="home">Home</a></li>
				<li id="nav-buildgame"><a href="buildgame">Build Game</a></li>
				<li id="nav-playgame"><a href="playgame">Play Game</a></li>
				<li id="nav-score"><a href="score">Score</a></li>
				<li id="nav-contact"><a href="contact">Contact us</a></li>
			</ul>
		</div>
		<!--end header-->

		<script type="text/javascript">
			function showTopScores() {
				var topScoresSection = document
						.getElementById('topScoresSection');
				var allScoresSection = document
						.getElementById('allScoresSection');
				var myScoresSection = document
						.getElementById('myScoresSection');

				if (topScoresSection != null) {
					topScoresSection.style.display = 'block';
				}

				if (allScoresSection != null) {
					allScoresSection.style.display = 'none';
				}

				if (myScoresSection != null) {
					myScoresSection.style.display = 'none';
				}
			}

			function showAllScores() {
				var topScoresSection = document
						.getElementById('topScoresSection');
				var allScoresSection = document
						.getElementById('allScoresSection');
				var myScoresSection = document
						.getElementById('myScoresSection');

				if (topScoresSection != null) {
					topScoresSection.style.display = 'none';
				}

				if (allScoresSection != null) {
					allScoresSection.style.display = 'block';
				}

				if (myScoresSection != null) {
					myScoresSection.style.display = 'none';
				}
			}

			function showMyScores() {
				var topScoresSection = document
						.getElementById('topScoresSection');
				var allScoresSection = document
						.getElementById('allScoresSection');
				var myScoresSection = document
						.getElementById('myScoresSection');

				if (topScoresSection != null) {
					topScoresSection.style.display = 'none';
				}

				if (allScoresSection != null) {
					allScoresSection.style.display = 'none';
				}

				if (myScoresSection != null) {
					myScoresSection.style.display = 'block';
				}
			}
		</script>

		<div id="main">
			<div id="user">
				<%
					if (session.getAttribute("user") == null) {
				%>

				<a href="home" style="font-weight: bold">Login</a>

				<%
					} else {
				%>

				<a style="margin-right: 30px">Welcome ${user.name}</a> <a
					href="logout" style="margin-left: 30px; font-weight: bold">Logout</a>


				<%
					}
				%>
			</div>
			<div id="content">
				<div class="post">

					<table cellpadding="0" cellspacing="0">
						<tr>
							<td><a style="cursor: pointer;"
								onclick="javascript:showMyScores();">My Scores</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
							</td>
							<td><a style="cursor: pointer;"
								onclick="javascript:showAllScores();">All Scores</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
							</td>
							<td><a style="cursor: pointer;"
								onclick="javascript:showTopScores();">Top Scores</a></td>
						</tr>
					</table>
					<br /> <br /> <span id="allScoresSection" style="display: none;">
						<h3 class="post-title">
							<a href="#" title="">[All Scores]</a>
						</h3> <br />
						<table>
							<tr>
								<th align="left">Player</th>
								<th align="left">Score</th>
								<th align="left">Game</th>
							</tr>
							<c:forEach var="oneScore" items="${allScores}">
								<tr>
									<td style="width: 150px;">${oneScore.user.name}</td>
									<td style="width: 100px;">${oneScore.score}</td>
									<td style="width: 100px;">${oneScore.game.name}</td>
								</tr>
							</c:forEach>
						</table>
					</span> <span id="topScoresSection" style="display: none;">
						<h3 class="post-title">
							<a href="#" title="">[Top 10 Scores]</a>
						</h3> <br /> <a href="#"><img width="200" height="200"
							src="../images/top10players.jpg" class="post-image"
							alt="[alt text]" title="[title text]" /></a>
						<table>
							<tr>
								<th align="left">Player</th>
								<th align="left">Score</th>
								<th align="left">Game</th>
							</tr>
							<c:forEach var="oneScore" items="${topScores}">
								<tr>
									<td style="width: 150px;">${oneScore.user.name}</td>
									<td style="width: 100px;">${oneScore.score}</td>
									<td style="width: 100px;">${oneScore.game.name}</td>
								</tr>
							</c:forEach>
						</table>
					</span> <span id="myScoresSection" style="display: block;">
						<h3 class="post-title">
							<a href="#" title="">[My scores]</a>
						</h3> <br />
						<table>
							<tr>
								<th align="left">Player</th>
								<th align="left">Score</th>
								<th align="left">Game</th>
							</tr>
							<c:forEach var="oneScore" items="${myScores}">
								<tr>
									<td style="width: 150px;">${oneScore.user.name}</td>
									<td style="width: 100px;">${oneScore.score}</td>
									<td style="width: 100px;">${oneScore.game.name}</td>
								</tr>
							</c:forEach>
						</table>
					</span>
				</div>
				<!--end post-->

			</div>
			<!--end content-->


			<!--end sidebar-->

		</div>
		<!--end main-->
		<div id="sidebar" style="height: 450px">
			<h3 class="sidebar-title">Help</h3>
			<p>Top scores are displayed here. Play more games and get high
				scores to get on top of our score board!</p>
			<b>Connect:</b>
			<p>
				Find more Games, Game makers, High scores on our <a
					href="https://www.facebook.com/pages/Design-Patterns-in-Java/275169485855198">
					<b>Facebook Page &#187</b>
				</a>
			</p>


		</div>
		<div class="line"></div>

		<div id="footer">
			<p class="copyright">Copyright � 2011 / All Rights Reserved</p>
			<p class="social">
				<a
					href="https://www.facebook.com/pages/Design-Patterns-in-Java/275169485855198">Follow
					us on Facebook �</a>
			</p>
		</div>
		<!--end footer-->

	</div>
	<!--end wrap-->

</body>
</html>