package com.example.e_commerce.repositories;

import com.example.e_commerce.models.Merchandise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MerchandiseRepository extends JpaRepository<Merchandise, Long> {
    Optional<Object> findByName(String name);
}
