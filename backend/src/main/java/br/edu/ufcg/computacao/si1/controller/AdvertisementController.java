package br.edu.ufcg.computacao.si1.controller;


import br.edu.ufcg.computacao.si1.exceptions.UserNotFoundException;
import br.edu.ufcg.computacao.si1.models.advertisement.Advertisement;
import br.edu.ufcg.computacao.si1.services.AdvertisementServiceImpl;
import br.edu.ufcg.computacao.si1.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;


@CrossOrigin
@RestController
@RequestMapping(value = "api/advertisements")
public class AdvertisementController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AdvertisementServiceImpl advertisementService;

    public AdvertisementController(AdvertisementServiceImpl advertisementService, AuthenticationService authenticationService) {
        this.advertisementService = advertisementService;
        this.authenticationService = authenticationService;

    }

    @RequestMapping(
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Advertisement> addAdvertisement(@RequestBody Advertisement ad, @RequestHeader(value = "Authorization") String token) {

        try {
            ad.setUser(authenticationService.getUserFromToken(token).get());
            advertisementService.create(ad);

        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @CrossOrigin
    @RequestMapping(
            method = RequestMethod.GET
    )
    public ResponseEntity<Collection<Advertisement>> getAds(@RequestParam(value = "type", required = false) String type, @RequestParam(value = "startDate", required = false) Long startDate, @RequestParam(value = "endDate", required = false) Long endDate) throws IOException {

        if (type != null) {
            return new ResponseEntity<>(advertisementService.getAdByType(type), HttpStatus.OK);
        }

        if (startDate != null) {
            return new ResponseEntity<>(advertisementService.getAdsByDate(getDate(startDate), getDate(endDate)), HttpStatus.OK);
        }

        return new ResponseEntity<>(advertisementService.getAds(), HttpStatus.OK);
    }

    private Date getDate(Long date) throws IOException {
        if (date != null) {
            Timestamp timestamp = new Timestamp(date);
            return new Date(timestamp.getTime());
        } else {
            return new Date();
        }
    }

}
