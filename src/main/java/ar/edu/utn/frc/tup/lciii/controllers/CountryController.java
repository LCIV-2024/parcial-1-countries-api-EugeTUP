package ar.edu.utn.frc.tup.lciii.controllers;
import ar.edu.utn.frc.tup.lciii.dtos.common.CountryDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.PostRequestDTO;
import ar.edu.utn.frc.tup.lciii.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CountryController {

    @Autowired
    CountryService countryService;


    @GetMapping("/countries")
    public ResponseEntity<?> getAllCountries(@RequestParam(required = false) String name,
                                                         @RequestParam(required = false) String code) {


        if(name!=null){

            List<CountryDTO> countries= countryService.getCountryByName(name);
            return ResponseEntity.ok(countries);
        }
        if(code!=null){
            List<CountryDTO> countries= countryService.getCountryByCode(code);
            return ResponseEntity.ok(countries);
        }

        List<CountryDTO> countries =countryService.getAllCountriesDTO();
        if(countries.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(countries);

    }



    @GetMapping("/countries/{continent}/continent")
    public ResponseEntity<List<CountryDTO>> getCountriesByContinent(@PathVariable String continent) {
        List<CountryDTO> countries = countryService.getCountryByContinent(continent);
        if(countries.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(countries);
    }


    @GetMapping("/countries/{language}/language")
    public ResponseEntity<List<CountryDTO>> getCountriesByLanguage(@PathVariable String language) {
        List<CountryDTO> countries = countryService.getCountriesByLanguage(language);
        if(countries.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(countries);
    }


    @GetMapping("/countries/most-borders")
    public ResponseEntity<CountryDTO> getCountryWithMostBorders() {
        CountryDTO country = countryService.getCountryWithMostBorders();
        if(country==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(country);
    }




    @PostMapping("/countries")
    public  ResponseEntity<List<CountryDTO>> getContries(@RequestBody PostRequestDTO request){
        List<CountryDTO> countries = countryService.getContries(request.getAmountOfCountryToSave());
        if(countries.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(countries);
    }





}