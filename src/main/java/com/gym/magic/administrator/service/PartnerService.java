package com.gym.magic.administrator.service;

import java.util.List;

import com.gym.magic.administrator.dto.PartnerDto;

public interface PartnerService {

	PartnerDto create(PartnerDto partnerDto);

	PartnerDto getPartnerById(Long idPartner);

	List<PartnerDto> getAllPartner();

	PartnerDto updatePartner(PartnerDto partnerUpdate);

	void deletePartner(Long idPartner);

}
