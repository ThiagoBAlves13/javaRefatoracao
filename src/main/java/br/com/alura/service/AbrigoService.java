package br.com.alura.service;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.alura.client.ClientHTTPConfig;
import br.com.alura.domain.Abrigo;

public class AbrigoService {

    private final ClientHTTPConfig clientHTTPConfig;

    public AbrigoService(ClientHTTPConfig clientHTTPConfig) {
        this.clientHTTPConfig = clientHTTPConfig;
    }

    public void listarAbrigos() throws InterruptedException, IOException {

        String uri = "http://localhost:8080/abrigos";
        HttpResponse<String> response = clientHTTPConfig.dispararRequisicaoGet(uri);

        String responseBody = response.body();
        Abrigo[] abrigos = new ObjectMapper().readValue(responseBody, Abrigo[].class);
        List<Abrigo> abrigoList = Arrays.stream(abrigos).toList();
        System.out.println("Abrigos cadastrados:");
        for (Abrigo abrigo : abrigoList) {
            long id = abrigo.getId();
            String nome = abrigo.getNome();
            System.out.println(id + " - " + nome);
        }
    }

    public void cadastrarAbrigo() throws InterruptedException, IOException {
        System.out.println("Digite o nome do abrigo:");
        String nome = new Scanner(System.in).nextLine();
        System.out.println("Digite o telefone do abrigo:");
        String telefone = new Scanner(System.in).nextLine();
        System.out.println("Digite o email do abrigo:");
        String email = new Scanner(System.in).nextLine();

        Abrigo abrigo = new Abrigo(nome, telefone, email);

        String uri = "http://localhost:8080/abrigos";
        HttpResponse<String> response = clientHTTPConfig.dispararRequisicaoPost(uri, abrigo);

        int statusCode = response.statusCode();
        String responseBody = response.body();
        if (statusCode == 200) {
            System.out.println("Abrigo cadastrado com sucesso!");
            System.out.println(responseBody);
        } else if (statusCode == 400 || statusCode == 500) {
            System.out.println("Erro ao cadastrar o abrigo:");
            System.out.println(responseBody);
        }
    }

}
