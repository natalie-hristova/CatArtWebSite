<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.HashMap" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
			<title>Insert title here</title>
			<link rel="stylesheet" type="text/css" href="../css/Background.css"></link>
			<link rel="stylesheet" type="text/css" href="../css/FrontPage.css"></link>
			<link rel="stylesheet" type="text/css" href="../css/PhotoNormal.css"></link>
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
			<div>
				<ul>
					<li class = "floatLeft">
						<h4>
							<ul>
							<li class="firstInLine">SORT</li>
							<li> <a href="BrowserPage.jsp">What's Hot</a></li>
							<li> <a href="BrowserPage.jsp">Undiscovered</a></li>
							<li> <a href="BrowserPage.jsp">Most Stars</a></li>
							<li> <a href="BrowserPage.jsp">Alphabetic</a></li>
							<li> <a href="BrowserPage.jsp">Newest</a></li>
							<li> <a class="firstInLine">SEARCH</a></li>
							<li><input type="text" name="search" placeholder=" "></li>
							<li class="firstInLine">CATEGORIES</li>
							<li> <a href="BrowserPage.jsp">Photo</a></li>
							<li> <a href="BrowserPage.jsp">Digital</a></li>
							<li> <a href="BrowserPage.jsp">Traditional</a></li>
							<li> <a href="BrowserPage.jsp">Crafts</a></li>
							<li> <a href="BrowserPage.jsp">Comics</a></li>
							<li> <a href="BrowserPage.jsp">FanArt</a></li>
							<li class="lastInLine"> <a href="BrowserPage.jsp">Scetch</a></li>
						</ul>
					</h4>
				</li>
				<li> 
  				<%-- 
  					HashMap<Integer, String> list = (HashMap<Integer, String>) request.getAttribute("/ShowImg");
  					out.print(list.isEmpty());
				--%>
				<%
					int k = 13;
						for(int i =1; i< k ; i++){
						String s = "D:/DB_IMG/";
						out.print("<div  class = \"imgfloat\"><a href=\"ImgPage.jsp\"><img src=\""+ (s+i) + ".jpg\"></img></a></div>");
					} 
				%>
					
				</li>
			</ul>
		</div>
	</body>
</html>