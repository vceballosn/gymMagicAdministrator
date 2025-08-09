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
public class PaymentDto {
	
    private Long id;
    private double amount;
    private LocalDate paymentDate;
    private String monthPaid;
    private PartnerDto partner;
    

}