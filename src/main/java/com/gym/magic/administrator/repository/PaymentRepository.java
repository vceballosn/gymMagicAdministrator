package com.gym.magic.administrator.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.gym.magic.administrator.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
