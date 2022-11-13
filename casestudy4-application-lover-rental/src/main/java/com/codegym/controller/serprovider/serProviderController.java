package com.codegym.controller.serprovider;

import com.codegym.model.Services;
import com.codegym.service.SerProvice.ISerProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@CrossOrigin("*")
@PropertySource("classpath:application.properties")
@RequestMapping("/serProvider")
public class serProviderController {

    @Autowired
    private ISerProviderService serProviderService;

    @GetMapping("/lists")
    public ResponseEntity<Iterable<Services>> showAllServices() {
        Iterable<Services> services = serProviderService.findAll();
        return new ResponseEntity<>(services, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Services> saveServices(@RequestBody Services services) {
        return new ResponseEntity<>(serProviderService.save(services), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Services> findServiceById(@PathVariable Long id) {
        Optional<Services> customerOptional = serProviderService.findById(id);
        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerOptional.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Services> updateService(@PathVariable Long id, @RequestBody Services services) {
        Optional<Services> customerOptional = serProviderService.findById(id);
        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        services.setId(customerOptional.get().getId());
        return new ResponseEntity<>(serProviderService.save(services), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Services> deleteService(@PathVariable Long id) {
        Optional<Services> serviceOptional = serProviderService.findById(id);
        if (!serviceOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        serProviderService.delete(id);
        return new ResponseEntity<>(serviceOptional.get(), HttpStatus.NO_CONTENT);
    }
    @GetMapping("/findByProvider/{id}")
    public ResponseEntity<Iterable<Services>> findByProvider(@PathVariable Long id){
        Iterable<Services> services = serProviderService.findAllByProvider(id);
        return new ResponseEntity<>(services, HttpStatus.OK);
    }
}
