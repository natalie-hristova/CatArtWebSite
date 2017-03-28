<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="DAO.DBManager,model.User,DAO.UserDAO,java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Date JSP example</title>
</head>
<body>
	The date is:
	<% out.println(new java.util.Date()); %>
	Session timeout: All users:
	
	<ol>
	 <% for (String friend : UserDAO.getInstance().getListOfUsers()) { %>
	 <form action="../addFriend" method="post">
	 <li><%= friend %>
	 <input name="friend" value = <%= friend %>>
	 <input type="submit" value="Add to friends"></li>
	 </form>
	 
	 <% } %>
	 </ol>
	 


</body>
</html>