package com.api.mall;

import com.api.mall.models.*;
import com.api.mall.repositories.ICategoryRepository;
import com.api.mall.repositories.ISubCategoryRepository;
import com.api.mall.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Set;

@SpringBootApplication
public class MallApplication {

	public static void main(String[] args) {
		SpringApplication.run(MallApplication.class, args);
	}

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	IUserRepository userRepository;

	@Autowired
	ICategoryRepository categoryRepository;

	@Autowired
	ISubCategoryRepository subCategoryRepository;

	@Bean
	CommandLineRunner init() {
		return args -> {
			UserModel userModel = UserModel.builder()
					.email("Santiago@google.com")
					.username("Santiago")
					.password(passwordEncoder.encode("1234"))
					.roles(Set.of(RoleModel.builder()
							.name(ERole.ADMIN)
							.build()))
					.build();

			userRepository.save(userModel);

			UserModel userModel2 = UserModel.builder()
					.email("stive@google.com")
					.username("stiven")
					.password(passwordEncoder.encode("1234"))
					.roles(Set.of(RoleModel.builder()
							.name(ERole.VIGILANTE)
							.build()))
					.build();

			userRepository.save(userModel2);

			//Categories

			CategoryModel categoryModelAlimentos = new CategoryModel();
			categoryModelAlimentos.setNombre(ECategory.ALIMENTOS);
			categoryRepository.save(categoryModelAlimentos);

			CategoryModel categoryModelRopa = new CategoryModel();
			categoryModelRopa.setNombre((ECategory.ROPA_Y_TEXTILES));
			categoryRepository.save(categoryModelRopa);

			CategoryModel categoryModelDeportes = new CategoryModel();
			categoryModelDeportes.setNombre(ECategory.DEPORTES);
			categoryRepository.save(categoryModelDeportes);

			//SubCategories
			Long categoryId1 = 1L;
			CategoryModel categoria1 = categoryRepository.findById(categoryId1).orElse(new CategoryModel());

			SubCategoryModel subCategoryModelFrutas = new SubCategoryModel();
			subCategoryModelFrutas.setNombre(ESubCategory.FRUTAS);
			subCategoryModelFrutas.setCategoria(categoria1);
			subCategoryRepository.save(subCategoryModelFrutas);

			SubCategoryModel subCategoryModelGranos = new SubCategoryModel();
			subCategoryModelGranos.setNombre(ESubCategory.GRANOS);
			subCategoryModelGranos.setCategoria(categoria1);
			subCategoryRepository.save(subCategoryModelGranos);

			SubCategoryModel subCategoryModelVerduras = new SubCategoryModel();
			subCategoryModelVerduras.setNombre(ESubCategory.VERDURAS);
			subCategoryModelVerduras.setCategoria(categoria1);
			subCategoryRepository.save(subCategoryModelVerduras);

			Long categoryId2 = 2L;
			CategoryModel categoria2 = categoryRepository.findById(categoryId2).orElse(new CategoryModel());

			SubCategoryModel subCategoryModelHombre = new SubCategoryModel();
			subCategoryModelHombre.setNombre(ESubCategory.PARA_HOMBRE);
			subCategoryModelHombre.setCategoria(categoria2);
			subCategoryRepository.save(subCategoryModelHombre);

			SubCategoryModel subCategoryModelMujer = new SubCategoryModel();
			subCategoryModelMujer.setNombre(ESubCategory.PARA_MUJER);
			subCategoryModelMujer.setCategoria(categoria2);
			subCategoryRepository.save(subCategoryModelMujer);

			SubCategoryModel subCategoryModelNinos = new SubCategoryModel();
			subCategoryModelNinos.setNombre(ESubCategory.PARA_NIÑOS);
			subCategoryModelNinos.setCategoria(categoria2);
			subCategoryRepository.save(subCategoryModelNinos);

			SubCategoryModel subCategoryModelMixto = new SubCategoryModel();
			subCategoryModelMixto.setNombre(ESubCategory.MIXTO);
			subCategoryModelMixto.setCategoria(categoria2);
			subCategoryRepository.save(subCategoryModelMixto);

			Long categoryId3 = 3L;
			CategoryModel categoria3 = categoryRepository.findById(categoryId3).orElse(new CategoryModel());

			SubCategoryModel subCategoryModelFutbol = new SubCategoryModel();
			subCategoryModelFutbol.setNombre(ESubCategory.FUTBOL);
			subCategoryModelFutbol.setCategoria(categoria3);
			subCategoryRepository.save(subCategoryModelFutbol);

			SubCategoryModel subCategoryModelBaloncesto = new SubCategoryModel();
			subCategoryModelBaloncesto.setNombre(ESubCategory.BALONCESTO);
			subCategoryModelBaloncesto.setCategoria(categoria3);
			subCategoryRepository.save(subCategoryModelBaloncesto);

			SubCategoryModel subCategoryModelVoleibol = new SubCategoryModel();
			subCategoryModelVoleibol.setNombre(ESubCategory.VOLEIBOL);
			subCategoryModelVoleibol.setCategoria(categoria3);
			subCategoryRepository.save(subCategoryModelVoleibol);

		};
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:4200");
			}
		};
	}

}
