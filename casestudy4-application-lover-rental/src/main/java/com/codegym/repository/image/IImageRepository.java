package com.codegym.repository.image;

import com.codegym.model.Image;
import com.codegym.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImageRepository extends JpaRepository<Image, Long> {

}
