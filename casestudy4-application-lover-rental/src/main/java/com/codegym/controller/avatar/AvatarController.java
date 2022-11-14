package com.codegym.controller.avatar;



import com.codegym.model.Avatar;

import com.codegym.model.Provider;
import com.codegym.repository.avatar.IAvatarRepository;
import com.codegym.service.avatar.AvatarService;
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
@RequestMapping("/avatar")
public class AvatarController {

    @Autowired
    IAvatarRepository avatarRepository;

    @Autowired
    IProviderService providerService;

    @PostMapping("/upload/avatar/{id}")
    public ResponseEntity<AvatarUploadResponse> uplaodAvatar(@PathVariable Long id, @RequestParam("image") MultipartFile file)
            throws IOException {
        Provider provider = providerService.findById(id).get();

        Avatar avatar = avatarRepository.save(Avatar.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .image(AvatarService.compressAvatar(file.getBytes())).build());
//        provider.setAvatar(avatar);
        providerService.save(provider);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new AvatarUploadResponse("Image uploaded successfully: " +
                        file.getOriginalFilename()));
    }

    @GetMapping(path = {"/get/avatar/info/{name}"})
    public Avatar getAvatarDetails(@PathVariable("name") String name) throws IOException {

        final Optional<Avatar> dbAvatar = avatarRepository.findByName(name);

        return Avatar.builder()
                .name(dbAvatar.get().getName())
                .type(dbAvatar.get().getType())
                .image(AvatarService.decompressAvatar(dbAvatar.get().getImage())).build();
    }

    @GetMapping(path = {"/get/avatar/{name}"})
    public ResponseEntity<byte[]> getAvatar(@PathVariable("name") String name) throws IOException {

        final Optional<Avatar> dbAvatar = avatarRepository.findByName(name);

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(dbAvatar.get().getType()))
                .body(AvatarService.decompressAvatar(dbAvatar.get().getImage()));
    }
}

