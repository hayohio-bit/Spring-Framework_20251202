package org.zerock.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.dto.TodoDTO;

import lombok.extern.log4j.Log4j2;
@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Transactional	// 각 테스트 후 자동 롤백
@DisplayName("SimpleTodoMapper 통합 테스트")
class SimpleTodoMapperTest {

	@Autowired	// 실제 Mapper 주입
	private SimpleTodoMapper mapper;
	
	@Test
    @DisplayName("새로운 todo를 DB에 INSERT할 수 있다")
    public void testInsert() {
        TodoDTO dto = TodoDTO.builder()
            .title("Spring 공부")
            .description("MyBatis CRUD")
            .done(false)
            .build();
        
        mapper.insert(dto);
        
        assertNotNull(dto.getId(), "ID가 자동 생성되어야 함");
        
        log.info("✅ INSERT 성공: ID=" + dto.getId() + ", " + dto);
	}

	@Test
	public void testSelectOne() {
	    // Given: INSERT로 데이터 먼저 생성
	    TodoDTO dto = TodoDTO.builder()
	        .title("Spring 공부")
	        .description("MyBatis CRUD")
	        .done(false)
	        .build();
	    mapper.insert(dto);
	    
	    // When: 생성된 ID로 SELECT
	    TodoDTO found = mapper.selectOne(dto.getId());
	    
	    // Then: 조회된 데이터 검증
	    assertNotNull(found, "조회된 데이터가 null이면 안 됨");
	    assertEquals("Spring 공부", found.getTitle());
	    assertEquals("MyBatis CRUD", found.getDescription());
	    assertFalse(found.isDone());
	    
	    log.info("✅ SELECT ONE 성공: " + found);
	}
	

}
