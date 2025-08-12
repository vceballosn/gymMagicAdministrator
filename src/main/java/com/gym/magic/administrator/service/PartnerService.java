package com.gym.magic.administrator.service;

import java.util.List;
import java.util.Optional;

import com.gym.magic.administrator.dto.PartnerDto;
import com.gym.magic.administrator.dto.PartnerWithPaymentsDto;


public interface PartnerService {

	PartnerDto create(PartnerDto partnerDto);

	PartnerDto getPartnerById(Long idPartner);

	List<PartnerDto> getAllPartner();

	PartnerDto updatePartner(PartnerDto partnerUpdate);

	void deletePartner(Long idPartner);
	
	 Optional<PartnerWithPaymentsDto> getPartnerWithPayments(Long id);
	 
	 boolean isPartnerDelinquent(Long partnerId);
	 List<PartnerDto> getDelinquentPartners();

}
