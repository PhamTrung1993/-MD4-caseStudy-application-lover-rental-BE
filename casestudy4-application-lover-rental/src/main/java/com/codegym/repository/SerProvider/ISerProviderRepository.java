package com.codegym.repository.SerProvider;

import com.codegym.model.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface ISerProviderRepository extends JpaRepository<Services, Long> {
}
