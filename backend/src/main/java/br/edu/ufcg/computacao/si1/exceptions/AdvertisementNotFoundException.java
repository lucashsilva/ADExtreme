package br.edu.ufcg.computacao.si1.exceptions;

public class AdvertisementNotFoundException extends EntityNotFoundException {

    public AdvertisementNotFoundException() {
        super("Advertisement does not exist. ");
    }
}
