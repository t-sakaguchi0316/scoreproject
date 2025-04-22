<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.jsp" %>
<h2>科目情報変更</h2>
<p><label>科目コード</label></p>
<p><input type="text" name="cd" value="${cd}"></p>
<p><label>科目名</label></p>
<p><input type="text" name="name" value="${name}"></p>
<a href="subject_update_done.jsp"><input type="submit" value="変更"></a>
<p><a href="subject_list.jsp">戻る</a></p>
<%@include file="../footer.jsp"%>