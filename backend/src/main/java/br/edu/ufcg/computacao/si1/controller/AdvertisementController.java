package br.edu.ufcg.computacao.si1.controller;


import br.edu.ufcg.computacao.si1.exceptions.InvalidAdvertisementUserException;
import br.edu.ufcg.computacao.si1.models.advertisement.Advertisement;
import br.edu.ufcg.computacao.si1.services.AdvertisementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@CrossOrigin
@RestController
@RequestMapping(value = "api/advertisement")
public class AdvertisementController {

    @Autowired
    private AdvertisementServiceImpl advertisementService;

    public AdvertisementController(AdvertisementServiceImpl advertisementService) {
        this.advertisementService = advertisementService;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Advertisement> addAdvertisement(@RequestBody Advertisement ad){
        try {
            advertisementService.create(ad);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (InvalidAdvertisementUserException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(
            method = RequestMethod.GET
    )
    public ResponseEntity<Collection<Advertisement>> getAdvertisement(){
        Collection<Advertisement> ads = advertisementService.getAds();

        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

}
