package com.gym.magic.administrator.controller;

import java.time.LocalDate;
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
@AllArgsConstructor // Inyecci�n de dependencias a trav�s del constructor (recomendado)
@RestController
@RequestMapping("api/v1/partners") // Path base para todos los endpoints de este controlador
public class PartnerController {

	private final PartnerService partnerService;

	@PostMapping
	public ResponseEntity<PartnerDto> createPartner(@RequestBody PartnerDto partnerDto) {
		partnerDto.setDateRecord(LocalDate.now());
		PartnerDto savedPartnerDto = partnerService.create(partnerDto);
		return new ResponseEntity<>(savedPartnerDto, HttpStatus.CREATED);
	}
	
	
	

	    // El valor de "{id}" en la URL se mapea a la variable 'id' del m�todo.
	    @GetMapping("/{id}")
	    public String getProductById(@PathVariable Long id) {
	        return "Solicitado el producto con ID: " + id;
	    }


	/*@GetMapping("/{id}") // Path variable dentro de la anotaci�n
	public ResponseEntity<PartnerDto> getPartnerById(@PathVariable Long id) { // Usa nombres de variables m�s cortos y																		// descriptivos
		PartnerDto partnerDto = partnerService.getPartnerById(id);
		return ResponseEntity.ok(partnerDto);
	}*/

	@GetMapping
	public ResponseEntity<List<PartnerDto>> getAllPartner() {
		List<PartnerDto> partners = partnerService.getAllPartner();
		return ResponseEntity.ok(partners);
	}

	@PutMapping() // Path variable dentro de la anotaci�n
	public ResponseEntity<PartnerDto> updatePartner(@RequestBody PartnerDto partnerDto) { // Usa nombres de variables
																							// m�s cortos y descriptivos
		PartnerDto updatedPartnerDto = partnerService.updatePartner(partnerDto);
		return ResponseEntity.ok(updatedPartnerDto);
	}

	@DeleteMapping("/{id}") // Path variable dentro de la anotaci�n
	public ResponseEntity<String> deletePartner(@PathVariable Long id) { // Usa nombres de variables m�s cortos y
																			// descriptivos
		partnerService.deletePartner(id);
		return ResponseEntity.ok("Partner deleted successfully."); // Mensaje m�s claro
	}

}
