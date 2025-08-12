package com.gym.magic.administrator.service;

import java.util.List;

import com.gym.magic.administrator.dto.PaymentDto;

public interface PaymentService {

	PaymentDto create(PaymentDto paymentDto);

	PaymentDto getPaymentById(Long idPayment);

	List<PaymentDto> getAllPayment();

	PaymentDto updatePayment(Long id, PaymentDto paymentDtoUpdate);

	void deletePayment(Long idPayment);
}
