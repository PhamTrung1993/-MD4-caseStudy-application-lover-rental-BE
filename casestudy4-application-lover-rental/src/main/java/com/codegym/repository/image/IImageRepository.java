package com.codegym.repository.image;

import com.codegym.model.Avatar;
import com.codegym.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IImageRepository extends JpaRepository<Image, Long> {
    @Query(value = "select * from image where provider_id = :id", nativeQuery = true)
    Iterable<Image> findByProvider_Id(@Param("id") Long id);
}
