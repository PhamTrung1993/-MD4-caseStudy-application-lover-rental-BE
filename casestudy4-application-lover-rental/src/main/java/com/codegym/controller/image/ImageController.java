package com.codegym.controller.image;

import com.codegym.model.Image;


import com.codegym.model.Provider;
import com.codegym.repository.image.ImageRepository;
import com.codegym.service.image.ImageService;
import com.codegym.service.provider.IProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@PropertySource("classpath:application.properties")
@RequestMapping("/image")
public class ImageController {
    @Autowired
    ImageRepository imageRepository;

    @Autowired
    IProviderService providerService;

    @PostMapping("/upload/image/{providerId}")
    public ResponseEntity<ImageUploadResponse> uplaodImage(@PathVariable Long providerId, @RequestParam("image") MultipartFile file)
            throws IOException {

        Provider provider = providerService.findById(providerId).get();

        Image image = imageRepository.save(Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .image(ImageService.compressImage(file.getBytes())).build());
//        provider.getImages().add(image);
        providerService.save(provider);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ImageUploadResponse("Image uploaded successfully: " +
                        file.getOriginalFilename()));
    }

    @GetMapping(path = {"/get/image/info/{name}"})
    public Image getImageDetails(@PathVariable("name") String name) throws IOException {

        final Optional<Image> dbImage = imageRepository.findByName(name);

        return Image.builder()
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .image(ImageService.decompressImage(dbImage.get().getImage())).build();
    }

    @GetMapping(path = {"/get/image/{name}"})
    public ResponseEntity<byte[]> getImage(@PathVariable("name") String name) throws IOException {

        final Optional<Image> dbImage = imageRepository.findByName(name);

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(dbImage.get().getType()))
                .body(ImageService.decompressImage(dbImage.get().getImage()));
    }
}
