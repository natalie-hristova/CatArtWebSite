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
							<li class="goRight"><a href="../JSP/register.jsp">Join us</a></li>
							<li class="goRight"><a href="../HTML/index.html">Log In</a></li>
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
		