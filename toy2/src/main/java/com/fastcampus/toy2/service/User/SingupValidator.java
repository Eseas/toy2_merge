package com.fastcampus.toy2.service.User;

import com.fastcampus.toy2.domain.User.MemberDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Component
public class SingupValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return MemberDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MemberDto member = (MemberDto) target;

/*      검증이 필요한 컬럼 = id, pw, name, birth, gender, phone_nun, email

        notNull 컬럼 = id, pw, name, birth, gender, phone_nun, email
        길이 체크 컬럼 = id, pw, name, birth, phone_num
        형식 체크 컬럼 = id, pw, birth, email, phone_num(숫자로만 구성)

        ValidationUtils.rejectIfEmptyOrWhitespace() 메서드
        field는 컬럼값, errorCode는 오류 메시지 코드, defaultMessage는 errorCode가 없을 경우 전달될 메시지
        ex) ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "field.required", "입력란에 제대로 입력했는지 확인해주세요");

        디폴트 메시지는 가장 마지막.

        (추가 필요) 아이디 중복체크 확인 필요
            mbr_id 중복 체크 하고 validator로 넘어오거나,
            validator에서 중복 체크 조건을 추가하거나
        (추가 필요) 필수 동의 사항 체크 확인기능 추가 필요
            (문제) jsp에 있는 기능 껐을 때, 필수동의사항에 대한 확인이 없어서 필수동의 없이도 가입이 가능함.......

*/

        // 1) id - nonNull, 6~16, 공백과 한글 미포함
        String mbr_id = member.getMbr_id() == null ? "" : member.getMbr_id();
        if(mbr_id.isEmpty()){
            errors.rejectValue("mbr_id","field.required", "아이디는 필수 입력값입니다.");
        } else {
            // 길이 체크
            if(mbr_id.length() < 6 || mbr_id.length() > 16) {
                errors.rejectValue("mbr_id", "field.size", "아이디는 6~16자로 입력해야 합니다.");
            }
            // 공백과 한글 미포함 체크
            if(mbr_id.contains(" ") || mbr_id.matches(".*[가-힣].*")) {
                errors.rejectValue("mbr_id", "field.noWhitespace_Hangul", "아이디는 공백과 한글을 포함할 수 없습니다.");
            }
        }

        // 2) pw - notNull, 8~16, 공백과 한글 미포함, 영문(대소문자 구분), 숫자, 특수문자의 조합으로 작성
        String pw = member.getPw() == null ? "" : member.getPw();
        if(pw.isEmpty()){
            errors.rejectValue("pw","field.required", "비밀번호는 필수 입력값입니다.");
        } else {
            // 길이 체크
            if(pw.length()<8 || pw.length()>16) {
                errors.rejectValue("pw", "field.size", "비밀번호는 8~16자로 입력해야 합니다.");
            }
            // 공백과 한글 미포함 체크
            if(pw.contains(" ") || pw.matches(".*[가-힣].*")) {
                errors.rejectValue("pw", "field.noWhitespace_Hangul", "비밀번호에는 공백과 한글을 포함할 수 없습니다.");
            }
            // 영문(대소문자 구분), 숫자, 특수문자의 조합 체크
            boolean upperCase = false;  // 대문자 여부
            boolean lowerCase = false;  // 소문자 여부
            boolean number = false;     // 숫자 여부
            boolean specialChar = false;// 특수문자 여부
            for(int i=0;i<pw.length();i++){
                if(Character.isUpperCase(pw.charAt(i))) upperCase = true;
                if(Character.isLowerCase(pw.charAt(i))) lowerCase = true;
                if(Character.isDigit(pw.charAt(i))) number = true;
                // 대소문자나 숫자가 아닌 경우(특수문자인 경우)
                if(!Character.isLetterOrDigit(pw.charAt(i))) specialChar = true;
            } // 하나라도 충족되지 않으면 에러 메시지 출력
            if(!(upperCase && lowerCase && number && specialChar)) {
                errors.rejectValue("pw", "field.noWhitespace_Hangul", "비밀번호는 영문(대소문자 구분), 숫자, 특수문자가 모두 포함되어야 합니다.");
            }
        }

        // 3) name - notNull, 길이 체크
        String name = member.getName() == null ? "" : member.getName();
        if(name.isEmpty()){
            errors.rejectValue("name","field.required", "이름은 필수 입력값입니다.");
        } else {
            // 길이 체크
            if (name.length() > 50) {
                errors.rejectValue("name", "field.size", "이름은 50자 이내로 입력해야 합니다.");
            }
        }

        // 4) birth - notNull, 유효한 날짜인지 체크
        String birth = member.getBirth() == null ? "" : String.valueOf(member.getBirth());
        LocalDate localdate;
        if(birth.isEmpty()){
            errors.rejectValue("birth","field.required", "생년월일은 필수 입력값입니다.");
        } else {
            // 유효한 날짜인지 확인
            try { localdate = LocalDate.parse(birth);
            } catch (DateTimeParseException e){
                errors.rejectValue("birth","field.required", "유효하지 않은 날짜입니다.");
                return;
            }
            // 오늘보다 이후 날짜이면 true로 처리
            if(localdate.isAfter(LocalDate.now()))
                errors.rejectValue("birth", "field.size", "생년월일을 오늘 이전의 날짜로 선택해야 합니다.");
        }

        // 5) gender - notNull
        String gender = member.getGender() == null ? "" : member.getGender();
        if(gender.isEmpty()){
            errors.rejectValue("gender","field.required", "성별은 필수로 선택해야 합니다.");
        }

        // 6) phone_num - notNull, 길이 체크, 형식 체크
        String phone_num = member.getPhone_num() == null ? "" : member.getPhone_num();
        if(phone_num.isEmpty()){
            errors.rejectValue("phone_num","field.required", "휴대폰 번호는 필수로 선택해야 합니다.");
        } else {
            // 형식 체크
            if(!(phone_num.matches("^[0-9]*$"))){
                errors.rejectValue("phone_num","field.required", "휴대폰 번호는 숫자만 입력해주세요.");
            }
            // 길이 체크
            if(phone_num.length() == 13) {
                errors.rejectValue("phone_num", "field.size", "휴대폰 번호를 11자리를 모두 입력해주세요.");
            }
        }

        // 7) email - notNull, 길이 체크
        String email = member.getEmail() == null ? "" : member.getEmail();
        if(email.isEmpty()){
            errors.rejectValue("email","field.required", "이메일은 필수 입력값입니다.");
        } else {
            // 길이 체크
            if (email.length() > 50) {
                errors.rejectValue("email", "field.size", "50자 이내로 입력해야 합니다.");
            }
        }
    }

}
