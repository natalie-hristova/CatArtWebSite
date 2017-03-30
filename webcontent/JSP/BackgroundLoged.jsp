<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
	<header>
			<nav> 
					<h4>
						<ul>
							<img src="http://i.imgur.com/sAoFBWl.png"></img>
							<form action = "../login" method ="get">
								<input class = "noMods, goRight" type="submit" value="Log out">
							</form>
							<form action = "../uploadPage" method ="post">
								<input class = "noMods, goRight" type="submit" value="Upload image">
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
	</body>
</html>