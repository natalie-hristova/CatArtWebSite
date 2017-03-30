<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="../css/Background.css"></link>
	<link rel="stylesheet" type="text/css" href="../css/FrontPage.css"></link>
	<link rel="stylesheet" type="text/css" href="../css/PhotoNormal.css"></link>
	<link rel="stylesheet" type="text/css" href="../css/buttons.css"></link>
	<link rel="stylesheet" type="text/css" href="../css/register.css"></link>
</head>
	<dody>
		<jsp:include page="BackgroundLoged.jsp" />

<FORM ENCTYPE="multipart/form-data" ACTION="../UploadImgServlet" METHOD=POST>
	
	
			<div class = "moveToCenter">	
		<h3 class = "separate">Upload image:</h3>
	<div>
		<h4 class = "separate">Name your art:</h4> 
		<input class = "move, styleImput" type="text" placeholder="enter name" name="name" required="required"> 
	</div>
	<div>
		<h4 class = "separate">Tell us about your art:</h4> 
		<input class = "move, styleImput" type="text" placeholder="enter about" name="about" > 
	</div>
	<div>
		<h4 class = "separate">Genre:</h4> 
		<select class = "move, styleImput" name="genre"> 
			<option value="PHOTO">Photo</option>
			<option value="DIGITAL">Digital</option>
			<option value="TRADITIONAL">Traditional</option>
			<option value="CRAFTS">Crafts</option>
			<option value="COMICS">Comics</option>
			<option value="FANART">Fanart</option>
			<option value="SCETCH">Scetch</option>
		</select>
	</div>
	
      <b>Upload your file:</b>
      <INPUT NAME="file" TYPE="file">
      <input type="submit" value="Send File">
</div>
</FORM>

</body>
</html>