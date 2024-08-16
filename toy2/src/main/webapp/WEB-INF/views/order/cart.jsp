<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Shopping Cart</title>
    <link rel="stylesheet" type="text/css" href="/css/cart.css">
    <link rel="stylesheet" type="text/css" href="/css/modal.css"> <!-- 모달 스타일 추가 -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="/js/cart.js"></script>
</head>
<body>
<div class="shopping-bag">
    <h2>SHOPPING BAG</h2>
    <div class="shopping-bag-container">
        <div class="left">
            <!-- 전체 선택, 선택 삭제 -->
            <div class="head">
                <input type="checkbox" class="cb__style1 checkAll" id="checkAll" name="checkAll" />
                <label for="checkAll">전체선택 <span class="checkboxCount">0</span>/<span id="cartListSize">${cartList.size()}</span></label>
                <button type="button" class="delete__btn soldout">품절상품 삭제</button>
                <button type="button" class="delete__btn select" style="margin-right:20px;">선택 삭제</button>
            </div>
            <hr />
            <c:choose>
                <c:when test="${empty cartList || cartList.size() == 0}">
                    <div class="empty-cart-message">
                        <p>쇼핑백에 등록된 상품이 없습니다.</p>
                    </div>
                </c:when>
                <c:otherwise>
                    <ul class="order__list">
                        <c:forEach var="item" items="${cartList}">
                            <li class="product-item">
                                <div class="chk">
                                    <input type="checkbox" class="cb__style1 product-check"
                                           data-price="${item.productDto.p_origin_price * item.cartItemDto.count}"
                                           data-count="${item.cartItemDto.count}"
                                           data-stylenum="${item.cartItemDto.style_num}"
                                           data-psize="${item.cartItemDto.p_size}"
                                           id="checkProduct${item.cartItemDto.style_num}_${item.cartItemDto.p_size}"
                                           name="checkwish" />
                                    <label for="checkProduct${item.cartItemDto.style_num}${item.cartItemDto.p_size}"></label>
                                </div>
                                <div class="photo">
                                    <img src="https://via.placeholder.com/100" alt="상품 이미지" />
                                </div>
                                <div class="info">
                                    <p class="brand">NEPA</p>
                                    <p class="name">${item.productDto.p_name}</p>
                                    <p class="color size">
                                        <span class="color">${item.productColorDto.english_color}(${item.productColorDto.color_code})</span>
                                        <span class="size"> / ${item.cartItemDto.p_size}</span>
                                    </p>
                                    <p class="qty">수량 : ${item.cartItemDto.count}</p>
                                    <p class="price">￦ ${item.productDto.p_origin_price * item.cartItemDto.count}</p>
                                </div>
                                <div class="btn-box">
                                    <a href="javascript:void(0);" class="option__btn white" onclick="openModal()">옵션변경</a>
                                    <a href="#" class="buy__btn black">바로구매</a>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="right">
            <div class="total-box">
                <dl>
                    <dt>상품수량</dt>
                    <dd><span class="selectedCount">0</span>개</dd>
                </dl>
                <dl>
                    <dt>총 주문금액</dt>
                    <dd><span class="won">￦</span>&nbsp;<span class="selectedTotalPrice">0</span></dd>
                </dl>
                <dl>
                    <dt>배송비</dt>
                    <dd><span class="won">￦</span>&nbsp;<span id="productfee">0</span></dd>
                </dl>
                <dl>
                    <dt>할인금액</dt>
                    <dd><span class="won">￦</span>&nbsp;<span id="productsale">0</span></dd>
                </dl>
                <div class="total">
                    <dl>
                        <dt>총 결제금액</dt>
                        <dd><span class="won">￦</span>&nbsp;<span class="selectedTotalPrice">0</span></dd>
                    </dl>
                </div>
                <a href="#" class="buy__btn on">
                    <span class="num">총 <span class="selectedCount">0</span>건</span>
                    <span class="price">결제하기&nbsp&nbsp|&nbsp&nbsp<i class="won">￦</i>&nbsp;<span class="selectedTotalPrice">0</span></span>
                </a>
            </div>
            <p class="info-text">장기간 보관하신 상품은 시간이 지남에 따라 가격과 혜택이 변동 될 수 있으며, 최대 30일동안 보관됩니다.</p>
        </div>
    </div>
</div>

<!-- 옵션 변경 모달창 -->
<div id="optionModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeModal()">&times;</span>
        <h2>OPTION</h2>
        <div class="option-container">
            <div class="color-selection">
                <h3>Color</h3>
                <div class="colors">
                    <img src="https://via.placeholder.com/50" alt="Black">
                    <img src="https://via.placeholder.com/50" alt="Green">
                    <img src="https://via.placeholder.com/50" alt="Yellow">
                </div>
            </div>
            <div class="size-selection">
                <h3>Size</h3>
                <select>
                    <option value="000">000</option>
                    <option value="001">001</option>
                    <option value="002">002</option>
                </select>
            </div>
            <div class="quantity-selection">
                <h3>Quantity</h3>
                <div class="quantity-box">
                    <button onclick="decreaseQuantity()">-</button>
                    <input type="text" value="1" id="quantity" readonly>
                    <button onclick="increaseQuantity()">+</button>
                </div>
            </div>
        </div>
        <button class="change-btn">변경하기</button>
    </div>
</div>

</body>
</html>