<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.jsp" %>
<h2>成績参照(科目)</h2>
<jsp:include page="test_list_search.jsp"></jsp:include>
<div><label>科目:</label></div>
<table>
<tr>
<th>入学年度</th>
<th>クラス</th>
<th>学生番号</th>
<th>氏名</th>
<th>１回</th>
<th>２回</th>
</tr>
<tr>
<td>2021</td>
<td>201</td>
<td>2125001</td>
<td>大原 一郎</td>
<td>10</td>
<td>-</td>
</tr>
</table>
<%@include file="../footer.jsp"%>