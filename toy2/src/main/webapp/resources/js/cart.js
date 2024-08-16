$(document).ready(function() {
    // 모든 항목을 선택하거나 선택 해제하는 함수
    function CheckedAll() {
        const isChecked = $('#checkAll').is(':checked');
        $('.product-check').prop('checked', isChecked);
        updateCheckboxCount();  // 체크박스 선택된 수 업데이트
        updateSelectedInfo();  // 선택된 항목 수와 총 가격을 업데이트
    }

    // 선택된 체크박스 수를 업데이트하는 함수
    function updateCheckboxCount() {
        const checkboxCount = $('.product-check:checked').length; // 선택된 체크박스 수 계산
        $('.checkboxCount').text(checkboxCount);
    }

    // 선택된 항목 수와 총 가격을 업데이트하는 함수
    function updateSelectedInfo() {
        let selectedCount = 0;
        let selectedTotalPrice = 0;

        $('.product-check:checked').each(function() {
            const itemCount = parseInt($(this).data('count')); // 체크된 항목의 수량 가져오기
            selectedCount += itemCount;
            selectedTotalPrice += parseFloat($(this).data('price'));
        });

        $('.selectedCount').text(selectedCount);
        $('.selectedTotalPrice').text(selectedTotalPrice.toLocaleString('ko-KR'));

        // 개별 체크박스의 상태에 따라 전체 선택 체크박스 상태 설정
        const totalCheckboxes = $('.product-check').length;
        const checkedCheckboxes = $('.product-check:checked').length;
        $('#checkAll').prop('checked', totalCheckboxes === checkedCheckboxes);
    }

    // 삭제 기능 처리 (품절 상품 삭제, 선택 삭제)
    function deleteItems(isSoldOut) {
        let itemsToDelete;

        if (isSoldOut) {
            // 품절 상품 삭제
            itemsToDelete = $('.product-check').filter(function() {
                return $(this).data('salestate') === 'SC003';
            });
        } else {
            // 선택 삭제
            itemsToDelete = $('.product-check:checked');
        }

        if (itemsToDelete.length === 0) {
            alert(isSoldOut ? "품절 상품이 없습니다." : "선택된 상품이 없습니다.");
            return;
        }

        const items = itemsToDelete.map(function() {
            return {
                style_num: $(this).data('stylenum'), // 스타일 번호
                p_size: $(this).data('psize') // 사이즈
            };
        }).get();

        $.ajax({
            url: '/order/cart/removeItems',  // 삭제 요청을 처리할 URL
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(items),  // 서버로 보낼 데이터
            success: function(response) {
                // 성공적으로 삭제되면 화면에서 해당 항목들을 제거
                itemsToDelete.closest('li.product-item').remove();

                // 삭제 후 UI 업데이트
                updateCheckboxCount(); // 체크박스 수 업데이트
                updateSelectedInfo();

                // 장바구니가 비어 있는지 확인하고 비었으면 "쇼핑백에 등록된 상품이 없습니다." 메시지 표시
                const remainingItems = $('.product-item');
                if (remainingItems.length === 0) {
                    $('.order__list').html('<div class="empty-cart-message"><p>쇼핑백에 등록된 상품이 없습니다.</p></div>');
                    $('.checkboxCount').text('0');
                    $('.selectedCount').text('0');
                    $('.selectedTotalPrice').text('0');
                    $('#cartListSize').text('0'); // 전체 상품 수량도 0으로 업데이트
                } else {
                    // 남아있는 아이템의 개수로 cartListSize 업데이트
                    const updatedSize = remainingItems.length;
                    $('#cartListSize').text(updatedSize);
                    updateSelectedInfo();
                }
            },
            error: function(error) {
                alert("삭제 중 오류가 발생했습니다.");
            }
        });
    }

    // 품절상품 삭제 버튼 클릭 시
    $(document).on('click', '.delete__btn.soldout', function() {
        deleteItems(true);
    });

    // 선택 삭제 버튼 클릭 시
    $(document).on('click', '.delete__btn.select', function() {
        deleteItems(false);
    });

    // 개별 체크박스가 클릭될 때 실행되는 이벤트 핸들러
    $(document).on('click', '.product-check', function() {
        updateCheckboxCount(); // 체크박스 수 업데이트
        updateSelectedInfo();
    });

    // "전체 선택" 체크박스가 클릭될 때 실행되는 이벤트 핸들러
    $(document).on('click', '#checkAll', function() {
        CheckedAll();
    });

    // 초기 상태에서 선택된 항목 정보 업데이트
    updateCheckboxCount(); // 초기 체크박스 수 업데이트
    updateSelectedInfo();

    // 모달 열기
    function openModal() {
        document.getElementById("optionModal").style.display = "block";
    }

    // 모달 닫기
    function closeModal() {
        document.getElementById("optionModal").style.display = "none";
    }

    // 수량 증가
    function increaseQuantity() {
        let quantity = document.getElementById("quantity");
        quantity.value = parseInt(quantity.value) + 1;
    }

    // 수량 감소
    function decreaseQuantity() {
        let quantity = document.getElementById("quantity");
        if (parseInt(quantity.value) > 1) {
            quantity.value = parseInt(quantity.value) - 1;
        }
    }

    // 모달에서 변경하기 버튼 클릭 시
    function updateOptions() {
        // 옵션 변경 로직을 여기에 구현
        closeModal(); // 모달 닫기
    }

    // 모달 이벤트 핸들러
    $(document).on('click', '.option__btn', function() {
        openModal();
    });

    $(document).on('click', '.modal .close', function() {
        closeModal();
    });

    $(document).on('click', '.change-btn', function() {
        updateOptions();
    });

    $(document).on('click', '.quantity-box button', function() {
        if ($(this).text() === '+') {
            increaseQuantity();
        } else {
            decreaseQuantity();
        }
    });
});