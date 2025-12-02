package org.zerock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.zerock.service.HelloService;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
public class HelloController {


	private final HelloService helloService;
	
	
	
	
}


/*
의존성 주입 방식
1. 필드 주입
	@Autowired

2. 생성자 주입
	public HelloController(HelloService helloService) {
	this.HelloService = helloService;
	
3. 생성자 주입_Lombok
	@RequiredArgsConstructor
 */