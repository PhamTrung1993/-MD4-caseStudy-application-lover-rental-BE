package com.codegym.service.rating;

import com.codegym.model.Rating;
import com.codegym.service.IGeneralService;

public interface IRatingService extends IGeneralService<Rating> {
    Iterable<Rating> findByProvider_Id(Long id);
}
