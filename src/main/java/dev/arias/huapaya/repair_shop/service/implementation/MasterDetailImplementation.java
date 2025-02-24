package dev.arias.huapaya.repair_shop.service.implementation;

import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.arias.huapaya.repair_shop.persistence.entity.MasterDetailEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.MasterEntity;
import dev.arias.huapaya.repair_shop.persistence.repository.MasterDetailRepository;
import dev.arias.huapaya.repair_shop.persistence.repository.MasterRepository;
import dev.arias.huapaya.repair_shop.presentation.dto.master_detail.MasterDetailCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.master_detail.MasterDetailUpdateDTO;
import dev.arias.huapaya.repair_shop.presentation.exception.ExceptionMessage;
import dev.arias.huapaya.repair_shop.service.interfaces.MasterDetailService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MasterDetailImplementation implements MasterDetailService {

    private final MasterRepository masterRepository;

    private final MasterDetailRepository detailRepository;

    @Override
    public MasterDetailEntity create(MasterDetailCreateDTO data) {
        Optional<MasterEntity> masterOpt = this.masterRepository.findById(data.getIdMaster());
        if (!masterOpt.isPresent()) {
            throw new ExceptionMessage("Not found master");
        }
        MasterEntity master = masterOpt.get();
        boolean exists = master.getMaster_details().stream()
                .anyMatch(detail -> detail.getDescription().equals(data.getDescription().toUpperCase())
                        && detail.getValue().equals(data.getValue().toUpperCase()));

        if (exists) {
            throw new ExceptionMessage("Duplicate detail");
        }
        MasterDetailEntity masterDetailEntity = MasterDetailEntity.builder()
                .description(data.getDescription().toUpperCase())
                .value(data.getValue().toUpperCase())
                .build();
        master.getMaster_details().add(masterDetailEntity);
        this.masterRepository.save(master);
        return masterDetailEntity;
    }

    @Override
    public MasterDetailEntity update(MasterDetailUpdateDTO data, Long id) {
        Optional<MasterDetailEntity> masterDetailOpt = this.detailRepository.findById(id);
        if (!masterDetailOpt.isPresent()) {
            throw new ExceptionMessage("Not found master detail");
        }
        MasterDetailEntity masterDetailEntity = MasterDetailEntity.builder()
                .id(id)
                .description(data.getDescription().toUpperCase())
                .value(data.getValue().toUpperCase())
                .build();
        return this.detailRepository.save(masterDetailEntity);
    }

}
