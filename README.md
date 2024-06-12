# springboot-2024
Java 빅데이터 개발자과정 Spring Boot 학습 리포지토리

## 1일차
- Spring Boot 개요

- Spring Boot
    - Visual Studio Code
        - Extensions > Korean 검색, 설치
        - Extensions > Java 검색, Extension Pack for Java 설치
            - Debugger for Java 등 여섯 개 확장팩이 같이 설치
        - Extensions > Spring 검색 , Spring Extension
            - Spring Initializr Java Support 등 세 개의 확장팩이 같이 설치
        - Extensions > Gradle for Java 검색 설치
    - Gradle build tool 설치를 고려
        - https://gradle.org/releases/
    - Oracle 최신 버전 Docker
    - JDK 확인 > Spring 부트를 사용하기 위해서는 17버전 이상이 되어야 한다.
        - https://jdk.java.net/archive/
        - 시스템 속성 > 고급 > 환경 변수 중 JAVA_HOME 설정

- Spring Boot 프로젝트 생성
    - 메뉴 보기 > 명령 팔레트(Ctrl + Shift + P)
        - Spring Initalzr: Create a Gradle Project...
        - Specify Spring Boot version : 3.2.6
        - Specify project language : Java
        - Input Group Id : com.promm (개인적으로 변경할 것)
        - Input Artifact Id : spring01 (대문자 불가)
        - Specify packaging type : Jar
        - Specify Java version : 17
        - Search for dependencies : Selected 0 dependencies(나중에 추가해줄 것임)
        - 폴더 선택 Diaglog 팝업 : 원하는 폴더 선택 Generate ... 버튼 클릭
        - 우측 하단 팝업에서 open 버튼 클릭
        - Git 설정 옵션, Language Support for JAVA(TM) by Red Hat 설정 항상버튼 클릭


    - Node.js
    - React setting