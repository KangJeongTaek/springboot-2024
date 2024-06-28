package com.promm.backboard.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.promm.backboard.entity.Reset;

@Repository
public interface ResetRepository extends JpaRepository<Reset, Integer>{
    
    Optional<Reset> findByUuid(String uuid);
}
