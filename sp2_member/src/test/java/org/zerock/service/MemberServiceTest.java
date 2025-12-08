package org.zerock.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.dto.MemberDTO;

import lombok.extern.log4j.Log4j2;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
class MemberServiceTest {

	@Autowired
	private MemberService memberService;
	
	@Test
	void test() {
		memberService.getList()
		.forEach(member-> log.info(member));
	}

	@Test
	void testOne() {
		MemberDTO dto = memberService.memberById(1);
		log.info(dto);
	}
	
	@Test
	void testInsert() {
		MemberDTO dto = MemberDTO.builder()
				.name("홍길금")
				.email("goldload@test.com")
				.password("1234")
				.build();
		
		memberService.insert(dto);
	}
	
	@Test
	void testUpdate() {
		MemberDTO dto = MemberDTO.builder()
				.name("홍금")
				.email("gold@test.com")
				.password("1234")
				.build();
		
		memberService.update(dto);
	}
	
	@Test
	void testDelete() {
		memberService.delete(8); 
	}
	
}
