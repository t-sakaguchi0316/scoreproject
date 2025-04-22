<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.jsp" %>
<h2>学生情報登録</h2>
<div>
<label>入学年度</label>
<select></select>
<label>学生番号</label>
<p><input type="text" value="${no}"></p>
<label>氏名</label>
<p><input type="text" value="${name}"></p>
<label>クラス</label>
<select></select>
<p><input type="submit" value="登録して終了"></p>
<a href="student_list.jsp">戻る</a>

</div>
<%@include file="../footer.jsp"%>