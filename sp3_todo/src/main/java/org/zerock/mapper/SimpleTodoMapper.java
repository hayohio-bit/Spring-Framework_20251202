package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.zerock.dto.TodoDTO;

@Mapper	//MyBatis가 이 인터페이스를 자동으로 구현
public interface SimpleTodoMapper {

	// ===== CREATE =====
	// 새로운 todo를 DB에 추가 ▷ DB에 INSERT할 테이터를 전달
	// 매개변수: todo 객체
	// 반환: void (삽입된 행 수 또는 없음)
	void insert(TodoDTO dto);
	
    // ===== READ (단일) =====
    // 특정 ID의 todo를 조회
    // 매개변수: todo의 ID
    // 반환: SimpleTodo 객체 (없으면 null)
    TodoDTO selectOne(int id);
	
    // ===== READ (전체) =====
    // 모든 todo 목록을 조회
    // 매개변수 : 없음
    // 반환: SimpleTodo 리스트
    List<TodoDTO> selectAll();
    
    // ===== UPDATE =====
    // 기존 todo 수정
    // 매개변수 : 수정할 todo 객체
    // 반환 : void ▷ 수정된 데이터를 반환할 필요 없음
    void update(TodoDTO dto);
    
    // ===== DELETE =====
    // 특정 ID의 todo 삭제
    // 매개변수 : todo의 ID
    // 반환 : void ▷ 삭제된 데이터를 반환할 필요 없음
    void delete(int id);
    
	
}
