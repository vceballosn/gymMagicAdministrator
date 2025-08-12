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

import com.gym.magic.administrator.dto.PaymentDto;
import com.gym.magic.administrator.service.PaymentService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@AllArgsConstructor // Inyección de dependencias a través del constructor (recomendado)
@RestController
@RequestMapping("api/v1/Payments") // Path base para todos los endpoints de este controlador
public class PaymentController {
	private final PaymentService paymentService;
	
	@PostMapping
	public ResponseEntity<PaymentDto> createPayment(@RequestBody PaymentDto paymentDto) {
		PaymentDto savedpaymentDto = paymentService.create(paymentDto);
		return new ResponseEntity<>(savedpaymentDto, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/{id}") // Path variable dentro de la anotación
	public ResponseEntity<PaymentDto> getPaymentById(@PathVariable Long id) { // Usa nombres de variables más cortos y
																				// descriptivos
		PaymentDto paymentDto = paymentService.getPaymentById(id);
		return ResponseEntity.ok(paymentDto);
	}
	
	@GetMapping
	public ResponseEntity<List<PaymentDto>> getAllPayment() {
		List<PaymentDto> payments = paymentService.getAllPayment();
		return ResponseEntity.ok(payments);
	}
	
	@PutMapping("/{id}") // Path variable dentro de la anotación
	public ResponseEntity<PaymentDto> updatePayment(@PathVariable Long id,@RequestBody PaymentDto paymentDto) { // Usa nombres de variables
																							// más cortos y descriptivos
		PaymentDto updatedPaymentDto = paymentService.updatePayment(id,paymentDto);
		return ResponseEntity.ok(updatedPaymentDto);
	}

	@DeleteMapping("/{id}") // Path variable dentro de la anotación
	public ResponseEntity<String> deletePayment(@PathVariable Long id) { // Usa nombres de variables más cortos y
																			// descriptivos
		paymentService.deletePayment(id);
		return ResponseEntity.ok("Payment deleted successfully."); // Mensaje más claro
	}

	

}
