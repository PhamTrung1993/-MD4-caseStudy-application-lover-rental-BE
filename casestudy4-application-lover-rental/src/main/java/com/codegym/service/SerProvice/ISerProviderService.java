package com.codegym.service.SerProvice;

import com.codegym.model.Services;
import com.codegym.service.IGeneralService;

public interface ISerProviderService extends IGeneralService<Services> {
    public Iterable<Services> findAllByProvider(Long id);
}
