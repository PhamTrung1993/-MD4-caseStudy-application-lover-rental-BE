package com.codegym.controller.rating;

import com.codegym.model.DTO.RatingDTO;
import com.codegym.model.Provider;
import com.codegym.model.Rating;
import com.codegym.service.provider.IProviderService;
import com.codegym.service.rating.IRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@PropertySource("classpath:application.properties")
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    IRatingService ratingService;

    @Autowired
    IProviderService provideService;

    @GetMapping("/{providerId}")
    public ResponseEntity<Iterable<Rating>> getRating(@PathVariable Long providerId){
        Iterable<Rating> ratings = ratingService.findByProvider_Id(providerId);
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity<Rating> save(@RequestBody RatingDTO ratingDTO){
        Rating rating = new Rating();
        rating.setComment(ratingDTO.getComment());
        Provider provider = provideService.findById(ratingDTO.getProviderId()).get();
        rating.setProvider(provider);
        return new ResponseEntity<>(ratingService.save(rating), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Rating> delete(@PathVariable Long id){
        if (!ratingService.findById(id).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Rating rating = ratingService.findById(id).get();
        ratingService.delete(id);
        return new ResponseEntity<>(rating,HttpStatus.OK);
    }
}
