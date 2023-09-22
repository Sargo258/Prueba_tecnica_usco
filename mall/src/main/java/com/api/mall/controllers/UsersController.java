package com.api.mall.controllers;

import com.api.mall.controllers.request.CreateUserDTO;
import com.api.mall.models.*;
import com.api.mall.repositories.IShopRepository;
import com.api.mall.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IShopRepository shopRepository;

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createUser(@RequestBody CreateUserDTO createUserDTO){

        ShopModel shopModel = shopRepository.findById(createUserDTO.getShopModel().getId())
                .orElseThrow(() -> new IllegalArgumentException("Local no encontrado"));

        //Set<RoleModel> roles = createUserDTO.getRoles().stream()
        //        .map(role -> RoleModel.builder()
        //                .name(ERole.valueOf(role))
        //                .build())
        //        .collect(Collectors.toSet());


        UserModel userModel = UserModel.builder()
                .username(createUserDTO.getUsername())
                .password(passwordEncoder.encode(createUserDTO.getPassword()))
                .email(createUserDTO.getEmail())
                .roles(Set.of(RoleModel.builder()
                        .name(ERole.USER_LOCAL)
                        .build()))
                .shopModel(shopModel)
                .build();

        userRepository.save(userModel);


        return ResponseEntity.ok(userModel);
    }
}
