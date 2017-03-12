package br.edu.ufcg.computacao.si1.controller;


import br.edu.ufcg.computacao.si1.exceptions.InvalidAdvertisingUserException;
import br.edu.ufcg.computacao.si1.models.Advertising;
import br.edu.ufcg.computacao.si1.services.AdvertisingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@CrossOrigin
@RestController
@RequestMapping(value = "api/advertisings")
public class AdvertisingController {

    @Autowired
    private AdvertisingServiceImpl advertisingService;


    @RequestMapping(
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Advertising> addAdvertising(@RequestBody Advertising ad){
        try {
            advertisingService.create(ad);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (InvalidAdvertisingUserException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(
            method = RequestMethod.GET
    )
    public ResponseEntity<Collection<Advertising>> getAdvertisings(){
        Collection<Advertising> ads = advertisingService.getAds();

        return new ResponseEntity<>(ads, HttpStatus.OK);
    }




}
