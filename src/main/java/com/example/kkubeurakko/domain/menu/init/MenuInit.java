package com.example.kkubeurakko.domain.menu.init;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.kkubeurakko.domain.menu.Menu;
import com.example.kkubeurakko.domain.menu.MenuCategory;
import com.example.kkubeurakko.domain.menu.MenuRepository;
import com.example.kkubeurakko.domain.menu.Menu_MenuCategory;
import com.example.kkubeurakko.domain.menu.Menu_MenuCategoryRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MenuInit {
	private final MenuRepository menuRepository;
	private final Menu_MenuCategoryRepository menuMenuCategoryRepository;
	@PostConstruct
	private void initialize(){
		initializeMenu();
		initializeCategory();
	}
	private void initializeMenu(){
		List<Menu> menuList = List.of(
			new Menu(
				1L,
				"양념꾸브",
				BigDecimal.valueOf(19_000),
				"숯불양념구이 + 치킨무 + 콜라500ml\n꾸브라꼬만의 특제소스의 맛\n달달 매콤한 맛을 좋아하시는 분",
				"https://menu-img-bucket.s3.ap-northeast-2.amazonaws.com/menu-img/trending/%EC%96%91%EB%85%90%EA%BE%B8%EB%B8%8C.jpg",
				LocalDateTime.now(ZoneId.of("Asia/Seoul")),
				LocalDateTime.now(ZoneId.of("Asia/Seoul"))
			),
			new Menu(
				2L,
				"소금꾸브",
				BigDecimal.valueOf(18_000),
				"숫불향 가득, 고소담백한 숯불소금구이",
				"https://menu-img-bucket.s3.ap-northeast-2.amazonaws.com/menu-img/trending/%EC%88%AF%EB%B6%88%EC%86%8C%EA%B8%88%EA%B5%AC%EC%9D%B4.jpg",
				LocalDateTime.now(ZoneId.of("Asia/Seoul")),
				LocalDateTime.now(ZoneId.of("Asia/Seoul"))
			),
			new Menu(
				3L,
				"데리꾸브",
				BigDecimal.valueOf(19_000),
				"데리야끼 + 치킨무 + 콜라 500ml\n추억 속에 있는 데리야끼의 맛\n'단짠의 정석'",
				"https://menu-img-bucket.s3.ap-northeast-2.amazonaws.com/menu-img/trending/%EC%88%AF%EB%B6%88%EB%8D%B0%EB%A6%AC%EC%95%BC%EB%81%BC.jpg",
				LocalDateTime.now(ZoneId.of("Asia/Seoul")),
				LocalDateTime.now(ZoneId.of("Asia/Seoul"))
			),
			new Menu(
				4L,
				"170도 후라이드",
				BigDecimal.valueOf(18_000),
				"고소바삭한 후라이드치킨의 정석",
				"https://menu-img-bucket.s3.ap-northeast-2.amazonaws.com/menu-img/trending/%ED%9B%84%EB%9D%BC%EC%9D%B4%EB%93%9C%EC%B9%98%ED%82%A8.jpg",
				LocalDateTime.now(ZoneId.of("Asia/Seoul")),
				LocalDateTime.now(ZoneId.of("Asia/Seoul"))
			),
			new Menu(
				5L,
				"취향존중 두마리세트",
				BigDecimal.valueOf(30_900),
				"입맛대로 취향대로 골라먹는 두마리세트",
				"https://menu-img-bucket.s3.ap-northeast-2.amazonaws.com/menu-img/trending/%EB%82%B4%EB%A7%98%EB%8C%80%EB%A1%9C_%EB%91%90%EB%A7%88%EB%A6%AC%EC%84%B8%ED%8A%B8.jpg",
				LocalDateTime.now(ZoneId.of("Asia/Seoul")),
				LocalDateTime.now(ZoneId.of("Asia/Seoul"))
			),
			new Menu(
				6L,
				"170도 마늘간장",
				BigDecimal.valueOf(19_000),
				"알싸한 마늘향과 깊은 간장의 황금비율 마늘간장치킨",
				"https://menu-img-bucket.s3.ap-northeast-2.amazonaws.com/menu-img/chicken/%EB%A7%88%EB%8A%98%EA%B0%84%EC%9E%A5%EC%B9%98%ED%82%A8.jpg",
				LocalDateTime.now(ZoneId.of("Asia/Seoul")),
				LocalDateTime.now(ZoneId.of("Asia/Seoul"))
			),
			new Menu(
				7L,
				"마라꾸브",
				BigDecimal.valueOf(21_000),
				"중독성 강한 마라와 화끈한 숯불향의 만남, 숯불마라구이",
				"https://menu-img-bucket.s3.ap-northeast-2.amazonaws.com/menu-img/chicken/%EB%A7%88%EB%9D%BC%EB%9D%BD%E6%A8%82.png",
				LocalDateTime.now(ZoneId.of("Asia/Seoul")),
				LocalDateTime.now(ZoneId.of("Asia/Seoul"))
			),
			new Menu(
				8L,
				"순살 마라꾸브",
				BigDecimal.valueOf(22_500),
				"중독성 강한 마라와 화끈한 숯불향의 만남, 숯불마라구이",
				"https://menu-img-bucket.s3.ap-northeast-2.amazonaws.com/menu-img/chicken/%EC%88%9C%EC%82%B4+%EB%A7%88%EB%9D%BC%EB%9D%BD%E6%A8%82.png",
				LocalDateTime.now(ZoneId.of("Asia/Seoul")),
				LocalDateTime.now(ZoneId.of("Asia/Seoul"))
			),
			new Menu(
				9L,
				"170도 양념치킨",
				BigDecimal.valueOf(19_000),
				"매콤달달한 특제 소스로 중독성 강한 숯불양념구이",
				"https://menu-img-bucket.s3.ap-northeast-2.amazonaws.com/menu-img/chicken/%EC%96%91%EB%85%90%EC%B9%98%ED%82%A8.jpg",
				LocalDateTime.now(ZoneId.of("Asia/Seoul")),
				LocalDateTime.now(ZoneId.of("Asia/Seoul"))
			),
			new Menu(
				10L,
				"감미버터 구이",
				BigDecimal.valueOf(20_000),
				"부드러운 버터와 달콤한 소스의 감미로운 조화 풍미 가득한\n감미버터구이",
				"https://menu-img-bucket.s3.ap-northeast-2.amazonaws.com/menu-img/side/%EA%B0%90%EB%AF%B8%EB%B2%84%ED%84%B0%EA%B5%AC%EC%9D%B4.png",
				LocalDateTime.now(ZoneId.of("Asia/Seoul")),
				LocalDateTime.now(ZoneId.of("Asia/Seoul"))
			),
			new Menu(
				11L,
				"고소할거야..고소한 떡튀김",
				BigDecimal.valueOf(1_500),
				"아는사람만 아는 떡튀김\n숯불양념구이와 환상의 조합",
				"https://menu-img-bucket.s3.ap-northeast-2.amazonaws.com/menu-img/side/%EA%B3%A0%EC%86%8C%ED%95%9C+%EB%96%A1%ED%8A%80%EA%B9%80.jpg",
				LocalDateTime.now(ZoneId.of("Asia/Seoul")),
				LocalDateTime.now(ZoneId.of("Asia/Seoul"))
			),
			new Menu(
				12L,
				"양념구이와 찰떡궁합 김말이",
				BigDecimal.valueOf(4_000),
				"고추향이 은은하게 퍼지는 '김말이'\n5p",
				"https://menu-img-bucket.s3.ap-northeast-2.amazonaws.com/menu-img/side/%EA%B9%80%EB%A7%90%EC%9D%B4.jpg",
				LocalDateTime.now(ZoneId.of("Asia/Seoul")),
				LocalDateTime.now(ZoneId.of("Asia/Seoul"))
			),
			new Menu(
				13L,
				"대게 맛있는 게살튀김",
				BigDecimal.valueOf(4_000),
				"게살이 통~통~",
				"https://menu-img-bucket.s3.ap-northeast-2.amazonaws.com/menu-img/side/%EB%8C%80%EA%B2%8C+%EA%B2%8C%EC%82%B4%ED%8A%80%EA%B9%80.jpg",
				LocalDateTime.now(ZoneId.of("Asia/Seoul")),
				LocalDateTime.now(ZoneId.of("Asia/Seoul"))
			),
			new Menu(
				14L,
				"마늘통통 똥집튀김",
				BigDecimal.valueOf(12_000),
				"겉은 바삭 속은 쫄깃한 똥집튀김과 알싸한 통마늘을 한번에",
				"https://menu-img-bucket.s3.ap-northeast-2.amazonaws.com/menu-img/side/%EB%A7%88%EB%8A%98%ED%86%B5%ED%86%B5+%EB%98%A5%EC%A7%91%ED%8A%80%EA%B9%80.jpg",
				LocalDateTime.now(ZoneId.of("Asia/Seoul")),
				LocalDateTime.now(ZoneId.of("Asia/Seoul"))
			),
			new Menu(
				15L,
				"맛감자",
				BigDecimal.valueOf(4_000),
				"동글동글 짭조롬한 감자를 소스에 촵~",
				"https://menu-img-bucket.s3.ap-northeast-2.amazonaws.com/menu-img/side/%EB%A7%9B%EA%B0%90%EC%9E%90.jpg",
				LocalDateTime.now(ZoneId.of("Asia/Seoul")),
				LocalDateTime.now(ZoneId.of("Asia/Seoul"))
			),
			new Menu(
				16L,
				"모듬 튀김",
				BigDecimal.valueOf(5_500),
				"김말이 2p + 야채튀김 2p + 오징어튀김 2p",
				"https://menu-img-bucket.s3.ap-northeast-2.amazonaws.com/menu-img/side/%EB%AA%A8%EB%93%AC+%ED%8A%80%EA%B9%80.jpg",
				LocalDateTime.now(ZoneId.of("Asia/Seoul")),
				LocalDateTime.now(ZoneId.of("Asia/Seoul"))
			),
			new Menu(
				17L,
				"모짜렐라치즈",
				BigDecimal.valueOf(3_000),
				"직화로 녹여주는 치즈~\n양념구이, 데리야끼 추가가능",
				"https://menu-img-bucket.s3.ap-northeast-2.amazonaws.com/menu-img/side/%EB%AA%A8%EC%A7%9C%EB%A0%90%EB%9D%BC%EC%B9%98%EC%A6%88.jpg",
				LocalDateTime.now(ZoneId.of("Asia/Seoul")),
				LocalDateTime.now(ZoneId.of("Asia/Seoul"))
			),
			new Menu(
				18L,
				"미니 찹쌀 핫도그",
				BigDecimal.valueOf(5_000),
				"맛나고 쫀득한 불도그를 한입에~\n6p",
				"https://menu-img-bucket.s3.ap-northeast-2.amazonaws.com/menu-img/side/%EB%AF%B8%EB%8B%88+%EC%B0%B9%EC%8C%80%EB%8F%84%EA%B7%B8.jpg",
				LocalDateTime.now(ZoneId.of("Asia/Seoul")),
				LocalDateTime.now(ZoneId.of("Asia/Seoul"))
			),
			new Menu(
				19L,
				"자기야 커플링 대신 새우링",
				BigDecimal.valueOf(4_000),
				"새우가 가득~ '새우링'",
				"https://menu-img-bucket.s3.ap-northeast-2.amazonaws.com/menu-img/side/%EC%83%88%EC%9A%B0%EB%A7%81.jpg",
				LocalDateTime.now(ZoneId.of("Asia/Seoul")),
				LocalDateTime.now(ZoneId.of("Asia/Seoul"))
			),
			new Menu(
				20L,
				"시즈닝 듬뿍 양념감자",
				BigDecimal.valueOf(4_000),
				"배불러도 계속 손이 가능 중독성 '양념감자'",
				"https://menu-img-bucket.s3.ap-northeast-2.amazonaws.com/menu-img/side/%EC%8B%9C%EC%A6%88%EB%8B%9D+%EC%96%91%EB%85%90%EA%B0%90%EC%9E%90.jpg",
				LocalDateTime.now(ZoneId.of("Asia/Seoul")),
				LocalDateTime.now(ZoneId.of("Asia/Seoul"))
			),
			new Menu(
				21L,
				"엄청난 우동사리 볶음(인기)",
				BigDecimal.valueOf(3_500),
				"탱탱쫄깃쫄깃한 우동면과 소스의 조화",
				"https://menu-img-bucket.s3.ap-northeast-2.amazonaws.com/menu-img/side/%EC%9A%B0%EB%8F%99%EC%82%AC%EB%A6%AC%EB%B3%B6%EC%9D%8C.jpg",
				LocalDateTime.now(ZoneId.of("Asia/Seoul")),
				LocalDateTime.now(ZoneId.of("Asia/Seoul"))
			),
			new Menu(
				22L,
				"즉석밥",
				BigDecimal.valueOf(2_000),
				"숯불치킨에 빠질 수 없는 '치밥'\n데워서 보내드릴게용",
				"https://menu-img-bucket.s3.ap-northeast-2.amazonaws.com/menu-img/side/%EC%A6%89%EC%84%9D%EB%B0%A5.jpg",
				LocalDateTime.now(ZoneId.of("Asia/Seoul")),
				LocalDateTime.now(ZoneId.of("Asia/Seoul"))
			),
			new Menu(
				23L,
				"띠듀스틱 사주세요 치즈스틱",
				BigDecimal.valueOf(4_000),
				"겉은 바삭 속은 쫀득쫀득 '치즈스틱'\n5p",
				"https://menu-img-bucket.s3.ap-northeast-2.amazonaws.com/menu-img/side/%EC%B9%98%EC%A6%88%EC%8A%A4%ED%8B%B1.jpg",
				LocalDateTime.now(ZoneId.of("Asia/Seoul")),
				LocalDateTime.now(ZoneId.of("Asia/Seoul"))
			),
			new Menu(
				24L,
				"치킨똥집 세트",
				BigDecimal.valueOf(29_000),
				"단품메뉴1 + 마늘통통 똥집튀김 + 치킨무 + 콜라 500ml",
				"https://menu-img-bucket.s3.ap-northeast-2.amazonaws.com/menu-img/%EC%B9%98%ED%82%A8%EB%98%A5%EC%A7%91%EC%84%B8%ED%8A%B8.jpg",
				LocalDateTime.now(ZoneId.of("Asia/Seoul")),
				LocalDateTime.now(ZoneId.of("Asia/Seoul"))
			),
			new Menu(
				25L,
				"순살 감미버터꾸브",
				BigDecimal.valueOf(21_500),
				"부드러운 버터와 달콤한 소스의 감미로운 조화 풍미 가득한\n감미버터구이",
				"https://menu-img-bucket.s3.ap-northeast-2.amazonaws.com/menu-img/%EC%88%9C%EC%82%B4_%EA%B0%90%EB%AF%B8%EB%B2%84%ED%84%B0%EA%B5%AC%EC%9D%B4.png",
				LocalDateTime.now(ZoneId.of("Asia/Seoul")),
				LocalDateTime.now(ZoneId.of("Asia/Seoul"))
			),
			new Menu(
				26L,
				"포장마차 떡볶이",
				BigDecimal.valueOf(5_000),
				"오동통한 가래떡이 들어간 부산식 떡볶이\n포장마차에서 먹던 그 맛 그대로...",
				"https://menu-img-bucket.s3.ap-northeast-2.amazonaws.com/menu-img/side/%ED%8F%AC%EC%9E%A5%EB%A7%88%EC%B0%A8+%EB%96%A1%EB%B3%B6%EC%9D%B4.jpg",
				LocalDateTime.now(ZoneId.of("Asia/Seoul")),
				LocalDateTime.now(ZoneId.of("Asia/Seoul"))
			),
			new Menu(
				27L,
				"진짜진짜 쪼~온득한 흑미 치즈볼",
				BigDecimal.valueOf(5_000),
				"찹쌀도넛과 자연산치즈의 조화\n5p",
				"https://menu-img-bucket.s3.ap-northeast-2.amazonaws.com/menu-img/side/%ED%9D%91%EB%AF%B8+%EC%B9%98%EC%A6%88%EB%B3%BC.jpg",
				LocalDateTime.now(ZoneId.of("Asia/Seoul")),
				LocalDateTime.now(ZoneId.of("Asia/Seoul"))
			)
		);

		menuRepository.saveAll(menuList);
	}
	private void initializeCategory(){
		List<Menu_MenuCategory> menuCategoryList = List.of(
			new Menu_MenuCategory(
				1L,
				MenuCategory.TRENDING
			),
			new Menu_MenuCategory(
				1L,
				MenuCategory.CHICKEN
			),
			new Menu_MenuCategory(
				2L,
				MenuCategory.TRENDING
			),
			new Menu_MenuCategory(
				2L,
				MenuCategory.CHICKEN
			),
			new Menu_MenuCategory(
				3L,
				MenuCategory.CHICKEN
			),
			new Menu_MenuCategory(
				4L,
				MenuCategory.CHICKEN
			),
			new Menu_MenuCategory(
				5L,
				MenuCategory.TRENDING
			),
			new Menu_MenuCategory(
				5L,
				MenuCategory.PACKAGE
			),
			new Menu_MenuCategory(
				6L,
				MenuCategory.CHICKEN
			),
			new Menu_MenuCategory(
				7L,
				MenuCategory.NEW
			),
			new Menu_MenuCategory(
				7L,
				MenuCategory.CHICKEN
			),
			new Menu_MenuCategory(
				8L,
				MenuCategory.NEW
			),
			new Menu_MenuCategory(
				8L,
				MenuCategory.CHICKEN
			),
			new Menu_MenuCategory(
				9L,
				MenuCategory.CHICKEN
			),
			new Menu_MenuCategory(
				10L,
				MenuCategory.CHICKEN
			),
			new Menu_MenuCategory(
				11L,
				MenuCategory.SIDE
			),
			new Menu_MenuCategory(
				12L,
				MenuCategory.SIDE
			),
			new Menu_MenuCategory(
				13L,
				MenuCategory.SIDE
			),
			new Menu_MenuCategory(
				13L,
				MenuCategory.SIDE
			),
			new Menu_MenuCategory(
				14L,
				MenuCategory.SIDE
			),
			new Menu_MenuCategory(
				15L,
				MenuCategory.SIDE
			),
			new Menu_MenuCategory(
				16L,
				MenuCategory.SIDE
			),
			new Menu_MenuCategory(
				17L,
				MenuCategory.SIDE
			),
			new Menu_MenuCategory(
				18L,
				MenuCategory.SIDE
			),
			new Menu_MenuCategory(
				19L,
				MenuCategory.SIDE
			),
			new Menu_MenuCategory(
				20L,
				MenuCategory.SIDE
			),
			new Menu_MenuCategory(
				21L,
				MenuCategory.SIDE
			),
			new Menu_MenuCategory(
				22L,
				MenuCategory.SIDE
			),
			new Menu_MenuCategory(
				23L,
				MenuCategory.SIDE
			),
			new Menu_MenuCategory(
				24L,
				MenuCategory.PACKAGE
			),
			new Menu_MenuCategory(
				25L,
				MenuCategory.CHICKEN
			),
			new Menu_MenuCategory(
				26L,
				MenuCategory.SIDE
			),
			new Menu_MenuCategory(
				27L,
				MenuCategory.SIDE
			)
		);

		menuMenuCategoryRepository.saveAll(menuCategoryList);
	}
}
