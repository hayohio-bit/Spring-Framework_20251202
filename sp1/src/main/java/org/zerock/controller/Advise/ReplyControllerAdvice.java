package org.zerock.controller.Advise;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.zerock.service.exception.ReplyException;

import lombok.extern.log4j.Log4j2;

@RestControllerAdvice
@Log4j2
public class ReplyControllerAdvice {

	@ExceptionHandler(ReplyException.class)
	public ResponseEntity<String> handleReplyError(ReplyException ex){
		
		log.error(ex.getMessage());
		
		return ResponseEntity.status(ex.getCode()).body(ex.getMsg());
		
	}
}

// ReplyException 타입의 객체를 감지해서 ResponseEntity 타입으로 
// 404나 505 등과 같은 HTTP 응답 상태 코드와 함께 에러 메시지를 전송하는 역할을 함
