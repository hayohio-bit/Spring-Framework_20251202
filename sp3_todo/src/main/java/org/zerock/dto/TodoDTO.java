package org.zerock.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/* create table simple_todo
- id: INT (자동증가, PK)
- title: VARCHAR(200) - 필수
- description: VARCHAR(500) - 선택사항
- done: BOOLEAN (기본값: false)
- created_at: TIMESTAMP (기본값: 현재시간)
*/
@Data
@Builder
@NoArgsConstructor  // 기본 생성자 자동 생성
@AllArgsConstructor  // 모든 필드 생성자 자동 생성
public class TodoDTO {
	
	private int id;
	private String title;
	private String description;
	private boolean done;
	private LocalDateTime createdAt;
	
	// createdAt이 null이면 NullPointerException 발생하므로 아래와 같이 설정
	public String getCreatedDate() {
	    if (createdAt == null) {
	        return null;  // ← null 처리 추가!
	    }
	    return createdAt.format(DateTimeFormatter.ISO_DATE);
	}
}
