package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.dto.SampleDTO;
import org.zerock.service.HelloService;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/sample")
@ToString

public class HelloController {
	
	private final HelloService helloService;
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
	
	/* String : /WEB-INF/views/ex1.jsp	▶ POST 방식으로 성공/실패시 다른 JSP 실행해야 하는 경우
	 * void : /WEB-INF/views/sample/ex1.jsp */
	// 메서드 리턴타입을 void로 지정하면 호출하는 경로와 실행되는 JSP 파일은 동일하게 설정됨
	// void ▶ 입력을 유도하는 경로 or GET방식으로 동작하는 목록화면은 void로 설계
	
	//localhost:8080/sample/ex1 -> void : /WEB-INF/views/sample/ex1.jsp
	@GetMapping("/ex1")	//sample/ex1
	public void ex1() {
		log.info("/sample/ex1");
	}
	
	//localhost:8080/sample/ex2 -> String : /WEB-INF/views/sample/success.jsp
	@GetMapping("/ex2")
	public String ex2() {
		log.info("/sample/success");
		return "sample/success";
	}
	
	//localhost:8080/sample/ex3 -> redirect -> localhost:8080/sample/ex3re
	@GetMapping("/ex3")
	public String ex3() {
		return "redirect:/sample/ex3re";
	}
	//localhost:8080/sample/ex3re -> String -> /WEB-INF/views/sample/ex3Result.jsp
	@GetMapping("/ex3re")
	public String ex3Re() {
		return "sample/ex3Result";
	}
	
	//http://localhost:8080/sample/ex4?name=홍길동
	//http://localhost:8080/sample/ex4?n1=10&name=홍길동
	// --> void -> /WEB-INF/views/sample/ex4.jsp
	@GetMapping("/ex4")
	public void ex4(@RequestParam(name="n1", defaultValue = "1") int num,
					@RequestParam("name") String name) {
		
		log.info("num: " + num);
		log.info("name : " + name);
	}
	
	//http://localhost:8080/sample/ex4? , Method:Post
	@PostMapping("/ex4")
	public void ex4Post(@RequestParam(name="n1", defaultValue = "1") int num,
					@RequestParam("name") String name) {
		
		log.info("num: " + num);
		log.info("name : " + name);
	}
	
	//http://localhost:8080/sample/ex5?name=홍길동&age=20
	// void -> /WEB-INF/views/sample/ex5.jsp
	@GetMapping("/ex5")
	public void ex5(SampleDTO dto) {
		log.info(dto);
	}
	
	//http://localhost:8080/sample/ex6
	// void -> /WEB-INF/views/sample/ex6.jsp
	@GetMapping("/ex6")
	public void ex6(Model model) {
		model.addAttribute("name", "Hong Gil Dong");
		model.addAttribute("age", 16);
	}
	
	@GetMapping("/ex6_1")
	public ModelAndView ex6_1() {
		ModelAndView mav = new ModelAndView();
		
		// 뷰 이름 지정
		mav.setViewName("sample/ex6_1");
		
		// 데이터 넣기
		mav.addObject("name", "Hong Gil Dong");
		mav.addObject("age", 1);
		
		return mav;
	}
	
	// http://localhost:8080/sample/ex7 - 요청
	// http://localhost:8080/sample/ex8 - 재요청
	// String -> /WEB-INF/views/sample/ex8.jsp?name=Hong&age=25
	@GetMapping("/ex7")
	public String ex7(RedirectAttributes rttr) {
		rttr.addAttribute("name", "Hong");
		rttr.addFlashAttribute("age", 25);
		return "redirect:/sample/ex8";
	}
	
	// http://localhost:8080/sample/ex8
	// void -> /WEB-INF/views/sample/ex8.jsp
	@GetMapping("/ex8")
	public void ex8() {
		log.info("/sample/ex8");
	}
	
}

