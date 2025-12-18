package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.dto.ReplyDTO;
import org.zerock.dto.ReplyListPagingDTO;
import org.zerock.mapper.ReplyMapper;
import org.zerock.service.exception.ReplyException;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
// 이 클래스가 서비스 레이어의 빈(Bean)임을 스프링에게 알림, 스프링이 자동 관리
@RequiredArgsConstructor
// final 필드를 인자로 받는 생성자를 롬복이 자동 생성해서 의존성 주입에 사용
@Transactional
// 이 클래스의 public 메서드들을 트랙잭션 범위 안에서 실행하도록 지정
@Log4j2
public class ReplyService {

	private final ReplyMapper replyMapper;
	// 댓글과 관련된 DB 작업(insert,read,update,delete)을 수행하는 ReplyMapper를 의존성으로 가짐
	// final : 생성자 주입만 가능, @RequireArgsConstructor가 생성자 자동 생성
	
	
	// 댓글 추가 / void 반환값 없음 / 전달받은 replyDTO를 이용해 실제 DB에 댓글추가하는 역할만 담당하는 메서드
	public void add(ReplyDTO replyDTO) {
	
		try {
			replyMapper.insert(replyDTO);
		}catch (Exception e) {
			throw new ReplyException(500, "INSERT ERROR");
			// 에러 래핑 : 낮은 레벨 예외 e를 숨기고, "댓글 등록 실패" 상황을 명확하게 알림
		}
	}
	
	
	// 단건 조회 메서드
	public ReplyDTO getOne(int rno) {
	// rno를 받아 해당 댓글 한 건을 조회하는 서비스 레벨 진입 메서드로, 조회결과를 ReplyDTO로 반환함
		
		try {
			return replyMapper.read(rno);
			// ReplyMapper의 read 메서드 호출 > 전달받은 rno와 일치하는 댓글 레코드 DB에서 조회
			// > ReplyDTO 형태로 반환 
		}catch (Exception e) {
			throw new ReplyException(404, "NOT FOUND");
		}
	}
	
	
	// 수정 메서드
	public void modify(ReplyDTO replyDTO) {
		
		try {
			int count = replyMapper.update(replyDTO);
			// ReplyMapper의 update 호출해 DB에 댓글 내용 수정
			// > 수정된 행(row)의 개수를 count에 받아 수정 성공 여부를 판단하는 기반으로 사용
			
			if(count == 0) {
			// 조건에 맞는 댓글이 없다는 의미이므로 "수정 대상 댓글이 존재하지 않는 상황"을 비즈니스 에러로 처리하기 위한 검사
				throw new ReplyException(404, "NOT FOUND");
				// 수정 대상 부재를 
			}
		} catch (Exception e) {
			throw new ReplyException(500, "UPDATE ERROR");
		}
	}
	
	public void remove(int rno) {
		
		try {
			int count = replyMapper.delete(rno);
			
			if(count == 0) {
				throw new ReplyException(404, "NOT FOUND");
			}
		} catch (Exception e) {
			throw new ReplyException(500, "DELETE ERROR");
		}
	}
	
	public ReplyListPagingDTO listOfBoard( Long bno, int page, int size ) {
		
		try {
			
			int skip = (page -1) * size;
			
			List<ReplyDTO> replyDTOList = replyMapper.listOfBoard(bno, skip, size);
			
			int count = replyMapper.countOfBoard(bno);
			
			return new ReplyListPagingDTO(replyDTOList, count, page, size);
			
		}catch (Exception e) {
			throw new ReplyException(500, e.getMessage());
		}
		
	}
	
}
