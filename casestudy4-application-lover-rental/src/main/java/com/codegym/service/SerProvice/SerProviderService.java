package com.codegym.service.SerProvice;

import com.codegym.model.Services;
import com.codegym.repository.SerProvider.ISerProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SerProviderService implements ISerProviderService{
    @Autowired
    ISerProviderRepository serProviderRepository;

    @Override
    public Iterable<Services> findAll() {
        return serProviderRepository.findAll();
    }

    @Override
    public Optional<Services> findById(Long id) {
        return serProviderRepository.findById(id);
    }

    @Override
    public Services save(Services service) {
        return serProviderRepository.save(service);
    }

    @Override
    public void delete(Long id) {
            serProviderRepository.delete(serProviderRepository.findById(id).get());
    }

    @Override
    public Iterable<Services> findAllByProvider(Long id) {
        return serProviderRepository.findAllByProvider_Id(id);
    }
}
