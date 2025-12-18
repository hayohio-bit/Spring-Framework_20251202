package org.zerock.dto;

import java.util.List;
import java.util.stream.IntStream;

import lombok.Data;

@Data
// 롬복 통합 어노테이션 : getter, setter, toString, equals, hashCode 등을 자동 생성하는 DTO 클래스 선언부
public class ReplyListPagingDTO {
// 댓글 목록 + 페이징 정보를 한 번에 담는 DTO
	
	private List<ReplyDTO> replyDTOList;
	// 댓글 리스트 필드 : 현재 페이지에 표시할 댓글들을 ReplyDTO 목록 형태로 보관
	
	
	private int totalCount;		// 전체 댓글의 총 개수
	// 마지막 페이지가 어디까지인지, 다음/이전 버튼 표시 여부를 계산하는 기준 값으로 사용됨
	
	private int page, size;		// 페이지 파마리터 필드
	// 현재 페이지 번호(page)와 한 페이지에 몇 개의 댓글을 보여줄지(size)를 저장해 페이징 연산 입력 값으로 활용
	
	private int start, end;		// 현재 페이지 블록의 시작과 끝 페이지
	private boolean prev, next;	// 페이지 네비게이션에서 이전/다음 버튼 표시여부 제어하는 불리언 플래그
	
	private List<Integer> pageNums;		// 페이지 번호 목록 필드
	// 정수 리스트 형태로 보관해, 뷰 템플릿이 바로 순회하며 사용하도록 지원
	
	
	/*	★★★ 생성자 : 페이징 계산 핵심
	 *	현재 페이지의 댓글 목록, 전체 댓글 수, 현재 페이지 번호, 페이지 크기를 입력받아
	 *	내부 필드 값 세팅과 동시에 페이징 계산(start/end/prev/next/pageNums)을 모두 수행하는 초기화 메서드 */
	public ReplyListPagingDTO(List<ReplyDTO> replyDTOList, 
			int totalCount, int page, int size) {
		
		this.replyDTOList = replyDTOList;
		this.totalCount = totalCount;
		this.page = page;
		this.size = size;
		
		// 페이지 블록 끝 계산
		// page=7 이면 1~10블록 → tempEnd=10, page=13이면 11~20블록 → tempEnd=20
		int tempEnd = (int)(Math.ceil(page/10.0)) * 10;
		
		// 페이지 블록 끝에서 9를 빼서 해당 블록의 시작 페이지 구함
		this.start = tempEnd - 9;
		
		// start 값이 1이 아니라면 이전 페이지로 이동
		this.prev = start != 1;
		
		// 블록 초과 여부 검사
		// 블롞 최대 레코드 수 > 실제 전체 레코드 수 → 확인하여 마지막 블록에서 빈페이지 생기는 상황 방지하려는 조건
		if( (tempEnd * size) > totalCount ) {
			// 실제 마지막 페이지 계산
			this.end = (int) (Math.ceil(totalCount / (double)size) );
		
		// 블록 끝 유지
		}else {
			this.end = tempEnd;
		}
		
		// 다음 블록 플래그
		// end 최대 레코드 수보다 totalCount가 더 크면 뒤에 페이지 남아있다는 의미 → next=true → "다음"표시
		this.next = totalCount > (this.end * size);
		
		// 페이지 번호 리스트 생성
		// 페이지 인덱스 리스트 생성 : start부터 end까지의 연속된 정수 범위를 스트림으로 생성한 뒤
		// 박싱(boxed)하여 List<Integer>로 변환함으로써,
		// 뷰에서 페이지 네비게이션을 그릴 때 그대로 순회 출력할 수 있는 페이지 번호 목록을 만듦
		this.pageNums = IntStream.rangeClosed(start, end)
				.boxed()
				.toList();
	}
	
	
	
	
}
