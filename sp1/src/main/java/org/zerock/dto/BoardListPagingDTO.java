package org.zerock.dto;

import java.util.List;
import java.util.stream.IntStream;

import lombok.Data;

@Data
public class BoardListPagingDTO {

	private List<BoardDTO> boardDTOList; // 현재 페이지 게시글 목록

	private int totalCount; // 전체 게시글 수 (총 253개)

	private int page, size; // 현재 페이지(5), 페이지당 개수(10)

	private int start, end;	// 페이지번호 범위 시작/끝 (1~10)
	
	private boolean prev, next;	// [이전][다음] 버튼 표시 여부
	
	private List<Integer> pageNums;	// 화면에 보여줄 페이지 번호들 [1,2,3,...,10]
	
	
	public BoardListPagingDTO(List<BoardDTO> boardDTOList, 
			int totalCount, int page, int size) {

		this.boardDTOList = boardDTOList;
		this.totalCount = totalCount;
		this.page = page;
		this.size = size;
		
		// 현재페이지 기준 10개 블록의 끝페이지 계산
		// page=25 -> tempEnd=30 (21~30 블록)
		int tempEnd = (int)(Math.ceil(page/10.0)) * 10;
		
		// 블록 시작페이지 (30-9=21)
		this.start = tempEnd - 9;
		
		// [이전]버튼: start가 1이 아니면 표시 (11~20 블록이면 prev=true)
		this.prev = start != 1;
		
		// 실제 마지막페이지 계산
		// 30*10=300 > totalCount=253 -> 총 게시물 253개이므로 end=26페이지
		if( (tempEnd * size) > totalCount ) {
			this.end = (int) ( Math.ceil(totalCount / (double)size) );
		} else {
			this.end = tempEnd;
		}
		
		// [다음]버튼: end*size < totalCount 이면 표시
		this.next = totalCount > (this.end * size);
		
		// 화면에 출력할 페이지 번호 리스트 [21,22,23,...,26]
		this.pageNums = IntStream.rangeClosed(start, end).boxed().toList();
				
		/*
		 * start=21, end=26, pageNums=[21,22,23,24,25,26]
		 * prev=true, next=false
		 * UI: [이전] 21 22 23 24 25 26 [다음X]
		 */
	}

}
