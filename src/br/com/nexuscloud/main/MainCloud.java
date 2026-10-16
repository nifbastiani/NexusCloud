package br.com.nexuscloud.main;

import br.com.nexuscloud.exceptions.CapacidadeExcedidaException;
import br.com.nexuscloud.exceptions.ServidorOfflineException;
import br.com.nexuscloud.model.Cliente;
import br.com.nexuscloud.model.CloudManager;
import br.com.nexuscloud.model.Container;
import br.com.nexuscloud.model.Servidor;

public class MainCloud {
    public static void main(String[] args) {
        CloudManager banco = new CloudManager();
        Cliente cliente1 = new  Cliente("111.222.333-44", "Wayne Enterprises");
        Servidor servidor1 = new Servidor("192.168.0.1", 5.50, 16.0);
        banco.registrarCliente(cliente1);
        Container container1 = new Container("C-01", 10.0);
        Container container2 = new Container("C-02", 8.0);

        //servidor1.alocarContainer(container1);

        servidor1.ligar();
        try {
            servidor1.alocarContainer(container1);
        } catch (CapacidadeExcedidaException e) {
            e.getMessage();
        }
        

        try {
            servidor1.alocarContainer(container2);
        } catch (CapacidadeExcedidaException e) {
            System.out.println("Capturado com sucesso" + e.getMessage());
        }

        System.out.println("Valor da fatura ativa: R$" + banco.calcularFaturaAtiva(cliente1));
    }
}
