package com.example.kkubeurakko.api.service.menu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.kkubeurakko.api.controller.menu.response.MenuImgResponse;
import com.example.kkubeurakko.api.exception.menu.MenuImgNotFoundException;
import com.example.kkubeurakko.api.service.menu.response.FindMenuResponse;
import com.example.kkubeurakko.domain.menu.MenuLabel;
import com.example.kkubeurakko.domain.menu.MenuRepository;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.S3Object;

@Service
@RequiredArgsConstructor
public class MenuService {
	private final S3Client s3Client;
	@Value("${spring.cloud.aws.s3.bucket}")
	private String bucketName;
	@Value("${spring.cloud.aws.s3.url}")
	private String path;

	private final MenuRepository menuRepository;
	public List<MenuImgResponse> getMenuListForCategory(String label){
		List<MenuImgResponse> menuImgResponseList = new ArrayList<>();
		String menuLabel = MenuLabel.getMenuLabelToLowerCase(label);
		ListObjectsV2Request listObjectsV2Request = ListObjectsV2Request.builder()
			.bucket(bucketName)
			.prefix(path + "/" + menuLabel + "/")
			.build();

		ListObjectsV2Response listObjectsV2Response = s3Client.listObjectsV2(listObjectsV2Request);

		for(S3Object s3Object : listObjectsV2Response.contents()){
			String key = s3Object.key();
			String name = key.substring(key.lastIndexOf("/") + 1);
			try{
				byte[] img = s3Client.getObjectAsBytes(builder -> builder
					.bucket(bucketName)
					.key(key)
					.build()
				).asByteArray();
				FindMenuResponse findMenuResponse = getMenuPriceAndId(name);
				menuImgResponseList.add(
					new MenuImgResponse(
						findMenuResponse.getMenuId(),
						name,
						img,
						findMenuResponse.getPrice()
					)
				);
			} catch (Exception e){
				throw new MenuImgNotFoundException();
			}
		}
		return menuImgResponseList;
	}

	private FindMenuResponse getMenuPriceAndId(String name){
		return menuRepository.findPriceAndIdByMenuName(name);
	}
}
