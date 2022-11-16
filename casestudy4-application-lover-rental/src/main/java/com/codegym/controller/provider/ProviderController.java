package com.codegym.controller.provider;


import com.codegym.model.*;
import com.codegym.model.DTO.ProviderDTO;

import com.codegym.service.SerProvice.ISerProviderService;
import com.codegym.service.provider.IProviderService;
import com.codegym.service.rating.IRatingService;
import com.codegym.service.role.IRoleService;
import com.codegym.service.user.IUserCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.io.IOException;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin("*")
@PropertySource("classpath:application.properties")
@RequestMapping("/provider")
public class ProviderController {

    @Autowired
    IRoleService roleService;

    @Autowired
    IRatingService ratingService;

    @Autowired
    private Environment env;

    @Autowired
    IUserCRUDService userCRUDService;
    @Autowired
    private ISerProviderService iSerProviderService;

    @Autowired
    private IProviderService providerService;



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
    public ResponseEntity<Provider> updateProvider(@PathVariable Long id, @RequestBody Provider provider) throws IOException {
        Optional<Provider> providerOptional = providerService.findById(id);
        if (!providerOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        provider.setId(providerOptional.get().getId());
        return new ResponseEntity<>(providerService.save(provider), HttpStatus.OK);
    }
    @PutMapping("/updateUser/{idP}/{idU}")
    public ResponseEntity<Provider> updateUserOfProvider(@PathVariable Long idP,@PathVariable Long idU, @RequestBody User user) throws IOException {
        Optional<Provider> providerOptional = providerService.findById(idP);
        if (!providerOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        providerService.updateUser(idP, idU);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/updateService/{idP}/{idS}")
    public ResponseEntity<Provider> updateUserOfProvider(@PathVariable Long idP, @PathVariable Long idU) throws IOException {
        providerService.get6ProviderByView();
        Optional<Provider> providerOptional = providerService.findById(idP);
        Optional<Services> servicesOptional = iSerProviderService.findById(idU);
        if (!providerOptional.isPresent() && !servicesOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        providerService.setService(idP,idU);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Provider> deleteProvider(@PathVariable Long id) {
        Optional<Provider> customerOptional = providerService.findById(id);
        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Rating> listRating;
        listRating = (List<Rating>) ratingService.findByProvider_Id(id);
        if (listRating.size() > 0){
            for (int i = 0; i < listRating.size(); i++) {
                ratingService.delete(listRating.get(i).getId());
        }
        }
        providerService.delete(id);
        return new ResponseEntity<>(customerOptional.get(), HttpStatus.NO_CONTENT);
    }
    @GetMapping("/newestProvider")
    public ResponseEntity<Provider> getNewestProvider(){
        Optional<Provider> providerOptional = providerService.getNewestProvider();
        return new ResponseEntity<>(providerOptional.get(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Provider> create(@RequestBody ProviderDTO providerDTO, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasFieldErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Provider provider = new Provider();
        provider.setId(providerDTO.getId());
        provider.setName(providerDTO.getName());
        provider.setAge(providerDTO.getAge());
        provider.setGender(providerDTO.getGender());
        provider.setPrice(providerDTO.getPrice());
        provider.setHobby(providerDTO.getHobby());
        provider.setView(providerDTO.getView());
        provider.setWeight(providerDTO.getWeight());
        provider.setHeight(providerDTO.getHeight());
        provider.setStatus(providerDTO.getStatus());
        provider.setNationality(providerDTO.getNationality());
        provider.setFacebook(providerDTO.getFacebook());
        provider.setHasBeenHired(providerDTO.getHasBeenHired());
        provider.setDescription(providerDTO.getDescription());
        provider.setCity(providerDTO.getCity());
        User user = userCRUDService.findById(providerDTO.getUserId()).get();
        Role role = roleService.findById(Long.parseLong(String.valueOf(4))).get();
        user.setRole(role);
        provider.setUser(user);
        List listServices = providerDTO.getServicesId();
        for (int i = 0; i < listServices.size(); i++) {
            Long serviceId = Long.parseLong(listServices.get(i).toString());
            provider.getService().add(iSerProviderService.findById(serviceId).get());
        }
        providerService.save(provider);
        return new ResponseEntity<>(providerService.save(provider), HttpStatus.OK);
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
        Iterable<Provider> providers = providerService.findAllByGender(gender);
        return new ResponseEntity<>(providers, HttpStatus.OK);
    }
    @GetMapping("/findProviderByFullName/{queryName}")
    public ResponseEntity<Iterable<Provider>> findProviderByFullName(@PathVariable String queryName) {
        Iterable<Provider> providers = providerService.findAllByFullName('%' + queryName + '%');
        return new ResponseEntity<>(providers, HttpStatus.OK);
    }
    @GetMapping("/findAllByCity/{city}")
    public ResponseEntity<Iterable<Provider>> findProviderByCity(@PathVariable String city) {
        Iterable<Provider> providers = providerService.list12ProviderSuitableForCity(city);
        return  new ResponseEntity<>(providers, HttpStatus.OK);
    }
    @GetMapping("/serProvidedByUser/{userId}")
    public ResponseEntity<ArrayList<Services>> get3SerProviderRandom(@PathVariable Long userId) {
        ArrayList<Services> serProviders = providerService.get3Service(userId);
        return new ResponseEntity<>(serProviders, HttpStatus.OK);

    }

    @PostMapping("/comment/{id}")
    public ResponseEntity<Rating> insertComment(@PathVariable Long id, @RequestBody Rating rating, BindingResult bindingResult) throws IOException{
        if (bindingResult.hasFieldErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Rating rating1 = new Rating();
        Provider provider = providerService.findById(id).get();
        rating1.setProvider(provider);
        rating1.setComment(rating.getComment());
        return new ResponseEntity<>(rating1, HttpStatus.OK);
    }
    @DeleteMapping("/comment/{id}")
    public ResponseEntity<Rating> delComment(@PathVariable Long id){
        Optional<Rating> rating = ratingService.findById(id);
        if (!rating.isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ratingService.delete(id);
        return new ResponseEntity<>(rating.get(), HttpStatus.OK);
    }

    @GetMapping("/showRatingProvider/{id}")
    private ResponseEntity<Iterable<Rating>> showRatingProvider(@PathVariable Long id) {
        Iterable<Rating> ratingProvider = ratingService.findByProvider_Id(id);
        return new ResponseEntity<>(ratingProvider, HttpStatus.OK);
    }
    @GetMapping("/findProviderByGenderAndCityAndAge")
    public ResponseEntity<Iterable<Provider>> findProviderByGenderAndCityAndAge( String gender, String city, int fromAge, int toAge) {
        Iterable<Provider> providers = providerService.findAllByGenderContainingAndAgeContainingAndCity(gender,'%' + city + '%', fromAge, toAge);
        return new ResponseEntity<>(providers, HttpStatus.OK);

    }
    @GetMapping("/rent6Provider")
    private ResponseEntity<Iterable<Provider>> rent6Provider() {
        Iterable<Provider> providers = providerService.get6ProviderByView();
        return new ResponseEntity<>(providers, HttpStatus.OK);
    }
}
