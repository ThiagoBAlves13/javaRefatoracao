package br.com.alura.command.abrigo;

import java.io.IOException;

import br.com.alura.client.ClientHTTPConfig;
import br.com.alura.command.Command;
import br.com.alura.service.AbrigoService;

public class ListarAbrigoCommand implements Command {

    @Override
    public void execute() {
        try {
            ClientHTTPConfig clientHTTPConfig = new ClientHTTPConfig();

            AbrigoService abrigoService = new AbrigoService(clientHTTPConfig);
            abrigoService.listarAbrigos();

        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }

}
