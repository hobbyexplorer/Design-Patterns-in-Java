
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="cache-control" content="no-cache">
<title>Contact Us</title>

<link rel="stylesheet" href="../css/style.css" />

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<script src="../js/contact-form-process.js"></script>
<script src="../js/jquery.min.js" type="text/javascript"></script>
<script src="../js/jquery-photofy-2.0.43.min.js" type="text/javascript"></script>
<script src="../js/images.js" type="text/javascript"></script>
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
<script language="javascript">
function sendit()
	{
			alert("Send it!");
			 
	}
</script> 

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

		<div id="main">
			<div id="user">	
			<%
						if (session.getAttribute("user")==null) {
					%>
					
					<a href="home"  style= "font-weight: bold">Login</a> 
					
					<%
						} else {
					%>
					
					<a style= "margin-right: 30px" >Welcome ${user.name}</a>
				
				
                    <a href="logout" style= "margin-left: 30px; font-weight: bold" >Logout</a>
								
					
					<%
								}
							%>
			</div>
			<div id="content">


				<div id="photofy"></div>
				<script type="text/javascript">
					$("#photofy").photofy({
						imageSource : images,
						maxImages : 15,
						delay : 4000
					});
				</script>

			</div>

 
			<!--end content-->



		</div>
		<!--end main-->
		<div id="sidebar" style="height:600px">



				<h2 class="page-title">Contact</h2>
				<p>Please use the form below if you&#8217;d like to get in touch
					about hiring me or if you would like to discuss anything else.</p>
				<p class="hide" id="response"></p>

	<form action='http://140.182.173.16/mail.php' method='post'onsubmit="sendit()" id="contact-form" 
    :include page="contact"/
    
    >
			 
					
						<input id="form_name" type="text" name="name" value="Your Name"
							onfocus="if(this.value=='Your Name'){this.value=''};"
							onblur="if(this.value==''){this.value='Your Name'};" /> <input
							id="form_email" type="text" name="mail" value="Email"
							onfocus="if(this.value=='Email'){this.value=''};"
							onblur="if(this.value==''){this.value='Email'};" /> <input
							id="form_subject" type="text" name="subject" value="Subject"
							onfocus="if(this.value=='Subject'){this.value=''};"
							onblur="if(this.value==''){this.value='Subject'};" 
                            /> 
                          
						<textarea id="form_message" rows="10" cols="15" name="message"></textarea>
						 <input   class="button"      type="submit" name="submit"
							value="Submit"   /> 
					
				</form>


			</div>
			<!--end sidebar-->
		<div class="line"></div>

		<div id="footer">
			<p class="copyright">Copyright � 2011/ All Rights Reserved</p>
			<p class="social">
				<a
					href="https://www.facebook.com/pages/Design-Patterns-in-Java/275169485855198">Follow
					us on Facebook �</a>
			</p>
		</div>
		<!--end footer-->

	</div>
	<!--end wrap-->
      
<% 
//设置缓存为空 
response.setHeader( "Pragma ", "No-cache "); 
response.setHeader( "Cache-Control ", "no-cache "); 
response.setDateHeader( "Expires ",   0); 
%>
</body>
</html>