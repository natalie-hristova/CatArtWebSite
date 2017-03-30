<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="DAO.DBManager,model.User,DAO.UserDAO,java.util.List"%>
     <%@ page errorPage="error.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Friends:</title>
</head>
<body bgcolor="lightblue">
<h1>Your friends</h1>
<table border = "1">
	<tr>
	<td>User</td>
	<td>Remove</td>
	<td>Block</td>
	</tr>
	
	 <% for (String friend : UserDAO.getInstance().getListOfFriends((String)session.getAttribute("user"))) { %>
	 <tr>
	 <form name= "removefr" action="../removeFriend" method="post">
	 <td><%= friend %></td>
	 <td>
	 <input name="remove" type= "hidden" value = <%= friend %>>
	 <input type="submit" value="Remove from friends">
	 </form>
	 </td>
	 <td><form action="../blockFriend" method="post">
	 <input name="blocked" type= "hidden" value = <%= friend %>>
	 <input type="submit" value="Block friend">
	 </form>
	 </td>
	 </tr>
	 <% } %>
	 </tr>
	 </table>
	 <a href="../HTML/home.html"><button>Back</button></a>
</body>
</html>