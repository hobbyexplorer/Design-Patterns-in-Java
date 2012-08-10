
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
<meta charset="utf-8">
<title>Register</title>

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
			function clearRegistrationForm() {
				var nameInputTextBoxElement = document
						.getElementById('nameInputTextBox');
				var emailInputTextBoxElement = document
						.getElementById('emailInputTextBox');
				var userNameInputTextBoxElement = document
						.getElementById('userNameInputTextBox');
				var passwordInputTextBoxElement = document
						.getElementById('passwordInputTextBox');
				var reenterPasswordInputTextBoxElement = document
						.getElementById('reenterPasswordInputTextBox');
				var ageInputTextBoxElement = document
						.getElementById('ageInputTextBox');
				var maleRadioButtonElement = document
						.getElementById('maleRadioButton');
				var femaleRadioButtonElement = document
						.getElementById('femaleRadioButton');

				if (nameInputTextBoxElement != null) {
					nameInputTextBoxElement.value = '';
				}

				if (emailInputTextBoxElement != null) {
					emailInputTextBoxElement.value = '';
				}

				if (userNameInputTextBoxElement != null) {
					userNameInputTextBoxElement.value = '';
				}

				if (passwordInputTextBoxElement != null) {
					passwordInputTextBoxElement.value = '';
				}

				if (reenterPasswordInputTextBoxElement != null) {
					reenterPasswordInputTextBoxElement.value = '';
				}

				if (ageInputTextBoxElement != null) {
					ageInputTextBoxElement.value = '';
				}

				if (maleRadioButtonElement != null) {
					maleRadioButtonElement.checked = false;
				}

				if (femaleRadioButtonElement != null) {
					femaleRadioButtonElement.checked = false;
				}
			}

			function validateRegistrationForm() {
				var returnValue = true;

				// validation for name
				var nameInputTextBoxElement = document
						.getElementById('nameInputTextBox');
				var nameInputValidatorElement = document
						.getElementById('nameInputValidator');

				if (nameInputTextBoxElement != null) {
					if (nameInputTextBoxElement.value == '') {
						if (nameInputValidatorElement != null) {
							nameInputValidatorElement.style.display = "block";
							returnValue = false;
						}
					} else {
						if (nameInputValidatorElement != null) {
							nameInputValidatorElement.style.display = "none";
						}
					}
				}

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

				// validation for password
				var passwordInputTextBoxElement = document
						.getElementById('passwordInputTextBox');
				var passwordInputValidatorElement = document
						.getElementById('passwordInputValidator');
				var reenterPasswordInputValidatorElement = document
						.getElementById('reenterPasswordInputValidator');

				if (passwordInputTextBoxElement != null) {
					if (passwordInputTextBoxElement.value == '') {
						if (passwordInputValidatorElement != null) {
							passwordInputValidatorElement.style.display = "block";
							returnValue = false;
						}

						if (reenterPasswordInputValidatorElement != null) {
							reenterPasswordInputValidatorElement.style.display = "none";
						}
					} else {
						if (passwordInputValidatorElement != null) {
							passwordInputValidatorElement.style.display = "none";
						}

						// validation for password repeat
						var reenterPasswordInputTextBoxElement = document
								.getElementById('reenterPasswordInputTextBox');

						if (reenterPasswordInputTextBoxElement != null) {
							if (reenterPasswordInputTextBoxElement.value != passwordInputTextBoxElement.value) {
								if (reenterPasswordInputValidatorElement != null) {
									reenterPasswordInputValidatorElement.style.display = "block";
									returnValue = false;
								}
							} else {
								if (reenterPasswordInputValidatorElement != null) {
									reenterPasswordInputValidatorElement.style.display = "none";
								}
							}
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

				<h2 class="page-title">Register</h2>

				<div class="line"></div>
				<br />
				<form action="adduser" method="POST">
					<table>
						<tr>
							<td valign="top"><span class="formFieldLabel">Name: </span>
							</td>
							<td valign="top"><input type="text" id="nameInputTextBox"
								name="name" /></td>
							<td><span id="nameInputValidator" class="missingInfo"
								style="display: none;">Name is required</span></td>
						</tr>
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
							<td valign="top"><span class="formFieldLabel">Enter
									password: </span></td>
							<td valign="top"><input type="password"
								id="passwordInputTextBox" name="password" /></td>
							<td><span id="passwordInputValidator" class="missingInfo"
								style="display: none;">Password is required</span></td>
						</tr>
						<tr>
							<td valign="top"><span class="formFieldLabel">Re-enter
									password: </span></td>
							<td valign="top"><input type="password"
								id="reenterPasswordInputTextBox" name="passwordRepeat" /></td>
							<td><span id="reenterPasswordInputValidator"
								class="missingInfo" style="display: none;">Passwords do
									not match</span></td>
						</tr>
						<tr>
							<td valign="top"><span class="formFieldLabel">Age: </span></td>
							<td valign="top"><input type="text" id="ageInputTextBox"
								maxlength="3" name="age" /></td>
						</tr>
						<tr>
							<td valign="top"><span class="formFieldLabel">Sex: </span></td>
							<td valign="top"><span class="formFieldLabel"> <input
									type="radio" id"="maleRadioButton" checked title="Male"
									name="sexField" value="Male" size="20" />Male <span
									style="margin-left: 20px;"></span> <input type="radio"
									id"="femaleRadioButton" name="sexField" title="Female"
									value="Female" />Female
							</span></td>
						</tr>
						<tr>
							<td colspan="2"><br /> <br />
								<center>
									<input type="submit"
										onclick="javascript:return validateRegistrationForm();"
										value="Submit" /><span style="margin-left: 40px"></span> <input
										type="button" onclick="javascript:clearRegistrationForm();"
										value="Clear" />
								</center></td>
						</tr>
					</table>
				</form>

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