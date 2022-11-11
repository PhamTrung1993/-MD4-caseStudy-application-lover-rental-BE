package com.codegym.service.provider;

import com.codegym.model.Provider;
import com.codegym.model.Services;
import com.codegym.repository.provider.IProviderRepository;
import com.codegym.service.SerProvice.ISerProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProviderService implements IProviderService {

    @Autowired
    IProviderRepository providerRepository;

    @Autowired
    ISerProviderService serProviderService;

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

    @Override
    public Iterable<Provider> findAllByFullName(String name) {
        return providerRepository.findAllByFullName(name);
    }

    @Override
    public Iterable<Provider> getProviderByHasBeenHired8female() {
        return providerRepository.getProviderByHasBeenHired8female();
    }

    @Override
    public Iterable<Provider> getProviderByHasBeenHired4male() {
        return providerRepository.getProviderByHasBeenHired4male();
    }

    @Override
    public Iterable<Provider> get6ProviderByView() {
        return providerRepository.get6ProviderByView();
    }

    @Override
    public Iterable<Provider> findAllByGender(String gender) {
        return providerRepository.findAllByGender(gender);
    }

    @Override
    public Iterable<Provider> list12ProviderSuitableForCity(String city) {
        return providerRepository.list12ProviderSuitableForCity(city);
    }
    @Override
    public ArrayList<Services> get3Service(Long id) {
        ArrayList<Services> services = new ArrayList<>();

        ArrayList<BigInteger> serviceIdList = (ArrayList<BigInteger>) providerRepository.get3Service(id);
        for (int i = 0; i < serviceIdList.size(); i++) {
            Long id1 = serviceIdList.get(i).longValue();
            Optional<Services> serProvided = serProviderService.findById(id1);
            services.add(serProvided.get());
        }
        return services;
    }

}
