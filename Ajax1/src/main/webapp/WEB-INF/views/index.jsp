<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/reset.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/fontawesome-free-5.10.1-web/css/all.min.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/admin.css" />
  <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
  <script src="${pageContext.request.contextPath}/resources/admin.js"></script>
</head>
<body>
  <header class="top_nav">
    <a href="index.html"><i class="fas fa-user-friends"></i> 회원관리</a>
    <a href=""><i class="fas fa-clipboard-list"></i> 게시판관리</a>
    <a href="gallery.html"><i class="fab fa-dochub"></i> 갤러리관리</a>
  </header>
  <div class="content">
    <div class="button_section">
      <a class="new_row">새로 생성하기</a>
    </div>
    <div class="search_section">
      <input type="text" /><button>검색</button>
    </div>
    <div class="table_section">
      <table>
        <colgroup>
          <col style="width: 10%;"/>
          <col />
          <col />
          <col style="width: 20%;" />
        </colgroup>
        <thead>
          <tr>
            <th>no</th><th>이름</th><th>주소</th><th></th>
          </tr>
        </thead>
        <tbody>
        </tbody>
      </table>
    </div>
  </div>

</body>
</html>
