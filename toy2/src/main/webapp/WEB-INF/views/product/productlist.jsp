<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>NEPA Products</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/productlist.css?ver=1">
</head>
<body>
<div class="container">
    <div class="top-right-buttons">
        <a href="${pageContext.request.contextPath}/mypage">마이페이지</a>
        <button class="search-btn" onclick="openSearch()">검색</button>
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
    <div id="searchModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeSearch()">&times;</span>
            
            <!-- 검색 조건 선택 -->
            <select class="search-option">
                <option value="product_id">Product ID</option>
                <option value="p_name">Product Name</option>
            </select>

            <!-- 검색 입력 -->
            <input type="text" class="search-input" placeholder="검색어를 입력하세요">
            
            <!-- 검색 버튼 -->
            <button class="submit-btn" onclick="submitSearch()">검색</button>
        </div>
    </div>
    <h2>${current_category}(${total_count})</h2>
    <div class="categories">
        <c:forEach var="entry" items="${select_category}">
            <a href="${pageContext.request.contextPath}/product/list?category_id=${entry.key}&page=1&sort=2" class="category-link">${entry.value}</a>
        </c:forEach>
        <select id="sortCriteria" name="sortCriteria" onchange="updateSort()">
            <option value="1">이름순</option>
            <option value="2">인기순</option>
            <option value="3">신상품순</option>
            <option value="4">낮은가격순</option>
            <option value="5">높은가격순</option>
        </select>
    </div>
    <div class="products">
        <c:forEach var="product" items="${products}">
            <div class="product-item" onclick="location.href=`${pageContext.request.contextPath}/product/detail?id=${product.product_id}`;">
                <img src="http://localhost:8080/toy2_war_exploded/image/${product.main_image_url}.jpeg" alt="Product Image">
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

    // 검색
    function openSearch() {
        document.getElementById("searchModal").style.display = "block";
    }

    function closeSearch() {
        // 모달 닫기
        document.getElementById("searchModal").style.display = "none";

        // 검색어 초기화
        document.querySelector(".search-input").value = "";
    }

    // 모달 외부 클릭 시 모달 닫기
    window.onclick = function(event) {
        var modal = document.getElementById("searchModal");
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
</script>