<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@page import="DAO.DBManager,model.User,DAO.UserDAO,java.util.List"%>
     <%@ page errorPage="error.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Blocked users</title>
</head>
<body bgcolor="lightblue">
<h1>Blocked users:</h1>
<table border = "1">
	<tr>
	<td>User</td>
	<td>Unblock</td>
	</tr>

	 <%if (UserDAO.getInstance().getListOfBlocked((String)session.getAttribute("user")).size()>0){
		 for (String blocked : UserDAO.getInstance().getListOfBlocked((String)session.getAttribute("user"))) { %>
			 <tr>
	<form name="blockedUser" action="../unblockUser" method="post">
			 <td><%= blocked %></td>
			 <td><input type= "hidden" name="blocked" value = <%= blocked %>>
			 <input type="submit" value="UnBlock user"></td>
			 </form></tr>
			 
		 <%  } %></table>
	 <% }else{ %>
	 <h1>NO Blocked users,<%= session.getAttribute("user") %> </h1>
	 <%} %>
	
	  <a href="../HTML/home.html"><button>Back</button></a>
</body>
</html>