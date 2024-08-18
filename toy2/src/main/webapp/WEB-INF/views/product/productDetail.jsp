<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Product Detail</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/productDetail.css?ver=1">
</head>
<body>
<div>
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
</div>
<div class="product-container">
    <div class="product-image">
        <img src="http://localhost:8080/toy2_war_exploded/image/${product.main_image_url}.jpeg" alt="Product Image">
    </div>
    <div class="product-details">
        <h1>${product.p_name}</h1>
        <div class="product-pricing">
            <span class="final-price">${product.p_sale_price}원</span>
            <span class="price">${product.p_origin_price}원</span>
            <span class="discount">${product.p_discount_per}%</span>
        </div>
        <div class="color-options">
            <h2>색상</h2>
            <form id="color-form">
                <div class="radio-group">
                    <c:forEach var="productKind" items="${productkind}">
                        <div class="color-option">
                            <input type="radio"
                                   class="color-radio"
                                   id="${productKind.style_num}"
                                   name="color_code"
                                   value="${productKind.color_code}"
                                   onchange="redirectToColorPage('${productKind.color_code}')"
                                   <c:if test="${productKind.color_code == selectedColorCode}">checked</c:if> />
                            <label for="${productKind.style_num}" class="color-label">
                                    ${productKind.color_code}
                            </label>
                        </div>
                    </c:forEach>
                </div>
            </form>
        </div>
        <div class="radio-group">
            <h2>사이즈</h2>
            <br>
            <c:forEach var="sizeStock" items="${productStock}">
                <label>
                    <input type="radio" class="size-radio" name="size" value="${sizeStock.p_size}" ${sizeStock.p_stock == 0 ? 'disabled' : ''}>
                    <div class="size-radio-item ${sizeStock.p_stock == 0 ? 'disabled' : ''}">
                            ${sizeStock.p_size}
                    </div>
                </label>
            </c:forEach>
        </div>
        <div class="quantity-selector">
            <h2>수량 : </h2>
            <div class="quantity-controls">
                <button class="quantity-btn" onclick="decreaseQuantity()">-</button>
                <input type="text" id="quantity" value="1" readonly>
                <button class="quantity-btn" onclick="increaseQuantity()">+</button>
            </div>
            <p><span id="totalPrice">${product.p_sale_price} * ${quantity}</span>원</p>
        </div>
        <button type="button" class="cartbutton">장바구니 담기</button>
        <button type="button" class="buybutton">바로 구매하기</button>
    </div>
</div>

<div class="product-detail">
    <h1>상품 상세 정보</h1>
    <!-- 상품 정보 내용 -->

    <div class="review-list">
        <a href="/reviewForm" class="review-button">리뷰 등록</a>
        <h2>리뷰</h2>
        <br>
        <c:forEach var="review" items="${reviewList}">
            <div class="review-item">
                <div class="review-title">${review.title}</div>
                <div class="review-content">${review.content}</div>
                <div class="review-rating">
                    <!-- 별점 출력 -->
                    <c:forEach var="i" begin="1" end="${review.rating}">
                        <span class="star full"></span>
                    </c:forEach>
                    <!-- 반별점 출력 -->
                    <c:if test="${review.rating % 1 != 0}">
                        <span class="star half"></span>
                    </c:if>
                </div>
                <div class="review-date">${review.createdDate}</div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>



<script type="text/javascript">
    function redirectToColorPage() {
        // 선택된 라디오 버튼의 value 값을 가져옴
        const selectedColorCode = document.querySelector('input[name="color_code"]:checked').value;
        console.log("selectedColorCode = " + selectedColorCode);
        // 현재 경로에 color_code를 쿼리스트링으로 추가하여 페이지 이동
        const url = `${pageContext.request.contextPath}/product/detail?id=${product.product_id}&color_code=`;
        const result = url + selectedColorCode;
        console.log("result = " + result);
        window.location.href = result;
    }

    // 상품 가격 및 초기 수량
    const productPrice = ${product.p_sale_price};
    let quantity = parseInt(document.getElementById("quantity").value);

    // 총 가격 계산 및 업데이트 함수
    function updateTotalPrice() {
        const totalPrice = productPrice * quantity;
        document.getElementById('totalPrice').textContent = totalPrice.toLocaleString();
    }

    // 수량 증가 함수
    function increaseQuantity() {
        quantity++;
        document.getElementById("quantity").value = quantity;
        updateTotalPrice(); // 총 가격 업데이트
    }

    // 수량 감소 함수
    function decreaseQuantity() {
        if (quantity > 1) {
            quantity--;
            document.getElementById("quantity").value = quantity;
            updateTotalPrice(); // 총 가격 업데이트
        }
    }

    // 페이지 로드 시 초기 총 가격 표시
    updateTotalPrice();
</script>