package com.gym.magic.administrator.mapper;

import com.gym.magic.administrator.dto.PaymentDto;
import com.gym.magic.administrator.entity.Payment;

public class PaymentMapper {
	
	public static PaymentDto mapToPaymentDto(Payment payment) {
		return new PaymentDto(
			payment.getId(),
			payment.getAmount(),
			payment.getPaymentDate(),
			payment.getMonthPaid(),
			payment.getPartner().getId()
		);
	}
	
	public static Payment mapToPayment(PaymentDto paymentDto) {
		return new Payment(
			paymentDto.getId(),
			paymentDto.getAmount(),
			paymentDto.getPaymentDate(),
			paymentDto.getMonthPaid(),
			// Placeholder: Corregir según tu implementación de PartnerMapper
			null // PartnerMapper.mapToPartner(paymentDto.getPartner())
		);
	}
}