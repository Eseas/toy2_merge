<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Product Review</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/productReview.css?ver=1' />">
</head>
<body>

<div class="container">
    <h1>리뷰 작성</h1>
    <form action="/submit_review" method="POST">
        <label for="title">리뷰 제목</label>
        <input type="text" id="title" name="title" required>

        <label for="content">리뷰 내용</label>
        <textarea id="content" name="content" rows="5" required></textarea>

        <label for="rating">별점</label>
        <div class="rating-container">
            <!-- 반별점 단위로 선택 가능한 별 -->
            <span class="star" data-value="0.5">&#9733;</span>
            <span class="star" data-value="1">&#9733;</span>
            <span class="star" data-value="1.5">&#9733;</span>
            <span class="star" data-value="2">&#9733;</span>
            <span class="star" data-value="2.5">&#9733;</span>
            <span class="star" data-value="3">&#9733;</span>
            <span class="star" data-value="3.5">&#9733;</span>
            <span class="star" data-value="4">&#9733;</span>
            <span class="star" data-value="4.5">&#9733;</span>
            <span class="star" data-value="5">&#9733;</span>
        </div>

        <input type="hidden" id="rating" name="rating" value="0">

        <button type="submit">리뷰 등록</button>
    </form>
</div>

<script>
    // 별점 선택 기능 스크립트
    const stars = document.querySelectorAll('.star');
    const ratingInput = document.getElementById('rating');

    stars.forEach(star => {
        star.addEventListener('mouseover', () => {
            resetStars();
            highlightStars(star.getAttribute('data-value'));
        });

        star.addEventListener('click', () => {
            ratingInput.value = star.getAttribute('data-value');
            highlightStars(star.getAttribute('data-value'), true);
        });

        star.addEventListener('mouseleave', () => {
            if (!ratingInput.value) {
                resetStars();
            } else {
                highlightStars(ratingInput.value, true);
            }
        });
    });

    function resetStars() {
        stars.forEach(star => {
            star.classList.remove('hovered', 'selected');
        });
    }

    function highlightStars(value, selected = false) {
        stars.forEach(star => {
            if (star.getAttribute('data-value') <= value) {
                star.classList.add(selected ? 'selected' : 'hovered');
            }
        });
    }
</script>

</body>
</html>
