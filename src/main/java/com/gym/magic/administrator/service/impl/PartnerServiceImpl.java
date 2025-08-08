package com.gym.magic.administrator.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gym.magic.administrator.dto.PartnerDto;
import com.gym.magic.administrator.entity.Partner;
import com.gym.magic.administrator.mapper.PartnerMapper;
import com.gym.magic.administrator.repository.PartnerRepository;
import com.gym.magic.administrator.service.PartnerService;
import com.gym.magic.administrator.exception.ResourceNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PartnerServiceImpl implements PartnerService {
	
	private PartnerRepository partnerRepository;
	@Override
	public PartnerDto create(PartnerDto partnerDto) {
		Partner partner = PartnerMapper.mapToPartner(partnerDto);
		Partner partnerSave = partnerRepository.save(partner);
		return PartnerMapper.mapToPartnerDto(partnerSave);
		
	}

	@Override
	public PartnerDto getPartnerById(Long idPartner) {
		Partner partnerId = partnerRepository.findById(idPartner)
	            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + idPartner));
	    return PartnerMapper.mapToPartnerDto(partnerId);
	
	}

	@Override
	public List<PartnerDto> getAllPartner() {
		  List<Partner> partners = partnerRepository.findAll();
		    return partners.stream()
		            .map(partner -> PartnerMapper.mapToPartnerDto(partner))
		            .collect(Collectors.toList());
	}

	@Override
	public PartnerDto updatePartner(PartnerDto partnerUpdate) {
		Partner partner = new Partner();
		partner.setId(partnerUpdate.getId());
		partner.setName(partnerUpdate.getName());
		partner.setEmail(partnerUpdate.getEmail());
		partner.setPhone(partnerUpdate.getPhone());
		partner.setDateRecord(partnerUpdate.getDateRecord());
		Partner PartnerSave = partnerRepository.save(partner);
	    
		return  PartnerMapper.mapToPartnerDto(PartnerSave);
	}

	@Override
	public void deletePartner(Long idPartner) {
		Partner partner = partnerRepository.findById(idPartner)  .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + idPartner));
		partnerRepository.deleteById(partner.getId());
		
	}
	
	
	
	
	

}
