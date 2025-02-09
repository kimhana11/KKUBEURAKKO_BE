package com.example.kkubeurakko.api.service.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.example.kkubeurakko.api.controller.menu.response.MenuImgResponse;
import com.example.kkubeurakko.api.exception.menu.MenuImgNotFoundException;
import com.example.kkubeurakko.api.service.menu.mapper.MenuMapper;
import com.example.kkubeurakko.domain.menu.Menu;
import com.example.kkubeurakko.domain.menu.MenuCategory;
import com.example.kkubeurakko.domain.menu.MenuLabel;
import com.example.kkubeurakko.domain.menu.MenuRepository;
import com.example.kkubeurakko.domain.menu.Menu_MenuCategoryRepository;
import com.example.kkubeurakko.domain.menu.Menu_MenuLabelRepository;

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
	private final Menu_MenuLabelRepository menuMenuLabelRepository;

	private final Map<String, Function<String, List<Long>>> ID_FETCH_STRATEGY =
		Map.of(
			"category", variable -> menuMenuCategoryRepository.findMenuIdListByCategory(MenuCategory.getMenuCategory(variable)),
			"label", variable -> menuMenuLabelRepository.findMenuIdListByLabel(MenuLabel.getMenuLabel(variable))
		);

	public List<MenuImgResponse> getMenuList(String classification, String variable){

		List<Long> menuIdList = fetchMenuIds(classification, variable);
		return menuToMenuImgResponse(menuIdList);
	}

	private List<Long> fetchMenuIds(String classification, String variable){
		return ID_FETCH_STRATEGY.getOrDefault(classification, v -> List.of())
			.apply(variable);
	}

	private List<MenuImgResponse> menuToMenuImgResponse(List<Long> menuIdList) {
		try {
			if (menuIdList.isEmpty()) {
				throw new NullPointerException();
			}
			Map<Long, Menu> menuMap = menuRepository.findAllById(menuIdList).stream()
				.collect(Collectors.toMap(Menu::getId, Function.identity()));

			return menuIdList.stream()
				.map(menuMap::get)
				.filter(Objects::nonNull)
				.map(menuMapper::menuToMenuImgResponse)
				.collect(Collectors.toList());
		} catch (NullPointerException e) {
			throw new MenuImgNotFoundException();
		}
	}
}
