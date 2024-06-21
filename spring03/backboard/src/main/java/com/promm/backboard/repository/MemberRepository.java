package com.promm.backboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.promm.backboard.entity.Member;


@Repository
public interface MemberRepository extends JpaRepository<Member,Long>{
    
    Member findByUsernameAndPassword(String username, String password);
}
