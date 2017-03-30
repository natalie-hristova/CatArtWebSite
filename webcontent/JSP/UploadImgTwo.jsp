<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="css/Background.css"></link>
	<link rel="stylesheet" type="text/css" href="css/FrontPage.css"></link>
	<link rel="stylesheet" type="text/css" href="css/PhotoNormal.css"></link>
	<link rel="stylesheet" type="text/css" href="css/buttons.css"></link>
	<link rel="stylesheet" type="text/css" href="css/register.css"></link>
</head>
<body>
	<header>
			<nav> 
					<h4>
						<ul>
							<img src="http://i.imgur.com/sAoFBWl.png"></img>
							<form action = "login" method ="get">
								<input class = "noMods, goRight" type="submit" value="Log out">
							</form>
							<form action = "uploadPage" method ="post">
								<input class = "noMods, goRight" type="submit" value="Upload image">
							</form>
							<form action = "HTML/home.html">
								<input class = "noMods, goRight" type="submit" value="Friends">
							</form>
							<form action = "JSP/UserPage.jsp">
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
					<form  action="welcome" method="get">
						<input class = "noMods" type="submit" value="Welcome">
					</form>
				</div>
				<div class = "col">
					<form  action="browse" method="get">
						<input class = "noMods" type="submit" value="Browse">
					</form>
				</div>
			</h1>
		</div>
	<div class = "moveToCenter">
		<FORM enctype="multipart/form-data" action="UploadImgServlet" method=POST>
		  		<b><h1> &nbsp &nbsp &nbsp &nbspChoose your image:</h1></b>
		  		<INPUT class = "move, styleImput" NAME="file" TYPE="file">
		   		</br>
		   		<input class = "move, styleImput" type="submit" value="Submit">
			</div>
		</FORM>
	</div>
		
	</body>
</html>