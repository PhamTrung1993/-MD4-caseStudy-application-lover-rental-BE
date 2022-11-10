package com.codegym.service.provider;

import com.codegym.model.Provider;
import com.codegym.repository.provider.IProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProviderService implements IProviderService {

    @Autowired
    IProviderRepository providerRepository;

    @Override
    public Iterable<Provider> findAll() {
        return providerRepository.findAll();
    }

    @Override
    public Optional<Provider> findById(Long id) {
        return providerRepository.findById(id);
    }

    @Override
    public Provider save(Provider provider) {
        return providerRepository.save(provider);
    }

    @Override
    public void delete(Long id) {
        providerRepository.deleteById(id);
    }
}
