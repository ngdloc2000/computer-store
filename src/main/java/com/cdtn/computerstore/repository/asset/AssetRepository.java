package com.cdtn.computerstore.repository.asset;

import com.cdtn.computerstore.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {

    List<Asset> findAllByProductId(Long productId);

    @Query(value = "select a.image_link from asset a where a.product_id = :productId", nativeQuery = true)
    List<String> findAllImageLinkProduct(Long productId);
}
