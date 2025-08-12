package com.gym.magic.administrator.service.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gym.magic.administrator.dto.PartnerDto;
import com.gym.magic.administrator.dto.PartnerWithPaymentsDto;
import com.gym.magic.administrator.entity.Partner;
import com.gym.magic.administrator.entity.Payment;
import com.gym.magic.administrator.exception.ResourceNotFoundException;
import com.gym.magic.administrator.mapper.PartnerMapper;
import com.gym.magic.administrator.repository.PartnerRepository;
import com.gym.magic.administrator.service.PartnerService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PartnerServiceImpl implements PartnerService {

	private PartnerRepository partnerRepository;
	private static final long GRACE_PERIOD_DAYS = 30; // Define el periodo de gracia

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
		return partners.stream().map(partner -> PartnerMapper.mapToPartnerDto(partner)).collect(Collectors.toList());
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

		return PartnerMapper.mapToPartnerDto(PartnerSave);
	}

	@Override
	public void deletePartner(Long idPartner) {
		Partner partner = partnerRepository.findById(idPartner)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + idPartner));
		partnerRepository.deleteById(partner.getId());

	}
	
	public Optional<PartnerWithPaymentsDto> getPartnerWithPayments(Long id) {
        return partnerRepository.findById(id)
                .map(PartnerMapper::mapToPartnerWithPaymentsDto);
    }
	
	 public boolean isPartnerDelinquent(Long partnerId) {
	        // 1. Obtener el socio de la base de datos
	        Optional<Partner> optionalPartner = partnerRepository.findById(partnerId);

	        if (optionalPartner.isEmpty()) {
	            // El socio no existe, no es moroso.
	            return false;
	        }

	        Partner partner = optionalPartner.get();
	        if (partner.getPayments().isEmpty()) {
	            // Si no tiene pagos, es moroso.
	            return true;
	        }

	        // 2. Encontrar el pago más reciente
	        Optional<Payment> latestPayment = partner.getPayments().stream()
	            .max(Comparator.comparing(Payment::getPaymentDate));

	        if (latestPayment.isEmpty()) {
	            // No se pudo encontrar el último pago, es moroso por defecto.
	            return true;
	        }

	        LocalDate lastPaymentDate = latestPayment.get().getPaymentDate();

	        // 3. Comparar la fecha del último pago con la fecha actual
	        LocalDate currentDate = LocalDate.now();
	        long daysSinceLastPayment = ChronoUnit.DAYS.between(lastPaymentDate, currentDate);

	        // Se considera moroso si han pasado más de 30 días desde el último pago
	        return daysSinceLastPayment > GRACE_PERIOD_DAYS;
	    }
	 
	 
	 // Método privado para la lógica de morosidad
	    private boolean isDelinquent(Partner partner) {
	        if (partner.getPayments().isEmpty()) {
	            return true;
	        }

	        Optional<Payment> latestPayment = partner.getPayments().stream()
	                .max(Comparator.comparing(Payment::getPaymentDate));

	        if (latestPayment.isEmpty()) {
	            return true;
	        }

	        LocalDate lastPaymentDate = latestPayment.get().getPaymentDate();
	        LocalDate currentDate = LocalDate.now();
	        long daysSinceLastPayment = ChronoUnit.DAYS.between(lastPaymentDate, currentDate);

	        return daysSinceLastPayment > GRACE_PERIOD_DAYS;
	    }
	    
	
	 
	 // Método para obtener todos los socios morosos
	    public List<PartnerDto> getDelinquentPartners() {
	        List<Partner> allPartners = partnerRepository.findAll();

	        return allPartners.stream()
	                .filter(this::isDelinquent) // Filtra los socios morosos
	                .map(PartnerMapper::mapToPartnerDto) // Mapea las entidades a DTOs
	                .collect(Collectors.toList());
	    }

}
