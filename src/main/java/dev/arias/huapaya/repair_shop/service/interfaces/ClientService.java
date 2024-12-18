package dev.arias.huapaya.repair_shop.service.interfaces;

import java.util.Optional;

import org.springframework.data.domain.Pageable;

import dev.arias.huapaya.repair_shop.persistence.entity.ClientEntity;
import dev.arias.huapaya.repair_shop.presentation.dto.client.ClientCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.client.ClientFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.client.ClientPaginationDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.client.ClientUpdateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.main.PageDTO;

public interface ClientService {

    public ClientEntity create(ClientCreateDTO client);

    public Optional<ClientFindOneDTO> findOne(Long id);

    public ClientEntity update(ClientUpdateDTO client, Long id);

    public PageDTO<ClientPaginationDTO> pagination(Pageable pageable);

}
