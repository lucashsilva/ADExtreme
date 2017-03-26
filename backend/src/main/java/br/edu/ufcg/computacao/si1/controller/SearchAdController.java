package br.edu.ufcg.computacao.si1.controller;

import br.edu.ufcg.computacao.si1.models.advertisement.Advertisement;
import br.edu.ufcg.computacao.si1.services.AdvertisementServiceImpl;
import br.edu.ufcg.computacao.si1.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;

/**
 * Created by gustavo on 25/03/17.
 */

@CrossOrigin
@RestController
@RequestMapping(value = "api/advertisements/find")
public class SearchAdController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AdvertisementServiceImpl advertisementService;

    public SearchAdController(AdvertisementServiceImpl advertisementService) {
        this.advertisementService = advertisementService;
    }

    @RequestMapping(
            method = RequestMethod.POST
    )
    public ResponseEntity<Collection<Advertisement>> getAdvertisementsByDate(@RequestParam("initialDate") Date initialDate, @RequestParam("finalDate")Date finalDate){

        return new ResponseEntity<>(advertisementService.getAdsByDate(initialDate, finalDate), HttpStatus.OK);
    }

}
