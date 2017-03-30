<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
     pageEncoding="ISO-8859-1"%>
     <%@page import="DAO.DBManager,model.User,DAO.UserDAO,java.util.List"%>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <html>
 <head>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <title>Friends</title>
 </head>
 <body>
 	<ol>
 	 <% for (String friend : UserDAO.getInstance().getListOfFriends((String)session.getAttribute("user"))) { %>
 	 <form name= "addfr"action="../removeFriend" method="post">
 	 <li><%= friend %>
 	 <input name="friend" value = <%= friend %>>
 	 <input type="submit" value="Remove from friends"></li>
 	 </form>
 	  <form name = "blockfr" action="../blockFriend" method="post">
 	 <input name="blocked" value = <%= friend %>>
 	 <input type="submit" value="Block friend"></li>
 	 </form>
 	 <% } %>
 	 </ol>
 </body>
 </html>