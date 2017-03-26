package br.edu.ufcg.computacao.si1.controller;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufcg.computacao.si1.exceptions.UserNotFoundException;
import br.edu.ufcg.computacao.si1.models.advertisement.Advertisement;
import br.edu.ufcg.computacao.si1.models.advertisement.FurnitureAdvertisement;
import br.edu.ufcg.computacao.si1.services.AdvertisementServiceImpl;
import br.edu.ufcg.computacao.si1.services.AuthenticationService;



@CrossOrigin
@RestController
@RequestMapping(value = "api/advertisements")
public class AdvertisementController {
	
	@Autowired
	private AuthenticationService authenticationService;

    @Autowired
    private AdvertisementServiceImpl advertisementService;

    public AdvertisementController(AdvertisementServiceImpl advertisementService) {
        this.advertisementService = advertisementService;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Advertisement> addAdvertisement(@RequestBody Advertisement ad, @RequestHeader(value="Authorization") String token){
        
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

    @RequestMapping(
            method = RequestMethod.GET
    )
    public ResponseEntity<Collection<Advertisement>> getAllAdvertisements(){
        Collection<Advertisement> ads = advertisementService.getAds();

        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

}
