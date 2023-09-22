package com.api.mall.controllers;

import com.api.mall.models.*;
import com.api.mall.repositories.IActionLogRepository;
import com.api.mall.repositories.IShopRepository;

import com.api.mall.repositories.ISubCategoryRepository;
import com.api.mall.repositories.IUserRepository;
import com.api.mall.services.ShopService;
import com.api.mall.services.UserDetailsServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private IShopRepository shopRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ISubCategoryRepository subCategoryRepository;

    @Autowired
    private IActionLogRepository actionLogRepository;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'VIGILANTE', 'USER_LOCAL')")
    public ArrayList<ShopModel> getShops() {
        return this.shopService.getShops();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ShopModel saveShop(@RequestBody @Valid ShopModel shop) {

        SubCategoryModel subCategoryModel = subCategoryRepository.findById(shop.getSubCategoria().getId())
                .orElseThrow(() -> new IllegalArgumentException("Sub categoria no encontrado"));

        shop.setSubCategoria(subCategoryModel);

        ShopModel savedShop = shopService.saveShop(shop);

        // Guardar el registro de acción
        ActionLog actionLog = new ActionLog();
        actionLog.setActionType("SAVE_SHOP");
        actionLog.setEntityType("SHOP");
        actionLog.setEntityId(savedShop.getId());
        actionLog.setTimestamp(LocalDateTime.now());
        actionLogRepository.save(actionLog);


        return savedShop;
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'USER_LOCAL')")
    @PutMapping(path = "/{id}")
    public ResponseEntity<ShopModel> updateShop(@PathVariable long id, @RequestBody ShopModel shopModel) {
        ShopModel updatedModel = shopRepository.findById(id).orElse(null);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        UserModel userModel = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        Long localId = userModel.getShopModel() != null ? userModel.getShopModel().getId() : null;

        System.out.println("localId: " + localId + ", userModel.getId(): " + userModel.getId());


        if (updatedModel == null) {
            return ResponseEntity.notFound().build();
        }

        // Compare localId with the id of the shop to be updated
        if (localId == null || localId.equals(id)) {
            SubCategoryModel subCategoryModel = subCategoryRepository.findById(shopModel.getSubCategoria().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Sub categoria no encontrada"));

            updatedModel.setNombreNegocio(shopModel.getNombreNegocio());
            updatedModel.setUbicacion(shopModel.getUbicacion());
            updatedModel.setRepresentanteLegal(shopModel.getRepresentanteLegal());
            updatedModel.setTelefonoContacto(shopModel.getTelefonoContacto());
            updatedModel.setEstado(shopModel.getEstado());
            updatedModel.setSubCategoria(subCategoryModel);

            ShopModel savedShop = shopRepository.save(updatedModel);

            return ResponseEntity.ok(savedShop);
        } else {
            // localId doesn't match the id of the shop to be updated
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }



    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ShopModel> deleteShop(@PathVariable long id) {
        Optional<ShopModel> optionalShop = shopRepository.findById(id);

        if (optionalShop.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ShopModel shopModel = optionalShop.get();

        shopRepository.delete(shopModel);

        // Guardar el registro de acción
        ActionLog actionLog = new ActionLog();
        actionLog.setActionType("DELETE_SHOP");
        actionLog.setEntityType("SHOP");
        actionLog.setEntityId(id);
        actionLog.setTimestamp(LocalDateTime.now());
        actionLogRepository.save(actionLog);

        return ResponseEntity.ok(shopModel);
    }


}
