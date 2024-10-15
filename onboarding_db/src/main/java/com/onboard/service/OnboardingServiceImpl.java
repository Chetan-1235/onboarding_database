package com.onboard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onboard.Repository.EntityCategoryRepository;
import com.onboard.Repository.EntityDetailsRepository;
import com.onboard.entity.EntityCategory;
import com.onboard.entity.EntityDetails;
import com.onboard.exception.OnboardingStatusException;
import com.onboard.util.EntityStatus;

@Service
public class OnboardingServiceImpl implements OnboardingService {

    private final EntityDetailsRepository entityRepository;
    private final EntityCategoryRepository entityCategoryRepository; // New repository

    @Autowired
    public OnboardingServiceImpl(EntityDetailsRepository entityRepository, EntityCategoryRepository entityCategoryRepository) {
        this.entityRepository = entityRepository;
        this.entityCategoryRepository = entityCategoryRepository; // Inject the new repository
    }

    @Override
    public EntityDetails registerEntity(EntityDetails vendor) {
        Optional<EntityDetails> optionVendor = entityRepository.findByTinNo(vendor.getTinNo());

        // if Vendor is not an old User
        if (optionVendor.isEmpty()) {
            return entityRepository.save(vendor);
        } else {
            EntityDetails vendb = optionVendor.get();

            // Handle different statuses using a switch case
            switch (vendb.getStatus()) {
                case ACTIVE:
                    throw new OnboardingStatusException(vendb.getStatus(), "ONB_501");
                case INACTIVE:
                    throw new OnboardingStatusException(vendb.getStatus(), "ONB_502");
                case INPROGRESS:
                    throw new OnboardingStatusException(vendb.getStatus(), "ONB_503");
                case BG_FAILED:
                    throw new OnboardingStatusException(vendb.getStatus(), "ONB_504");
                case USER_CANCELLED:
                    vendor.setId(vendb.getId());
                    return entityRepository.save(vendor);
                default:
                    throw new IllegalArgumentException("Unknown status: " + vendb.getStatus());
            }
        }
    }

    @Override
    public EntityDetails getEntityById(Integer id) {
        return entityRepository.findById(id).orElse(null);
    }

    @Override
    public List<EntityDetails> getAllEntitys() {
        return entityRepository.findAll();
    }

    @Override
    public EntityDetails updateEntity(EntityDetails entity) {
        return entityRepository.save(entity);
    }

    @Override
    public EntityDetails updateEntityStatus(Integer id, EntityStatus status) {
        EntityDetails updateStatusEntity = entityRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Entity not found"));
        updateStatusEntity.setStatus(status);
        return entityRepository.save(updateStatusEntity);
    }


	  @Override
	    public EntityCategory createCategory(EntityCategory category) {
	        return entityCategoryRepository.save(category);
	    }

	    @Override
	    public List<EntityCategory> getAllCategories() {
	        return entityCategoryRepository.findAll();
	    }

	    @Override
	    public EntityCategory getCategoryById(Integer id) {
	        return entityCategoryRepository.findById(id).get();
	    }
	
}
