package com.raphaelsena.crud_clientes.services;

import com.raphaelsena.crud_clientes.models.Client;
import com.raphaelsena.crud_clientes.models.dtos.ClientDTO;
import com.raphaelsena.crud_clientes.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public ClientDTO create(ClientDTO obj) {
        Client client = new Client();
        client.setName(obj.getName());
        client.setCpf(obj.getCpf());
        client.setIncome(obj.getIncome());
        client.setBirthDate(obj.getBirthDate());
        client.setChildren(obj.getChildren());

        client = clientRepository.save(client);

        return new ClientDTO(client);
    }
}
