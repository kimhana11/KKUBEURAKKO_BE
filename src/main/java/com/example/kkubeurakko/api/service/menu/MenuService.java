package com.example.kkubeurakko.api.service.menu;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.kkubeurakko.api.controller.menu.response.MenuImgResponse;
import com.example.kkubeurakko.api.exception.menu.MenuImgNotFoundException;
import com.example.kkubeurakko.api.service.menu.mapper.MenuMapper;
import com.example.kkubeurakko.domain.menu.Menu;
import com.example.kkubeurakko.domain.menu.MenuCategory;
import com.example.kkubeurakko.domain.menu.MenuRepository;
import com.example.kkubeurakko.domain.menu.Menu_MenuCategoryRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class MenuService {
	// private final S3Client s3Client;
	// @Value("${spring.cloud.aws.s3.bucket}")
	// private String bucketName;
	// @Value("${spring.cloud.aws.s3.url}")
	// private String path;

	private final MenuRepository menuRepository;
	private final Menu_MenuCategoryRepository menuMenuCategoryRepository;
	private final MenuMapper menuMapper;
	public List<MenuImgResponse> getMenuListForCategory(String category){
		MenuCategory menuCategory = MenuCategory.getMenuCategory(category);
		List<Long> menuIdList = menuMenuCategoryRepository.findMenuIdListByCategory(menuCategory);

		return menuToMenuImgResponse(menuIdList);
	}

	private List<MenuImgResponse> menuToMenuImgResponse(List<Long> menuIdList){
		List<MenuImgResponse> menuImgResponseList = new ArrayList<>();
		for(Long id : menuIdList){
			Menu menu = menuRepository.findById(id).orElseThrow(()-> new MenuImgNotFoundException());
			MenuImgResponse menuImgResponse = menuMapper.menuToMenuImgResponse(menu);
			menuImgResponseList.add(menuImgResponse);
		}
		return menuImgResponseList;
	}
}
