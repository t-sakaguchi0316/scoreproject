<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
<meta charset="UTF-8">
<title>得点管理システム</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css'/>"></head>
<body class="bg-light">

<div class="header-wrapper">
    <h1>得点管理システム</h1>
    <a href="logout.jsp">ログアウト</a>
</div>

<div class="main-container">
    <div class="menu-wrapper">
        <%@ include file="../sidebar.jsp" %>
	</div>