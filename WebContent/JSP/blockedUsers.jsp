<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@page import="DAO.DBManager,model.User,DAO.UserDAO,java.util.List"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Blocked users</title>
</head>
<body>
<ol>
	 <% for (String blocked : UserDAO.getInstance().getListOfBlocked((String)session.getAttribute("user"))) { %>
	 <form name= "unblockfr" action="../unblockUser" method="post">
	 <li><%= blocked %>
	 <input name="blocked" value = <%= blocked %>>
	 <input type="submit" value="UnBlock user"></li>
	 </form>
	 <% } %>
	 </ol>
</body>
</html>