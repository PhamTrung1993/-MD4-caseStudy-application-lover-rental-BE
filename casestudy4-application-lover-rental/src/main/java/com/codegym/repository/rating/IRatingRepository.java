package com.codegym.repository.rating;

import com.codegym.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface IRatingRepository extends JpaRepository<Rating, Long> {
    @Query(nativeQuery = true, value = "select * from rating where provider_id = :id")
    Iterable<Rating>findByProvider_Id(@Param("id") Long id);
}
