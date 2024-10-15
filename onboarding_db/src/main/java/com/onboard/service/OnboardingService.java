package com.onboard.service;

import java.util.List;

import com.onboard.entity.EntityCategory;
import com.onboard.entity.EntityDetails;
import com.onboard.util.EntityStatus;

public interface OnboardingService {

	EntityDetails registerEntity(EntityDetails entity);

    EntityDetails getEntityById(Integer id);

    List<EntityDetails> getAllEntitys();

    EntityDetails updateEntity(EntityDetails entity);

    EntityDetails updateEntityStatus(Integer id, EntityStatus status);

    // New methods for EntityCategory
    EntityCategory createCategory(EntityCategory category);
    
    List<EntityCategory> getAllCategories();
    
    EntityCategory getCategoryById(Integer id);
    
    

}