<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>NEPA</title>
    <link rel="stylesheet" href="<c:url value='/resources/static/css/index.css?ver=7' />">
</head>
<body>
<div class="container">
    <div class="top-right-buttons">
        <a href="${pageContext.request.contextPath}/mypage">마이페이지</a>
        <c:choose>
            <c:when test="${not empty sessionScope.user}">
                <a href="${pageContext.request.contextPath}/logout">로그아웃</a>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/login">로그인</a>
            </c:otherwise>
        </c:choose>
        <a href="${pageContext.request.contextPath}/cart">장바구니</a>
    </div>

    <div class="wrapper">
        <div class="home" onclick="location.href='${pageContext.request.contextPath}'">NEPA</div>
    </div>

    <div class="menu">
        <h1 class="mainMessage">Category</h1>
    </div>

    <div class="product-list">
        <h1 onclick="location.href='${pageContext.request.contextPath}/product/list?category_id=1&page=1&sort=2'">신발</h1>
        <div class="category">
            <h2 onclick="location.href='${pageContext.request.contextPath}/product/list?category_id=3&page=1&sort=2'">MEN</h2>
            <ul>
                <li onclick="location.href='${pageContext.request.contextPath}/product/list?category_id=5&page=1&sort=2'">트레킹</li>
                <li onclick="location.href='${pageContext.request.contextPath}/product/list?category_id=6&page=1&sort=2'">하이킹</li>
                <li onclick="location.href='${pageContext.request.contextPath}/product/list?category_id=7&page=1&sort=2'">워킹</li>
                <li onclick="location.href='${pageContext.request.contextPath}/product/list?category_id=8&page=1&sort=2'">샌들/슬라이드</li>
            </ul>
        </div>
        <div class="category">
            <h2 onclick="location.href='${pageContext.request.contextPath}/product/list?category_id=4&page=1&sort=2'">WOMEN</h2>
            <ul>
                <li onclick="location.href='${pageContext.request.contextPath}/product/list?category_id=9&page=1&sort=2'">트레킹</li>
                <li onclick="location.href='${pageContext.request.contextPath}/product/list?category_id=10&page=1&sort=2'">하이킹</li>
                <li onclick="location.href='${pageContext.request.contextPath}/product/list?category_id=11&page=1&sort=2'">워킹</li>
                <li onclick="location.href='${pageContext.request.contextPath}/product/list?category_id=12&page=1&sort=2'">샌들/슬라이드</li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>