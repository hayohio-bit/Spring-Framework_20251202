package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.dto.BoardDTO;
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
}
