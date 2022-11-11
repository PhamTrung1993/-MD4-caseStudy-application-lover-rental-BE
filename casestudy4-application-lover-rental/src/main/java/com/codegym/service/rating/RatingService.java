package com.codegym.service.rating;

import com.codegym.model.Rating;
import com.codegym.repository.rating.IRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class RatingService implements IRatingService{

    @Autowired
    IRatingRepository iRatingRepository;

    @Override
    public Iterable<Rating> findAll() {
        return iRatingRepository.findAll();
    }

    @Override
    public Optional<Rating> findById(Long id) {
        return iRatingRepository.findById(id);
    }

    @Override
    public Rating save(Rating rating) {
        return iRatingRepository.save(rating);
    }

    @Override
    public void delete(Long id) {
        iRatingRepository.deleteById(id);
    }

    @Override
    public Iterable<Rating> findByProvider_Id(Long id) {
        return iRatingRepository.findByProvider_Id(id);
    }
}
