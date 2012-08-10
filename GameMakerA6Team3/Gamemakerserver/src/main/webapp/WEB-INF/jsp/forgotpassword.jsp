
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
<meta charset="utf-8">
<title>Forgot Password</title>

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
			function clearForgotPasswordForm() {
				var emailInputTextBoxElement = document
						.getElementById('emailInputTextBox');
				var userNameInputTextBoxElement = document
						.getElementById('userNameInputTextBox');

				if (emailInputTextBoxElement != null) {
					emailInputTextBoxElement.value = '';
				}

				if (userNameInputTextBoxElement != null) {
					userNameInputTextBoxElement.value = '';
				}
			}

			function validateForgotPasswordForm() {
				var returnValue = true;

				// validation for email
				var emailInputTextBoxElement = document
						.getElementById('emailInputTextBox');
				var emailInputValidatorElement = document
						.getElementById('emailInputValidator');

				if (emailInputTextBoxElement != null) {
					if (emailInputTextBoxElement.value == '') {
						if (emailInputValidatorElement != null) {
							emailInputValidatorElement.style.display = "block";
							returnValue = false;
						}
					} else {
						if (emailInputValidatorElement != null) {
							emailInputValidatorElement.style.display = "none";
						}
					}
				}

				// validation for username
				var userNameInputTextBoxElement = document
						.getElementById('userNameInputTextBox');
				var userNameInputValidatorElement = document
						.getElementById('userNameInputValidator');

				if (userNameInputTextBoxElement != null) {
					if (userNameInputTextBoxElement.value == '') {
						if (userNameInputValidatorElement != null) {
							userNameInputValidatorElement.style.display = "block";
							returnValue = false;
						}
					} else {
						if (userNameInputValidatorElement != null) {
							userNameInputValidatorElement.style.display = "none";
						}
					}
				}

				return returnValue;

			}
		</script>
		<div id="main">
			<div id="user">
				<%
					if (session.getAttribute("user") == null) {
				%>

				Already registered? <a href="home" style="font-weight: bold">Login</a>

				<%
					} else {
				%>

				<a style="margin-right: 30px">Welcome ${user.name}</a> <a
					href="logout" style="margin-left: 30px; font-weight: bold">Logout</a>


				<%
					}
				%>
			</div>
			<div id="project-content">

				<h2 class="page-title">Forgot Password</h2>

				<div class="line"></div>
				<br />
				<form action="getpassword" method="POST">
					<table>
						<tr>
							<td valign="top"><span class="formFieldLabel">Email:
							</span></td>
							<td valign="top"><input type="text" id="emailInputTextBox"
								name="email" /></td>
							<td><span id="emailInputValidator" class="missingInfo"
								style="display: none;">Email is required</span></td>
						</tr>
						<tr>
							<td valign="top"><span class="formFieldLabel">Username:
							</span></td>
							<td valign="top"><input type="text"
								id="userNameInputTextBox" name="userName" /></td>
							<td><span id="userNameInputValidator" class="missingInfo"
								style="display: none;">Username is required</span></td>
						</tr>
						<tr>
							<td colspan="2"><br /> <br />
								<center>
									<input type="submit"
										onclick="javascript:return validateForgotPasswordForm();"
										value="Email my password to me now !" /><span style="margin-left: 40px"></span> <input
										type="button" onclick="javascript:clearForgotPasswordForm();"
										value="Clear" />
								</center></td>
						</tr>
					</table>
				</form>
				
				<%
					if (session.getAttribute("forgotPasswordUser") != null) {
				%>
					Your password has been mailed to the email address you entered.<br />
					Please re-enter the information in the form and re-submit, if you want it to be mailed again.
				<%
					}
				%>

				<%
					if (session.getAttribute("noPasswordUser") != null) {
				%>
					Incorrect email or user name entered. Please verify and re-enter the details.
				<%
					}
				%>
			</div>

		</div>
		<!--end main-->
		<div id="sidebar" style="height: 450px">
			<h3 class="sidebar-title">Unlock more features!</h3>
			<p>
				&#187 Build your own game <br> &#187 Gamer Profile <br>
				&#187 Get High scores <br> &#187 Save your build games <br>
				&#187 Publish your games <br> &#187 Save different versions <br>
				
			<h6>Register and become a member today!</h6>
			</p>
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