package com.example.demowebflux.handler;

import com.example.demowebflux.bean.City;
import com.example.demowebflux.dao.CityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 城市处理器类
 *
 * @author ：zxq
 * @date ：Created in 2021/1/14 15:31
 */
@Component
public class CityHandler {

    private final CityDao cityDao;

    @Autowired
    public CityHandler(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    public Mono<Long> save(City city) {
        return Mono.create(cityMonoSink -> cityMonoSink.success(cityDao.save(city)));
    }

    public Mono<City> findCityById(Long id) {
        return Mono.justOrEmpty(cityDao.findCityById(id));
    }

    public Flux<City> findAllCity() {
        return Flux.fromIterable(cityDao.findAll());
    }

    public Mono<Long> modifyCity(City city) {
        return Mono.create(cityMonoSink -> cityMonoSink.success(cityDao.updateCity(city)));
    }

    public Mono<Long> deleteCity(Long id) {
        return Mono.create(cityMonoSink -> cityMonoSink.success(cityDao.deleteCity(id)));
    }

}
