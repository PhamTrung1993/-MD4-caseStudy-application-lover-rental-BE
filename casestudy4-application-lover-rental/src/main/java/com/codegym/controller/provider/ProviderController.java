package com.codegym.controller.provider;


import com.codegym.model.Image;
import com.codegym.model.Provider;
import com.codegym.model.ProviderForm;
import com.codegym.service.image.IImageService;
import com.codegym.service.provider.IProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin("*")
@PropertySource("classpath:application.properties")
@RequestMapping("/provider")
public class ProviderController {
    @Autowired
    private Environment env;

    @Value("${upload.path}")
    private String fileUpload;

    @Autowired
    private IImageService imageService;

    @Autowired
    private IProviderService providerService;


    @ModelAttribute("Image")
    public Iterable<Image> allProvinces() {
        return imageService.findAll();
    }

    @GetMapping("/lists")
    public ResponseEntity<Iterable<Provider>> showAllUser() {
        Iterable<Provider> users = providerService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Provider> findProviderById(@PathVariable Long id) {
        Optional<Provider> customerOptional = providerService.findById(id);
        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerOptional.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Provider> updateProvider(@PathVariable Long id, @RequestBody ProviderForm providerForm) throws IOException {
        if (id != null){
            providerForm.setId(id);
            MultipartFile avatar = providerForm.getAvatar();
            Provider provider = new Provider();
            provider.setAvatar(avatar.getOriginalFilename());
            provider.buildByProvider(providerForm);
            FileCopyUtils.copy(avatar.getBytes(), new File(fileUpload + "/" + "avatar" + providerForm.getId()+ "/" + avatar.getOriginalFilename()));
            providerService.save(provider);
            return new ResponseEntity<>(provider, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Provider> deleteCustomer(@PathVariable Long id) {
        Optional<Provider> customerOptional = providerService.findById(id);
        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        providerService.delete(id);
        return new ResponseEntity<>(customerOptional.get(), HttpStatus.NO_CONTENT);
    }

    @PostMapping("/save")
    public ResponseEntity<Provider> create(ProviderForm providerForm, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        MultipartFile avatar = providerForm.getAvatar();
        List<MultipartFile> multipartFiles = providerForm.getImage();
        Provider provider = new Provider();
        if (providerForm.getId() != null) {
            provider.setId(providerForm.getId());
        }
        provider.setAvatar(avatar.getOriginalFilename());
        FileCopyUtils.copy(avatar.getBytes(), new File(fileUpload + "/" + "avatar" + providerForm.getId()+ "/" + avatar.getOriginalFilename()));
        provider.buildByProvider(providerForm);
        providerService.save(provider);
        if (multipartFiles != null) {
            for (MultipartFile multipartFiler : multipartFiles) {
                Image image = new Image();
                FileCopyUtils.copy(multipartFiler.getBytes(), new File(fileUpload + "/" + "image" + providerForm.getId()+ "/" + multipartFiler.getOriginalFilename()));
                image.setImageName(multipartFiler.getOriginalFilename());
                provider.getImage().add(image);
                imageService.save(image);
            }
        }
        return new ResponseEntity<>(provider, HttpStatus.CREATED);
    }
}
