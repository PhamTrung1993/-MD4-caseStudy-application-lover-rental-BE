package com.codegym.repository.provider;

import com.codegym.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProviderRepository extends JpaRepository<Provider, Long> {
}
