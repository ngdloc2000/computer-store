package com.cdtn.computerstore.repository.specification;

import com.cdtn.computerstore.entity.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpecificationRepository extends JpaRepository<Specification, Long> {

    Optional<Specification> findByProductId(Long productId);
}
