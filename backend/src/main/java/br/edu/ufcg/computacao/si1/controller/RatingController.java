package br.edu.ufcg.computacao.si1.controller;


import br.edu.ufcg.computacao.si1.exceptions.UserAutoRatingException;
import br.edu.ufcg.computacao.si1.exceptions.UserRatingOwnAdvertisementException;
import br.edu.ufcg.computacao.si1.models.rating.AdvertisementRating;
import br.edu.ufcg.computacao.si1.models.rating.UserRating;
import br.edu.ufcg.computacao.si1.services.AdvertisementRatingServiceImpl;
import br.edu.ufcg.computacao.si1.services.UserRatingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "api/rating")
public class RatingController {

    @Autowired
    private UserRatingServiceImpl userRatingService;

    @Autowired
    private AdvertisementRatingServiceImpl advertisementRatingService;

    public RatingController(UserRatingServiceImpl userRatingService, AdvertisementRatingServiceImpl advertisementRatingService) {
        this.userRatingService = userRatingService;
        this.advertisementRatingService = advertisementRatingService;
    }

    @RequestMapping(
            value = "/user",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserRating> addUserRating(@RequestBody UserRating userRating){

        try {
            userRatingService.create(userRating);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (UserAutoRatingException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(
            value = "/advertisement",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AdvertisementRating> addAdvertisementRating(@RequestBody AdvertisementRating advertisementRating){

        try {
            advertisementRatingService.create(advertisementRating);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (UserRatingOwnAdvertisementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }




}
