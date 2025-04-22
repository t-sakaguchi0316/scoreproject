<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.jsp" %>
<h2>科目情報削除</h2>
<p><label>「subject_name(subject_cd)を削除してもよろしいでしょうか」</label></p>
<input type="submit" value="削除">
<a href="subject_list.jsp">戻る</a>
<input type="hidden" name="subject_cd" value="${subject_cd}">
<input type="hidden" name="subject_name" value="${subject_name}">
<%@include file="../footer.jsp"%>