# springboot-2024
Java 빅데이터 개발자과정 Spring Boot 학습 리포지토리

## 1일차
- Spring Boot 개요
    - 개발 환경, 개발 난이도를 낮추는 작업
    - Servlet > EJB > JSP > Spring(부흥기) > Spring Boot(끝판왕!!)
    - 장점
        - Spring의 기술을 그대로 사용가능(마이그레이션 간단)
        - JPA를 사용하면 ERD나 DB 설계를 하지 않고도 손쉽게 DB 생성 가능
        - Tomcat Webserver가 내장(따로 설치할 필요가 없음)
        - 서포트 기능 다수 존재(개발을 쉽게 도와줌)
        - JUnit 테스트, Log4J2 로그도 모두 포함
        - JSP, **Thymeleaf**,Mustache 등... 편하게 사용가능
        - DB 연동이 매우 간단하다.

    - MVC
        <img src="https://raw.githubusercontent.com/KangJeongTaek/springboot-2024/main/images/sp002.png" width="730px">


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

    - TroubleShooting
        1. 프로젝트 생성이 진행되다가 Gradle Connection 에러가 뜨면,
            - Extensions > Gradle for Java를 제거
            - VS Code 재시작한 뒤 프로젝트 재생성
        2. Gradle 빌드시 버전 에러로 빌드가 실패하면
            - Gradle build tool 사이트에서 에러가 표시한 버전의 Gradle bt 다운로드
            - 개발 컴퓨터에 설치
        3.  - :'compileJava' execution failed...
            - JDK 17 ...... error 메시지
            - Java JDK 잘못된 설치 x86(32bit) x64bit 혼용 설치

    - 프로젝트 생성 후
        -/build.gradle과 /resources/application.properites(yml)를 확인
        - src/java/groupid/artifactid/Java 소스파일 위치, 작업
        - src/main/resources/ 프로젝트 설정 파일, 웹 리소스 파일(css,js,html,jsp, ...)
        - Spring01Application.java Run 클릭해서 프로젝트 실행
        - Gradle 빌드
            - 터미널에서 .\gradlew.bat 실행
            - Gradle for Java 확장에서 실행 > Tasks > Build > Build play icon(Run task) 실행
            - Spring Boot Dashboard
                - apps > Spring01 > Run | Debug 중에서 하나 아이콘 클릭해서 서버 실행
                - Debug로 실행해야 Hot code replace가 동작한다.
                    <img src="https://raw.githubusercontent.com/KangJeongTaek/springboot-2024/main/images/sp001.png" width="350px">
            - 브라우저 변경설정
                - 설정(Ctrl + ,) > browser > Spring > Dashboard Open with 'Internal' -> 'external'로 변경
                - Chrome을 기본 브라우저 사용 추천




    - Node.js
    - React setting
    - https://sitereport.netcraft.com/
        - 웹사이트, 웹 서버 확인 사이트


