<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.jsp" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Calendar,java.util.List,java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%
    // 今年を基準に前後10年の List を作成してリクエスト属性にセット
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    List<Integer> yearList = new ArrayList<>();
    for (int y = currentYear - 10; y <= currentYear + 10; y++) {
        yearList.add(y);
    }
    request.setAttribute("yearList", yearList);
    // classList は Action でセット済みとする。直接 JSP で生成したい場合は同様にリストを作る
%>

<html>
<head>
  <meta charset="UTF-8" />
  <title>学生登録 (STDM002)</title>
  <link rel="stylesheet" href="css/style.css" />
</head>
<body>
  <!-- 1. 画面タイトル -->
  <h2>学生情報登録</h2>

  <form action="StudentCreate.action" method="post">
    <!-- 2. 入学年度ラベル -->
    <label for="ent_year">入学年度</label>
    <!-- 3. 入学年度セレクトボックス name="ent_year" -->
    <select id="ent_year" name="ent_year" required>
      <option value="">----</option>
      <c:forEach var="year" items="${yearList}">
        <option value="${year}"
          <c:if test="${param.ent_year == year}">selected</c:if>>
          ${year}年
        </option>
      </c:forEach>
    </select>
    <br/>

    <!-- 4. 学生番号ラベル -->
    <label for="no">学生番号</label>
    <!-- 5. 学生番号入力テキスト -->
    <input type="text"
           id="no"
           name="no"
           value="${param.no}"
           placeholder="学生番号を入力してください"
           maxlength="10"
           required />
    <br/>

    <!-- 6. 氏名ラベル -->
    <label for="name">氏名</label>
    <!-- 7. 氏名入力テキスト -->
    <input type="text"
           id="name"
           name="name"
           value="${param.name}"
           placeholder="氏名を入力してください"
           maxlength="30"
           required />
    <br/>

    <!-- 8. クラスラベル -->
    <label for="class_num">クラス</label>
    <!-- 9. クラスセレクトボックス name="class_num" -->
    <select id="class_num" name="class_num" required>
      <option value="">----</option>
      <c:forEach var="cls" items="${classList}">
        <option value="${cls}"
          <c:if test="${param.class_num == cls}">selected</c:if>>
          ${cls}
        </option>
      </c:forEach>
    </select>
    <br/><br/>

    <!-- 10. 登録して終了ボタン name="end" -->
    <button type="submit" name="end">登録して終了</button>
  </form>

  <!-- 11. 戻るリンク -->
  <p>
    <a href="StudentCreate.action">戻る</a>
    &nbsp;|&nbsp;
    <a href="student_list.jsp">学生管理一覧へ</a>
  </p>
</body>
</html>

<%@include file="../footer.jsp"%>