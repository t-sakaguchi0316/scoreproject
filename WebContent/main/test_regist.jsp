<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.jsp" %>
<h2>成績管理</h2>
<label>入学年度</label>
<p><select></select></p>

<label>クラス</label>
<p><select></select></p>

<label>科目</label>
<p><select></select></p>

<label>回数</label>
<p><select></select></p>

<input type="submit" value="検索">


<div><label>科目:</label></div>
<table>
	<tr>
	<th>入学年度</th>
	<th>クラス</th>
	<th>学生番号</th>
	<th>氏名</th>
	<th>点数</th>
	</tr>
	<tr>
	<th>2023</th>
	<td>131</td>
	<td>2325001</td>
	<td>大原一郎1</td>
	<td><input type="text" name="point_${test.student.no }" value="${test.point}"></td>
	</tr>
</table>
<a href="subject_create_done.jsp"><input type="submit" value="登録して終了"></a>
<%@include file="../footer.jsp"%>