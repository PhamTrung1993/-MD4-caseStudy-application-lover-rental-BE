package com.codegym.controller.avatar;



import com.codegym.model.Avatar;
import com.codegym.model.AvatarForm;
import com.codegym.model.Provider;
import com.codegym.service.avatar.IAvatarService;
import com.codegym.service.provider.IProviderService;
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
@RequestMapping("/avatar")
public class AvatarController {

    @Autowired
    IProviderService providerService;
    @Autowired
    IAvatarService avatarService;

    @Value("${upload.path}")
    private String fileUpload;

    @PostMapping
    public ResponseEntity<Avatar> create(@PathVariable Long id,@RequestBody AvatarForm avatarForm, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        MultipartFile multipartFile = avatarForm.getMultipartFile();
        Avatar avatar = new Avatar();
        if (avatarForm.getId() != null) {
            avatar.setId(avatarForm.getId());
            Provider provider = providerService.findById(id).get();
            avatar.setProvider(provider);
            avatar.setName(avatarForm.getMultipartFile().getOriginalFilename());
            FileCopyUtils.copy(multipartFile.getBytes(), new File(fileUpload + "/" + "saveavatar" + "/" + multipartFile.getOriginalFilename()));
        }
        avatarService.save(avatar);

        return new ResponseEntity<>(avatar, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Avatar> avatar = avatarService.findById(id);
        if (!avatar.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Avatar avatar1 = avatar.get();
        avatarService.delete(id);
        if (avatar != null) {
            avatarService.delete(avatar1.getId());
            new File(fileUpload + avatar1.getName()).delete();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("lists")
    public ResponseEntity<Iterable<Avatar>> listAvatar(){
        Iterable<Avatar> avatars = avatarService.findAll();
        return new ResponseEntity<>(avatars, HttpStatus.OK);
    }
}

