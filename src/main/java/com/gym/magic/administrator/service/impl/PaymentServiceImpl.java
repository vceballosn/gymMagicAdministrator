package com.gym.magic.administrator.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gym.magic.administrator.dto.PaymentDto;
import com.gym.magic.administrator.entity.Payment;
import com.gym.magic.administrator.exception.ResourceNotFoundException;
import com.gym.magic.administrator.mapper.PaymentMapper;
import com.gym.magic.administrator.repository.PaymentRepository;
import com.gym.magic.administrator.service.PaymentService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService  {
	
	private PaymentRepository paymentRepository;

	@Override
	public PaymentDto create(PaymentDto paymentDto) {
		Payment payment = PaymentMapper.mapToPayment(paymentDto);
		Payment paymentSave = paymentRepository.save(payment);
		return PaymentMapper.mapToPaymentDto(paymentSave);
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
		    return payments.stream()
		            .map(payment -> PaymentMapper.mapToPaymentDto(payment))
		            .collect(Collectors.toList());
	}

	@Override
	public PaymentDto updatePayment(PaymentDto paymentDtoUpdate) {
		
	
	    
		Payment payment = new Payment();
		payment.setId(paymentDtoUpdate.getId());
		payment.setAmount(paymentDtoUpdate.getAmount());
		payment.setPaymentDate(paymentDtoUpdate.getPaymentDate());
		payment.setMonthPaid(paymentDtoUpdate.getMonthPaid());
	//	payment.setPartner(paymentDtoUpdate.getPartner());
		Payment paymentSave = paymentRepository.save(payment);
	    
		return  PaymentMapper.mapToPaymentDto(paymentSave);
	}

	@Override
	public void deletePayment(Long idPayment) {
		Payment payment = paymentRepository.findById(idPayment)  .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + idPayment));
		paymentRepository.deleteById(payment.getId());
		
	}

}
