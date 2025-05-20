<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.jsp" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>

<sql:setDataSource var="ds"
                   driver="org.h2.Driver"
                   url="jdbc:h2:file:~/scoreproject;AUTO_SERVER=TRUE"
                   user="sa" password="" />

<html>
<head>
  <meta charset="UTF-8" />
  <title>学生管理一覧 (STDM001)</title>
  <link rel="stylesheet" href="css/style.css" />
</head>
<body>
  <h2>学生管理一覧</h2>

  <!-- 新規登録 -->
  <div style="text-align: right; margin-bottom: 1em;">
    <a href="student_create">新規登録</a>
  </div>

  <!-- 絞り込みフォーム -->
  <form method="get" action="">  <!-- ← ここを修正 -->
    <label for="f1">入学年度</label>
    <select id="f1" name="f1">
      <option value="">----</option>
      <sql:query var="years" dataSource="${ds}">
        SELECT DISTINCT ENT_YEAR FROM STUDENT ORDER BY ENT_YEAR
      </sql:query>
      <c:forEach var="y" items="${years.rows}">
        <option value="${y.ent_year}"
          <c:if test="${param.f1 == y.ent_year}">selected</c:if>>
          ${y.ent_year}
        </option>
      </c:forEach>
    </select>

    <label for="f2">クラス</label>
    <select id="f2" name="f2">
      <option value="">----</option>
      <sql:query var="classes" dataSource="${ds}">
        SELECT DISTINCT CLASS_NUM FROM STUDENT ORDER BY CLASS_NUM
      </sql:query>
      <c:forEach var="cno" items="${classes.rows}">
        <option value="${cno.class_num}"
          <c:if test="${param.f2 == cno.class_num}">selected</c:if>>
          ${cno.class_num}
        </option>
      </c:forEach>
    </select>

    <input type="checkbox" id="f3" name="f3" value="t"
      <c:if test="${param.f3 == null || param.f3 == 't'}">checked</c:if> />
    <label for="f3">在学中</label>

    <button type="submit">絞り込み</button>
  </form>

  <!-- ここから検索後の結果取得＆表示 -->
  <sql:query var="students" dataSource="${ds}">
    SELECT ENT_YEAR,
           NO,
           NAME,
           CLASS_NUM,
           IS_ATTEND
      FROM STUDENT
     WHERE 1=1
       <c:if test="${not empty param.f1}">
         AND ENT_YEAR = ${param.f1}
       </c:if>
       <c:if test="${not empty param.f2}">
         AND CLASS_NUM = '${param.f2}'
       </c:if>
       <c:if test="${param.f3 == 't'}">
         AND IS_ATTEND = TRUE
       </c:if>
     ORDER BY ENT_YEAR, NO
  </sql:query>

  <!-- 検索結果件数 -->
  <div>検索結果: <c:out value="${fn:length(students.rows)}" /> 件</div>

  <!-- 結果テーブル -->
  <table border="1" cellpadding="4" cellspacing="0">
    <thead>
      <tr>
        <th>入学年度</th>
        <th>学生番号</th>
        <th>氏名</th>
        <th>クラス</th>
        <th>在学中</th>
        <th>操作</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="row" items="${students.rows}">
        <tr>
          <td><c:out value="${row.ent_year}" /></td>
          <td><c:out value="${row.no}"       /></td>
          <td><c:out value="${row.name}"     /></td>
          <td><c:out value="${row.class_num}"/></td>
          <td>
            <c:choose>
              <c:when test="${row.is_attend}">○</c:when>
              <c:otherwise>×</c:otherwise>
            </c:choose>
          </td>
          <td><a href="student_edit?no=${row.no}">変更</a></td>
        </tr>
      </c:forEach>

      <c:if test="${empty students.rows}">
        <tr>
          <td colspan="6" style="text-align:center;">
            学生情報が存在しませんでした
          </td>
        </tr>
      </c:if>
    </tbody>
  </table>
</body>
</html>

