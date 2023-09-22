package com.api.mall.services;

import com.api.mall.models.ShopModel;
import com.api.mall.repositories.IShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ShopService {

    @Autowired
    IShopRepository shopRepository;


    public ArrayList<ShopModel> getShops() {
        return (ArrayList<ShopModel>) shopRepository.findAll();
    }

    public ShopModel saveShop(ShopModel shop) {
        return shopRepository.save(shop);
    }

}
