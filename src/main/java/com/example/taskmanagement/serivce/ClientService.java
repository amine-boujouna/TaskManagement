package com.example.taskmanagement.serivce;

import com.example.taskmanagement.entity.Client;
import com.example.taskmanagement.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public Client ajouterClient(Client client){
        return clientRepository.save(client);
    }

    public Client modfierClient(long id,Client client){
        Optional<Client> optianlclient=clientRepository.findById(id);
        if(optianlclient.isPresent()){
            Client existclient = optianlclient.get();
            existclient.setAdress(client.getAdress());
            existclient.setCity(client.getCity());
            existclient.setDatenaissance(client.getDatenaissance());
            existclient.setEmail(client.getEmail());
            existclient.setNom(client.getNom());
            existclient.setPrenom(client.getPrenom());
            existclient.setTelephone(client.getTelephone());
            return clientRepository.save(client);
        }else{
                throw new RuntimeException("Client non trouveé avec l'id"+id);
        }
    }

    public void supprimerClient(long id){
        clientRepository.deleteById(id);
    }
    public List<Client> listDesClients(){
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(long id){
        return clientRepository.findById(id);
    }

    public List<Client> searchClients(String value) {
        return clientRepository.findByAnyParameter(value);
    }

    public Page<Client> listDesClients(int page, int size) {
        Pageable pageable = PageRequest.of(page, size); // Définir la page et la taille de la page
        return clientRepository.findAll(pageable); // Retourner la page de clients
    }
}
