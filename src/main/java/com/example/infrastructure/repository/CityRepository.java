package com.example.infrastructure.repository;

import com.example.domain.city.City;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "city", path = "city", excerptProjection = CityProjection.class)
public interface CityRepository extends PagingAndSortingRepository<City, Integer> {
}
