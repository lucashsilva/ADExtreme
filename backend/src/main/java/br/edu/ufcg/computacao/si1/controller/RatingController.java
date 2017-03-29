package br.edu.ufcg.computacao.si1.controller;


import br.edu.ufcg.computacao.si1.exceptions.RatingException;
import br.edu.ufcg.computacao.si1.exceptions.UserNotFoundException;
import br.edu.ufcg.computacao.si1.models.rating.Rating;
import br.edu.ufcg.computacao.si1.services.AuthenticationService;
import br.edu.ufcg.computacao.si1.services.RatingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

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

    /***
     * Add a new rating to a user or advertisement
     * @param rating must not be null
     * @param token must be a valid token
     * @return Http status OK if the ratings was successfully created,
     *          FORBIDDEN if the user was not found and
     *          BAD_REQUEST if the user was not allowed to do this operation
     */
    @RequestMapping(
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Rating> addRating(@RequestBody Rating rating,
                                            @RequestHeader(value = "Authorization") String token){
        try {
            rating.setPublisherName(authenticationService
                    .getUserFromToken(token).get().getName().toString());

            ratingService.create(rating);
        } catch (RatingException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /***
     * Get all ratings related to the given entity id
     * @param id must be a valid id
     * @param token must be a valid token
     * @return a collection of ratings
     */
    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Collection<Rating>> getRatingsById(@PathVariable Long id,
                                                             @RequestHeader(value = "Authorization")  String token){
        try {
            authenticationService.getUserFromToken(token);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ratingService.getRatingsByRatedEntityId(id), HttpStatus.OK);
    }

    /***
     * Get the average grade from all ratings related to the given entity id
     * @param id must be a valid id
     * @param token must be a valid token
     * @return the average grade of all ratings
     */
    @RequestMapping(
            value = "/average/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Double> getAverageRating(@PathVariable Long id,
                                                   @RequestHeader(value = "Authorization")  String token){
        try {
            authenticationService.getUserFromToken(token);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        double average = ratingService.getAverageRating(id);
        return new ResponseEntity<Double>(average, HttpStatus.OK);
    }
}
