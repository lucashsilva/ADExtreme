package br.edu.ufcg.computacao.si1.exceptions;


public class EntityNotFoundException extends Exception {

    public EntityNotFoundException(String message){
        super("Entity not found. " + message);
    }

    public EntityNotFoundException(){
        super("Entity not found. ");
    }
}
