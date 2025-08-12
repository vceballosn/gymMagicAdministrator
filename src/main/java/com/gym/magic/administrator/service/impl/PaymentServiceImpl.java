package com.gym.magic.administrator.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.magic.administrator.dto.PaymentDto;
import com.gym.magic.administrator.entity.Partner;
import com.gym.magic.administrator.entity.Payment;
import com.gym.magic.administrator.exception.ResourceNotFoundException;
import com.gym.magic.administrator.mapper.PaymentMapper;
import com.gym.magic.administrator.repository.PartnerRepository;
import com.gym.magic.administrator.repository.PaymentRepository;
import com.gym.magic.administrator.service.PaymentService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private PartnerRepository partnerRepository;

	@Override
	public PaymentDto create(PaymentDto paymentDto) {
		// 1. Convertir el DTO a la entidad Payment
		Payment payment = PaymentMapper.mapToPayment(paymentDto);

		// 2. Obtener el Partner de la base de datos usando el partnerId del DTO
		Partner partner = partnerRepository.findById(paymentDto.getPartner_id()).orElseThrow(
				() -> new IllegalArgumentException("Partner with ID " + paymentDto.getPartner_id() + " not found."));

		// 3. Asignar el Partner a la entidad Payment
		payment.setPartner(partner);

		// 4. Establecer la fecha de pago (si no viene en el DTO)
		if (payment.getPaymentDate() == null) {
			payment.setPaymentDate(LocalDate.now());
		}

		// 5. Guardar la entidad Payment
		Payment savedPayment = paymentRepository.save(payment);

		// 6. Opcional: Devolver el objeto guardado como DTO
		// return PaymentMapper.mapToDto(savedPayment);
		// (Asumiendo que tienes un método de mapeo de Entidad a DTO)

		return PaymentMapper.mapToPaymentDto(savedPayment);
	}

	@Override
	public PaymentDto getPaymentById(Long idPayment) {
		Payment paymenyId = paymentRepository.findById(idPayment)
				.orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + idPayment));
		return PaymentMapper.mapToPaymentDto(paymenyId);
	}

	@Override
	public List<PaymentDto> getAllPayment() {
		List<Payment> payments = paymentRepository.findAll();
		return payments.stream().map(payment -> PaymentMapper.mapToPaymentDto(payment)).collect(Collectors.toList());
	}

	@Override
	public PaymentDto updatePayment(Long id, PaymentDto paymentDtoUpdate) {
	    // 1. Encontrar la entidad Payment existente
	    Payment payment = paymentRepository.findById(id)
	        .orElseThrow(() -> new RuntimeException("Payment with ID " + id + " not found."));

	    // 2. Encontrar la entidad Partner a partir del ID del DTO
	    Partner partner = partnerRepository.findById(paymentDtoUpdate.getPartner_id())
	        .orElseThrow(() -> new RuntimeException("Partner with ID " + paymentDtoUpdate.getPartner_id() + " not found."));

	    // 3. Actualizar los campos del Payment
	    payment.setAmount(paymentDtoUpdate.getAmount());
	    payment.setPaymentDate(paymentDtoUpdate.getPaymentDate());
	    payment.setMonthPaid(paymentDtoUpdate.getMonthPaid());

	    // 4. Establecer la relación de objeto (la forma correcta de hacerlo)
	    payment.setPartner(partner);

	    // 5. Guardar el objeto Payment actualizado
	    Payment updatedPayment = paymentRepository.save(payment);

	    // 6. Opcional: Mapear a DTO y retornar
	    return PaymentMapper.mapToPaymentDto(updatedPayment);
	}

	@Override
	public void deletePayment(Long idPayment) {
		Payment payment = paymentRepository.findById(idPayment)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + idPayment));
		paymentRepository.deleteById(payment.getId());

	}

}
