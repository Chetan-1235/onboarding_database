package com.onboard.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onboard.entity.AddressDetails;

@Repository
public interface AddressDetailsRepository extends JpaRepository<AddressDetails, Integer> {
}
