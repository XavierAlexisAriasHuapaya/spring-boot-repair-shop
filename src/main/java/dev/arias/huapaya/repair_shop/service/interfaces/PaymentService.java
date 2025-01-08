package dev.arias.huapaya.repair_shop.service.interfaces;

import java.util.Optional;

import org.springframework.data.domain.Pageable;

import dev.arias.huapaya.repair_shop.persistence.entity.PaymentEntity;
import dev.arias.huapaya.repair_shop.presentation.dto.main.PageDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.payment.PaymentCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.payment.PaymentFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.payment.PaymentPaginationDTO;

public interface PaymentService {

    public PaymentEntity create(PaymentCreateDTO payment);

    public Optional<PaymentFindOneDTO> findOne(Long id);

    public PageDTO<PaymentPaginationDTO> pagination(Pageable pageable);

}
