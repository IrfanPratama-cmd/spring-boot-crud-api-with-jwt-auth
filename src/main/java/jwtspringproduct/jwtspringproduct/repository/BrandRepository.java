package jwtspringproduct.jwtspringproduct.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import jwtspringproduct.jwtspringproduct.model.Brand;


public interface BrandRepository extends JpaRepository<Brand, UUID> {
    List<Brand> findByName(String name);
}
