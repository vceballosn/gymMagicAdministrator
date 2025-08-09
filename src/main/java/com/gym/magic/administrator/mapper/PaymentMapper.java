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
			// Nota: Si Partner en la entidad no es un DTO, necesitas un mapper para Partner también
			// PaymentDto espera un PartnerDto, pero Payment.getPartner() retorna un Partner.
			// Supongamos que ya tienes un PartnerMapper.
			// PartnerMapper.mapToPartnerDto(payment.getPartner())
			null // Placeholder: Corregir según tu implementación de PartnerMapper
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