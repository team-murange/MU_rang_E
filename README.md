
<img src="https://user-images.githubusercontent.com/77563814/202142308-614a5630-8757-44f6-bf08-cd388874750d.png" href="src/main/resources/static/images/murange.pdf"/>

<h1 align="center"> MUrangE <img src="https://user-images.githubusercontent.com/77563814/209101841-f736b358-8f22-4988-b24d-5ddd057a6edf.png" width="27" height="27"/> </h1>

#### 목차 

1. [뮤랑이 소개](#뮤랑이murange-소개)
2. [프로젝트 시연](#-프로젝트-시연)
3. [웹 구조도](#-웹-구조도)
4. [API 설계](#-api-설계)
5. [DB 설계](#-db-설계)
6. [UI 기획](#-ui-기획)
7. [License](#-license)
8. [개발 일지 및 이슈](#-개발-일지-및-이슈)
9. [사용자 피드백](#-사용자-피드백)

## 뮤랑이(MUrangE) 소개

    뮤랑이는 표정 인식 기반 음악 추천 웹사이트 (MUsic🎵 & Emotion😀)입니다.
    학교 졸업 프로젝트, 수상🎉

#### 프로젝트 소개 : [PPT 링크 이동하기](src/main/resources/static/images/murange.pdf) | [PDF 다운로드하기](https://github.com/2022-project/MU_rang_E/files/10293762/murange.pdf)

#### 메인 기능

1. **표정 분석 후 음악 추천** :  사용자의 표정을 5초간 **분석 후, 해당 감정에 맞는 음악을 추천**해줍니다.
2. **감정 색상 달력** : 분석 후, 사용자의 감정이 **달력에 색상으로 저장**됩니다. (가장 높은 수치의 감정 2가지를 기준으로 색상이 정해집니다)




## 💻 프로젝트 시연

#### 사이트 이동 : https://www.murange.site

https://user-images.githubusercontent.com/77563814/209975379-de27c09f-df02-4de2-8eea-5879967c604f.mp4

## 🏠 웹 구조도

![image](https://user-images.githubusercontent.com/77563814/198512288-ecb70706-b587-4c3f-802b-e5c4657dd03e.png)

- **FE** : Javascript, Ajax, JQeury, HTML, CSS
- **BE** : Java Spring, MySQL, Spring Data JPA, Querydsl

## 📁 API 설계

- **API 문서 (Postman)** : https://documenter.getpostman.com/view/17088295/2s8ZDbX1Qk

![image](https://user-images.githubusercontent.com/77563814/214326515-5afd91b6-ecc5-487e-bd9c-c7037e6105d3.png)

## 📂 DB 설계

![murangeDB](https://user-images.githubusercontent.com/77563814/209538865-e16752b1-8785-42f7-b929-e816f777dc8a.png)

## 🖼 UI 기획

![image](https://user-images.githubusercontent.com/77563814/193090214-6f6e2ff0-022e-4045-8e94-60dd72965cdd.png)

## 📑 License

- Face Detection API : https://github.com/WebDevSimplified/Face-Detection-JavaScript
- Javascript Calendar API : https://github.com/brospars/simple-calendar
- SoundCloud Crawling : https://github.com/2022-project/crawling

## 📝 개발 일지 및 이슈
- **개발 일지**
  1. [뮤랑이를 소개합니다 🎵😀](https://sooyoungh.github.io/murange-1)
  2. [메인 기능1 : 표정 분석 후 음악 추천 with Querydsl](https://sooyoungh.github.io/murange-3)
  3. [메인 기능2 : 오늘의 감정 색상을 달력에 저장하기 with Enum](https://sooyoungh.github.io/murange-4)
  8. [HTTPS로 배포하기 with AWS](https://sooyoungh.github.io/murange-8)
- **이슈 및 해결**
    - [Camera API HTTP 작동 불가](https://sooyoungh.github.io/murange-issue-1)
    - [CORS 에러와 Spring Security](https://sooyoungh.github.io/murange-issue-2)

## 📬 사용자 피드백

> 학교 졸업 전시회에서 시연 후 수집한 사용자 피드백


[전체 사용자 피드백.xlsx](https://github.com/2022-project/MU_rang_E/files/10293756/default.xlsx)

[⬆맨 위로 가기](#목차)
