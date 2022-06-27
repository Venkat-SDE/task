package com.assignment.ennea.solutions.repository;

import com.assignment.ennea.solutions.entity.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository Class
 */
@Repository
public interface ProductRepository extends JpaRepository<Products, UUID> {

    /**
     * @param supplierName it takes supplierName
     * @param pageable     and pageable as parameters
     * @return then returns the list of data based on the parameters with pagination
     */
    Page<Products> findBySupplier(String supplierName, Pageable pageable);

    /**
     * @param name     it takes name
     * @param pageable and pageable as parameters
     * @return then returns the list of data based on the parameters with pagination
     */
    Page<Products> findByName(String name, Pageable pageable);

}
