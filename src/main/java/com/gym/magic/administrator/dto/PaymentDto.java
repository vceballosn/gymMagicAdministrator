package com.gym.magic.administrator.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	
	@NotNull(message = "El monto no puede ser nulo")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor que 0")
    private double amount;
    
    @NotNull(message = "La fecha de pago no puede ser nula")
    private LocalDate paymentDate;
    
    @NotBlank(message = "El mes pagado no puede estar en blanco")
    private String monthPaid;

    @NotNull(message = "El ID del socio no puede ser nulo")
    @Min(value = 1, message = "El ID del socio debe ser un número positivo")
    private Long partner_id;
    

}