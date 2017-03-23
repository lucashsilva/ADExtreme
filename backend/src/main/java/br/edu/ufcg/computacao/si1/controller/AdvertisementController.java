package br.edu.ufcg.computacao.si1.controller;


import br.edu.ufcg.computacao.si1.exceptions.InvalidAdvertisementUserException;
import br.edu.ufcg.computacao.si1.models.advertisement.*;
import br.edu.ufcg.computacao.si1.services.AdvertisementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@CrossOrigin
@RestController
@RequestMapping(value = "api/advertisements")
public class AdvertisementController {

    @Autowired
    private AdvertisementServiceImpl advertisementService;

    public AdvertisementController(AdvertisementServiceImpl advertisementService) {
        this.advertisementService = advertisementService;
    }

    @RequestMapping(
            value = "/new_furniture",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Advertisement> addAdvertisement(@RequestBody FurnitureAdvertisement ad){
        try {
            advertisementService.create(ad);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (InvalidAdvertisementUserException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(
            value = "/new_job",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Advertisement> addAdvertisement(@RequestBody JobAdvertisement ad){
        try {
            advertisementService.create(ad);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (InvalidAdvertisementUserException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(
            value = "/new_realty",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Advertisement> addAdvertisement(@RequestBody RealtyAdvertisement ad){
        try {
            advertisementService.create(ad);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (InvalidAdvertisementUserException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(
            value = "/new_service",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Advertisement> addAdvertisement(@RequestBody ServiceAdvertisement ad){
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
    public ResponseEntity<Collection<Advertisement>> getAllAdvertisements(){
        Collection<Advertisement> ads = advertisementService.getAds();

        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

}
