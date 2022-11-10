package com.codegym.controller.image;

import com.codegym.model.Image;
import com.codegym.model.ImageForm;
import com.codegym.service.image.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@PropertySource("classpath:application.properties")
@RequestMapping("/image")
public class ImageController {
    @Autowired
    IImageService iImageService;

    @Value("${upload.path}")
    private String fileUpload;

    @PostMapping
    public ResponseEntity<Image> create(ImageForm imageForm, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        MultipartFile multipartFile = imageForm.getFileImage();
        Image image = new Image();
        if (imageForm.getId() != null) {
            image.setId(imageForm.getId());

            image.setImageName(imageForm.getFileImage().getOriginalFilename());
            FileCopyUtils.copy(multipartFile.getBytes(), new File(fileUpload + "/" + "image" + "/" + multipartFile.getOriginalFilename()));
        }
        iImageService.save(image);

        return new ResponseEntity<>(image, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Image> image = iImageService.findById(id);
        if (!image.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Image image1 = image.get();
        iImageService.delete(id);
        if (image != null) {
            iImageService.delete(image1.getId());
            new File(fileUpload + image1.getImageName()).delete();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
