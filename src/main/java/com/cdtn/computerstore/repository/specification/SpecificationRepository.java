package com.cdtn.computerstore.repository.specification;

import com.cdtn.computerstore.entity.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecificationRepository extends JpaRepository<Specification, Long> {

    Specification findByProductId(Long productId);
}
