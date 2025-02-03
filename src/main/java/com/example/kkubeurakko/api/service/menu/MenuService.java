package com.example.kkubeurakko.api.service.menu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.kkubeurakko.api.controller.menu.response.MenuImgResponse;
import com.example.kkubeurakko.api.exception.menu.MenuImgNotFoundException;
import com.example.kkubeurakko.api.service.menu.mapper.MenuMapper;
import com.example.kkubeurakko.api.service.menu.response.FindMenuResponse;
import com.example.kkubeurakko.domain.menu.Menu;
import com.example.kkubeurakko.domain.menu.MenuCategory;
import com.example.kkubeurakko.domain.menu.MenuLabel;
import com.example.kkubeurakko.domain.menu.MenuRepository;
import com.example.kkubeurakko.domain.menu.Menu_MenuCategoryRepository;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.S3Object;

@Service
@RequiredArgsConstructor
public class MenuService {
	private final S3Client s3Client;
	// @Value("${spring.cloud.aws.s3.bucket}")
	// private String bucketName;
	// @Value("${spring.cloud.aws.s3.url}")
	// private String path;

	private final MenuRepository menuRepository;
	private final Menu_MenuCategoryRepository menuMenuCategoryRepository;
	private final MenuMapper menuMapper;
	public List<MenuImgResponse> getMenuListForCategory(String category){
		// List<MenuImgResponse> menuImgResponseList = new ArrayList<>();
		// String menuLabel = MenuCategory.getMenuCategoryToLowerCase(label);
		// ListObjectsV2Request listObjectsV2Request = ListObjectsV2Request.builder()
		// 	.bucket(bucketName)
		// 	.prefix(path + "/" + menuLabel + "/")
		// 	.build();
		//
		// ListObjectsV2Response listObjectsV2Response = s3Client.listObjectsV2(listObjectsV2Request);
		//
		// for(S3Object s3Object : listObjectsV2Response.contents()){
		// 	String key = s3Object.key();
		// 	String name = key.substring(key.lastIndexOf("/") + 1);
		// 	try{
		// 		byte[] img = s3Client.getObjectAsBytes(builder -> builder
		// 			.bucket(bucketName)
		// 			.key(key)
		// 			.build()
		// 		).asByteArray();
		// 		FindMenuResponse findMenuResponse = getMenuPriceAndId(name);
		// 		menuImgResponseList.add(
		// 			new MenuImgResponse(
		// 				findMenuResponse.getMenuId(),
		// 				name,
		// 				img,
		// 				findMenuResponse.getPrice()
		// 			)
		// 		);
		// 	} catch (Exception e){
		// 		throw new MenuImgNotFoundException();
		// 	}
		// }
		// return menuImgResponseList;
		MenuCategory menuCategory = MenuCategory.getMenuCategory(category);
		List<MenuImgResponse> menuImgResponseList = new ArrayList<>();
		List<Long> menuIdList = menuMenuCategoryRepository.findMenuIdListByCategory(menuCategory);

		for(Long id : menuIdList){
			Menu menu = menuRepository.findById(id).orElseThrow(()-> new MenuImgNotFoundException());
			MenuImgResponse menuImgResponse = menuMapper.menuToMenuImgResponse(menu);
			menuImgResponseList.add(menuImgResponse);
		}
		return menuImgResponseList;
	}

	private FindMenuResponse getMenuPriceAndId(String name){
		return menuRepository.findPriceAndIdByMenuName(name);
	}
}
