<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.jsp" %>
<h2>学生情報変更</h2>
<label>入学年度</label>
<p><input type="text" name="ent_yaer" value="${ent_year}"></p>
<label>学生番号</label>
<p><input type="text" name="no" value="${no}"></p>
<label>氏名</label>
<p><input type="text" name="name" value="${name}"></p>
<label>クラス</label>
<p><select name="class_num"></select><p>
<label>在学中</label>
<input type="checkbox" name="is_attend">
<p><a href="student_update_done.jsp"><input type="submit" value="変更" name="login"></a></p>
<p><a href="student_list.jsp">戻る</a></p>
<%@include file="../footer.jsp"%>
