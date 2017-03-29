<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import = "java.util.*" %>
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
						<form action = "../login" method ="get">
							<input class = "noMods, goRight" type="submit" value="Log out">
						</form>
						<form action = "../HTML/home.html">
							<input class = "noMods, goRight" type="submit" value="Friends">
						</form>
						<form action = "../JSP/UserPage.jsp">
							<input class = "noMods, goRight" type="submit" value="My Profile">
						</form>
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
	<% Random rn = new Random();
	int i = rn.nextInt(100);%>
		<p class = "rowPic">
			<h1>
			<form action="../ViewImg">
  				<input class = "firstPic" type="image" src="../DB_IMG/<%=i%>.jpg" alt="Submit">
				<input type="hidden" name = "imgNum" value="<%=i++%>">
			</form>
			<form action="../ViewImg">
  				<input class = "typePic" type="image"src="../DB_IMG/<%=i%>.jpg" alt="Submit">
				<input type="hidden" name = "imgNum" value="<%=i++%>">
			</form>
			<form action="../ViewImg">
  				<input class = "typePic" type="image" src="../DB_IMG/<%=i%>.jpg" alt="Submit">
				<input type="hidden" name = "imgNum" value="<%=i++%>">
			</form>
			<form action="../ViewImg">
  				<input class = "typePic" type="image" src="../DB_IMG/<%=i%>.jpg" alt="Submit">
				<input type="hidden" name = "imgNum" value="<%=i++%>">
			</form>
			</h1>
		</p>
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
				<form action="../ViewImg">
  					<input class = "firstPic" type="image" value = "<%=i%>" src="../DB_IMG/<%=i%>.jpg" alt="Submit">
					<input type="hidden" name = "imgNum" value="<%=i++%>">
				</form>
				<form action="../ViewImg">
  					<input class = "typePic" type="image" value = "<%=i%>" src="../DB_IMG/<%=i%>.jpg" alt="Submit">
					<input type="hidden" name = "imgNum" value="<%=i++%>">	
				</form>
				<form action="../ViewImg">
  					<input class = "typePic" type="image" value = "<%=i%>" src="../DB_IMG/<%=i%>.jpg" alt="Submit">
					<input type="hidden" name = "imgNum" value="<%=i++%>">
				</form>
				<form action="../ViewImg">
  					<input class = "typePic" type="image" value = "<%=i%>" nsrc="../DB_IMG/<%=i%>.jpg" alt="Submit">
					<input type="hidden" name = "imgNum" value="<%=i%>">
				</form>
			</h1>
		</p>
	<footer>@made in China </footer>
	
</body>
</html>