package com.croma.server.controller;

import com.croma.server.model.Client;
import com.croma.server.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

  private final ClientRepository clientRepository;

  @Autowired
  public ClientController(ClientRepository clientRepository) {
      this.clientRepository = clientRepository;
  }

  @GetMapping
  public List<Client> getAllClients() {
      return clientRepository.findAll();
  }

  @PostMapping
  public Client createClient(@RequestBody Client client) {
      return clientRepository.save(client);
  }

  @PutMapping("/{id}")
  public Client updateClient(@PathVariable Long id, @RequestBody Client updatedClient) {
    return clientRepository.findById(id)
      .map(client -> {
        client.setName(updatedClient.getName());
        client.setEmail(updatedClient.getEmail());
        client.setPhone(updatedClient.getPhone());
        client.setCompanyName(updatedClient.getCompanyName());
        return clientRepository.save(client);
      })
      .orElseThrow(() -> new RuntimeException("Client not found"));
  }

  @DeleteMapping("/{id}")
  public void deleteClient(@PathVariable Long id) {
    clientRepository.deleteById(id);
  }
}

