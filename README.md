# KKUBEURAKKO_BE
꾸브라꼬 안양점 백엔드 개발 레포지토리

### 
## 패키지
### 🏷️ 기본 패키지에 entity명을 하위 패키지로 생성한다.  
ex) User entity의 경우 domain > user > User.java  
User service의 경우 api > service > user > UserService.java  
형태로 생성

ERD  
https://www.erdcloud.com/d/NSHRJKDaXjERp2FPf
  
####
 - domain
 > entity와 관련된 로직  
 > repository도 포함

 - api
 > controller에서 받는 request와 service용 request는 분리

## 응답코드
 - BAD

|제목|내용|설명|
|------|---|---|
|506|콘텐츠 없음|조회된 데이터가 없음|
|테스트1|테스트2|테스트3|
|테스트1|테스트2|테스트3|
