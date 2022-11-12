package com.codegym.service.image;

import com.codegym.model.Image;
import com.codegym.service.IGeneralService;

import java.util.Optional;

public interface IImageService extends IGeneralService<Image> {
        public Iterable<Image> findByProvider_id(Long id);
}
