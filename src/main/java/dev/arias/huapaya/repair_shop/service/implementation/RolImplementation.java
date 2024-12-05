package dev.arias.huapaya.repair_shop.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.arias.huapaya.repair_shop.persistence.entity.RolEntity;
import dev.arias.huapaya.repair_shop.persistence.repository.RolRepository;
import dev.arias.huapaya.repair_shop.presentation.dto.main.PageDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.rol.RolCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.rol.RolFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.rol.RolPaginationDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.rol.RolUpdateDTO;
import dev.arias.huapaya.repair_shop.service.interfaces.RolService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RolImplementation implements RolService {

    private final RolRepository repository;

    @Transactional(readOnly = false)
    @Override
    public RolEntity create(RolCreateDTO rol) {
        RolEntity rolCreate = RolEntity.builder()
                .description(rol.getDescription())
                .build();
        return this.repository.save(rolCreate);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<RolFindOneDTO> findOne(Long id) {
        Optional<RolEntity> rolOpt = this.repository.findById(id);
        if (!rolOpt.isPresent()) {
            return Optional.empty();
        }
        RolEntity rol = rolOpt.get();
        RolFindOneDTO dto = RolFindOneDTO.builder()
                .id(id)
                .description(rol.getDescription())
                .status(rol.getStatus())
                .build();
        return Optional.of(dto);
    }

    @Transactional(readOnly = false)
    @Override
    public RolEntity update(RolUpdateDTO rol, Long id) {
        RolEntity findUpdate = this.repository.findById(id).get();
        findUpdate.setDescription(rol.getDescription());
        return this.repository.save(findUpdate);
    }

    @Transactional(readOnly = true)
    @Override
    public PageDTO<RolPaginationDTO> pagination(Pageable pageable) {
        Page<RolEntity> rolPage = this.repository.findAll(pageable);
        List<RolPaginationDTO> dtoPage = rolPage.stream()
                .map(rol -> new RolPaginationDTO(rol))
                .toList();
        return new PageDTO<>(dtoPage, rolPage.getNumber(), rolPage.getSize(), rolPage.getTotalElements());
    }

}
