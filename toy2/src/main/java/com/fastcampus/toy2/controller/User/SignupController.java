package com.fastcampus.toy2.controller.User;

import com.fastcampus.toy2.domain.User.MemberDto;
import com.fastcampus.toy2.service.User.SignupService;
import com.fastcampus.toy2.service.User.SingupValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class SignupController {
    @Autowired
    SignupService signupService;

    @GetMapping("/signup")
    public String signup() {
        return "signupForm";
    }

    @PostMapping("/isCheck")
    public ResponseEntity<String> idCheck(String mbr_id) throws Exception {
        boolean isCheck = signupService.idDuplCheck(mbr_id);
        String result = "";
        if (isCheck){
            result = "available";
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        result = "ID already in use";
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    private final SingupValidator singupValidator = new SingupValidator();

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(singupValidator);
    }

    @PostMapping("/saveDB")
    public ResponseEntity<Map<String,Object>> saveDB(@Validated @ModelAttribute MemberDto member, BindingResult result) throws Exception {
    /*  id 중복확인 여부
            중복확인 안 한 경우) -> alert 중복확인 메시지 띄우기
            중복확인 한 경우) -> validator 진행
                 검증에 실패한 경우) -> 에러메시지 띄우기
                 검증에 성공한 경우) -> DB에 저장 시도
                      DB저장 성공한 경우
                      DB저장 실패한 경우
    */
        // 검증에 실패한 경우
        singupValidator.validate(member, result);
        Map resultMap = new HashMap();
        if(result.hasErrors()){
            // 에러메시지를 Map 형태로 담아서
            for(FieldError error : result.getFieldErrors()){
                resultMap.put(error.getField(), error.getDefaultMessage());
            }
            // jsp로 해당 status와 함께 errorMap을 전달 (어떻게? map으로)
            ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
            System.out.println("[Controller ErrorMesssage] = "+responseEntity.getBody());
            return responseEntity;
            // 검증에 성공한 경우
        } else {
            boolean saveDB = signupService.memberSignup(member);
            if (saveDB) {
                // DB저장 성공한 경우
                resultMap.put("status", "success");
                return new ResponseEntity<>(resultMap, HttpStatus.OK);
            } else {
                // DB저장 실패한 경우
                resultMap.put("status", "failed");
                return new ResponseEntity<>(resultMap, HttpStatus.OK);
            }
        }
    }

}
