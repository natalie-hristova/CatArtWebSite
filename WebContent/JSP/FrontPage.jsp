<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/PhotoNormal.css"></link>
<link rel="stylesheet" type="text/css" href="../css/PhotoFloat.css"></link>
<link rel="stylesheet" type="text/css" href="../css/Background.css"></link>
<link rel="stylesheet" type="text/css" href="../css/buttons.css"></link>
</head>
<body>


<header>
		<nav> 
			<h4>
				<ul>
					<img src="http://i.imgur.com/sAoFBWl.png"></img>
					<li class="goRight"><a href="../HTML/register.html">Join us</a></li>
					<li class="goRight"><a href="../HTML/index.html">Log In</a></li>
					</br>
					</br>
					</br>
				</ul>
			</h4>
		</nav>
	</header>
		<div class = "row">
			<h1>
				<div class = "col">
					<form  action="../welcome" method="get">
						<input class = "noMods" type="submit" value="Welcome">
					</form>
				</div>
				<div class = "col">
					<form  action="../browse" method="get">
						<input class = "noMods" type="submit" value="Browse">
					</form>
				</div>
			</h1>
		</div>
<header class = "head">
	<nav class = "border">
		<ul class = "type">
			<li><h1>Undiscovered</h1></li>
			<li class="goRight">				
				<form  action="../browse" method="get">
					<input class = "noMod" type="submit" value="Browse more > ">
				</form>
			</li>
		</ul>
	</nav>
</header>
				<% Random rn = new Random();%>
<h6  class= "stopFloat"></h6>
<ul class ="moveToTheLeft">
	<li>
		<ul class="floatLeft">
			<li><a><img src="../DB_IMG/<%=rn.nextInt(100)%>.jpg"></a></li>
			<li class ="moveDown"><a><img src="../DB_IMG/<%=rn.nextInt(100)%>.jpg"></a></li>
			<li class ="moveDown"><a><img src="../DB_IMG/<%=rn.nextInt(100)%>.jpg"></a></li>
		</ul>
	</li>
	<li class="floatLeft">
		<dir><a><img src="../DB_IMG/<%=rn.nextInt(100)%>.jpg"></a></dir>
		<ul>
			<dir><li class= "floatLeft"><a><img src="../DB_IMG/<%=rn.nextInt(100)%>.jpg"></a></li></dir>
			<li class= "floatLeft"><a><img src="../DB_IMG/<%=rn.nextInt(100)%>.jpg"></a></li>
		</ul>
	</li>
	<li>
		<ul class= "floatLeft">
			<li><a><img src="../DB_IMG/<%=rn.nextInt(100)%>.jpg"></a></img></li>
			<li class ="moveDown"><a><img src="../DB_IMG/<%=rn.nextInt(100)%>.jpg"></a></img></li>
			<li class ="moveDown"><a><img src="../DB_IMG/<%=rn.nextInt(100)%>.jpg"></a></img></li>
		</ul>
	</li>
	<li class="floatLeft">
		<a><img src="../DB_IMG/<%=rn.nextInt(100)%>.jpg"></a>
	</li>
	<li>
		<ul class="floatLeft">
			<li><a><img src="../DB_IMG/<%=rn.nextInt(100)%>.jpg"></a></img></li>
			<li class ="moveDown"><a><img src="../DB_IMG/<%=rn.nextInt(100)%>.jpg"></a></img></li>
			<li class ="moveDown"><a><img src="../DB_IMG/<%=rn.nextInt(100)%>.jpg"></a></img></li>
		</ul>
	</li>
	<li class="floatLeft">
		<dir><a><img src="../DB_IMG/<%=rn.nextInt(100)%>.jpg"></a></dir>
		<ul>
			<dir><li class= "floatLeft"><a><img src="../DB_IMG/<%=rn.nextInt(100)%>.jpg"></a></li></dir>
			<li class= "floatLeft"><a><img src="../DB_IMG/<%=rn.nextInt(100)%>.jpg"></a></li>
		</ul>
	</li>
</ul>
<h6  class= "stopFloat"></h6>
<header class = "head">
	<nav class = "border">
		<ul class = "type">
			<li><h1>Most popular</h1></li>
				<li class="goRight">
					<form  action="../browse" method="get">
						<input class = "noMod" type="submit" value="Browse more > ">
					</form>
				</li>
		</ul>
	</nav>
</header>
		<p class = "rowPic">
			<h1>
				<div class = "firstPic"><a><img src="../DB_IMG/<%=rn.nextInt(100)%>.jpg"></a> </img></div>
				<div class = "typePic"><a><img src="../DB_IMG/<%=rn.nextInt(100)%>.jpg"></a> </img></div>
				<div class = "typePic"><a><img src="../DB_IMG/<%=rn.nextInt(100)%>.jpg"></a> </img></div>
				<div class = "typePic"><a><img src="../DB_IMG/<%=rn.nextInt(100)%>.jpg"></a> </img></div>
			</h1>
		</p>
	<footer>@made in China </footer>
	
</body>
</html>