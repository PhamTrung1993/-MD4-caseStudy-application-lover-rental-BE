package com.codegym.service.provider;

import com.codegym.model.Provider;
import com.codegym.repository.provider.IProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ProviderService implements IProviderService {

    @Autowired
    IProviderRepository providerRepository;

    @Override
    public Iterable<Provider> findAll() {
        return providerRepository.findAll();
    }

    @Override
    public Optional<Provider> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Provider save(Provider provider) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
