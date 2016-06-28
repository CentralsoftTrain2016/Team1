<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="message" class="java.lang.String" scope="request" />
<jsp:useBean id="userNamemessage" class="java.lang.String"
	scope="request" />
<jsp:useBean id="passwordmessage" class="java.lang.String"
	scope="request" />
<jsp:useBean id="bean" class="service.bean.Bean" scope="request" />
<html>
<head>
<link href="style.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>社員新規登録</title>
</head>
<body>
	<center>
		<h1>登録確認</h1>
			ユーザーID：<%= bean.getUserID() %><br><br><br>
			パスワード： <%= bean.getPassword() %><br><br><br>
			名前： <%= bean.getUserName() %><br><br><br>
			誕生日： <%= bean.getBirthDay() %><br><br><br>
			新入社員：<%= bean.getUserType() %><br><br><br>
		<form action="login.jsp"><br>
			<input type="submit" value="ログイン画面へ">
		</form>
		<br> <br> <br>
	</center>
</body>
</html>