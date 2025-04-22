<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.jsp" %>
<h2>学生管理</h2>
<label>入学年度</label>
<select name="f1"></select>
<label>クラス</label>
<select name="f2"></select>
<p><input type="checkbox" name="f3" value="t">在学中</p>
<a href="student_create.jsp">新規登録</a>
<input type="submit" value="絞込み">
<p>検索結果:</p>
<table>
<tr>
<th>入学年度</th>
<th>学生番号</th>
<th>氏名</th>
<th>クラス</th>
<th>在学中</th>
</tr>
<tr>
<td>2021</td>
<td>2125001</td>
<td>大原太郎</td>
<td>201</td>
<td>〇</td>
<td><a href="student_update.jsp">変更</a></td>
</tr>
</table>
<%@include file="../footer.jsp"%>