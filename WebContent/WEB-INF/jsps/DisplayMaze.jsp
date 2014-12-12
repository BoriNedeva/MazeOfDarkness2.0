
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="images/jpg; charset=ISO-8859-1">

<title>Insert title here</title>
</head>
<body>
<%-- <%=request.getAttribute("show") %>
 --%>
 <%=session.getAttribute("show") %>
		<table>
		<tr>
			<td>
				<form method="get" action="left" > 
					<input type="submit" value="left" name="right">	
				</form>
			</td>
			<td>
				<form method="get" action="right" >
					 <input type="submit" value="right" name="right">
				</form>
			</td>
			<td>
				<form method="get" action="up" >
					 <input type="submit" value="up" name="right">
				</form>
			</td>
			<td>	
				<form method="get" action="down" >
					 <input type="submit" value="down" name="right">	
				</form>
			</td>
		</tr>
		</table>

</body>
</html>