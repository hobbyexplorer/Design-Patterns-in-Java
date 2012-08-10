<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
<meta charset="utf-8">
<title>Build Game</title>

<link rel="stylesheet" href="../css/style.css" />

<link rel="stylesheet" href="../css/floatbox.css"/>
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
				<a href="home"> <img src="../images/head_image.png" 

alt=""
					width="960" height="245" />
				</a>
			</h1>
			<ul id="nav">
				<li id="nav-home"><a href="home">Home</a></li>
				<li id="nav-buildgame"><a href="buildgame">Build 

Game</a></li>
				<li id="nav-playgame"><a href="playgame">Play 

Game</a></li>
				<li id="nav-score"><a href="score">Score</a></li>
				<li id="nav-contact"><a href="contact">Contact 

us</a></li>
			</ul>
		</div>
		<!--end header-->
		<script type="text/javascript">
			function loadBuildGameValues(arg0, arg1, arg2, arg3) {
				var userIdInputTextBoxElement = document
						.getElementById

('userIdInputTextBox');
				var gameIdInputTextBoxElement = document
						.getElementById

('gameIdInputTextBox');
				var versionInputTextBoxElement = document
						.getElementById

('versionInputTextBox');
				var gameNameInputTextBoxElement = document
						.getElementById

('gameNameInputTextBox');
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
						submitButtonElement.click();
						document.getElementById

('menulist').innerHTML = "<h3><a>Loading Game Maker...</a></h3>";
					}
				}
			}
		</script>
		<div style="display: none;">
			<form action="buildonbuildgameversion" method="POST">
				<input type="text" id="userIdInputTextBox" 

name="userId" /> <input
					type="text" id="gameIdInputTextBox" 

name="gameId" /> <input
					type="text" id="versionInputTextBox" 

name="version" /> <input
					type="text" id="gameNameInputTextBox" 

name="gameName" /> <input
					type="submit" id="submitButton">
				
			</form>
		</div>
		<div id="main">
			<div id="user">
				<%
					if (session.getAttribute("user") == null) {
				%>

				<a href="home" style="font-weight: bold">Login</a>

				<%
					} else {
				%>

				<a style="margin-right: 30px">Welcome ${user.name} 

${game.gameId}</a> <a
					href="logout" style="margin-left: 30px; 

font-weight: bold">Logout</a>


				<%
					}
				%>
			</div>
			<div id="content">
				<h2 class="page-title">Build your own game!</h2>
				<p id="portfolio-intro">Please see the instruction 

below and
					have fun building your fancy game</p>

				<div class="post" id="menulist">
					<!-- <h2>
						<a href="../build_jnlp/team3-a6-

user-1.jnlp">[Build new game]</a>
					</h2> -->
					<!-- <h2>[Rebuild old game]</h2>
					<h2>[Build List]</h2> -->

					<h2>
						[${gameName}]
						<!--  <font color="blue">[Resume 

Building]</font> -->
					</h2>

					<c:forEach var="oneGame" 

items="${allGameVersions}">
						<h3>

							<a href="#"
								

onclick="javascript:loadBuildGameValues('${userId}','${oneGame.gameId}', 

'${oneGame.version}','${gameName }');">${gameName}
								Ver

${oneGame.version}.0 </a>

						</h3>
					</c:forEach>
         
				</div>
				<!--end portfolio-item-->
				
				
<script language="javascript">


var characters = new Array('!', '"', '#', '$', '%', '&', '`', '(', ')', '*', '+', 

',', '-', '.', '/', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ':', ';', '<', 

'=', '>', '?', '@', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 

'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '[', '\\', ']', 

'^', '_', '\'', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 

'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '{', '|', '}', 

'~');

var alphanumchars = new Array('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 

'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 

'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 

'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 

'z');

var keyval = '';
var keysize = -1;

function random_character(array) {
 return array[Math.floor(Math.random() * array.length)];
}

function generate(f) {

 keyval = '';
 f = document.getElementById("keygen");

 for (i=0; i < 3; i++) {
  if (f.r_size[i].checked) {
   keysize = f.r_size[i].value;
  }
 }

 if (keysize == "custom") {
  if (f.r_custom_size.value != '') {
   keysize = f.r_custom_size.value;
  } else {
   alert("If you choose a custom size, you must enter a value!!");
   return;
  }
 }

 for (i = 0; i < keysize; i++) {
  keyval += random_character(characters);
 }
 
 f.r_key.value = keyval;

 return;
}

//-->
</script>


 

 






<a name="keygen_a">

<div class="floatbox">
<p>

<form name="keygen" id="keygen" action = "addGameKey" method="POST">
<table cellpadding="5">
 <tr><td><b>Key size:</b></td>
  <td><input type="radio" name="r_size" value="8">False Security (8 characters)</td>
 </tr>

 <tr><td>&nbsp;</td>
  <td><input type="radio" name="r_size" value="20" checked="yes">Bare Minimum 

Security (20 characters)</td>
 </tr>

 <tr><td>&nbsp;</td>
  <td><input type="radio" name="r_size" value="custom">Custom Size: <input 

type="text" size="2" maxlength="2" name="r_custom_size"> characters <font size="-

1"><i>(no more than 50.)</i></font></td>

 </tr>

 <tr>
  <td><input type="submit" name="submit" class="button" href="javascript:generate

(this)" onclick="javascript:generate(this)"  value="generate"></td>
  <td><input type="text" size="50" maxlength="50" name="r_key" value=""></td>
 </tr>
 
 
</table>
</form> 
</div>


			</div>

			<!--end of side bar division-->

			<a style="float: right;" class="button" 

href="buildgame">Back</a>
		</div>
		<!--end main-->
		<div id="sidebar">
			<a
				href="https://www.facebook.com/pages/Design-

Patterns-in-Java/275169485855198">
				<h3 class="sidebar-title">Follow us on Facebook 

&#187</h3>
			</a> <a href="help">
				<h3 class="sidebar-title">Check the guide &#187</h3>
			</a> <a href=http://www.youtube.com/embed/Mida307GiZw>
				<h3 class="sidebar-title">Watch video &darr;</h3>
			</a>
			<iframe width="225" height="150"
				src="http://www.youtube.com/embed/Mida307GiZw" 

frameborder="0"
				allowfullscreen></iframe>
			<br>
			<b>Requirements:</b>
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
			<p class="copyright">Copyright � 2011 / All Rights 

Reserved</p>
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