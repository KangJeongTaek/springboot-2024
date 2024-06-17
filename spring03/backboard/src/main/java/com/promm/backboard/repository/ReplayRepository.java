package com.promm.backboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.promm.backboard.entity.Replay;

@Repository
public interface ReplayRepository extends JpaRepository<Replay,Long>{
    
}
