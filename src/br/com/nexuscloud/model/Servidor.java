package br.com.nexuscloud.model;

import java.rmi.server.ServerCloneException;
import java.util.ArrayList;
import java.util.List;

import br.com.nexuscloud.exceptions.CapacidadeExcedidaException;
import br.com.nexuscloud.exceptions.ServidorOfflineException;

public class Servidor {
    private String ip;
    private double precoPorHora;
    private double ramTotal;
    private boolean statusLigado;
    private List<Container> containers = new ArrayList<>();
    
    public Servidor(String ip, double precoPorHora, double ramTotal) {
        this.ip = ip;
        this.precoPorHora = precoPorHora;
        this.ramTotal = ramTotal;
        this.statusLigado = false; //statusLigado nasce desligado
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ip == null) ? 0 : ip.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Servidor other = (Servidor) obj;
        if (ip == null) {
            if (other.ip != null)
                return false;
        } else if (!ip.equals(other.ip))
            return false;
        return true;
    }

    public void ligar(){
        statusLigado = true;
    }

    public void desligar(){
        statusLigado = false;
    }

    public double calcularRamEmUso(){
        return containers.stream()
                .mapToDouble(c -> c.getRamNecessaria())
                .sum();
    }

    public void alocarContainer(Container c) throws CapacidadeExcedidaException{
        if(statusLigado == false){
            throw new ServidorOfflineException("Erro: não é possível alocar containers em um servidor desligado.");
        }

        double ramEmUso = calcularRamEmUso();
        if((ramEmUso + c.getRamNecessaria()) > ramTotal){
            throw new  CapacidadeExcedidaException("Falha Crítica: Memória RAM insuficiente no servidor " + ip + ".");
        }
        containers.add(c);

    }

    public double getPrecoPorHora() {
        return precoPorHora;
    }

    public boolean isStatusLigado(){
        return  statusLigado;
    }

    
}
