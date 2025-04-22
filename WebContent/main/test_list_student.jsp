<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.jsp" %>
<h2>成績参照(学生)</h2>
<jsp:include page="test_list_search.jsp"></jsp:include>
<div><label>氏名:</label></div>
<table>
<tr>
<th>科目名</th>
<th>科目コード</th>
<th>回数</th>
<th>点数</th>
</tr>
<tr>
<td>国語</td>
<td>A02</td>
<td>1</td>
<td>98</td>
</tr>
</table>
<%@include file="../footer.jsp"%>