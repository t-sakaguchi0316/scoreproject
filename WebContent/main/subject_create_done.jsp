<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <meta charset="UTF-8" />
  <title>学生登録完了 (STDM003)</title>
  <link rel="stylesheet" href="css/style.css" />
</head>
<body>
<%@include file="../header.jsp" %>

  <!-- 1: 画面タイトル -->
  <h2>学生情報登録</h2>

  <!-- 2: 完了メッセージ -->
  <p>登録が完了しました</p>

  <!-- 3: 戻るリンク：学生登録画面へ -->
  <p>
    <a href="student_create">戻る</a>
    &nbsp;|&nbsp;
    <!-- 4: 学生番号一覧リンク：学生管理一覧画面へ -->
    <a href="student_list.jsp">学生番号一覧</a>
  </p>

  <!-- フッタ読み込み（必要に応じて） -->
  <%-- <jsp:include page="/WEB-INF/jsp/common/footer.jsp" /> --%>
</body>
</html>
