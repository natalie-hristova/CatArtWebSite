<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    
  <%
  	String name = (String)request.getAttribute("name"); 
    String date = (String)request.getAttribute("date"); 
    String genre = (String)request.getAttribute("genre"); 
    String info = (String)request.getAttribute("info"); 
    String link = (String)request.getAttribute("link"); 
    String username = (String)request.getAttribute("username");  
  %>
  
	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
			<title>Insert title here</title>
			<link rel="stylesheet" type="text/css" href="css/Background.css"></link>
			<link rel="stylesheet" type="text/css" href="css/FrontPage.css"></link>
			<link rel="stylesheet" type="text/css" href="css/buttons.css"></link>
			<link rel="stylesheet" type="text/css" href="css/Myimg.css"></link>
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
		<div class = "moveToCenter"><img src="<%=link%>"></img></div>		
		
<div>
	<ul class = "border, head">
		<li><img src = "DB_IMG/profile.jpg"></img></li>
		<li><h4 class = "color">Name: <%out.print(name); %></h4></li>
		<li><h4 class = "color">User: <% out.print(username); %></h4></li>
		<li><h4 class = "color">Info: <% out.print(info); %> </h4></li>
		<li><h4 class = "color">Genre: <% out.print(genre); %> </h4></li>
		<li><h4 class = "color">Upload date: <% out.print(date); %> </h4></li>
	</ul>	
</div>

<div>
	<ul class = "border, head">
		<li class = "background"><h4 class = "color">COMMENTS</h4></li>

	</ul>	
</div>


<div>
	<ul class = "border, head">
		<li><img src = "DB_IMG/profile.jpg"></img></li>
		<li><h4 class = "color">by  user123</h4></li>
		<li><h4 class = "color">cool </h4></li>
	</ul>	
</div>

<div>
	<ul class = "border, head">
		<li><img src = "DB_IMG/profile.jpg"></img></li>
		<li><h4 class = "color">by  user123</h4></li>
		<li><h4 class = "color"> fghj fghj tyui fghj rtyu 678 tyu fgh 
		Info of this image  gh dfg hjtyu 789 fghj rtyu fghj fghj tyui fghj rtyu 678 tyu fgh 
		Info of this image  gh dfg hjtyu 789 fghj rtyu fghj fghj tyui fghj rtyu 678 tyu fgh </h4></li>
	</ul>	
</div>

	</body>
</html>