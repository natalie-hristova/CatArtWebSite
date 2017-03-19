<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/PhotoNormal.css"></link>
<link rel="stylesheet" type="text/css" href="../css/PhotoFloat.css"></link>
<link rel="stylesheet" type="text/css" href="../css/Background.css"></link>
</head>
<body>


<header>
		<nav> 
			<h4>
				<ul>
					<img src="http://i.imgur.com/sAoFBWl.png"></img>
					<li><input type="text" name="search" placeholder="Search.."></li>
					<li class="specialButton"><input type="button" value= "Go!" onclick="doSearch()"></li>
					<li class="goRight"><a href="../HTML/register.html">Join us</a></li>
					<li class="goRight"><a href="../HTML/index.html">Log In</a></li>
				</ul>
			</h4>
		</nav>
	</header>
	<div class = "row">
		<h1>
			<div class = "col"><a href="../JSP/FrontPage.jsp">Welcome</a></div>
			<div class = "col"><a href="../JSP/BrowserPage.jsp">Browse</a></div>
		</h1>
	</div>
<header class = "head">
	<nav class = "border">
		<ul class = "type">
			<li><h1>Undiscovered</h1></li>
			<li class="goRight"><a href="BrowserPage.jsp"><h3 class="rightSide">Browse More ></h3></a></li>
		</ul>
	</nav>
</header>
<h6  class= "stopFloat"></h6>
<ul class ="moveToTheLeft">
	<li>
		<ul class="floatLeft">
			<li><a href="ImgPage.jsp"><img src="http://static.baubau.bg/resources/neverni-mitove-za-kotkite.jpg"></a></li>
			<li class ="moveDown"><a href="ImgPage.jsp"><img src="http://static.baubau.bg/resources/neverni-mitove-za-kotkite.jpg"></a></li>
			<li class ="moveDown"><a href="ImgPage.jsp"><img src="http://static.baubau.bg/resources/neverni-mitove-za-kotkite.jpg"></a></li>
		</ul>
	</li>
	<li class="floatLeft">
		<dir><a href="ImgPage.jsp"><img src="http://static.baubau.bg/resources/neverni-mitove-za-kotkite.jpg"></a></dir>
		<ul>
			<dir><li class= "floatLeft"><a href="ImgPage.jsp"><img src="http://i.imgur.com/062uOyq.jpg"></a></li></dir>
			<li class= "floatLeft"><a href="ImgPage.jsp"><img src="http://i.imgur.com/FQHuis1.jpg"></a></li>
		</ul>
	</li>
	<li>
		<ul class= "floatLeft">
			<li><a href="ImgPage.jsp"><img src="http://i.imgur.com/mIcObyL.jpg"></a></img></li>
			<li class ="moveDown"><a href="ImgPage.jsp"><img src="http://i.imgur.com/956JIPW.jpg"></a></img></li>
			<li class ="moveDown"><a href="ImgPage.jsp"><img src="http://i.imgur.com/PJB6lKd.jpg"></a></img></li>
		</ul>
	</li>
	<li class="floatLeft">
		<a href="ImgPage.jsp"><img src="http://i.imgur.com/jlFgGpe.jpg"></a>
	</li>
	<li>
		<ul class="floatLeft">
			<li><a href="ImgPage.jsp"><img src="http://i.imgur.com/Jvh1OQm.jpg"></a></img></li>
			<li class ="moveDown"><a href="ImgPage.jsp"><img src="http://i.imgur.com/Jvh1OQm.jpg"></a></img></li>
			<li class ="moveDown"><a href="ImgPage.jsp"><img src="http://i.imgur.com/4AiXzf8.jpg"></a></img></li>
		</ul>
	</li>
	<li class="floatLeft">
		<dir><a href="ImgPage.jsp"><img src="http://i.imgur.com/sVzpjJN.jpg"></a></dir>
		<ul>
			<dir><li class= "floatLeft"><a href="ImgPage.jsp"><img src="http://i.imgur.com/sVzpjJN.jpg"></a></li></dir>
			<li class= "floatLeft"><a href="ImgPage.jsp"><img src="http://i.imgur.com/sVzpjJN.jpg"></a></li>
		</ul>
	</li>
</ul>
<h6  class= "stopFloat"></h6>
<header class = "head">
	<nav class = "border">
		<ul class = "type">
			<li><h1>Most popular</h1></li>
			<li class="goRight"><a href="BrowserPage.jsp"><h3 class="rightSide">Browse More ></h3></a></li>
		</ul>
	</nav>
</header>
		<p class = "rowPic">
			<h1>
				<div class = "firstPic"><a href="ImgPage.jsp"><img src="http://i.imgur.com/Jvh1OQm.jpg"></a> </img></div>
				<div class = "typePic"><a href="ImgPage.jsp"><img src="http://i.imgur.com/062uOyq.jpg"></a> </img></div>
				<div class = "typePic"><a href="ImgPage.jsp"><img src="http://i.imgur.com/FQHuis1.jpg"></a> </img></div>
				<div class = "typePic"><a href="ImgPage.jsp"><img src="http://i.imgur.com/Jvh1OQm.jpg"></a> </img></div>
			</h1>
		</p>
	<footer>@made in China </footer>
	
</body>
</html>