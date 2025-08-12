package com.gym.magic.administrator.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartnerWithPaymentsDto {
	
	private Long id;
    private String name;
    private String email;
    private LocalDate dateRecord;
    private List<PaymentDto> payments; // La lista de pagos anidada

}
