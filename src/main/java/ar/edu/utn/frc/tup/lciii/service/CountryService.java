package ar.edu.utn.frc.tup.lciii.service;

import ar.edu.utn.frc.tup.lciii.dtos.common.CountryDTO;
import ar.edu.utn.frc.tup.lciii.model.Country;
import ar.edu.utn.frc.tup.lciii.model.CountryEntity;
import ar.edu.utn.frc.tup.lciii.repository.CountryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CountryService {

        @Autowired
        CountryRepository countryRepository;

        @Autowired
        ModelMapper modelMapper;

        @Autowired
        RestTemplate restTemplate;

        public List<Country> getAllCountries() {
                String url = "https://restcountries.com/v3.1/all";
                List<Map<String, Object>> response = restTemplate.getForObject(url, List.class);
                return response.stream().map(this::mapToCountry).collect(Collectors.toList());
        }

        /**
         * Agregar mapeo de campo cca3 (String)
         * Agregar mapeo campos borders ((List<String>))
         */
        private Country mapToCountry(Map<String, Object> countryData) {
                Map<String, Object> nameData = (Map<String, Object>) countryData.get("name");
                return Country.builder()
                        .name((String) nameData.get("common"))
                        .code((String) countryData.get("cca3"))
                        .population(((Number) countryData.get("population")).longValue())
                        .area(((Number) countryData.get("area")).doubleValue())
                        .region((String) countryData.get("region"))
                        .borders((List<String>) countryData.get("borders"))
                        .languages((Map<String, String>) countryData.get("languages"))
                        .build();
        }


        private CountryDTO mapToDTO(Country country) {
                return new CountryDTO(country.getCode(), country.getName());
        }




        public List<CountryDTO> getCountryByName(String name) {

                List<CountryDTO> countriesDTO = new ArrayList<>();

                List<Country> countries = getAllCountries().stream().filter(country -> country.getName().equals(name)).collect(Collectors.toList());
                if(countries.isEmpty()){
                    return null;
                }
                for(Country c : countries){
                        if(c.getName().equals(name)){
                                CountryDTO countryDTO = mapToDTO(c);
                                countriesDTO.add(countryDTO);
                        }
                       ;
                }
                return countriesDTO;

        }

        public List<CountryDTO> getCountryByCode(String code){

                List<CountryDTO> countriesDTO = new ArrayList<>();

                List<Country> countries = getAllCountries().stream().filter(country -> country.getCode().equals(code)).collect(Collectors.toList());
                for(Country c : countries){
                        CountryDTO countryDTO = mapToDTO(c);
                        countriesDTO.add(countryDTO);
                        return countriesDTO;
                }
                if(countries.isEmpty()){
                    return null;
                }
                return countriesDTO;
        }


        public List<CountryDTO> getAllCountriesDTO() {

                List<CountryDTO> countriesDTO = new ArrayList<>();

                List<Country> countries =  getAllCountries();
                if(countries.isEmpty()){
                    return null;
                }
                for(Country c : countries){
                        CountryDTO countryDTO = mapToDTO(c);
                        countriesDTO.add(countryDTO);
                }

                return countriesDTO;
        }


        public List<CountryDTO> getCountryByContinent(String continent) {

                List<CountryDTO> countriesDTO = new ArrayList<>();

                List<Country> countries = getAllCountries().stream().filter(country -> country.getRegion().equals(continent)).collect(Collectors.toList());
                if(countries.isEmpty()){
                    return null;
                }
                for(Country c : countries){
                        CountryDTO countryDTO = mapToDTO(c);
                        countriesDTO.add(countryDTO);
                }
                return countriesDTO;

        }


        public List<CountryDTO> getCountriesByLanguage(String leng){
                List<CountryDTO> countriesDTO = new ArrayList<>();
                List<Country> countries = getAllCountries();

                if(countries.isEmpty()){
                        return null;
                }

                for(Country c : countries){
                        if(c.getLanguages()!= null && c.getLanguages().containsValue(leng)){
                                CountryDTO countryDTO = mapToDTO(c);
                                countriesDTO.add(countryDTO);
                        }
                }
                return countriesDTO;
        }


        public CountryDTO getCountryWithMostBorders() {
                return null;
        }


        public List<CountryDTO> getContries(Long amount){

                if(amount > 10L){
                        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "La cantidad no puede ser mayor a 10");
                }

                List<CountryDTO> countries = new ArrayList<>();
                List<Country> countriesList = getAllCountries();

                for(int i = 0; i < amount; i++){
                        Collections.shuffle(countriesList);
                        Country c = countriesList.get(i);
                        CountryEntity country = new CountryEntity();
                        country.setName(c.getName());
                        country.setCode(c.getCode());
                        country.setPopulation(c.getPopulation());
                        country.setArea(c.getArea());

                        CountryEntity savedCountry = countryRepository.save(country);
                        countries.add(new CountryDTO(savedCountry.getCode(), savedCountry.getName()));
                }

                Collections.shuffle(countries);
                return countries;
        }

}