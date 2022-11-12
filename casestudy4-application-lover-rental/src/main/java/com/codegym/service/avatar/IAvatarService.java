package com.codegym.service.avatar;


import com.codegym.model.Avatar;
import com.codegym.service.IGeneralService;

import java.util.Optional;

public interface IAvatarService extends IGeneralService<Avatar> {
    public Optional<Avatar> findByProvider_id(Long id);
}
