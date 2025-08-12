package com.gym.magic.administrator.utility;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.Optional;

import com.gym.magic.administrator.entity.Partner;
import com.gym.magic.administrator.entity.Payment;
import com.gym.magic.administrator.repository.PartnerRepository;

public class Utility {
	
	private static final long GRACE_PERIOD_DAYS = 30; // Define el periodo de gracia
	
	public static boolean isPartnerDelinquent(Long partnerId,PartnerRepository partnerRepository ) {
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
    public static boolean isDelinquent(Partner partner) {
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
    


}
