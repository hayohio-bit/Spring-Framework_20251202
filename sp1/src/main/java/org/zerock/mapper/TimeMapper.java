package org.zerock.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
@Mapper
@Repository
public interface TimeMapper {

	@Select("select now()")
	String getTime();
	
	String getTime2();
	
}
