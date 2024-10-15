package com.onboard.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onboard.entity.EntityCategory;

@Repository
public interface EntityCategoryRepository extends JpaRepository<EntityCategory, Integer> {
	Optional<EntityCategory> findByName(String name);
}