## 2,3일차
- Oracle 도커로 설치 (https://www.docker.com/get-started/)
    - 설치되어 있는 Oracle의 서비스 종료
    - Docker에서 Oracle 이미지 컨테이너를 다운로드 후 실행
    - 윈도우 서비스 내(services.msc) oracle 관련 서비스 종료
    - Docker에서 Oracle 이미지 컨테이너를 다운로드 후 실행
    - Docker 설치 시 오류 Docker Desktop-WSL Update failed
        - Docker Desktop 실행 종료 후 Windows 업데이트 실행 최신판 재부팅
        - https://github.com/microsoft/WSL/releases, wsl.2.x.x.x64.msi 다운로드 설치 한 뒤
        - Docker Desktop 재설치
        - Oracle 최신판 설치
```shell
    > docker --version
    > docker pull container-registry.oracle.com/database/free:latest
    latest: ....
    ... : Download complete
    > docker images
    REPOSITORY                                    TAG       IMAGE ID       CREATED       SIZE
    container-registry.oracle.com/database/free   latest    7510f8869b04   7 weeks ago   8.7GB
    > docker run -d -p 1521:1521 --name oracle container-registry.oracle.com/database/free
    ....
    > docker logs oracle
    ...
    ####################
    DATABASE IS READY TO USE!
    ####################
    ...
    > docker exec -it oracle bash
    bash-4.4$
```

- oracle system 사용자 비번 oracle 설정
```shell
    bash-4.4$ ./setPassword.sh oracle
```


- Oracle 접속확인
    - DBeaver 탐색기 > Create > Connection



- Database 설정
- H2 DB -> Spring Boot에서 손쉽게 사용 가능한 Inmemory DB, Oracle, Mysql,SQLServer 등과 쉽게 호환된다.
- Oracle -> 운영 시 사용한 DB
- MySQL -> 사용만 해볼 것
- Oracle PKNUSB / pknu_p@ss로 생성하기
    ```shell
    > sqlplus system/password
    SQL>
    ```
```shell
    SQL > select name from v$database;
    // 서비스명 확인
    // 최신버전에서 사용자 생성시 c## prefix 방지 쿼리
    SQL> ALTER SESSION SET "_ORACLE_SCRIPT"=true;
    // 사용자 생성
    SQL> create user PKNUSB identified by "pknu_p@ss"
    // 사용자 권한
    SQL> grant CONNECT, RESOURCE,CREATE SESSION, CREATE TABLE, CREATE SEQUENCE, CREATE VIEW TO PKNUSB;
    // 사용자 계정 테이블 공간설정, 공간쿼터
    SQL > alter user pknusb default tablespace users;
    SQL > alter user pknusb quota unlimited on users;
```

- Spring Boot + MyBatis
    - application name : spring02
    - spring boot 3.3.x는 Mybatis Framework를 지원하지 않는다.
    - Dependency 중 DB(H2, Oracle, MySQL)이 선택되어 있으면 웹서버 실행이 안 됨
        - Spring Boot DevTools
        - Spring Web
        - Thymeleaf
        - Oracle Driver
        - Mybatis starter
        - lombok

    - build.gradle 확인
    - application.properties 추가작성
    ```properties
        ## Mybatis설정
        ## mapper 폴더 및에 여러가지 폴더가 내재, 확장자는 .xml이지만 파일명은 뭐든지
        mybatis.mapper-locations=classpath:/mapper/**/*.xml
        mybatis.type-aliases-package=com.promm.spring02.domain

        spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
        spring.datasource.url=jdbc:oracle:thin:@localhost:1521/xe
        spring.datasource.username==pknusb
        spring.datasource.password=pknu_p@ss
    ```
    - MyBatis 적용
        - SpringBoot 이전 resource/WEB-INF 위치에 root-context.xml에 DB, Mybatis 설정
        - SpringBoot 이후 application.properties + config.java로 변경

    - MyBatis 개발시 순서
        0. application.properties spring.datasource.url=jdbc:oracle:thin:@localhost:1521/xe, thin 뒤에 :이 삭제되어 있었음.
        1. DataBase 테이블 작성
        2. MyBatis 설정 -> /config/MyBatisConfig.java
        3. 테이블과 1:1로 매핑될 클래스 (Model or Domain r Entity or Dto or Vo(readOnly) ...디렉토리 안에) 작성
            - 테이블 컬럼 이름의 _을 Java 필드에서 작성할 떄 캐멀 형식으로 작성한다.
        4. DB와 데이터를 주고 받을 수 있는 클래스(dao, **mapper**, repository ) 작성
            - 쿼리를 클래스내 작성가능, xml로 분리가능
        5. (Model) 분리했을 경우 /resources/mapper/클래스명.xml 작성, 쿼리 입력
        6. 서비스 레이어 작성 -> 서비스 인터페이스(/service/*Service.java)와 구현 클래스(/serviceImpl/*ServiceImpl.java)를 작성
        7. 사용자가 접근하는 @RestController 컨트롤러 클래스 작성 -> @Controller 변경 가능
        8. ~~(Controller) 경우에 따라 @SpringBootApplication 클래스에 SqlSessionFactory 빈을 생성 메서드 작성~~
        9. (View) /resource/tamplates/ Thymeleaf html 작성

    
    - JavaEE 9 이전은 javax 였으나 JavaEE 9 이후는 jakarta로 변경


## 4일차
- Spring Boot JPA + Oracke + Thymeleaf +React
    - JPA -> DB 설계를 하지 않고 엔티티 클래스를 DB로 자동생성 해주는 기술, Query도 만들 필요가 x
    - H2 > Oracle, MySQL, SQLserver 등과 달리 Inmemory Db, 스프링부트가 실행되면 같이 실행되는 DB
        - 개발 편의성, 다른 DB로의 전환 용이성
    - Thymeleaf -> JSP의 단점인 복잡한 템플릿형태 + 스파게티 코드를 조금 더 간결하게 해주는 템플릿
    - Bootstrap -> 웹디자인 및 css의 혁신!
    - 소셜로그인 -> 구글, 카카오, 네이버 등등 소셜로 로그인기능
    - React -> 프론트엔드를 분리, 벡엔드 서버와 프론트엔드 서버를 따로 관리(통합도 가능)

- Spring Boot JPA 프로젝트 생성
    - Artifact Id -> backboard
    - Dependency
        1. Spring Boot DevTools
        2. Lombok
        3. Spring Web
        4. Thymeleaf
        5. Oracle Driver(나중에)
        6. H2 Database(나중에)
        7. Data JPA(나중에)

- Spring Boot JPA 프로젝트 개발시작
    1. (Setting) build.gradle 디펜던시 확인
    2. application.properties 기본설정 입력
        - 포트 번호
        - 로그 색상
        - 자동 재빌드
        - 로그 레벨
    3. 각 기능별로폴더를 생성(controller,service,entity...)
    4. /controller/MainController.java 생성, 기본 기능 구현
    5. application.properties H2, JPA 설정 추가
    6. 웹 서버 실행해서 http://localhost:8088/h2-console 연결 확인

    7. (개발) /entity/Board
        - GenerationType
            - AUTO : SPRING BOOT에서 자동으로 선택
            - IDENTITY : MySQL, SQLServer
            - SEQUENCE : Oracle
        - column이름을 createDate로 만들면 DB에 컬럼명이 create_date로 생성
        - 컬럼명에 언더바를 넣지 않으려면 @Column(name = "createDate") 사용
    8. entity/Reply.java 생성
    9. 두 엔티티간 @OneToMany, @ManyToOne을 설정
    10. 웹 서버 재시작 후 h2-console에서 테이블 생성 확인
    11. /repository/BoardRepository.java 인터페이스(JpaRepository 상속) 생성
    12. /repository/ReplyRepository.java 인터페이스(JpaRepository 상속) 생성
    13. /test/.../repository/BoardRepositorTests.java 생성
    14. 테스트 시작 > 웹 서버 실행 > h2-console 확인

## 5일차
- Tip
    - Java Test 중 OpenJDK 64-Bit Server VM Warninig:Sharing is ... 빨간색 경고가 뜨면
    - Ctrl + , > Java Test Config > settings.json 편집
    ```json
     "java.test.config": {
        "vmArgs": [
            "-Xshare:off"
        ]
    }
    ```
    - 저장 후 실행

- Spring Boot JPA 프로젝트 개발 계속
    15. JUnitTest로 기본적 CRUD 완료
    16. /service/BoardService.java 생성 후 findBoardAll() 메소드 작성
    17. /controller/BoardController.java 생성 후 /board/list 실행할 수 있는 메서드 작성
    18. /templates/board/list.html 작성
        - Thymeleaf 속성
            - th:if ="${board != null}"
            - th:each="board : ${boardList}"
            - th:text="${board.title}"