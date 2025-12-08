package org.zerock.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
	/*
CREATE TABLE tbl_member (
  mno        INT AUTO_INCREMENT PRIMARY KEY,
  name       VARCHAR(100) NOT NULL,
  email      VARCHAR(200) NOT NULL UNIQUE,
  password   VARCHAR(200) NOT NULL,
  regdate    TIMESTAMP DEFAULT NOW(),
  updatedate TIMESTAMP DEFAULT NOW()
);
	 */
@Data
@Builder             //setter 대용으로 사용 가능
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
	private int mno;
	private String name;
	private String email;
	private String password;
	private LocalDateTime regDate;
	private LocalDateTime updateDate;
	
	public String getCreatedDate() {
		return regDate.format(DateTimeFormatter.ISO_DATE);
	}
}
