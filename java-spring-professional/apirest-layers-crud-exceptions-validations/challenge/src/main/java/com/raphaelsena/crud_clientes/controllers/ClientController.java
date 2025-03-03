package com.raphaelsena.crud_clientes.controllers;

import com.raphaelsena.crud_clientes.models.dtos.ClientDTO;
import com.raphaelsena.crud_clientes.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
        ClientDTO clientDTO = clientService.findById(id);
        return ResponseEntity.ok(clientDTO);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> create(@RequestBody ClientDTO clientDTO) {
        ClientDTO createdClientDTO = clientService.create(clientDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(clientDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(createdClientDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        ClientDTO updatedClientDTO = clientService.update(id, clientDTO);
        return ResponseEntity.ok(updatedClientDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
