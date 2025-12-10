package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.dto.BoardDTO;
import org.zerock.dto.BoardListPagingDTO;
import org.zerock.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardService {

	private final BoardMapper boardMapper;
	
	public List<BoardDTO> getList(){
		return boardMapper.list();
	}

	public Long register(BoardDTO dto) {
		int insertCount = boardMapper.insert(dto);
		
		log.info("insertCount : " + insertCount);
		
		return dto.getBno();
	}

	public BoardDTO read(Long bno) {
		BoardDTO boardDTO = boardMapper.selectOne(bno);
		
		return boardDTO;
	}

	public void remove(Long bno) {
		boardMapper.remove(bno);
	}

	public void modify(BoardDTO dto) {
		boardMapper.update(dto);
	}
	
	public BoardListPagingDTO getList(int page, int size) {
		
		// 페이지 번호가 0보다 작으면 무조건 1페이지
		page = page <= 0? 1 : page;
		// 사이즈가 10보다 작거나 100보다 크면 10
		// size : 한 페이지에 몇 개의 데이터를 보여주는 지
		size = (size <= 10 || page > 100) ? 10: size;
		
		int skip = (page -1 ) * size; // 2페이지라면 (2-1) * 10 이 되어야 함
		
		List<BoardDTO> list = boardMapper.list2(skip, size);
		
		int total = boardMapper.listCount();
		
		return new BoardListPagingDTO(list, total, page, size);
		
	}

	
}
