package com.codegym.repository.SerProvider;

import com.codegym.model.Services;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISerProviderRepository extends JpaRepository<Services, Long> {
}
