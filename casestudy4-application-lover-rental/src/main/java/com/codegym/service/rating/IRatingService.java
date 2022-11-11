package com.codegym.service.rating;

import com.codegym.model.Rating;
import com.codegym.service.IGeneralService;

import java.util.Optional;

public interface IRatingService extends IGeneralService<Rating> {
    Iterable<Rating> findByProvider_Id(Long id);
}
