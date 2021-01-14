package com.example.demowebflux.controller;

import com.example.demowebflux.bean.City;
import com.example.demowebflux.handler.CityHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * web flux city controller
 *
 * @author ：zxq
 * @date ：Created in 2021/1/14 15:44
 */
@RestController
@RequestMapping("/city")
@Slf4j
public class CityController {

    @Autowired
    private CityHandler cityHandler;

    @GetMapping("/{id}")
    public Mono<City> findCityById(@PathVariable("id") Long id) {
        return cityHandler.findCityById(id);
    }

    @GetMapping()
    public Flux<City> findAllCity() {
        return cityHandler.findAllCity();
    }

    @PostMapping()
    public Mono<Long> saveCity(@RequestBody City city) {
        log.info("start");
        Mono<Long> id = cityHandler.save(city);
        log.info("end");
        return id;
    }

    @PutMapping()
    public Mono<Long> modifyCity(@RequestBody City city) {
        return cityHandler.modifyCity(city);
    }

    @DeleteMapping("/{id}")
    public Mono<Long> deleteCity(@PathVariable("id") Long id) {
        return cityHandler.deleteCity(id);
    }

}
