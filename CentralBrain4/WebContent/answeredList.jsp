<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
 <jsp:useBean id="bean" class="service.bean.AnsweredListBean" scope="request" />
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<LINK rel="stylesheet" href="style.css" type="text/css">
	<title>回答済質問一覧</title>
	<jsp:include page="pageSplit.jsp" />
	  <script type="text/javascript">
        <!--
        function mysubmit(questionID){
        var f = document.forms['questiondisp'];
        document.getElementById('questionID').value=questionID;
        f.submit();
        return true;
        }
        -->
        </script>
</head>
<body>
	<h1>回答済質問一覧</h1>
	<jsp:include page="tag.jsp" /><br>
<form method="Get" action="QuestionServlet" name="questiondisp">
<input type="hidden" id="questionID" name="questionID">
	<table>
		<tr><th>質問</th></tr>
			<% for( domain.Question question : bean.getAnswered() ){ %>
				<tr>
					<td><input type="button" value ="*" class="qbtn" onclick="return mysubmit('<%=question.getQUESTIONID().get() %>');"></td>
					<td><%= question.getCutQUESTIONDETAIL() %></td>
				</tr>
			<% } %>
	</table>
</form>
	<span id="prev">＜</span>　<span id="page"></span>　<span id="next">＞</span><br>

</body>
</html>