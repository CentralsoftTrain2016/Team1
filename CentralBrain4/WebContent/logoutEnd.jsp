<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="style.css" rel="stylesheet" type="text/css" />
<title>ログアウト</title>
</head>
<body>
<center>
     <h3>ログアウトします</h3>
     <h5>「はい」を押すとログアウトが完了します</h5>


<form method="POST" action="EntranceServlet">
	<input type="submit" value ="はい"  >
</form>
<form method="POST" action="TopServlet">
<input type="submit" value ="いいえ"  >
</form>
</center>



</body>
</html>