package com.gym.magic.administrator.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gym.magic.administrator.dto.PartnerDto;
import com.gym.magic.administrator.service.PartnerService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@AllArgsConstructor // Inyección de dependencias a través del constructor (recomendado)
@RestController
@RequestMapping("api/v1/partners") // Path base para todos los endpoints de este controlador
public class PartnerController {

	private final PartnerService partnerService;

	@PostMapping
	public ResponseEntity<PartnerDto> createPartner(@RequestBody PartnerDto partnerDto) {
		PartnerDto savedPartnerDto = partnerService.create(partnerDto);
		return new ResponseEntity<>(savedPartnerDto, HttpStatus.CREATED);
	}

	@GetMapping("/{id}") // Path variable dentro de la anotación
	public ResponseEntity<PartnerDto> getPartnerById(@PathVariable Long id) { // Usa nombres de variables más cortos y
																				// descriptivos
		PartnerDto employeeDto = partnerService.getPartnerById(id);
		return ResponseEntity.ok(employeeDto);
	}

	@GetMapping
	public ResponseEntity<List<PartnerDto>> getAllPartner() {
		List<PartnerDto> employees = partnerService.getAllPartner();
		return ResponseEntity.ok(employees);
	}

	@PutMapping() // Path variable dentro de la anotación
	public ResponseEntity<PartnerDto> updatePartner(@RequestBody PartnerDto partnerDto) { // Usa nombres de variables
																							// más cortos y descriptivos
		PartnerDto updatedEmployeeDto = partnerService.updatePartner(partnerDto);
		return ResponseEntity.ok(updatedEmployeeDto);
	}

	@DeleteMapping("/{id}") // Path variable dentro de la anotación
	public ResponseEntity<String> deletePartner(@PathVariable Long id) { // Usa nombres de variables más cortos y
																			// descriptivos
		partnerService.deletePartner(id);
		return ResponseEntity.ok("Partner deleted successfully."); // Mensaje más claro
	}

}
