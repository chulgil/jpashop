# 스프링 부트와 JPA을 활용한 쇼핑몰 웹앱 개발

---
## 1. 프로젝트 환경설정

 > [스프링 부트 스타터](https://start.spring.io/)로 프로젝트 생성
  - 자바 버젼 : 11
  - 스프링프레임워크 : 2.6.3
  - 핵심 라이브러리 : 스프링 MVC, 스프링ORM, JPA, 하이버네이트 , 스프링데이터 JPA
  - 기타 라이브러리 : H2, 커넥션풀:HicariCP, Thymeleaf, 로깅(SLF4J & LogBack)
  - 테스트 라이브러리 : junit, mockito, assertj, spring-test
  - 의존성관리 : gradle 7.3.3

 > 라이브러리 상세

 * spring-boot-starter-web
   - spring-boot-starter-tomcat: 톰캣 (웹서버)
   - spring-boot-starter-thymeleaf: 타임리프 템플릿 엔진(View)
 * spring-boot-starter-data-jpa
 * spring-boot-starter-aop
   - spring-boot-starter-jdbc
   - HikariCP 커넥션 풀 (부트 2.0 기본)
   - hibernate + JPA: 하이버네이트 + JPA
   - spring-data-jpa: 스프링 데이터 JPA
 * spring-boot-starter(공통): 스프링 부트 + 스프링 코어 + 로깅
   - spring-boot
   - spring-core
   - spring-boot-starter-logging
   - logback, slf4j
 * spring-boot-starter-test
   - junit: 테스트 프레임워크
   - mockito: 목 라이브러리
   - assertj: 테스트 코드를 좀 더 편하게 작성하게 도와주는 라이브러리
   - spring-test: 스프링 통합 테스트 지원

> SQL실행 파라미터를 로그로 남기기
* 다음 [외부 라이브러리](https://github.com/gavlyukovskiy/spring-boot-data-source-decorator) 추가
 - `implementation 'com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.8.0'`
 - 주의 : 운영 환경에서는 성능 이슈가 있을 수 있기 때문에 제외한다.

---

## 2. 도메인 분석 설계

### 2.1 요구사항 분석

>  기능목록

 - 회원 기능 : 등록, 조회
 - 상품 기능 : 등록, 조회, 수정
 - 주문 기능 : 상품 주문, 주문 조회, 주문 취소
 - 요구 사항 :
   - 상품은 재고 관리가 필요
   - 상품을 카테고리로 구분 (도서, 음반, 영화) 
   - 상품 주문시 배송정보 입력
 
### 2.2 도메인 모델
---
![기본 도메인](uml/BaseDomain.png)
---
![상세 도메인](uml/DetailDomain.png)
---
![테이블 설계](uml/ERD.png)
---

