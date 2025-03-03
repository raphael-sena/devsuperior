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

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Client client = clientRepository.findById(id).get();
        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO create(ClientDTO obj) {
        Client client = new Client();
        return fromDTO(obj, client);
    }


    @Transactional
    public ClientDTO update(Long id, ClientDTO obj) {
        Client client = clientRepository.getReferenceById(id);
        return fromDTO(obj, client);
    }

    private ClientDTO fromDTO(ClientDTO obj, Client client) {
        client.setName(obj.getName());
        client.setCpf(obj.getCpf());
        client.setIncome(obj.getIncome());
        client.setBirthDate(obj.getBirthDate());
        client.setChildren(obj.getChildren());

        client = clientRepository.save(client);

        return new ClientDTO(client);
    }

    @Transactional
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}
