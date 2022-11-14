package com.codegym.service.provider;

import com.codegym.model.Provider;
import com.codegym.model.Services;
import com.codegym.service.IGeneralService;

import java.util.ArrayList;
import java.util.Optional;

public interface IProviderService extends IGeneralService<Provider> {

    Iterable<Provider>findAllByFullName(String name);
    Iterable<Provider> getProviderByHasBeenHired8female();
    Iterable<Provider> getProviderByHasBeenHired4male();
    Iterable<Provider> get6ProviderByView();

    Iterable<Provider>findAllByGender(String gender);

    Iterable<Provider> list12ProviderSuitableForCity(String city);

    ArrayList<Services> get3Service(Long id);

    Iterable<Provider>  findAllByGenderContainingAndAgeContainingAndCity(String gender,String city,int fromAge, int toAge);

    void setService(Long idP, Long idS);

    Optional<Provider> getNewestProvider();

    void updateUser(Long idP, Long idU);

    Optional<Provider> findByUser_Id(Long userId);
}
