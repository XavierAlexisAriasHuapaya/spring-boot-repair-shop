package dev.arias.huapaya.repair_shop.service.implementation;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.arias.huapaya.repair_shop.persistence.entity.MasterEntity;
import dev.arias.huapaya.repair_shop.persistence.repository.MasterRepository;
import dev.arias.huapaya.repair_shop.presentation.dto.main.PageDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.master.MasterFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.master_detail.MasterDetailPaginationDTO;
import dev.arias.huapaya.repair_shop.presentation.exception.ExceptionMessage;
import dev.arias.huapaya.repair_shop.service.interfaces.MasterDetailService;
import dev.arias.huapaya.repair_shop.service.interfaces.MasterService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MasterImplementation implements MasterService {

    private final MasterRepository repository;

    private final MasterDetailService masterDetailService;

    @Override
    public MasterFindOneDTO findOne(Pageable pageable, Long id) {
        Optional<MasterEntity> masterOpt = this.repository.findById(id);
        if (!masterOpt.isPresent()) {
            throw new ExceptionMessage("Not found master");
        }
        MasterEntity master = masterOpt.get();
        PageDTO<MasterDetailPaginationDTO> pageMasterDetail = this.masterDetailService.pagination(id, pageable);
        MasterFindOneDTO masterDTO = MasterFindOneDTO.builder()
                .id(id)
                .description(master.getDescription())
                .master_details(pageMasterDetail)
                .status(master.getStatus())
                .build();
        return masterDTO;
    }

}
