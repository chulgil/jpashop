## 스프링 부트와 JPA을 활용한 쇼핑몰 웹앱 개발

---
### 0. 프로젝트 환경설정

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