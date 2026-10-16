package br.com.nexuscloud.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CloudManager {
    private Map<Cliente, Set<Servidor>> bancoDeDados = new HashMap<>();

    public  CloudManager(){

    }

    public void registrarCliente(Cliente c){
        if(!bancoDeDados.containsKey(c)){ //se cliente não existir
            bancoDeDados.put(c, new HashSet<>()); //registra o cliente
            //usa-se HashSet para garantir que não haja servidores duplicados e sem ordem específica
        }
    }

    public void provisionarServidor(Cliente c, Servidor s){
        registrarCliente(c); //cadastra o cliente no mapa antes de adicionar o servidor
        bancoDeDados.get(c).add(s); //adiciona o servidor ao Set do cliente correspondente
    }

    public double calcularFaturaAtiva(Cliente c){
        Set<Servidor> servidoresDoCliente = bancoDeDados.get(c);
        //se o cliente não existir ou não ter servidor a fatura é 0
        if(servidoresDoCliente == null){
            return 0.0;
        }

        return servidoresDoCliente.stream()
                .filter(s -> s.isStatusLigado()) //filtra apenas os servidores ligados
                .mapToDouble(s -> s.getPrecoPorHora()) //pega o preço por hora de cada servidor
                .sum(); //soma todos os preços que foram filtrados
    }
}
