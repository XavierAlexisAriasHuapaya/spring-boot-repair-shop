package dev.arias.huapaya.repair_shop.service.implementation;

import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.arias.huapaya.repair_shop.persistence.entity.MasterEntity;
import dev.arias.huapaya.repair_shop.persistence.repository.MasterRepository;
import dev.arias.huapaya.repair_shop.presentation.dto.master.MasterFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.exception.ExceptionMessage;
import dev.arias.huapaya.repair_shop.service.interfaces.MasterService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MasterImplementation implements MasterService {

    private final MasterRepository repository;

    @Override
    public MasterFindOneDTO findOne(Long id) {
        Optional<MasterEntity> masterOpt = this.repository.findById(id);
        if (!masterOpt.isPresent()) {
            throw new ExceptionMessage("Not found master");
        }
        MasterEntity master = masterOpt.get();
        MasterFindOneDTO masterDTO = MasterFindOneDTO.builder()
                .id(id)
                .description(master.getDescription())
                .master_details(master.getMaster_details())
                .status(master.getStatus())
                .build();
        return masterDTO;
    }

}
