package br.com.alura.command.pet;

import java.io.IOException;

import br.com.alura.client.ClientHTTPConfig;
import br.com.alura.command.Command;
import br.com.alura.service.PetService;

public class ImportarPetsAbrigoCommand implements Command{

    @Override
    public void execute() {
        try {
            ClientHTTPConfig clientHTTPConfig = new ClientHTTPConfig();

            PetService petService = new PetService(clientHTTPConfig);
            petService.importarPetsAbrigo();

        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

}
