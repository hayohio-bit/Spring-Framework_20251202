## 📌 프로젝트 소개
Spring Boot 사용 전 기본 개념과 MVC 흐름 이해를 위한 Spring Legacy 학습 프로젝트입니다.

---

## 🎯 학습 목표
- Spring Framework의 핵심 동작 원리 이해
- Spring MVC 요청 처리 흐름 파악
- DI(Dependency Injection)와 IoC(Inversion of Control) 개념 습득
- XML 기반 설정 및 빈(Bean) 관리 경험
- Spring Boot의 자동 설정 이전의 수동 설정 학습

---

## 🛠 개발 환경
- **Java Version**: Java 8 이상
- **Spring Framework**: Spring Legacy
- **IDE**: Eclipse / STS (Spring Tool Suite)
- **Build Tool**: Maven
- **Server**: Apache Tomcat

---

## 📂 프로젝트 구조
Spring-Framework_20251202/
├── src/
│ └── main/
│ ├── java/
│ │ └── [패키지 구조]
│ ├── resources/
│ │ └── [설정 파일]
│ └── webapp/
│ └── WEB-INF/
│ ├── views/
│ └── web.xml
└── pom.xml

---

## 📚 학습 내용

### 1. Spring MVC 기본 구조
- DispatcherServlet 설정 및 역할
- Handler Mapping & Handler Adapter
- ViewResolver 설정

### 2. Controller 개발
- `@Controller` 어노테이션
- `@RequestMapping`을 통한 URL 매핑
- Model 객체를 통한 데이터 전달

### 3. DI/IoC 실습
- XML 기반 Bean 설정
- Constructor Injection vs Setter Injection
- Component Scan

### 4. View 처리
- JSP를 활용한 View 개발
- JSTL/EL 표현식
- Form 데이터 바인딩

---

## 🔍 Spring Boot와의 차이점

| 항목 | Spring Legacy | Spring Boot |
|------|--------------|-------------|
| 설정 방식 | XML 기반 수동 설정 | 자동 설정 (Auto Configuration) |
| 의존성 관리 | 개별 라이브러리 명시 | Starter 의존성 |
| 내장 서버 | 외부 Tomcat 필요 | 내장 서버 포함 |
| 배포 | WAR 파일 | JAR 파일 (실행 가능) |

---

## 💡 핵심 개념

### Spring MVC 요청 흐름
1. **클라이언트 요청** → DispatcherServlet
2. **HandlerMapping** → 적절한 Controller 탐색
3. **Controller** → 비즈니스 로직 처리 및 Model 데이터 생성
4. **ViewResolver** → View 이름을 실제 JSP 경로로 변환
5. **View(JSP)** → Model 데이터를 활용해 화면 렌더링
6. **응답 반환** → 클라이언트

### DI (Dependency Injection)
- 객체 간의 의존관계를 외부에서 주입
- 결합도 낮추고 테스트 용이성 향상
- XML 또는 Annotation 기반 설정

### IoC (Inversion of Control)
- 제어의 역전: 객체 생성 및 관리를 Spring Container가 담당
- ApplicationContext가 Bean의 생명주기 관리

---

## 📖 참고 자료
- [Spring Framework 공식 문서](https://spring.io/projects/spring-framework)
- 부트캠프 수업 자료
- Spring MVC 아키텍처 다이어그램

---

## 📝 학습 일지
- **2025.12.02**: 프로젝트 초기 설정 및 GitHub 레포지토리 생성

---


## 📄 License
이 프로젝트는 학습 목적으로 작성되었습니다.
