package com.gym.magic.administrator.mapper;

import java.util.stream.Collectors;

import com.gym.magic.administrator.dto.PartnerDto;
import com.gym.magic.administrator.dto.PartnerWithPaymentsDto;
import com.gym.magic.administrator.entity.Partner;

public class PartnerMapper {

	public static PartnerDto mapToPartnerDto(Partner partner) {
		return new PartnerDto(partner.getId(), partner.getName(), partner.getEmail(), partner.getPhone(),
				partner.getDateRecord());
	}

	public static Partner mapToPartner(PartnerDto partnerDto) {

		return new Partner(partnerDto.getId(), partnerDto.getName(), partnerDto.getEmail(), partnerDto.getPhone(),
				partnerDto.getDateRecord(), null);
	}
	
	 public static PartnerWithPaymentsDto mapToPartnerWithPaymentsDto(Partner partner) {
	        if (partner == null) return null;

	        PartnerWithPaymentsDto dto = new PartnerWithPaymentsDto();
	        dto.setId(partner.getId());
	        dto.setName(partner.getName());
	        dto.setEmail(partner.getEmail());
	        dto.setDateRecord(partner.getDateRecord());

	        // Mapea la lista de entidades Payment a una lista de PaymentDto
	        if (partner.getPayments() != null) {
	            dto.setPayments(partner.getPayments().stream()
	                    .map(PaymentMapper::mapToPaymentDto) // Asume que tienes un PaymentMapper
	                    .collect(Collectors.toList()));
	        }

	        return dto;
	    }

}
