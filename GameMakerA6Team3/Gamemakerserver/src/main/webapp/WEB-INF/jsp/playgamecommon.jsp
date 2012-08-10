<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
<meta charset="utf-8">
<title>Play Game</title>

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


<script type="text/javascript">
function hidead()
{document.getElementById("ad").style.display="none";}
</script>
<div id="ad" style="position:absolute">


<table width="300" height="200" border="0" cellpadding="0" cellspacing="0">

              <tr>

                            <td width="300" height="200">
                            <script language=JavaScript> 
                            var bannerAD=new Array(); //image path
                            var adNum=0;       
                            bannerAD[0]="../images/slide-1.png";
                            bannerAD[1]="../images/slide.jpg";
                            bannerAD[2]="../images/slide-2.jpg";
                                                       
                            function setTransition(){

                                          if (document.all){

                                          bannerADrotator.filters.revealTrans.Transition=Math.floor(Math.random()*23);//set change pic function
                                                        bannerADrotator.filters.revealTrans.apply(); //call change pic function

                                          }

                            }           

                            function playTransition(){

                                          if (document.all)
                                                        bannerADrotator.filters.revealTrans.play() //play pic

                            }           

                            function nextAd(){
                            	
                                          if(adNum<bannerAD.length-1)adNum++;
                                          else adNum=0;
                                          setTransition();
                                          document.images.bannerADrotator.src=bannerAD[adNum];
                                          playTransition();
                                          theTimer=setTimeout("nextAd()", 2000); //2s change one pic
                            }                         

                            </script>                           
                           <img style="FILTER: revealTrans(duration=2,transition=20)" height=200 src="" width=300 border=0 name=bannerADrotator>
                            <SCRIPT language=JavaScript>nextAd()</SCRIPT>
                            </td>
              </tr>
</table>




<DIV onclick="hidead();" style="FONT-SIZE: 12pt; CURSOR: hand; COLOR: black " align=right>close</DIV></div>
<script>
var x = 50,y = 60
var xin = true, yin = true
var step = 0.3
var delay = 0.3
var obj=document.getElementById("ad")
function floatAD() {
var L=T=0
var R= document.body.clientWidth-obj.offsetWidth
var B = document.body.clientHeight-obj.offsetHeight
obj.style.left = x + document.body.scrollLeft
obj.style.top = y + document.body.scrollTop
x = x + step*(xin?1:-1)
if (x < L) { xin = true; x = L}
if (x > R){ xin = false; x = R}
y = y + step*(yin?1:-1)
if (y < T) { yin = true; y = T }
if (y > B) { yin = false; y = B }
}
var itl= setInterval("floatAD()", delay)
obj.onmouseover=function(){clearInterval(itl)}
obj.onmouseout=function(){itl=setInterval("floatAD()", delay)}
</script> 





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
			function loadPlayGameValues(arg0, arg1, arg2, arg3) {
				var userIdInputTextBoxElement = document
						.getElementById('userIdInputTextBox');
				var gameIdInputTextBoxElement = document
						.getElementById('gameIdInputTextBox');
				var versionInputTextBoxElement = document
						.getElementById('versionInputTextBox');
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

				if (shouldSubmit) {
					if (submitButtonElement != null) {
						submitButtonElement.click();
						document.getElementById('listofallgames').innerHTML = "<h3><a>Loading "
								+ arg3 + "...</a></h3>";
					}
				}
			}
		</script>

		<div style="display: none;">
			<form action="buildonplaygame" method="POST">
				<input type="text" id="userIdInputTextBox" name="userId" /> <input
					type="text" id="gameIdInputTextBox" name="gameId" /> <input
					type="text" id="versionInputTextBox" name="version" /> <input
					type="submit" id="submitButton">
				</button>
			</form>
		</div>
		
		<div id="main">
			<div id="user" >	
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
				<h4>Please login to play games built by you!</h4>
			</div>
			<div id="content">
				<h2 class="page-title">Newly built games</h2>

				<div class="post" id="listofallgames">

					<c:forEach var="oneGame" end="2" items="${allGames}">

						<a style="cursor:pointer;"><img width="200" height="200"
							src="../images/gameicon.jpg" class="post-image" alt="[alt text]"
							title="[title text]" /></a>
						<h3>
							<a style="cursor:pointer;"
								onclick="javascript:loadPlayGameValues('${oneGame.userId}', '${oneGame.gameId}', '${oneGame.publishedVersion}','${oneGame.name}');">
								${oneGame.name} </a>
						</h3>
						<p>
							This game is build by User <a href="contact">${oneGame.user.name}</a>. Rated: ${oneGame.averageRating} 
							 <br> <a style="cursor:pointer;"
								onclick="javascript:loadPlayGameValues('${oneGame.userId}', '${oneGame.gameId}', '${oneGame.publishedVersion}','${oneGame.name}');">
								Play&raquo; </a>
						</p>
						<br>
					</c:forEach>

				</div>
				<!--end post-->
				
				<div class="post" id="remaininggames">
					<h2>
						<a style="cursor:pointer;" onClick="javascript:loadMoreGames();">...Load more
							Games</a>
					</h2>
				</div>
				<script type="text/javascript">
					function loadMoreGames() {
						
						var remainingGamesHTML = "";
						<c:forEach var="oneGame" items="${allGames}">
						var userId = 12;//'${oneGame.userId}';
						var gameId = 13;//'${oneGame.gameId}';
						var pubVersion = 14;//'${oneGame.publishedVersion}';
						var gameName = "Frogger";//'${oneGame.name}';

						remainingGamesHTML += '<a href="#"><img width="200" height="200" src="../images/gameicon.jpg" class="post-image"  /></a>';
						remainingGamesHTML += '<h3>';
						remainingGamesHTML += '<a style="cursor:pointer;" ';
						remainingGamesHTML += 'onclick="javascript:loadPlayGameValues(\'${oneGame.userId}\', \'${oneGame.gameId}\', \'${oneGame.publishedVersion}\',\'${oneGame.name}\');">';
						remainingGamesHTML += '${oneGame.name}' + ' </a>';
						remainingGamesHTML += '</h3> <p> This game is build by User <a href="contact"> ' + '${oneGame.user.name}' + ' </a> on 13th November 2011 <br> <a href="#"';
						remainingGamesHTML += ' onclick="javascript:loadPlayGameValues(\'${oneGame.userId}\', \'${oneGame.gameId}\', \'${oneGame.publishedVersion}\',\'${oneGame.name}\');">';
						remainingGamesHTML += 'Play&raquo; </a>';
						remainingGamesHTML += '</p> <br>';

						</c:forEach>
						document.getElementById("remaininggames").innerHTML = " ";
						document.getElementById("listofallgames").innerHTML = "";
						document.getElementById("listofallgames").innerHTML = remainingGamesHTML;
						
					}
				</script>


			</div>
			<!--end content-->
		</div>
		<!--end main-->
		<div id="sidebar" style="height:520px">
				
				<h3 class="sidebar-title">Help</h3>
				<p>Hit play on your desired game, Click the download link which
					will download a jnlp file to your computer. Open the jnlp file to
					start playing. Get high scores to get on top of our score board!</p>
				 <b> Share your scores on our :</b> <a href="https://www.facebook.com/pages/Design-Patterns-in-Java/275169485855198"> <h3>Facebook page &#187 </h3> </a>
				<br>  <br> <b>Requirements:</b>
				<p>
					Java run time environment(JRE) 1.5 or higher<br> <a
						href="http://www.oracle.com/technetwork/java/javase/downloads/index.html">Download
						JRE here</a>
				</p>


			</div>
			<!--end sidebar-->

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