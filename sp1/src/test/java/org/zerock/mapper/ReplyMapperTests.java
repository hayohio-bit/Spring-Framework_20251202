package org.zerock.mapper;

import java.util.List;

import org.junit.jupiter.api.Test;
//JUnit의 @Test 어노테이션을 사용하기 위한 import
import org.junit.jupiter.api.extension.ExtendWith;
// JUnit 확장 모델을 사용할 때 필요한 어노테이션
import org.springframework.beans.factory.annotation.Autowired;
//스프링의 의존성 주입(@Autowired) 어노테이션
import org.springframework.test.context.ContextConfiguration;
//테스트에서 사용할 스프링 설정 파일 위치를 지정하는 어노테이션
import org.springframework.test.context.junit.jupiter.SpringExtension;
// JUnit과 스프링을 연결해 주는 확장 클래스
import org.zerock.dto.ReplyDTO;
// 댓글 정보를 담는 DTO(데이터 전송 객체) 클래스
import lombok.extern.log4j.Log4j2;
// Lombok이 log4j2 로거 객체를 자동으로 생성해 주는 @Log4j2 어노테이션
@ExtendWith(SpringExtension.class)
//이 테스트 클래스에서 스프링 컨테이너를 사용하도록 JUnit에 알려줌
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
//스프링 설정 파일(root-context.xml)을 읽어와서 빈(Bean)들을 등록하도록 지정
@Log4j2
// log.info() 같은 로그 메서드를 사용할 수 있도록 logger 필드를 자동 생성
public class ReplyMapperTests {
	@Autowired
	// 스프링 컨테이너에 등록된 ReplyMapper 빈을 자동으로 주입받음(의존성 주입)
	private ReplyMapper replyMapper;
	// 댓글 관련 DB 작업을 담당하는 MyBatis 매퍼 인터페이스
	
	@Test
	public void testInsert() {
		
		// 게시물 번호 결정 - DB에 존재하는지 확인 필요
		Long bno = 525092L;
		
		// 새로운 댓글 생성
		ReplyDTO replyDTO = ReplyDTO.builder()
				.bno(bno)
				.replyText("댓글내용")
				.replyer("tester1")
				.build();
		
		replyMapper.insert(replyDTO);
	}
	
	@Test
	public void testRead() {
		ReplyDTO dto = replyMapper.read(1);
		log.info("dto :" + dto);
	}
	
	@Test
	public void testDelete() {
		replyMapper.delete(4);
	}
	
	@Test
	public void testUpdate() {
		
		ReplyDTO replyDTO = ReplyDTO.builder()
				.rno(2)
				.replyText("update ReplyText")
				.build();
		replyMapper.update(replyDTO);
	}
	
	@Test
	public void testInserts() {
		Long[] bnos = {525092L, 525091L, 525090L};
		
		for (Long bno : bnos) {
			for(int i = 0; i < 100; i++) {
				ReplyDTO replyDTO = ReplyDTO.builder()
						.bno(bno)
						.replyer("inerts1")
						.replyText("Sample Reply")
						.build();
				replyMapper.insert(replyDTO);
			} //end for inner
		} // end for
	}
	
	@Test
	public void testListOfBoard() {
		Long bno = 525090L;
		
		List<ReplyDTO> replyList = replyMapper.listOfBoard(bno, 0, 10);
		
		replyList.forEach(log::info);
	}

}
