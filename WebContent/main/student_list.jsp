<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.jsp" %>
<h2></h2>
<label>入学年度</label>
<select name="f1"></select>
<label>クラス</label>
<select name="f2"></select>
<p><input type="checkbox" name="f3">在学中</p>
<a href="">新規登録</a>
<p><input type="submit" value="絞込み"></p>
<p>検索結果:</p>
<%@include file="../footer.jsp"%>