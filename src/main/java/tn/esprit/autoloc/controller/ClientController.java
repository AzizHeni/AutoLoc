
package tn.esprit.autoloc.controller;

import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.service.ClientService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(
            @PathVariable Long id) {
        return clientService.getClientById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientService.saveClient(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(
            @PathVariable Long id,
            @RequestBody Client updatedClient) {

        return clientService.getClientById(id)
                .map(existing -> {
                    existing.setNom(updatedClient.getNom());
                    existing.setPrenom(updatedClient.getPrenom());
                    existing.setEmail(updatedClient.getEmail());
                    existing.setTelephone(updatedClient.getTelephone());

                    return ResponseEntity.ok(
                            clientService.saveClient(existing));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(
            @PathVariable Long id) {

        if (clientService.getClientById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
