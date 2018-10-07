package com.example.infrastructure.repository;

import com.example.domain.city.City;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection( name = "city", types = { City.class })
public interface CityProjection {
    String getName();

    @Value("#{target.name.length()}")
    int getLengthOfName();
}
