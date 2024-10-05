package ar.edu.utn.frc.tup.lciii.service;

import ar.edu.utn.frc.tup.lciii.dtos.common.CountryDTO;
import ar.edu.utn.frc.tup.lciii.model.Country;
import ar.edu.utn.frc.tup.lciii.model.CountryEntity;
import ar.edu.utn.frc.tup.lciii.repository.CountryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CountryServiceTest {

    @SpyBean
    CountryService countryService;

    @MockBean
    CountryRepository countryRepository;

    @MockBean
    RestTemplate restTemplate;




    @Test
    void getAllCountries() {

        // GIVEN

        List<LinkedHashMap<String, Object>> mockResponse = new ArrayList<>();
        LinkedHashMap<String, Object> countryData = new LinkedHashMap<>();
        LinkedHashMap<String, Object> nameData = new LinkedHashMap<>();
        nameData.put("common", "Argentina");
        countryData.put("name", nameData);
        countryData.put("cca3", "ARG");
        countryData.put("population", 45195777);
        countryData.put("area", 2780400.0);
        countryData.put("region", "Americas");
        countryData.put("borders", List.of("BRA", "CHL", "URY"));
        countryData.put("languages", Map.of("spa", "Spanish"));
        mockResponse.add(countryData);

        // WHEN
        when(restTemplate.getForObject("https://restcountries.com/v3.1/all", List.class)).thenReturn(mockResponse);


        // THEN
        List<Country> countries = countryService.getAllCountries();
        assertNotNull(countries);
        assertEquals(1, countries.size());
        assertEquals("Argentina", countries.get(0).getName());
        assertEquals("ARG", countries.get(0).getCode());
        assertEquals(45195777, countries.get(0).getPopulation());
        assertEquals(2780400.0, countries.get(0).getArea());
        assertEquals("Americas", countries.get(0).getRegion());

    }

    @Test
    void getCountryByName() {

        // GIVEN
        List<LinkedHashMap<String, Object>> mockResponse = new ArrayList<>();
        LinkedHashMap<String, Object> countryData = new LinkedHashMap<>();
        LinkedHashMap<String, Object> nameData = new LinkedHashMap<>();
        nameData.put("common", "Argentina");
        countryData.put("name", nameData);
        countryData.put("cca3", "ARG");
        countryData.put("population", 45195777);
        countryData.put("area", 2780400.0);
        countryData.put("region", "Americas");
        countryData.put("borders", List.of("BRA", "CHL", "URY"));
        countryData.put("languages", Map.of("spa", "Spanish"));
        mockResponse.add(countryData);

        // WHEN
        when(restTemplate.getForObject("https://restcountries.com/v3.1/all", List.class)).thenReturn(mockResponse);


        // THEN
        List<CountryDTO> response = countryService.getCountryByName("Argentina");
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals("Argentina", response.get(0).getName());
        assertEquals("ARG", response.get(0).getCode());

    }

    @Test
    void getCountryByCode() {

        // GIVEN
        List<LinkedHashMap<String, Object>> mockResponse = new ArrayList<>();
        LinkedHashMap<String, Object> countryData = new LinkedHashMap<>();
        LinkedHashMap<String, Object> nameData = new LinkedHashMap<>();
        nameData.put("common", "Argentina");
        countryData.put("name", nameData);
        countryData.put("cca3", "ARG");
        countryData.put("population", 45195777);
        countryData.put("area", 2780400.0);
        countryData.put("region", "Americas");
        countryData.put("borders", List.of("BRA", "CHL", "URY"));
        countryData.put("languages", Map.of("spa", "Spanish"));
        mockResponse.add(countryData);

        // WHEN
        when(restTemplate.getForObject("https://restcountries.com/v3.1/all", List.class)).thenReturn(mockResponse);

        // THEN
        List<CountryDTO> response = countryService.getCountryByCode("ARG");
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals("Argentina", response.get(0).getName());


    }

    @Test
    void getAllCountriesDTO() {

        // GIVEN
        List<LinkedHashMap<String, Object>> mockResponse = new ArrayList<>();
        LinkedHashMap<String, Object> countryData = new LinkedHashMap<>();
        LinkedHashMap<String, Object> nameData = new LinkedHashMap<>();
        nameData.put("common", "Argentina");
        countryData.put("name", nameData);
        countryData.put("cca3", "ARG");
        countryData.put("population", 45195777);
        countryData.put("area", 2780400.0);
        countryData.put("region", "Americas");
        countryData.put("borders", List.of("BRA", "CHL", "URY"));
        countryData.put("languages", Map.of("spa", "Spanish"));
        mockResponse.add(countryData);

        // WHEN
        when(restTemplate.getForObject("https://restcountries.com/v3.1/all", List.class)).thenReturn(mockResponse);

        // THEN

        List<CountryDTO> response = countryService.getAllCountriesDTO();
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals("Argentina", response.get(0).getName());
        assertEquals("ARG", response.get(0).getCode());

    }

    @Test
    void getCountryByContinent() {

        // GIVEN
        List<LinkedHashMap<String, Object>> mockResponse = new ArrayList<>();
        LinkedHashMap<String, Object> countryData = new LinkedHashMap<>();
        LinkedHashMap<String, Object> nameData = new LinkedHashMap<>();
        nameData.put("common", "Argentina");
        countryData.put("name", nameData);
        countryData.put("cca3", "ARG");
        countryData.put("population", 45195777);
        countryData.put("area", 2780400.0);
        countryData.put("region", "Americas");
        countryData.put("borders", List.of("BRA", "CHL", "URY"));
        countryData.put("languages", Map.of("spa", "Spanish"));
        mockResponse.add(countryData);

        // WHEN
        when(restTemplate.getForObject("https://restcountries.com/v3.1/all", List.class)).thenReturn(mockResponse);


        // THEN

        List<CountryDTO> response = countryService.getCountryByContinent("Americas");
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals("Argentina", response.get(0).getName());
        assertEquals("ARG", response.get(0).getCode());


    }

    @Test
    void getCountriesByLanguage() {

        // GIVEN
        List<LinkedHashMap<String, Object>> mockResponse = new ArrayList<>();
        LinkedHashMap<String, Object> countryData = new LinkedHashMap<>();
        LinkedHashMap<String, Object> nameData = new LinkedHashMap<>();
        nameData.put("common", "Argentina");
        countryData.put("name", nameData);
        countryData.put("cca3", "ARG");
        countryData.put("population", 45195777);
        countryData.put("area", 2780400.0);
        countryData.put("region", "Americas");
        countryData.put("borders", List.of("BRA", "CHL", "URY"));
        countryData.put("languages", Map.of("spa", "Spanish"));
        mockResponse.add(countryData);

        // WHEN
        when(restTemplate.getForObject("https://restcountries.com/v3.1/all", List.class)).thenReturn(mockResponse);

        // THEN
        List<CountryDTO> response = countryService.getCountriesByLanguage("Spanish");
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals("Argentina", response.get(0).getName());
        assertEquals("ARG", response.get(0).getCode());

    }

    @Test
    void getCountryWithMostBorders() {
    }

    @Test
    void getContries() {

        // GIVEN

        Long cant = 1L;

        List<LinkedHashMap<String, Object>> countries = new ArrayList<>();
        LinkedHashMap<String, Object> countryData = new LinkedHashMap<>();
        LinkedHashMap<String, Object> nameData = new LinkedHashMap<>();
        nameData.put("common", "Argentina");
        countryData.put("name", nameData);
        countryData.put("cca3", "ARG");
        countryData.put("population", 45195777);
        countryData.put("area", 2780400.0);
        countryData.put("region", "Americas");
        countryData.put("borders", List.of("BRA", "CHL", "URY"));
        countryData.put("languages", Map.of("spa", "Spanish"));
        countries.add(countryData);


        CountryEntity entity = new CountryEntity(1L, "Argentina","ARG",45195777L ,0.0);

        // WHEN
        when(restTemplate.getForObject("https://restcountries.com/v3.1/all", List.class)).thenReturn(countries);
        when(countryRepository.save(Mockito.any())).thenReturn(entity);

        // THEN

        List<CountryDTO> response = countryService.getContries(cant);
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals("Argentina", response.get(0).getName());
        assertEquals("ARG", response.get(0).getCode());

    }

    @Test
    void getContriesFail() {

        // GIVEN
        Long cant = 11L;

        Assertions.assertThrows(HttpClientErrorException.class, () ->
                countryService.getContries(cant));
    }

}