package com.example.taskmanagement.controller;

import com.example.taskmanagement.entity.Client;
import com.example.taskmanagement.serivce.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin("*")
public class ClientController {

    private final ClientService clientService;
    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @PostMapping()
    public ResponseEntity<Client> ajouterClient(@RequestBody Client client){
        Client nouveauClient = clientService.ajouterClient(client);
        return new ResponseEntity<>(nouveauClient, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Client> modifierClient(@RequestBody Client client,@PathVariable("id")long id) {
        try {
            Client client1 = clientService.modfierClient(id, client);
            return ResponseEntity.ok(client1);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping()
    public ResponseEntity<List<Client>> getAllClient() {
       List<Client> listclient=   clientService.listDesClients();
       return ResponseEntity.ok(listclient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerClientId(@PathVariable Long id) {
        try {
            clientService.supprimerClient(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Client>> getTaskById(@PathVariable Long id) {
        try {
            Optional<Client> client = clientService.getClientById(id);
            return ResponseEntity.ok(client);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Client>> searchClients(
            @RequestParam(value = "value", required = false) String value) {

        // Si aucun paramètre n'est fourni, renvoie une erreur 400 (Bad Request)
        if ((value == null || value.isEmpty())) {
            return ResponseEntity.badRequest().body(null);
        }

        // Appel de la méthode dans le repository
        List<Client> clients = clientService.searchClients(value != null ? value : "");

        // Retourne une réponse avec les résultats
        if (clients.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content si aucun résultat
        }
        return ResponseEntity.ok(clients); // 200 OK avec la liste des clients
    }

    @GetMapping("/getPagination")
    public ResponseEntity<Page<Client>> getAllClient(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        // Appeler la méthode dans le service pour récupérer les clients avec pagination
        Page<Client> clients = clientService.listDesClients(page, size);

        // Retourner la réponse avec la page de clients
        return ResponseEntity.ok(clients);
    }






}
