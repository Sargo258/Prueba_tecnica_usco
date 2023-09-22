package com.api.mall.repositories;

import com.api.mall.models.ShopModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IShopRepository extends JpaRepository<ShopModel, Long> {
}
