package com.raphaelsena.crud_clientes.services;

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.raphaelsena.crud_clientes.models.Client;
import com.raphaelsena.crud_clientes.models.dtos.ClientDTO;
import com.raphaelsena.crud_clientes.repositories.ClientRepository;
import com.raphaelsena.crud_clientes.services.exceptions.DatabaseException;
import com.raphaelsena.crud_clientes.services.exceptions.ObjectNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado."));
        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO create(ClientDTO obj) {
        Client client = new Client();
        return fromDTO(obj, client);
    }


    @Transactional
    public ClientDTO update(Long id, ClientDTO obj) {
        try {
            Client client = clientRepository.getReferenceById(id);
            return fromDTO(obj, client);
        } catch (EntityNotFoundException e) {
            throw new ObjectNotFoundException("Objeto não encontrado.");
        }
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

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ObjectNotFoundException("Objeto não encontrado");
        }

        try {
            clientRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial. Há entidades relacionadas a este cliente.");
        }
    }
}
