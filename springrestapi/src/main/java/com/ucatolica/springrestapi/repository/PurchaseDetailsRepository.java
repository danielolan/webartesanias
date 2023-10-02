package com.ucatolica.springrestapi.repository;

import com.ucatolica.springrestapi.model.PurchaseDetails;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseDetailsRepository extends JpaRepository<PurchaseDetails, Long> {
}
