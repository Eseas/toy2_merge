<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/signup.css" rel="stylesheet" type="text/css">
    <title>회원가입</title>
    <style>
        .error {
            color: red;
            font-size: 0.9em;
            margin-top: 5px;
        }
        .success {
            color: green;
            font-size: 0.9em;
            margin-top: 5px;
        }
        .memberInfo {
            margin-bottom: 15px;
        }
        .memberInfo label {
            display: block;
            margin-bottom: 5px;
        }
    </style>
</head>
<body>
    <h1> 회원가입 </h1>
    <form name="signupForm" method="post" action="/saveDB">
        <h3> 사이트 이용을 위한 약관에 동의해주세요. </h3>
        <div>
            <label>
                <input type="checkbox" id="allMandatoryAgreement" name="allMandatoryAgreement" onchange="allAgreement(this)">
                모두 동의
            </label><br>
            <label>
                <input type="checkbox" id="mandatoryAgreement1" name="mandatoryAgreement1">
                Nplus 이용 약관 (필수)
            </label><br>
            <label>
                <input type="checkbox" id="mandatoryAgreement2" name="mandatoryAgreement2">
                NEPA 개인정보 처리방침 (필수)
            </label><br>
            <label>
                <input type="checkbox" id="mandatoryAgreement3" name="mandatoryAgreement3">
                NEPA 멤버십 서비스 이용 약관 (필수)
            </label><br>
            <label>
                <input type="checkbox" id="mandatoryAgreement4" name="mandatoryAgreement4">
                개인정보 수집 및 이용 동의 (필수)
            </label><br>
            <label>
                <input type="checkbox" id="opt_info_collect_use_agree" name="opt_info_collect_use_agree">
                개인정보 수집 및 이용 동의 (선택)
            </label>
        </div>

        <h3> 마케팅 정보 수신 동의 </h3>
        <div>
            <label>
                <input type="checkbox" id="sms_rcv_agree" name="sms_rcv_agree">
                SMS 수신 동의 (선택)
            </label><br>
            <label>
                <input type="checkbox" id="tm_rcv_agree" name="tm_rcv_agree">
                TM 수신 동의 (선택)
            </label><br>
            <label>
                <input type="checkbox" id="email_rcv_agree" name="email_rcv_agree">
                E-MAIL 수신 동의 (선택)
            </label>
        </div>

        <h3> [필수] 회원가입 정보 </h3>
        <div class="memberInfo">
            <label for="name">성명</label>
            <input type="text" name="name" id="name">
        </div>
        <div class="memberInfo">
            <label for="birth">생년월일</label>
            <input type="date" name="birth" id="birth">
        </div>
        <div class="memberInfo">
            <label for="gender">성별</label>
            <select name="gender" id="gender">
                <option value="" disabled selected>성별 선택하기</option>
                <option value="F">여성</option>
                <option value="M">남성</option>
            </select>
        </div>
        <div class="memberInfo">
            <label for="phone_num">휴대폰번호</label>
            <input type="text" name="phone_num" id="phone_num" oninput="phoneCheck()">
            <!-- 휴대폰 번호 유효성 검사에 대한 결과 표시 -->
            <div id="phoneCheckResult" class="error"></div>
        </div>
        <div class="memberInfo">
            <label for="mbr_id">아이디</label>
            <input type="text" name="mbr_id" id="mbr_id" oninput="idCheck()">
            <input type="button" value="중복확인" name="mbr_id" onclick="duplIdCheck()">
            <!-- 아이디 유효성 검사에 대한 결과 표시 -->
            <div id="idCheckResult" class="error"></div>
            <!-- 아이디 중복 여부에 대한 결과 표시 (alert로 바꿔서 주석 처리했다) -->
            <!-- <div id="idDuplCheckResult" class="error"></div> -->
        </div>
        <div class="memberInfo">
            <label for="pw">비밀번호</label>
            <input type="password" name="pw" id="pw" oninput="pwCheck()">
            <!-- 비밀번호 유효성 검사에 대한 결과 표시 -->
            <div id="pwCheckResult" class="error"></div>
        </div>
        <div class="memberInfo">
            <label for="pw2">비밀번호 확인</label>
            <input type="password" id="pw2" oninput="duplPwCheck()">
            <!-- 비밀번호 일치 여부에 대한 결과 표시 -->
            <div id="pwDuplCheckResult" class="error"></div>
        </div>
        <div class="memberInfo">
            <label for="email">이메일</label>
            <input type="email" name="email" id="email" placeholder="이메일" required>
            @
