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
	
	//page :2, size : 10, typeStr = "TC", keyword: "수정"
	public BoardListPagingDTO getList(int page, int size, 
						String typeStr, String keyword) {
		
		// 페이지 번호가 0보다 작으면 무조건 1페이지
		page  = page <= 0 ? 1 : page;
		
		// 사이즈가 10보다 작거나 100보다 크면 10
		size = (size <= 10 || page > 100) ? 10 : size;
		
		/*
		 * 전체 데이터 100개 있다고 가정
		 * 1 page : 10 -> 100 ~ 91, skip 0
		 * 2 Page : 10 -> 90 ~ 81, skip 10
		 * 3 page : 10 -> 80 ~ 71 , skip 20
		 * .
		 * 5 page : 10 -> 60 ~ 51, skip 40
		 */
		
		int skip = (page - 1) * size;
		
		               // typeStr = "TC" => typeStr.split("") -> T|C
		String[] types = typeStr != null ? typeStr.split("") : null;		
		
		List<BoardDTO> list =  boardMapper.listSearch(skip, size, types, keyword);
		
		int total = boardMapper.listCountSearch(types, keyword);
		
		return new BoardListPagingDTO(list,total,page,size,typeStr,keyword);

	}

	public Long register(BoardDTO dto) {
		
		int insertCounter = boardMapper.insert(dto);
		
		log.info("insertCounter : " + insertCounter);
		
		return dto.getBno();
	}

	public BoardDTO read(Long bno) {
		
		return boardMapper.selectOne(bno);
	}

	public void remove(Long bno) {
		boardMapper.remove(bno);
	}

	public void modify(BoardDTO dto) {
		boardMapper.update(dto);
	}
	
	
}