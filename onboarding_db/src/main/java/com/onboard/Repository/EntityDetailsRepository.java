package com.onboard.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onboard.entity.EntityDetails;

import java.util.Optional;

@Repository
public interface EntityDetailsRepository extends JpaRepository<EntityDetails, Integer> {
    Optional<EntityDetails> findByTinNo(Integer tinNo);
}
