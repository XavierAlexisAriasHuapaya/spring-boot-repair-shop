package dev.arias.huapaya.repair_shop.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.arias.huapaya.repair_shop.persistence.entity.ClientEntity;
import dev.arias.huapaya.repair_shop.persistence.repository.ClientRepository;
import dev.arias.huapaya.repair_shop.presentation.dto.client.ClientCreateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.client.ClientFindOneDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.client.ClientPaginationDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.client.ClientUpdateDTO;
import dev.arias.huapaya.repair_shop.presentation.dto.main.PageDTO;
import dev.arias.huapaya.repair_shop.service.interfaces.ClientService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ClientImplementation implements ClientService {

    private final ClientRepository repository;

    @Override
    public ClientEntity create(ClientCreateDTO client) {
        ClientEntity clientCreate = ClientEntity.builder()
                .typePerson(client.getTypePerson())
                .gender(client.getGender())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .brithOrAnniversary(client.getBrithOrAnniversary())
                .address(client.getAddress())
                .phone(client.getPhone())
                .email(client.getEmail())
                .occupation(client.getOccupation())
                .observation(client.getObservation())
                .build();
        return this.repository.save(clientCreate);
    }

    @Override
    public Optional<ClientFindOneDTO> findOne(Long id) {
        Optional<ClientEntity> clientFind = this.repository.findById(id);
        if (!clientFind.isPresent()) {
            return Optional.empty();
        }
        ClientEntity client = clientFind.get();
        ClientFindOneDTO dto = ClientFindOneDTO.builder()
                .id(id)
                .typePerson(client.getTypePerson())
                .gender(client.getGender())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .brithOrAnniversary(client.getBrithOrAnniversary())
                .address(client.getAddress())
                .phone(client.getPhone())
                .email(client.getEmail())
                .occupation(client.getOccupation())
                .observation(client.getObservation())
                .createdAt(client.getCreatedAt())
                .updatedAt(client.getUpdatedAt())
                .status(client.getStatus())
                .build();
        return Optional.of(dto);
    }

    @Override
    public ClientEntity update(ClientUpdateDTO client, Long id) {
        ClientEntity clientFindOne = this.repository.findById(id).get();
        clientFindOne.setTypePerson(client.getTypePerson());
        clientFindOne.setGender(client.getGender());
        clientFindOne.setFirstName(client.getFirstName());
        clientFindOne.setLastName(client.getLastName());
        clientFindOne.setBrithOrAnniversary(client.getBrithOrAnniversary());
        clientFindOne.setAddress(client.getAddress());
        clientFindOne.setPhone(client.getPhone());
        clientFindOne.setEmail(client.getEmail());
        clientFindOne.setOccupation(client.getOccupation());
        clientFindOne.setObservation(client.getObservation());
        return this.repository.save(clientFindOne);
    }

    @Override
    public PageDTO<ClientPaginationDTO> pagination(Pageable pageable) {
        Page<ClientEntity> clientPage = this.repository.findAll(pageable);
        List<ClientPaginationDTO> clientList = clientPage.getContent()
                .stream()
                .map(client -> new ClientPaginationDTO(client))
                .toList();
        return new PageDTO<>(clientList, clientPage.getNumber(), clientPage.getSize(),
                clientPage.getTotalElements());
    }

}
