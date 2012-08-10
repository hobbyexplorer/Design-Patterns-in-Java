
﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
<meta charset="utf-8">
<title>Home</title>
<link rel="stylesheet" href="../css/style.css" />

<script src="../js/SlideTrans.js"></script>

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
		<div id="frontpage-content">
			<div id="featured-section">
				<style type="text/css">
.container,.container img {
	width: 320px;
	height: 220px;
	float: left;
	margin-bottom: 30px;
}
</style>
				<style type="text/css">
.container ul {
	list-style: none;
	margin: 0;
	padding: 0;
	float: left;
}

.num {
	position: absolute;
	right: 5px;
	bottom: 5px;
	font: 12px/1.5 tahoma, arial;
	height: 18px;
	float: around;
}

.num li {
	float: left;
	color: #d94b01;
	text-align: center;
	line-height: 16px;
	width: 16px;
	height: 16px;
	font-family: Arial;
	font-size: 11px;
	cursor: pointer;
	margin-left: 3px;
	border: 1px solid #f47500;
	background-color: #fcf2cf;
}
</style>
			<div id="main">
				<div class="container" id="idContainer2">
					<ul id="idSlider2">
						<li><a> <img
								src="http://cars--games.com/images/cars-games.jpg" alt=" " />
						</a></li>
						<li><a> <img
								src="http://kids-esl.com/wp-content/uploads/2011/04/400x300_dice_game.png"
								alt="  " />
						</a></li>
						<li><a> <img
								src="http://www.gameingcentral.com/wp-content/uploads/15_5_thumb.jpg"
								alt=" " />
						</a></li>
						<li><a> <img
								src="http://www.technoni.com/wp-content/uploads/game01-300x225.jpg"
								alt=" " />
						</a></li>
						<li><a> <img
								src="http://msnbcmedia1.msn.com/j/MSNBC/Components/Photo/_new/100601_Games_PlantsVsZombiesHD.grid-6x2.jpg"
								alt=" " />
						</a></li>
					</ul>
					<ul class="num" id="idNum">
					</ul>
				</div>
				<div id="latest-projects">
				<h4>Newly built game</h4>
				<div class="latest-project first-latest-project">
					<p>[Pacman]</p>
					<a href="#"> <img src="../images/pacman.jpg" class="post-image"
						alt="" title="[title text]" /></a>
				</div>
				<!--end latest-project-->
				<div class="latest-project">
					<p>[Car racing]</p>
					<a href="#"> <img src="../images/carracing.jpg"
						class="post-image" alt="" title="[title text]" /></a>
				</div>
				<!--end latest-project-->
				<div class="latest-project">
					<p>[Asteroid]</p>
					<a href="#"> <img src="../images/Asteroid.png"
						class="post-image" alt="[alt text]" title="[title text]" /></a>
				</div>
				<!--end latest-project-->
			</div>
			<!--end latest-projects-->
				</div> <!-- End of Main  -->
				<div id="sidebar" style="height: 540px">
					<%
						if (session.getAttribute("user") == null) {
					%>
					<form action="login" method="POST" id="login-form">
					<input id="form_username" type="text" name="userName" value="User Name"
							onfocus="if(this.value=='User Name'){this.value=''};"
							onblur="if(this.value==''){this.value='User Name'};" /> 
					<input id="form_password" type="password" name="password" value="password"
							onfocus="if(this.value=='password'){this.value=''};"
							onblur="if(this.value==''){this.value='password'};" />
					<input type="submit" name="submit" style="float: right; margin-right:20px; padding-left:15px; margin-left:50px; width:88px" class="button" value="Login" />
					<br />
					<a href="forgotpassword">Forgot password?</a>
					&nbsp;
					<div style="font-family:Arial, sans-serif; color:#0B79A3; font-weight:bolder; margin-top:25px; margin-left:80px; float:left; width:70px">New User?</div>
					<a style="float: right; margin-top: 14px; margin-right: 20px; margin-left: 5px; width:60px" class="button" href="register">Register</a>
						<!--<table>
							<tr>
								<td style="height: 30px; width: 80px;"><span
									class="formFieldLabel">Username: </span></td>
								<td><input type="text" id="userNameInputTextBox"
									name="userName" /></td>
							</tr>
							<tr>
								<td style="height: 40px;"><span class="formFieldLabel">Password:
								</span></td>
								<td><input type="password" id="passwordInputTextBox"
									name="password" /></td>
							</tr>
							    <div>
		         <c:if test="${credentialsEmpty == 'true'}"> 
			Authentication failed.<br/>
			Reason: Please enter both username and password
		</c:if>	
		<c:if test="${incorrectCredentials == 'true'}">
			Authentication failed.<br/> 
			Reason: Invalid username and password combination
		</c:if>
		                      </div>
							<tr>
								<td colspan="2"><a href="#">Forgot password?</a></td>
							</tr>
							<tr>
								<td colspan="2"><input type="submit" name="submit" style="float: right;" class="button" value="Login" /></td>
                            	<td colspan="2"><a style="float: right; margin-top: 10px;" class="button" href="register">Register</a></td>
							</tr>
							<tr>

							</tr>-->
						</table>
					</form>
					<%
						} else {
					%>
					<div id="user" >	
					<a style= "margin-right: 30px" >Welcome ${user.name}</a>
                    <a href="logout" style= "margin-left: 30px; font-weight: bold" >Logout</a>
                    
			</div> <br>
			
			
			<br>
			<a href="https://www.facebook.com/pages/Design-Patterns-in-Java/275169485855198"> <h3 class="sidebar-title"> Follow us on Facebook &#187 </h3> </a>
			<p> Post your high scores on our wall. Meet other gamers and builders.</p> 
			<a href="http://p532team03gamemaker.forumotion.com/" class="sidebar-title"><h3>Check out our Forum &#187 </h3></a>
			<p>Discuss about your favorite game in our forum. If your are a game builder, find out posts about how to build and monetize your games. </p>
					<!--<c:forEach var="user" items="${users}">
							${user.name}
							</c:forEach>-->
					
					<%
								}
							%>
				</div>
				<!--end featured-section-details-->
			</div>
			<!--end featured-section-->
			<div class="line"></div>
		</div>
		<!--end frontpage-content-->
		<div class="line"></div>
		<div id="footer">
			<p class="copyright">Copyright © 2011</p>
			<p class="social">
				
			</p>
		</div>
		<!--end footer-->
	</div>
	<!--end wrap-->

	<script>
		var nums = [], timer, n = $$("idSlider2").getElementsByTagName("li").length, st = new SlideTrans(
				"idContainer2", "idSlider2", n, {
					onStart : function() {
						forEach(nums, function(o, i) {
							o.className = st.Index == i ? "on" : "";
						})
					}
				});
		for ( var i = 1; i <= n; AddNum(i++)) {
		};
		function AddNum(i) {
			var num = $$("idNum").appendChild(document.createElement("li"));
			num.innerHTML = i--;
			num.onmouseover = function() {
				timer = setTimeout(function() {
					num.className = "on";
					st.Auto = false;
					st.Run(i);
				}, 200);
			}
			num.onmouseout = function() {
				clearTimeout(timer);
				num.className = "";
				st.Auto = true;
				st.Run();
			}
			nums[i] = num;
		}
		st.Run();
	</script>
</body>
</html>
