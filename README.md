## 프로젝트 개요
"자바 웹 개발 워크북"이라는 책을 실습하며 CRUD 게시판을 구현하였습니다. 댓글 달기, 파일 첨부, 게시판 조회 등의 기능을 제공하며, 서버 사이드 렌더링 방식(Thymeleaf)을 사용해 화면을 구성하였습니다.


<!--
# API 서버를 만들면서 기록한 내용들 입니다.
-->
## 개발환경
- 개발언어: 자바
- IDE: IntellJ(Community Edition)
- 프로젝트 SDK: JDK 11
- Spring Boot: 2.7.14
- 의존성 관리 툴: Gradle

## Architecture
<img width="700" alt="image" src="https://github.com/HJC96/uploadhub/assets/87226129/c34eb09d-3bd1-4e95-89ee-9b2a999b9b17">




## ERD 설계
<img width="501" alt="image" src="https://github.com/HJC96/uploadhub/assets/87226129/399eb054-0297-4756-966b-b5e0e07a7ce3">


## 화면 구성
부트스트랩의 디자인 템플릿 중 ‘Simple Side-bar’를 이용하였습니다.

- ### http://localhost:8080/board/list
<img width="900" alt="image" src="https://github.com/HJC96/uploadhub/assets/87226129/bd8f1c69-5242-4621-b6f7-7bb8f2c21557">


- ### http://localhost:8080/board/register
<img width="900" alt="image" src="https://github.com/HJC96/uploadhub/assets/87226129/e893bc62-cc19-4ccc-8956-c1b0316dcbd8">

- ### http://localhost:8080/board/read?bno=11&null
<img width="900" alt="image" src="https://github.com/HJC96/uploadhub/assets/87226129/13bce9e2-166e-4dc9-889a-d57354a6df72">

- ### http://localhost:8080/board/modify?bno=11&null
<img width="900" alt="image" src="https://github.com/HJC96/uploadhub/assets/87226129/ef9737be-942e-49a0-abee-387620fac502">


## Overview
<img width="900" alt="스크린샷 2023-08-17 오전 7 13 52" src="https://github.com/HJC96/uploadhub/assets/87226129/b7ae8aa5-1aa7-40b2-a9bf-d3adf15df3f0">
