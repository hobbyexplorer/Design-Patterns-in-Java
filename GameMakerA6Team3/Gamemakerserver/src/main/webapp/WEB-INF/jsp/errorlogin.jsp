<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
<meta charset="utf-8">
<title>Build Game</title>

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
				<a href="home"> <img src="../images/head_image.png" alt="" width="960"
					height="245" />
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
			function loadBuildGameValues(arg0, arg1, arg2, arg3) {
				var userIdInputTextBoxElement = document
						.getElementById('userIdInputTextBox');
				var gameIdInputTextBoxElement = document
						.getElementById('gameIdInputTextBox');
				var versionInputTextBoxElement = document
						.getElementById('versionInputTextBox');
				var gameNameInputTextBoxElement = document
						.getElementById('gameNameInputTextBox');
				var submitButtonElement = document
						.getElementById('submitButton');

				var shouldSubmit = true;

				if (userIdInputTextBoxElement != null) {
					userIdInputTextBoxElement.value = arg0;
				} else {
					shouldSubmit = false;
				}

				if (gameIdInputTextBoxElement != null) {
					gameIdInputTextBoxElement.value = arg1;
				} else {
					shouldSubmit = false;
				}

				if (versionInputTextBoxElement != null) {
					versionInputTextBoxElement.value = arg2;
				} else {
					shouldSubmit = false;
				}
				if (gameNameInputTextBoxElement != null) {
					gameNameInputTextBoxElement.value = arg3;
				} else {
					shouldSubmit = false;
				}

				if (shouldSubmit) {
					if (submitButtonElement != null) {
						document.getElementById('menulist').innerHTML = "<h3><a>Retrieving Game Version List...</a></h3>";
						submitButtonElement.click();

					}
				}
			}
		</script>
		<div style="display: none;">
			<form action="buildgameversionlist" method="POST">
				<input type="text" id="userIdInputTextBox" name="userId" /> <input
					type="text" id="gameIdInputTextBox" name="gameId" /> <input
					type="text" id="gameNameInputTextBox" name="gameName" /> <input
					type="text" id="versionInputTextBox" name="version" /> <input
					type="submit" id="submitButton">
				</button>
			</form>
		</div>
		<div id="main">
			<div id="content">
				<h2 class="page-title">Build your own game!</h2>
				<h6>Please see the instruction below and have fun building your
					game</h6>

				<div class="post" id="menulist">
					<h3>
						<br>You are not logged in!</br> Please <a href="home">Login</a> to
						Build games
					</h3>


				</div>
				<!--end portfolio-item first-->
			</div>
			<!--end content-->

			
			<!--end of side bar division-->



		</div>
		<!--end main-->
		<div id="sidebar">
				<a href="help">
					<h3 class="sidebar-title">Check the guide &#187</h3>
				</a> <a href=http://www.youtube.com/embed/Mida307GiZw>
					<h3 class="sidebar-title">Watch video &darr;</h3>
				</a>
				<iframe width="225" height="150"
					src="http://www.youtube.com/embed/Mida307GiZw" frameborder="0"
					allowfullscreen></iframe>
				<br><b>Requirements:</b>
				<p>
					Java run time environment(JRE) 1.5 or higher<br> <a
						href="http://www.oracle.com/technetwork/java/javase/downloads/index.html">Download
						JRE here</a>

				</p>
				<p>
					Adobe Flash player 7 or higher.<br> <a
						href="http://get.adobe.com/flashplayer/">Download Flash player</a>
				</p>
			</div>
		<div class="line"></div>

		<div id="footer">
			<p class="copyright">Copyright &#169 2011 / All Rights Reserved</p>
			<p class="social">			
			<a
					href="https://www.facebook.com/pages/Design-Patterns-in-Java/275169485855198">Follow
					us on Facebook >></a>
			</p>
		</div>
		<!--end footer-->

	</div>
	<!--end wrap-->

</body>

</html>