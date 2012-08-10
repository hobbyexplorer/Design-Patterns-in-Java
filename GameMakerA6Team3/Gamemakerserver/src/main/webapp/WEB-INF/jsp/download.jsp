<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
<meta charset="utf-8">
<title>Download Game</title>

<link rel="stylesheet" href="../css/style.css" />
<link rel="stylesheet" href="../css/floatbox.css" />

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
			function vote(amnt) {
				
				var starwidth = 25 * parseInt(amnt);

				var userIdInputTextBoxElement = document
						.getElementById('userIdInputTextBox');
				var gameIdInputTextBoxElement = document
						.getElementById('gameIdInputTextBox');
				var ratingInputTextBoxElement = document
						.getElementById('ratingInputTextBox');
				var versionInputTextBoxElement = document
						.getElementById('versionInputTextBox');
				var submitButtonElement = document
						.getElementById('submitButton');

				var shouldSubmit = true;
				document.getElementById("starmodification").innerHTML = "  Thanks for rating this game!<br><br>";
				
				if (userIdInputTextBoxElement != null) {
					userIdInputTextBoxElement.value = "<%=request.getParameter("user")%>";
				} else {
					shouldSubmit = false;
				}
				
				if (gameIdInputTextBoxElement != null) {
					gameIdInputTextBoxElement.value = "<%=request.getParameter("gameId")%>";

				} else {
					shouldSubmit = false;
				}

				if (versionInputTextBoxElement != null) {
					versionInputTextBoxElement.value = "<%=request.getParameter("version")%>";
				} else {
					shouldSubmit = false;
				}

				if (ratingInputTextBox != null) {
					ratingInputTextBox.value = amnt

				} else {
					shouldSubmit = false;
				}
				document.getElementById("current-rating").style.width = starwidth;
				
				if (shouldSubmit) {
					if (submitButtonElement != null) {
						submitButtonElement.click();
					}
				}
			}
		</script>

		<div style="display: none;">
			<form action="saverating" method="POST">
				<input type="text" id="userIdInputTextBox" name="userId" /> <input
					type="text" id="gameIdInputTextBox" name="gameId" /> <input
					type="text" id="versionInputTextBox" name="version" /> <input
					type="text" id="ratingInputTextBox" name="ratingInput"> <input
					type="submit" id="submitButton">
			</form>
		</div>
		<div id="main">
			<div id="user" style="height: 100px">
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
				<h2 class="page-title">Download your game</h2>
				<div class="post" id="listofallgames">

					<%
						String urlString = "http://localhost:7777/fall2011a6team3/play_jnlp/team3-a6-user-";
						//String urlString = "http://tintin.cs.indiana.edu:9999/fall2011a6team3/play_jnlp/team3-a6-user-";
						urlString += request.getParameter("user");
						urlString += "-game-";
						urlString += request.getParameter("gameId");
						urlString += "-version-";
						urlString += request.getParameter("version");
						urlString += ".jnlp";
					%>
					<a href='<%=urlString%>'> Click here to download the game </a> <br>


					<div class="floatbox" id="ratingbox" style="background: white">
						<h3>Game Rating</h3>
						
						Your rating:

						<ul class='star-rating'>
							<li class='current-rating' id='current-rating'
								style="width:25"></li>
							<li><a href="#" onclick="vote(1); return false;"
								title='1 star out of 5' class='one-star'>1</a></li>
							<li><a href="#" onclick="vote(2); return false;"
								title='2 star out of 5' class='two-stars'>2</a></li>
							<li><a href="#" onclick="vote(3); return false;"
								title='3 star out of 5' class='three-stars'>3</a></li>
							<li><a href="#" onclick="vote(4); return false;"
								title='4 star out of 5' class='four-stars'>4</a></li>
							<li><a href="#" onclick="vote(5); return false;"
								title='5 star out of 5' class='five-stars'>5</a></li>
						</ul>
						<div id="starmodification"></div>
						Average user rating:
						<ul class='star-rating'>
							<li class='current-rating' id='avg-user-rating'
								style="width:${25*gameforrating.averageRating}"></li>
							<li><a title='1 star out of 5' class='one-star'>1</a></li>
							<li><a title='2 star out of 5' class='two-stars'>2</a></li>
							<li><a title='3 star out of 5' class='three-stars'>3</a></li>
							<li><a title='4 star out of 5' class='four-stars'>4</a></li>
							<li><a title='5 star out of 5' class='five-stars'>5</a></li>
						</ul>
					</div>
					<div id='current-rating-result'></div>
					<!-- used to show "success" message after vote -->

				</div>

			</div>
			<!--end content-->
		</div>
		<!--end main-->
		<div id="sidebar">

			<h3 class="sidebar-title">Get In Touch</h3>
			<p>Would you like to discuss hiring me for a project you have in
				mind? If so, please contact me using one of the options below.</p>
			<b>Email</b> <b>Email</b>
			<p>bhawalr@umail.iu.edu</p>

			<p>msoparia@umail.iu.edu</p>
			<p>magevadi@umail.iu.edu</p>
			<p>haoxiang@umail.iu.edu</p>
			<p>wu44@umail.iu.edu</p>

		</div>
		<!--end sidebar-->
		<div class="line"></div>

		<div id="footer">
			<p class="copyright">Copyright © 2011 / All Rights Reserved</p>
			<p class="social">
				<a
					href="https://www.facebook.com/pages/Design-Patterns-in-Java/275169485855198">Follow
					us on Facebook »</a>
			</p>
		</div>
		<!--end footer-->

	</div>
	<!--end wrap-->
</body>
</html>