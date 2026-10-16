package br.com.nexuscloud.exceptions;

public class ServidorOfflineException extends RuntimeException{
    public ServidorOfflineException(String mensagem){
        super(mensagem);
    }
}
