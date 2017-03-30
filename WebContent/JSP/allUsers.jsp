<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="DAO.DBManager,model.User,DAO.UserDAO,java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	 <%@ page errorPage="error.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Date JSP example</title>
</head>
<body bgcolor="lightblue">
	<h1>All users:</h1>
	
	<table border = "1">
	<tr>
	<td>User</td>
	<td>Add friend</td>
	</tr>
	 <% for (String friend : UserDAO.getInstance().getAllNotBlockedOrFriends((String)session.getAttribute("user"))) { %>
	<tr> <form action="../addFriend" method="post">
	 <td><%= friend %></td>
	 <td><input name="friend" type= "hidden" value = <%= friend %>>
	 <input type="submit" value="Add to friends"></td>
	 </form></tr>
	 <% } %>
	 </table>
	  <a href="../HTML/home.html"><button>Back</button></a>


</body>
</html>