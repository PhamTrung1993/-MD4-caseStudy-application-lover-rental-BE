package com.codegym.repository.SerProvider;

import com.codegym.model.Image;
import com.codegym.model.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ISerProviderRepository extends JpaRepository<Services, Long> {
    @Query(value = "select * from services join provider_service on services.id = provider_service.service_id where provider_id = :id;", nativeQuery = true)
    Iterable<Services> findAllByProvider_Id(@Param("id") Long id);
}
