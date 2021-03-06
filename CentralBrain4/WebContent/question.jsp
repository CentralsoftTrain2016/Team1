<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="bean" class="service.bean.QuestionBean" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK rel="stylesheet" href="style.css" type="text/css">
<title>質問ページ</title>
</head>
<body>
	<h1>質問ページ</h1>
	<br>
	<jsp:include page="tag.jsp" />
	<!--  <input type="hidden" id="questionID" value=<%=bean%>> -->
	<h4>質問内容</h4>
	<form method="POST" action="AddFavoriteServlet">
		<input type="submit" value="☆この質問をお気に入りに登録☆" class="fbtn"><br>
		<input type="hidden" name="questionID"
			value=<%=bean.getQuestion().getQUESTIONID().get()%> /> <br>
	</form>
	<br>
	<div
		style="padding: 10px; margin-bottom: 10px; border: 1px dotted #333333; background-color: #ffff99;">
		<%=bean.getQuestion().getQUESTIONDETAIL().get()%>
	</div>
	<form method="POST" action="QuestionModifyServlet">
		<input type="hidden" name="QuestionID"
			value=<%=bean.getQuestion().getQUESTIONID().get()%> /> <input
			type="hidden" name="QuestionDetail"
			value="<%=bean.getQuestion().getQUESTIONDETAIL().get()%>" />
		<div style="display: inline-flex">
			<input type="submit" value="質問を修正する" />
	</form>
	<form method="POST" action="QuestionDeleteServlet">
		<input type="hidden" name="id"
			value=<%=bean.getQuestion().getQUESTIONID().get()%> />
		<input type="submit" value="質問を削除する" />
	</form>
	</div>
	<br>
	<br>
	<h4>ベストアンサー</h4>
	<br>
	<%
		if (bean.getBestAnswer().getANSWERDETAIL() != null) {
	%>
	<div
		style="padding: 10px; margin-bottom: 10px; border: 1px dotted #333333; background-color: pink;">
		<%=bean.getBestAnswer().getANSWERDETAIL().get()%>
	</div>
	<%
		} else {
	%>
	ベストアンサーは選ばれていません。
	<%
		}
	%>
	<br>
	<h4>その他の回答とアドバイス</h4>

	<br>
	<br>
	<%
		for (domain.Answer answer : bean.getAnswers()) {
	%>
	<%
		if (answer.getANSWERDETAIL() != null) {
	%>
	<div
		style="padding: 10px; margin-bottom: 10px; border: 1px dotted #333333; background-color: #99ffff;">
		<%="[回答]"%><br>
		<%=answer.getANSWERDETAIL().get()%>
		<br> <br> [アドバイス] <br>
		<%=answer.getADVICEDETAIL().get()%>
	</div>
	<%
		} else {
	%>
	<div
		style="padding: 10px; margin-bottom: 10px; border: 1px dotted #333333; background-color: #ccffff;">
		[アドバイス] <br>
		<%=answer.getADVICEDETAIL().get()%>
	</div>
	<%
		}
	%>
	<form method="POST" action="GoodServlet">
		<input type=hidden name="questionID"
			value=<%=bean.getQuestion().getQUESTIONID().get()%>> <input
			type="hidden" name="answerID" value=<%=answer.getANSWERID().get()%>>
		<input type="submit" value="いいね！" class="goodbtn" /> <br>
		<br>
		<br>
	</form>
	<%
		}
	%>

	<form method="POST" action="AnswerServlet">
		<input type=hidden name="questionID"
			value=<%=bean.getQuestion().getQUESTIONID().get()%>> <input
			type="submit" value="回答する" />
	</form>


</body>
</html>