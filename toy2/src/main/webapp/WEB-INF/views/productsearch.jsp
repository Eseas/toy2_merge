<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>NEPA Products</title>
    <link rel="stylesheet" href="<c:url value='/resources/static/css/productsearch.css?ver=1' />">
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
    <h2>${keyword}(${total_count})</h2>
    <div class="products">
        <c:forEach var="product" items="${products}">
            <div class="product-item" onclick="location.href=`${pageContext.request.contextPath}/product/detail?id=${product.product_id}`;">
                <img src="<c:url value='/resources/static/image/${product.main_image_url}.jpeg' />" alt="Product Image">
                <h3>${product.p_name}</h3>
                <p>가격: ${product.p_sale_price}원</p>
                <p>${product.p_brief_text}</p>
                <div class="star-ratings">
                    <div class="star-ratings-fill" style="width: calc(${product.p_average_grade} / 5 * 100%)">
                        <span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
                    </div>
                    <div class="star-ratings-base">
                        <span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
                    </div>
                    <div class="rating-text">
                            ${product.p_average_grade} / 5
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <!-- 페이지 네비게이션 -->
    <div class="pagination">
        <c:if test="${page != 1}">
            <a href="${pageContext.request.contextPath}/product/list?category_id=${category_id}&page=${prevPage}&sort=${sort}"
   		    	class="page-link"
		    >이전</a>
        </c:if>
        <c:forEach var="i" begin="1" end="${totalPages}">
            <a href="${pageContext.request.contextPath}/product/list?page=${i}" class="page-link <c:if test='${i == currentPage}'>active</c:if>'">${i}</a>
        </c:forEach>
        <c:if test="${page != maxPage}">
            <a href="${pageContext.request.contextPath}/product/list?category_id=${category_id}&page=${nextPage}&sort=${sort}"
               class="page-link"
            >다음</a>
        </c:if>
    </div>
</div>
</body>
</html>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        const urlParams = new URLSearchParams(window.location.search);
        const sortCriteria = urlParams.get('sort') || 'date'; // 기본값을 'date'로 설정

        const sortSelect = document.getElementById('sortCriteria');
        sortSelect.value = sortCriteria; // 현재 쿼리 파라미터에 맞는 값을 선택

        sortSelect.addEventListener('change', function() {
            updateSort();
        });
    });

    function updateSort() {
        const currentUrl = new URL(window.location.href);
        const sortCriteria = document.getElementById('sortCriteria').value;

        currentUrl.searchParams.set('sort', sortCriteria);

        window.location.href = currentUrl.toString();
    }
</script>