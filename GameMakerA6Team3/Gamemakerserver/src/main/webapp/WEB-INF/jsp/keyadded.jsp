
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
				
				

<a name="keygen_a">

<div class="floatbox">
<p>


<table cellpadding="5">
<tr>
<td>
<b>
Key details:
</b>
</td>

</tr>
 <tr>
 
 <td><b>
 Key : ${gamekey} 
 </b></td>
  <td><b>
 Game : ${gameName} 
 </b></td>
 </tr>

 <tr>
<td>
<form action="buildgameversion" method="GET"><input 

type="submit" name="submit" style="float: left;" class="button" 

value = "Generate more keys"></form>


</td>
<td>
<form action="buildgameversion" method="GET"><input 

type="submit" name="submit" style="float:left;" class="button" 

value = "Back"></form>


</td>
</tr>
 
</table>
 
</div>
This is a once-time-used key, please remember them carefully. If you press "Back button" you will never come back to see the key


			</div>

			<!--end of side bar division-->
 <p></p>
			
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