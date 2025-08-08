package com.gym.magic.administrator.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PartnerDto {
	
	 private Long id;
		
	    private String name;
	    private String email;
	    private String phone;
	    private LocalDate dateRecord;

}
