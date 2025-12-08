package org.zerock.dto;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import lombok.extern.log4j.Log4j2;

@Log4j2
@DisplayName("SimpleTodo DTO 단위 테스트")

	class SimpleTodoTest {
	
	// ===== 테스트 필드 =====
    private TodoDTO todo;
    private LocalDateTime now;
    
    // ===== 각 테스트 전 초기화 =====
    @BeforeEach
    void setUp() {
    todo = new TodoDTO();
    now = LocalDateTime.now();
    log.info("⚙️ [setUp] 테스트 초기화 완료");
    }
    
    // ===== 테스트 1: 기본 생성자 + Setter =====
	@Test
	@DisplayName("기본 생성자로 객체 생성 및 필드 설정")
	void testCreateAndSetFields() {
		// Given (상황) & When (메서드 실행)
		todo.setId(1);
		todo.setTitle("Spring 공부");
		todo.setDescription("MyBatis CRUD 구현");
		todo.setDone(false);
		todo.setCreatedAt(now);
		
		// Then (검증: 각 필드 설정 유효성 확인)
		assertNotNull(todo);	// 객체가 생성 확인
		assertEquals(1, todo.getId());	// id == 1 일치 확인
		assertEquals("Spring 공부", todo.getTitle()); // 제목 일치 확인
		assertEquals("MyBatis CRUD 구현", todo.getDescription());	// 설명 일치 확인
		assertFalse(todo.isDone());	// done == false ?
		assertNotNull(todo.getCreatedAt()); // 시간 설정 확인
		assertEquals(now, todo.getCreatedAt());
		
		log.info("✅ 기본 생성자 + Setter 테스트 성공: " + todo);
	}
	
    // ===== 테스트 2: 전체 필드 생성자 =====
	@Test
	@DisplayName("전체 필드 생성자로 객체를 생성할 수 있다.")
	void testAllArgsConstructor() {
		// Given & When
		TodoDTO newTodo = new TodoDTO(1, "Spring 공부", "MyBatis CRUD 구현", false, now);
		
        // Then
        assertNotNull(newTodo);
        assertEquals(1, newTodo.getId());
        assertEquals("Spring 공부", newTodo.getTitle());
        assertEquals("MyBatis CRUD 구현", newTodo.getDescription());
        assertFalse(newTodo.isDone());
        assertEquals(now, newTodo.getCreatedAt());
		log.info("✅ 전체 필드 생성자 테스트 성공: " + todo);
	}
	
    // ===== 테스트 3: 기본값 검증 =====
	@Test
	@DisplayName("기본 생성자의 기본값을 확인 할 수 있다.")
	void testDefaultValues() {
        // setUp()에서 새로운 SimpleTodo() 생성됨

		// Then
        assertNotNull(todo);
        assertEquals(0, todo.getId());              // int 기본값: 0
        assertNull(todo.getTitle());                // String 기본값: null
        assertNull(todo.getDescription());          // String 기본값: null
        assertFalse(todo.isDone());                 // boolean 기본값: false
        assertNull(todo.getCreatedAt());            // LocalDateTime 기본값: null
		
        log.info("✅ 기본값 테스트 성공: " + todo);
	}
	
    // ===== 테스트 4: 날짜 포맷 메서드 =====
	@Test
	@DisplayName("getCreatedDate() 메서드로 날짜를 포맷할 수 있다.")
	void testGetCreatedDate() {
		// Given
		LocalDateTime testDate = LocalDateTime.of(2025, 12, 8, 12, 35, 0);
		todo.setCreatedAt(testDate);
		
		// When
		String formatted = todo.getCreatedDate();
		
		assertNotNull(formatted);
		assertEquals("2025-12-08", formatted);	// ISO_DATE 형식: YYYY-MM-DD
		
		log.info("✅ getCreatedDate() 테스트 성공: " + formatted);
	}
	
    // ===== 테스트 5: toString 메서드 =====
	@Test
	@DisplayName("toString() 메서드가 정상 동작한다.")
	void testToString() {
		// Given
		todo.setId(1);
		todo.setTitle("테스트");
		
		// When
		String result = todo.toString();
		
		//Then
		assertNotNull(result);
		assertTrue(result.contains("SimpleTodo"));
		assertTrue(result.contains("1"));
		assertTrue(result.contains("테스트"));
		
        log.info("✅ toString() 테스트 성공:\n" + result);
	}
}
