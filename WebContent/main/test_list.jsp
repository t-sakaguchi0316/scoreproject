<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.jsp" %>
<input type="hidden" name="f" value="sj">
<h2>成績参照</h2>
<jsp:include page="test_list_search.jsp"></jsp:include>
<p><label>科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</label></p>
<%@include file="../footer.jsp"%>