package com.gym.magic.administrator.mapper;

import com.gym.magic.administrator.dto.PartnerDto;
import com.gym.magic.administrator.entity.Partner;

public class PartnerMapper {

	public static PartnerDto mapToPartnerDto(Partner partner) {
		return new PartnerDto(partner.getId(), partner.getName(), partner.getEmail(), partner.getPhone(),
				partner.getDateRecord());
	}

	public static Partner mapToPartner(PartnerDto partnerDto) {

		return new Partner(partnerDto.getId(), partnerDto.getName(), partnerDto.getEmail(), partnerDto.getPhone(),
				partnerDto.getDateRecord());
	}

}
