package com.codegym.controller.provider;

import com.codegym.model.Image;
import com.codegym.model.Provider;
import com.codegym.model.ProviderForm;
import com.codegym.model.Services;
import com.codegym.service.SerProvice.ISerProviderService;
import com.codegym.service.image.IImageService;
import com.codegym.service.provider.IProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@PropertySource("classpath:application.properties")
@RequestMapping("/provider")
public class ProviderController {
    @Autowired
    private Environment env;

//    @Value("${upload.path}")
//    private String fileUpload;

    @Autowired
    private IImageService imageService;

    @Autowired
    private IProviderService providerService;

    @Autowired
    private ISerProviderService serProviderService;

    @ModelAttribute("Image")
    public Iterable<Image> allProvinces() {
        return imageService.findAll();
    }

    @GetMapping("/lists")
    public ResponseEntity<Iterable<Provider>> showAllUser() {
        Iterable<Provider> users = providerService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Provider> saveProvider(@RequestBody Provider provider) {
        return new ResponseEntity<>(providerService.save(provider), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Provider> findCustomerById(@PathVariable Long id) {
        Optional<Provider> customerOptional = providerService.findById(id);
        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerOptional.get(), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Provider> updateCustomer(@PathVariable Long id, @RequestBody Provider provider) {
        Optional<Provider> customerOptional = providerService.findById(id);
        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        provider.setId(customerOptional.get().getId());
        return new ResponseEntity<>(providerService.save(provider), HttpStatus.OK);
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

    @GetMapping("/rent8Female")
    public ResponseEntity<Iterable<Provider>> getProviderByHasBeenHire8female() {
        Iterable<Provider> providers = providerService.getProviderByHasBeenHired8female();
        return new ResponseEntity<>(providers, HttpStatus.OK);
    }

    @GetMapping("/rent4Male")
    public ResponseEntity<Iterable<Provider>> getProviderByHasBeenHire4male() {
        Iterable<Provider> providers = providerService.getProviderByHasBeenHired4male();
        return new ResponseEntity<>(providers, HttpStatus.OK);
    }
    @GetMapping("/rentListForGender/{gender}")
    public ResponseEntity<Iterable<Provider>> findProviderForGender(@PathVariable String gender) {
        if (gender.equals("male")) {
            gender = "female";
        } else if (gender.equals("female")) {
            gender = "male";
        }
        Iterable<Provider> providers = providerService.findAllByGender(gender);
        return new ResponseEntity<>(providers, HttpStatus.OK);
    }
    @GetMapping("/findProviderByFullName")
    public ResponseEntity<Iterable<Provider>> findProviderByFullName( String queryName) {
        Iterable<Provider> providers = providerService.findAllByFullName('%' + queryName + '%');
        return new ResponseEntity<>(providers, HttpStatus.OK);
    }
    @GetMapping("/findAllByCity/{city}")
    public ResponseEntity<Iterable<Provider>> findProviderByCity(@PathVariable String city) {
        Iterable<Provider> providers = providerService.list12ProviderSuitableForCity(city);
        return  new ResponseEntity<>(providers, HttpStatus.OK);
    }
    @GetMapping("/serProvidedByUser")
    public ResponseEntity<ArrayList<Services>> get3SerProviderRandom(Long userId) {
        ArrayList<Services> serProviders = providerService.get3Service(userId);
        return new ResponseEntity<>(serProviders, HttpStatus.OK);
    }
}