<%--            직접입력 기능은 잠시 주석처리 --%>
<%--            <input type="text" name="email2" id="email2" placeholder="직접입력">--%>
            <select name="email_etc" id="email_etc">
                <option value="" disabled selected>도메인 선택하기</option>
                <option value="naver.com">naver.com</option>
                <option value="gmail.com">gmail.com</option>
                <option value="daum.net">daum.net</option>
            </select>
            <button type="button" id="email_verification">인증하기</button>
        </div>
        <div class="memberInfo">
            <label for="addr_cd">주소</label>
            <input type="text" name="addr_cd" id="addr_cd" placeholder="우편번호">
            <button type="button">주소찾기</button><br>
            <input type="text" name="addr_line1" id="addr_line1" placeholder="기본주소"><br>
            <input type="text" name="addr_line2" id="addr_line2" placeholder="상세주소"><br>
        </div>
        <div class="memberInfo">
            <input type="button" value="취소" id="cancel">
            <input type="button" value="가입완료" id="signup" onclick="signupTest()">
        </div>
   </form>


<script>
    // 1. 아이디 유효성 검사 기능
    function idCheck() {
        var mbr_id = document.getElementById('mbr_id').value;
        var idCheckResult = document.getElementById('idCheckResult');
        // console.log("mbr_id" + mbr_id);

        // 1-1) 길이 체크
        if(!(6<=mbr_id.length && mbr_id.length<=16)) {
            idCheckResult.textContent = "아이디는 6~16자로 입력해야 합니다.";
            idCheckResult.style.color = "red";
            return false;
        }

        // 1-2) 한글&공백 체크
        for(var i=0; i<mbr_id.length; i++){
            var ck = mbr_id.charAt(i);
            if(/\s/.test(ck) || /[가-힣]/.test(ck)) {
                idCheckResult.textContent = "아이디에는 한글과 공백을 포함할 수 없습니다.";
                idCheckResult.style.color = "red";
                return false;
            }
        }
        idCheckResult.textContent = ""; // 오류가 없으면 메시지 삭제
        return true;
    }

    // 2. 중복 아이디 확인 기능
    // (문제) 중복체크 후에 아이디를 재입력하면 textContent가 사라져야 하는데, 그대로 띄워져 있다. -> 우선 alert로 띄우는 걸로 임시 해결
    async function duplIdCheck() {
        var mbr_id = document.getElementById('mbr_id').value;

        if(!mbr_id){
            alert("아이디를 입력해주세요");
            return;
        }

        try {
            const response = await fetch('http://localhost:8080/toy2/isCheck', {
                method: 'POST',
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                body: new URLSearchParams({'mbr_id':mbr_id})
            });

            if(response.ok) {
                const result = await response.text();
                var idDuplCheckResult = document.getElementById('idDuplCheckResult');

                if (result === 'available') {
                    alert("사용 가능한 아이디입니다.");
                    // idDuplCheckResult.textContent = "사용 가능한 아이디입니다.";
                    // idDuplCheckResult.style.color = "green";
                } else {
                    alert("이미 사용중인 아이디입니다.");
                    // idDuplCheckResult.textContent = "이미 사용중인 아이디입니다.";
                    // idDuplCheckResult.style.color = "red";
                }
            } else {
                alert("서버와의 통신에 실패했습니다.");
            }
        } catch (error) {
            console.error('Error', error);
            alert("서버와의 통신에 실패했습니다.");
        }
        return true;
    }

    // 3. 비밀번호 입력기준 체크
    function pwCheck() {
        var pw = document.getElementById('pw').value;
        var pw2 = document.getElementById('pw2').value;
        var pwCheckResult = document.getElementById('pwCheckResult');

        // 2-1) 길이 체크
        if (!(8 <= pw.length && pw.length <= 16)) {
            pwCheckResult.textContent = "비밀번호는 8~16자로 입력해야 합니다.";
            pwCheckResult.style.color = "red";
            return false;
        }
        // 2-2) 한글&공백 체크
        for (var i = 0; i < pw.length; i++) {
            var ck = pw.charAt(i);
            if (/\s/.test(ck) || /[가-힣]/.test(ck)) {
                pwCheckResult.textContent = "비밀번호에는 한글과 공백을 포함할 수 없습니다."
                pwCheckResult.style.color = "red";
                return false;
            }
        }
        // 2-3) 영문, 숫자, 특수문자 조합 체크(대소문자 구분)
        var pwPattern = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,16}$/;
        if (!pwPattern.test(pw)) {
            pwCheckResult.textContent = "비밀번호는 영문, 숫자, 특수문자의 조합이어야 하며, 대소문자를 구분합니다.";
            pwCheckResult.style.color = "red";
            return false;
        }
        pwCheckResult.textContent = "사용가능한 비밀번호 입니다."; // 비밀번호 조건을 모두 만족할 때 메시지
        pwCheckResult.style.color = "green";
        return true;
    }

    // 4. 비밀번호 일치 여부 확인
    function duplPwCheck() {
        var pw = document.getElementById('pw').value;
        var pw2 = document.getElementById('pw2').value;
        var pwDuplCheckResult = document.getElementById('pwDuplCheckResult');

        if (pw != pw2) {
            pwDuplCheckResult.textContent = "비밀번호가 일치하지 않습니다.";
            pwDuplCheckResult.style.color = "red";
            return false;
        }
        pwDuplCheckResult.textContent = "비밀번호가 일치합니다."; // 비밀번호가 일치할 때 메시지
        pwDuplCheckResult.style.color = "green";
        return true;
    }

    // 5. 휴대폰번호 입력기준 체크
    function phoneCheck() {
        var phone_num = document.getElementById('phone_num').value;
        var phoneCheckResult = document.getElementById('phoneCheckResult');

        // 1-1) 숫자 체크
        for(var i=0; i<phone_num.length; i++){
            var ck = phone_num.charAt(i);
            if(!(/^\d+$/.test(ck))){
            //if(!('0'<=ck && ch <= '9')) {
                phoneCheckResult.textContent = "휴대폰번호는 숫자만 입력해주세요.";
                phoneCheckResult.style.color = "red";
                return false;
            }
        }
        // 1-2) 길이 체크
        if(!(phone_num.length===11)) {
            phoneCheckResult.textContent = "휴대폰번호 11자리를 모두 입력해주세요.";
            phoneCheckResult.style.color = "red";
            return false;
        }
        phoneCheckResult.textContent = ""; // 오류가 없으면 메시지 삭제
        return true;
    }

    // 6. 정상 가입 완료시
    async function signupTest() {
        // 6-1) 사용자가 필수 정보를 모두 입력했는지 확인
            // (필수) 사이트 이용 약관 동의
        var mandatoryAgreement1 = document.getElementById('mandatoryAgreement1');
        var mandatoryAgreement2 = document.getElementById('mandatoryAgreement2');
        var mandatoryAgreement3 = document.getElementById('mandatoryAgreement3');
        var mandatoryAgreement4 = document.getElementById('mandatoryAgreement4');
            // (선택) 마케팅 정보 수신 동의
        var opt_info_collect_use_agree = document.getElementById('opt_info_collect_use_agree').checked ? "Y" : "N";
        var sms_rcv_agree = document.getElementById('sms_rcv_agree').checked ? "Y" : "N";
        var tm_rcv_agree = document.getElementById('tm_rcv_agree').checked ? "Y" : "N";
        var email_rcv_agree = document.getElementById('email_rcv_agree').checked ? "Y" : "N";
            // 일반 정보
        var mbr_id = document.getElementById('mbr_id').value;
        var pw = document.getElementById('pw').value;
        var name = document.getElementById('name').value;
        var birth = document.getElementById('birth').value;
            // var gender = document.querySelector('input[name="gender"]:checked'); // <input type="radio">일때 쓰던 방식
        var gender = document.getElementById('gender').value;
        var phone_num = document.getElementById('phone_num').value;
        var email = document.getElementById('email').value + '@' + document.getElementById('email_etc').value;
        var addr_cd = document.getElementById('addr_cd').value;
        var addr_line1 = document.getElementById('addr_line1').value;
        var addr_line2 = document.getElementById('addr_line2').value;

        // 필수 입력 및 동의 사항에 대한 점검
        // if((!mandatoryAgreement1.checked||(!mandatoryAgreement2.checked) ||(!mandatoryAgreement3.checked) ||(!mandatoryAgreement4.checked))){
        //     alert("사이트 이용을 위한 약관의 모든 필수사항에 동의해주세요."); return; }
        // if(!name){ alert("이름을 입력해주세요"); return; }
        // if(!birth){ alert("생년월일을 입력해주세요"); return; }
        // if(!gender){ alert("성별을 선택해주세요"); return; }
        // if(!phone_num){ alert("휴대폰 번호를 입력해주세요"); return; }
        // if(!mbr_id){ alert("아이디를 입력해주세요"); return; }
        // if(!pw){ alert("비밀번호를 입력해주세요"); return; }
        // if(!email){ alert("이메일을 입력해주세요"); return; }

        // 필수 입력 항목이 아니라서 임시로 주석처리
        // if(!addr_cd){ alert("우편주소를 입력해주세요"); return; }
        // if(!addr_line1){ alert("기본주소를 입력해주세요"); return; }

        try {
            const response = await fetch('http://localhost:8080/toy2/saveDB', {
                method: 'POST',
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                body: new URLSearchParams(
                    {'mbr_id': mbr_id,
                    'pw': pw,
                    'name': name,
                    'birth': birth,
                    'gender': gender,
                    'phone_num': phone_num,
                    'email': email,
                    'addr_cd': addr_cd,
                    'addr_line1': addr_line1,
                    'addr_line2': addr_line2,
                    'opt_info_collect_use_agree': opt_info_collect_use_agree,
                    'sms_rcv_agree': sms_rcv_agree,
                    'tm_rcv_agree': tm_rcv_agree,
                    'email_rcv_agree': email_rcv_agree})
            });
            //response를 찍어보기
            if (response.ok) {
                const result = await resultMap.getValue();
                if (result === 'success') {
                    alert("회원가입을 축하합니다.");
                } else {
                    alert("입력란을 모두 제대로 입력했는지 확인해주세요.")
                }
            } else {
                const result = await resultMap.getValue();
                alert(result);
            }
        } catch (e) {
            // 예외 발생 시
        }
        return true;
    }

    // 0. 필수 동의사항 한번에 체크하는 기능
    function allAgreement(source) {
        // 'allAgreement' 체크박스의 상태를 가져옵니다.
        var isChecked = source.checked;

        // 모든 필수 체크박스를 선택합니다.
        var checkboxes = document.querySelectorAll('input[name="mandatoryAgreement1"], input[name="mandatoryAgreement2"], input[name="mandatoryAgreement3"], input[name="mandatoryAgreement4"], input[name="opt_info_collect_use_agree1"]');

        checkboxes.forEach(function(checkbox) {
            checkbox.checked = isChecked;
        });
    }
</script>
</body>
</html>