package br.edu.ufcg.computacao.si1.controller;


import br.edu.ufcg.computacao.si1.models.rating.Rating;
import br.edu.ufcg.computacao.si1.services.AuthenticationService;
import br.edu.ufcg.computacao.si1.services.RatingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "api/ratings")
public class RatingController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private RatingServiceImpl ratingService;

    public RatingController(RatingServiceImpl ratingService, AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
        this.ratingService = ratingService;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Rating> addRating(@RequestBody Rating rating, @RequestHeader(value = "Authorization") String token){

        try {
            rating.setPublisherName(authenticationService
                    .getUserFromToken(token).get().getName().toString());

            ratingService.create(rating);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);

    }
}
