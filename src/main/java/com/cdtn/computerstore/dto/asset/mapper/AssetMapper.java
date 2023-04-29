package com.cdtn.computerstore.dto.asset.mapper;

import com.cdtn.computerstore.entity.Asset;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class AssetMapper {

    public List<Asset> createAsset(Long productId, List<String> imageList) {

        List<Asset> assetList = new ArrayList<>();

        for (String image : imageList) {
            Asset asset = Asset.builder()
                    .productId(productId)
                    .imageLink(image)
                    .createAt(LocalDateTime.now())
                    .build();
            assetList.add(asset);
        }

        return assetList;
    }
}
