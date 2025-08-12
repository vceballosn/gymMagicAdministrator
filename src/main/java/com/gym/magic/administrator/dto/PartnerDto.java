package com.gym.magic.administrator.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
	@NotBlank(message = "El nombre no puede estar en blanco")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
	private String name;
	@Email(message = "El email debe ser válido")
    @Size(min = 2, max = 50, message = "El email debe tener entre 2 y 25 caracteres")
	private String email;
	@NotBlank(message = "El Telefono no puede estar en blanco")
	private String phone;
	
	private LocalDate dateRecord;

}
