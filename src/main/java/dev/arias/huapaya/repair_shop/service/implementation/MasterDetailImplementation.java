package dev.arias.huapaya.repair_shop.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.arias.huapaya.repair_shop.persistence.entity.MasterDetailEntity;
import dev.arias.huapaya.repair_shop.persistence.entity.MasterEntity;
import dev.arias.huapaya.repair_shop.persistence.repository.MasterDetailRepository;
import dev.arias.huapaya.repair_shop.persistence.repository.MasterRepository;
import dev.arias.huapaya.repair_shop.presentation.dto.main.PageDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.master_detail.MasterDetailCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.master_detail.MasterDetailFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.master_detail.MasterDetailPaginationDTO;
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
                .master(master)
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
        MasterEntity master = masterDetailOpt.get().getMaster();
        MasterDetailEntity masterDetailEntity = MasterDetailEntity.builder()
                .id(id)
                .master(master)
                .description(data.getDescription().toUpperCase())
                .value(data.getValue().toUpperCase())
                .build();
        return this.detailRepository.save(masterDetailEntity);
    }

    @Override
    public PageDTO<MasterDetailPaginationDTO> pagination(Long id, Pageable pageable) {
        Page<MasterDetailEntity> detailPage = this.detailRepository.findByMaster_Id(id, pageable);
        List<MasterDetailPaginationDTO> detailDTO = detailPage.getContent()
                .stream()
                .map(detail -> new MasterDetailPaginationDTO(detail))
                .toList();
        return new PageDTO<>(detailDTO, detailPage.getNumber(), detailPage.getSize(), detailPage.getTotalElements());
    }

    @Override
    public MasterDetailFindOneDTO findById(Long id) {
        Optional<MasterDetailEntity> masterDetailOpt = this.detailRepository.findById(id);
        if (!masterDetailOpt.isPresent()) {
            throw new ExceptionMessage("Not found master detail");
        }
        MasterDetailEntity masterDetail = masterDetailOpt.get();
        MasterDetailFindOneDTO masterDetailDTO = MasterDetailFindOneDTO.builder()
                .id(id)
                .description(masterDetail.getDescription())
                .value(masterDetail.getValue())
                .build();
        return masterDetailDTO;
    }

}
