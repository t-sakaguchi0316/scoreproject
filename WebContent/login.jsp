<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.jsp" %>
<form action="Login.action" method="post">

<h2>ログイン</h2>
<p>ID<input type="text" name="login"></p>
<p>パスワード<input type="password" name="password">
<p><input type="checkbox" name="chk_d_ps">パスワードを表示</p>
<p><input type="submit" name="login" value="ログイン"></p>
</form>

<%@include file="../footer.jsp"%>