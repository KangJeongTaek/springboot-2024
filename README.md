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
    {:start="15"}
    1. JUnitTest로 기본적 CRUD 완료
    2. /service/BoardService.java 생성 후 findBoardAll() 메소드 작성
    3. /controller/BoardController.java 생성 후 /board/list 실행할 수 있는 메서드 작성
    4. /templates/board/list.html 작성
        - Thymeleaf 속성
            - th:if ="${board != null}"
            - th:each="board : ${boardList}"
            - th:text="${board.title}"
    5. /service/BoardService.java에 findboardById()메서드 추가
    6. /controller/BardController.java에 /board/detail/{bno} 실행 메서드 작성
    7. /templates/board/detail.html 작성
        <img src="https://github.com/KangJeongTaek/springboot-2024/blob/main/images/sp003.png?raw=true" width="730px">

    8. /templates/board/detail.html에 댓글 영역 추가하기
    9. /service/ReplayService.java 생성 후 메서드 작성
    10. /controller/ReplayController.java 생성, /replay/create/{bno} 포스트매핑 메서드 작성
    11. [부트스트랩](https://www.getbootstrap.com) 적용
        - 다운로드 후 프로젝트에 적용
        - CDN 링크 추가
        - resources/static/ 안에 css폴더와 js폴더를 생성해서 위치


            <img src="https://github.com/KangJeongTaek/springboot-2024/blob/main/images/sp004.png?raw=true" width="730px">

## 6일차
- Spring Boot JPA 프로젝트 개발 계속
    1. Thymeleaf 레이아웃 사용을 위한 디펜던시 추가
    2. /templates/layout.html Thymeleaf로 레이아웃 템플릿 생성
    3. list.html, detail.html 레이아웃 템플릿 적용
    4. /templates/layout.html에 Bootstrap CDN 적용
    6. /templates/board/list.html에 등록버튼 추가
    5. /templates/board/create.html 게시글 작성 페이지 작성
    6. /controller/BoardController.jav creat() GetMapping 메소드 작성
    7. /service/BoardService.java boardSave(Board board) 작성
    8. /controller/BoardController.java create90 PostMapping 메서드 작성
    10. (문제) 아무 내용 안 적어도 저장됨
    11. (설정) build.gradle 입력값 검증 Spring Boot Validation 디펜던시 추가
    12. /validation/BoardFrom.java 클래스 생성
    13. /controller/BoardController.java에 BoardForm을 전달
    14. create.html 입력항목 name, id를 th:field로 변경(ex. th:field="*{title}")
    15. 댓글 등록에도 반영. ReplyForm, ReplyController에서 작업(12 ~ 14내용과 유사)
    16. 각 입력창에 공백을 넣었을 때 입력되는 문제가 있음 @NotEmpty는 스페이스를 허용하므로 -> @NotBlank로 수정

        <img src="https://github.com/KangJeongTaek/springboot-2024/blob/main/images/sp005.png?raw=true" width="730px">

    17. 네비게이션바(navbar) 추가
    18. Test를 통해 대량 데이터 추가

## 7일차
- Spring Boot JPA 프로젝트 개발 계속
    0. 개념
    ```sql
    -- 오라클 전용(12c 이상)
    select b1_0.bno,b1_0.content,b1_0.create_date,b1_0.title
    from board b1_0 offset 0 -- 0부터 시작해서 페이지 사이즈만큼 증가
    rows fetch first 10 rows only --페이지사이즈
    ```
    1. 페이징(중요!)
        - /repository/BoardRepository.java findAll(Paegeable pageable) 인터페이스 메서드 작성
        - /service/BoardService.java findByAll(int page) 메서드 작성
        - /controller/BoardController.java baardList() 메서드 수정
        - /templates/board/list.html boardList -> paging 변경
        - /templates/board/list.html 하단 페이징 버튼 추가. thymeleaf 기능 추가
        - /service/BoardService.java getList() 최신순 역정렬로 변경
        - /templates/board/list.html에 게시글 번호 수정


        <img src="https://github.com/KangJeongTaek/springboot-2024/blob/main/images/sp006.png?raw=true" width="730px">

    2. 댓글개수 표시
        - /templates/board/list.html td 뱃지 태그 추가

    3. H2 -> Oracle로 DB변경
        - build.gradle, ORacle dependency 추가
        - application.properties Oracle 관련 설정 추가, H2 설정 주석처리

    4. 스프링시큐리티(그 다음 중요!)
    - (설정) build.gradle 스프링 시큐리티 의존성 추가
    - /security/SecurityConfig.java 보안설정 파일 생성, 작성 -> 시큐리티가 모든 url를 허가하도록 변경

    - /entity/Member.java 생성
    - /repository/MemberRepository.java 인터페이스 생성
    - /service/MemberService.java 클래스 생성 memberSave() 메소드 생성

## 8일차
- Spring Boot JPA 프로젝트 개발 계속
    1. 스피링 시큐리티 계속
        - /secrurity/SecuritiyConfig.java에 BCryptPasswordEncoder를 빈으로 작업
        - /validation/MemberForm.java 작성
        - /controller/MemberController.java 생성
        - /entity/Member.java에 regDate 추가
        - /service/MemberService.java regDate() 부분 추가 작성
        - .templates/member/register.html 생성
        - (설정) Member 테이블에 저장된 회원정보 확인
        - /templates/layout.html에 회원가입 링크 추가
        - /controller/MemberController.java PostMapping register에 중복회원 방지 추가
        - /security/MemberRole.java enum으로 ROLE_ADMIN, ROLE_USER 생성
        - /entity/Member.java role 변수 추가

    2. 로그인 기능
        - /securitiy/SecuritiyConfig.java에 login url 설정
        - /templates/layout.html 로그인 링크 수정
        - /templates/member/login.html 작성
        - /repository/MemberRepository.java 수정
        - /controller/MemberController.java login Get/Post 메서드 작성
        - 로그인은 post를 사용하지 않고, spring Security가 지원하는 UserDetailsService 클래스 사용
        - /service/MemberSecurityService.java - 로그인은 post를 사용하지 않고, Spring securtiy가 지원하는 UserDetailsService 인터페이스를 구현하는 클래스 작성
        - /security/SecurityConfig.java 계정관리자 빈 추가
        - /templates/layout.html 로그인/로그아웃 토글 메뉴 추가

    3. 게시글 작성자 추가
        - /entity/Board.java에 작성자 필드 추가
        - /entity/Replay.java에 작성자 필드 추가
        - /service/MemberService.java memberFind() 메서드 작성
        - (Tip) default Exception으로 예외를 처리하면 메서드 뒤에 항상 throws Exception을 작성 해야 한다.
        - /common/NotFoundExceptipon.java 생성 -> throws Exception를 사용하는 곳에서 반영
        - /service/replayService.java replaySave()에 사용자 추가
        - /controller/ReplayController.java에 replaysave() 메서드 수정
        - /service/BoardService.java 
        - /controller/BoardController.java boardsave() 사용자 추가
        - /controller/ 작성하는 get/Post 메소드에 @PreAuthorize 애노테이션 추가
        - /config/securityConfig.java @PreAuthorize 동작하도록 내용 추가
        - /templates/board/detail.html 답변 textarea를 로그인 전, 로그인 후로 구분
        - /templates/board/list.html table 태그에 작성자 컬럼 추가하기
        - /templates/board/detail.html 게시글 작성자, 댓글 작성자 표시 추가


        <img src="https://github.com/KangJeongTaek/springboot-2024/blob/main/images/sp007.png?raw=true" width="730px">
## 9일차
- Spring Boot JPA 프로젝트 개발 계속

    1. 수정. 삭제
        - /entity/Board,Replay.java 수정일자 필드 추가
        - /templates/board/detail.html 수정, 삭제버튼 추가
            - sec:authorize="isAuthenticated()"이 없으면 500 에러 발생
        - /controller/BoardController.java modify() 메서드 작성
        - /templates/board/create.html  th:action을 삭제 삭제하더라도 들어온 곳의 GetMapping의 uri로 PostMapping 요청을 보낸다. ex) board/create로 get요청이 들어오면 board/create의 post로 내보내고 board/modify/{bno}로 get요청이 들어왔다면 board/modify/{bno}로 postMapping을 요청을 보낸다.
            -create.html 생성, 수정할 떄 모두 사용
        - /service/BoardService.java 수정 관련 메서드 추가
        - /controller/BoardController.java modify() 메서드 작성
            - html에는 BoardForm 객체의 값이 들어가 있으므로 Controller에서 Board 객체를 찾고 그것을 이용해 service 로직을 실행해야 한다.
        - /service/BoardService.java 삭제관련 메서드 추가
        - /controller/BardController.java delete() GET 메서드 작성

        - /templates/board/detail.html 댓글 수정, 삭제 버튼 추가
        - /service/ReplayService.java 에 삭제 관련 로직 추가
        - /controller/ReplayController.java modify와 delete get, post 메서드 작성
        - /templates/replay/modifly.html 작성

        - /templates/board/detail.html에 게시글, 댓글 수정 날짜 표시

    2. 앵커 기능
        - 추가, 수정, 삭제 시 이전 자신의 위치로 이동
            - /templates/board/detail.html 댓글마다 앵커링 추가
            - /controller/ReplayController.java modify() post매핑, return에 앵커링 추가
            - /service/ReplayService.java 생성 메서드 변경
            - /controller/ReplayController.java create Post 메서드 return에 앵커링 추가
            - /controller/BoardController.java detail() 메서드 수정
            - /templates/board/list.html에 검색창 추가

    3. 검색기능
        - /service/BoardService.java serach() 메서드 추가
        - repository/BoardRepository.java 메서드 추가
        - /service/BoardService.java getList() 메서드 하나 더 작성
        - /controller/BoardController.java list를 하나 더 작성
        - /tamplates/board/list.html 검색창 추가. searchForm 폼영역 추가, 페이징영역 수정, Javascript 추가

## 10일차
- Spring Boot JPA 프로젝트 개발 계속

    1. 검색 가능 - JPA Query
        - @Query 애노테이션 직접 쿼리를 작성
            - SQL의 표준 쿼리와는 차이가 있다(객체지향 쿼리, JPQL)
            - 복잡한 쿼리문이기에 JpaRepository가 자동으로 만들어줄 수 없을 때
        - /repository/BoardRepository.java findAllByKeyword() 메서드 작성
        - JPQL를 @Query("")에 작성
        - /service/BoardSrevice.java getList() 수정

    2. 마크다운 적용, 마크다운 에디터 추가
        - Wysiwyf 에디터 - CKEditer(https://ckeditor.com/), TinyMCE...
        - simplemde(https://simplemde.com/) Download.zip 클릭 혹은 깃허브에 CDN 링크를 복사 layout.html에 링크 추가
        - create.html에 tesxtarea id content를 simplemde로 변환하는 js 추가
        - detail.html textarea content simplemde js추가

        - (설정) build.gradle 마크다운 뷰 디펜던시 추가
        - /common/CommontUtil.java 생성
        - /templates/board/detail.html 마크다운 뷰 적용

        
        <img src="https://github.com/KangJeongTaek/springboot-2024/blob/main/images/sp009.png?raw=true" width="730px">

        
        <img src="https://github.com/KangJeongTaek/springboot-2024/blob/main/images/sp010.png?raw=true" width="730px">


    3. 카테고리 추가
        - /entity/Category.java 클래스 추가
        - /repository/CategoryRepository.java 인터페이스 추가
        - /service/CategoryService.java 추가
        - /entity/Board.java에 category 속성을 추가
        - /service/BoardService.java findByAll,searchBoard(), boardSave()메서드 작성
        - /service/BoardService.java 조회조건에 카테고리 추가 수정
        - 카테고리를 자유게시판, 질문응답게시판으로 분리
        - /templates/layout.html sidebar 추가 기입
        - /controller/BoardController.java GetMapping 메서드에 카테고리 매개변수를 추가
        - /templates/list.html 카테고리 변수 추가
        - /controller/BoardControoler.java create() Get과 Post 메서드 모두에 카테고리 추가


## 11일차
- Spring Boot JPA 프로젝트 개발 계속
    0. Restful URL이 잘못된 부분
        - /controller/MainController.java getMain() 메서드 return 값 수정
    1. 조회수 추가
        - /entity/Board.java 조회수 필드 추가
        - /service/BoardService.java hitBoard() 메서드 추가
        - /controller/BoardController.java detail() 메서드 수정
        - /templates/board/list.html 조회수 컬럼 추가
        - 후에 db를 다시 Oracle에서 -> H2로 수정할 것임

    2. AWS 사용
        - [aws amazon](https://aws.amazon.com/ko/)
            - [회원가입 메뉴얼](https://blogworks.co.kr/aws-%ED%9A%8C%EC%9B%90%EA%B0%80%EC%9E%85-%EB%A9%94%EB%89%B4%EC%96%BC/)
            - [라이트 세일](https://lightsail.aws.amazon.com/) 접속
            - 인스턴스 클릭 > 인스턴스 생성
            - 리전 서울
            - 인스턴스 이미지 > Linux/Unix
            - 블루프린트 > 운영체제 OS 전용 > Ubuntu가 편함!
            - 인스턴스 플랜 > 듀얼 스택
            - 크기 선택 > 전략에 맞는 크기 선택
            - 인스턴스 확인 > 원하는 이름으로 교체 후
            - 인스턴스 생성 클릭
            - 실행을 확인 하고 > 관리 클릭(⁝)
            - 네트워킹 > 고정 IP 연결 > 생성
        - [PuTTY](https://www.putty.org/) 설치
            - 계정 > SSH 키 > 기본 키 다운로드
            - PuTTYgen 실행 > Load 기본키 선택 > Save private key 클릭 > .ppk로 저장
            - PuTTY 실행
                - Host Name : AWS 고정아이피 입력
                - Connection > SSH > Auth > Credentials : Private key file for authentication 클릭 후 좀 전에 만든 ppk파일 선택
                - Session > Save Session명 입력 > save
                - Open 후 콘솔 login as : ubuntu 입력
            - FileZilla로 FTP 연결
                - [FileZilla](https://filezilla-project.org/download.php) 설치
                - 사이트 관리자 열기
                    - 새 사이트
                    - 프로토콜 : SFTP
                    - 호스트 : 고정 아이피 입력
                    - 로그온 유형 : 키 파일
                    - 사용자 : ubuntu
                    - 키 파일 : ppk 파일
                    - 연결
            - 설정 변경
            ```shell
                > sudo ln -sf /usr/share/zoneinfo/Asia/Seoul /etc/localtime
                ## 한국 시간으로 변경
                > hostname
                > sudo hostnamectl set-hostname [원하는 이름으로]
                > sudo reboot ## (서버 재시작)
                > sudo apt-get update ## 전체서버 패키지 업데이트
                > java
                > sudo apt-get install openjdk-17-jdk
                
                ## 설치 후 자바 확인
                > java --version
            ```
            - VSCode
                - Gradle for Java > Tasks > build > bootjar
                - *-SNAPSHOT.jar 생성 확인
            - FileZilla
                - *.jar > AWS로 전송
            - PuTTY
                ```shell
                > ls
                    ...
                >cd bootserver
                > ls
                backboard=1.0.1-SNAPSHOT.jar

                > sudo java -jar backboard-1.0.1-SNAPSHOT.jar
                ```
            - h2 추가 설정
                - spring.h2.console.settings.web-allow-others=true
                - DB 파일을 옮기기
            - 스프링부트 서버 백그랑눈드 실행 쉘 작성
                - nano start.sh
                ```shell
                #!/bin/bash

                JAR=backboard-1.0.2-SNAPSHOT.jar
                LOG=/home/ubuntu/bootserver/backbord_log.log

                nohup java -jar $JAR > $LOG 2>&1 &
                ```
                
                - 파일 권한 바꾸기
                ```shell
                > chmod +x start.sh
                ```

                -> nano stop.sh
                ```shell
                #!/bin/bash
                BB_PID=$(ps -ef | grep java | grep backboard | awk '{print $2}')

                if [ -z "$BB_PID" ];
                then
                echo "BACKBOARD is not running"
                else
                kill -9 $BB_PID
                echo "BACKBOARD terminated!"
                fi
                ```

                - 파일 권한 바꾸기(실행 가능)
                ```shell
                > chmode +x stop.sh
                ```
                - 해외 결제 카드가 없어 위의 단계를 진행하지 못 함.
                - 단 현재 AWS가 아닌 네이버 클라우드 플랫폼에서 서버를 보유 중임.

## 11일차
- Spring Boot JPA 프로젝트 개발 계속
    1. 에러페이지 작업(404,500, etc)
        - application.properties 에러페이지 관련 설정추가
        - /static/img/bg_error.jpg 저장
        - /templates/404.html과 500html, 그외는 error.html 페이지 작성
        - /controller/CustomErrorController.java 생성

    2. 비밀번호 벼경
        - build.gradle에 메일을 보내기 위한 dependency 추가
        - application.properties 메일 설정 입력
        - /service/MailService.java 생성
        - 네이버 메일의 설정
                  <img src="https://github.com/KangJeongTaek/springboot-2024/blob/main/images/sp011.png?raw=true" width="730px">
        - /restcontroller/EmailController.java 생성
        - 비밀번호 초기화 기능(메일서버 세팅)
        - 비밀번호 초기화 화면으로 이동
        - 비밀번호, 비밀번호 확인 입력
        - [포스트맨](https://www.postman.com/downloads/) 다운로드

## 12일차
- Spring Boot JPA 프로젝트 개발 계속
    0. 메일 작업 중 생긴 오류
        - 로그인 후 글을 작성하면 500에러가 발생
        - /board/create.html, /replay/modify.html에 있는 csrf 관련 태그 주석 처리
    1. 비밀번호 초기화(계속)
        - /templates/member/login.html 비밀번호 초기화 버튼
        - /templates/member/reset.html 작성
        - /mail/reset-mail GET매핑 메서드 작성
        - /controller/MailController.java 작성
        - /service/MemberService.java에 메일주소로 검색하는 메서드 추가
        - /service/MailService.java에 메일 전송 메서드 작성 및 수정
            - / UUID를 생성해서 메일로 전송하는 기능을 추가
            <img src="https://github.com/KangJeongTaek/springboot-2024/blob/main/images/sp012.png?raw=true" width="730px">

        - /entity/Reset.java 생성
        - /repository/ResetRepository.java 생성
        - /service/ResetService.java 생성
        - /service/MailService.java에 ResetService 객체 생성, 메일 전송 후 setReset()를 사용
        - /controller/MemberController.java /mail/reset-password/{}를 처리하는 Get메서드 작성
        - templates/member/new-password.html 생성
        - /controller/MemberController.java /member/reset-password Post메서드 작성
        - /service/MemberService.java 에 setMember() 메서드 추가
        - 이메일 화면 수정
        - /member/reset-password Get메서드 작성
        - /html...

            <img src="https://github.com/KangJeongTaek/springboot-2024/blob/main/images/sp013.png?raw=true" width="730px">
## 13일차
- 리액트 개요
    - 서버(백엔드)
    - 클라이언트(프론트엔드) -> html + css + js
    - js만 가지고 프론트엔드를 만들어보자 -> 리액트
    - css는 있어야 한다.
    - 페이스북이 자기 웹페이지 프론트를 좀 더 개선해보고자 개발 시작
    - 리액트는 기본적으로 SPA(Single Page Application)을 목적으로
    - node.js 서버사이브 js를 사용해서 서버를 동작
    - 패키지 매니저 종류 : npm, chocolatey,yarn ...

- 리액트 개발 방법
    1. [node.js](https://nodejs.org/en) 설치
        - node --version으로 확인. 현재 v20.15.0
    2. 리액트 프로젝트를 초기화
        - VS Code에서 터미널 오픈
        - npx create-react-app basic-app
    3. 리액트 실행
        - 콘솔에서 위에서 만든 프로젝트앱 이름까지 진입 basic-app
        - > npm start
        - 웹브우저 http://localhost:3000 서버 확인
        - node가 3000 포트로 웹서버를 실행
        - 웹서버가 실행된 상태에서 개발하는 것이 좋음
        - index.html or index.jsp ... index가 가장 첫 화면이다.
        - App.js가 메인 개발 부분이다.

- 리액트 기본 구조 및 개발 방법
    1. 깃헙 .gitignore에 react(node 관련 설정내용 추가)
    2. 깃헙에 .gitignore 먼저 커밋하고 푸쉬

    3. src/App.css App.js index.js
    4. javascript이기 때문에 js 위주로 개발
    5. App.js 부터 개발을 시작
- 리액트 기초 공부
    1. html의 태그처럼 개발자가 새로운 요소(객체)를 생성할 수 있음
        ```jsx
        function CustomButton(){ //CustomButton 객체 생성
            return(
                <button>MyButton</button>
            )
        }
        ```
    2. /component/CustomButton.js 생성 위 소스 코드를 옮긴
        - 같은 팡리이 아닌 곳에서 객체를 만들면
        - 가져와 쓰기 위해서 export default 객체 이름 필수
    
    3. React 문법은 JSX 일반 js와 조금 차이가 있음
        - className은 JSX에만 존재
        - HTML에 있던 class는 JSX에서 className으로 변경
        - 인라인으로 style 쓸 때 css 명칭이 다름
        - 대신 *.css 파일로 작업할 떄는 기존과 동일
        -JSX문법에는 모든 요소는 상위 태그 하나에 속해야 한다.

    4. 데이터 화면에 표시
        - 변수 생성시 const 많이 씀
        - 변수 사용 시 중괄호 사이에 입력
        - CSS를 *.css 파일에 작성할 때는 html에서 작성할 때와 동일
            - ex border-radius : 50%
        - JSX에 사용할 때는 다르게 작성
            - ex borderRadius : '50%'
        - 리액트에서 css를 쓸 때는 *.css파일로 작업할 것
    5. if 문
    ```jsx
    let isLoggiedIn = true;
    let content;
    if(isLoggined){
        content = <button>Log out</button>
    }else{
        content = <button>Log in</button>
    }
    ```
    ```jsx
        return (
            <>
                {isLoggedIn?(<button>Log out</button>):(<button>Log in</button>)}
            </>
        );
    ```
    6. 목록 표시
        - for, map() 함수를 많이 사용
        - map()을 사용하면 for문보다 짧게 구현이 가능하다.
        - map()을 사용할 때에는 각 child 요소 마다 key 속성이 존재해야 하며 그 값은 unique해야 한다.
        
        <img src="https://github.com/KangJeongTaek/springboot-2024/blob/main/images/react01.png?raw=true" width="730px">

    7. 이벤트 핸들링
        - form + onSubmit, tag + onClick
        - 이벤트 파라미터 전달
        - onClick ={function()} 은 함수를 호출하므로 렌더링할 때 실행된다.
        - 따라서 onClick = {() => function()}의 형태로 람다식으로 바꿔주어야 한다.
    8. 컴포넌트 간 데이터 전달
        - props 속성
        - props.속성이름.key이름
        - {속성이름}

        <img src="https://github.com/KangJeongTaek/springboot-2024/blob/main/images/react02.png?raw=true" width="730px">

    9. 화면 업데이트
        - useState : 앱의 상태를 기억하고, 사용하고 수정하기 위한 hooks
        - import {useState} from 'react';
    10. Hooks
        - use로 시작하는 함수를 Hooks라고 호칭 State와 Effect 이외에는 잘 사용하지 않음
        - useState : React에 컴포넌트 상태를 추가, 보관
        - useEffect : 컴포넌트에서 사이드 이펙트를 수행할 때
        - 기타 : useContext,useReducer,useCallback,useRef, ...


- 리액트 추가내용
    1. 리액트 관련 프레임 워크
        1. Next.js - 풀스택 React프레임워크
        2. Gatsby - CMS
        3. React Native - 안드로이드, ios 멀티플랫폼 프레임워크
    2. npm으로 추가 라이브러리 설치
        - > npm install react react-dom
    3. VS Code 확장
        - ES7 + React/Redux/React-Native snippet 설치
        - Simple React Snippets
        - Import Cost : 라이브러리 비용 계산
    4. 리액트 개발자 도구
        - 크롬용, 엣지용 등 브라우저마다 따로 존재
        - React Developer Tools 설치
    
## 14일차
- Spring Boot React 연동 프로젝트 개발 계속
    1. react 프로젝트 생성
        - 터미널 /spring03으로 이동
        - > npx create-react-app frontboard
        

    2. Spring Boot / React 같이 개발할 때
        - Spring Boot 웹 서버 실행
        - React 프론트웹서버 실행

    3. 리액트 라이브러리 설치, npm
        - 리액트용 BootStrap 설치
        - > npm install react-bootsrap bootstrap => css디자인
        **TIP npm audix fix --force는 하지 말 것**
        - >  npm install axios -> REST API 통신 라이브러리
        - > npm install react-router-dom -> 리액트 화면 네비게이션
        - > npm install react-js-pagination -> 리액트 페이징 처리 라이브러리
    4. frontboard 개발 시작
        - App.js 수정, logo.svg 삭제 rect-router-dom 으로 Routes, Route 사용
        - index.js 수정, reportWebVitals() 삭제
        - index.js, <React.StrictMode> 삭제 
        - /src/layout/Header.js 와 Footer.js 를 작성
        - /src/routes/Home.js BoardList.js QnaList.js Login.js 생성
        - Header.js에 react-route-dom 라이브러리를 추가하고 Link,useNavigate를 사용
    5. backboard RestAPI 추가
        - /restcontroller/RestBoardController.java 작성
    6. frontboard 개발 계속
        - /boardList.js 작성

    7. backboard RestAPI 변경 계속
        - (문제!) Spring boot에서 만든 Entitiy는 양방향 매핑을 하고 있기에 json으로 보낼 시 순환 참조가 발생한다.
        - 해결 방법 -> /Entitiy를 그대로 사용하지 말고 다른 객체로 변환하여 보내줄 필요가 있다.
        - /dto/BoardDto.java 작성
        - /dto/ReplayDto.java 작성
        - /RestBoardController.java getList()를 Board Entity -> BoardDto로 변경
        - /security/SecurityConfig.java CORS 설정 추가
    
    8. frontboard 개발 계속
        - BoardList.js axios 관련 RestAPI 호출 내용 추가
        - 테이블 내용을 boardList.map()을 사용해서 10개 리스트를 렌더링 해주었음

           <img src="https://github.com/KangJeongTaek/springboot-2024/blob/main/images/react03.png?raw=true" width="730px">

    
## 15일차
- Spring Boot React연동 프로젝트 개발 계속
    1. Spring Boot 서버가 실행되지 않았을 때
        - 프론트 서버부터 실행하면 Uncaught runtime error 발생
        - axios request가 예외 발생 try- catch로 wrapping 해주자
    2. 페이징
        - (Back) /dto/PagingDto.java 작성
        - (Back) /dto/Header.java 작성
        - RestBoardController.java list() 수정 List<BoardDto> -> Header<List<BoardDto>>로 형변환
        - (Front) /common/CommonFunc.js 생성 - 작성일 수정 함수
        - /BoardList.js 날짜부분에 formatDate() 적용
        - /BoardList.js 댓글 갯수 표시
        - dto/BoardDto.java 게시글 번호 변수 추가
        - RestBoardController.java 게시글번호 계싼 로직 추가
        - (Front) /BoardList.js bno를 num으로 변경

    3. 상세화면
        - (Back) RestBoardController.java detail() 메서드 작성
        - (Front) /BoardList.js 제목 부분 수정
        - /routes/BoardDetail.js 생성


## 16일차
- Spring Boot React연동 프로젝트 개발 계속
    0. npm -> node에서 만든 package manager
        - yarn, brew, chocolatey ...
        - node,npm,npx ... 명령어 먼저 공부하기
    1. 로그인
        -(Front) layout/Header.js 로그인, 회원가입 버튼으로 변경
        - Login.js 화면 수정
        - (Back) RestMemberController.java 생성 login() Post메서드 작성
        - MemberService.java, getMemberByUsernameAndPassword() 메서드 작성
        - Postman에서 테스트
        <img src="https://github.com/KangJeongTaek/springboot-2024/blob/main/images/react04.png?raw=true" width="730px">

        - (Front) Login.js axios 부분 작성
        - Home.js, localStorage 사용해서 로그인정보 출력
        

    2. 상세화면 완료
        - (Back) restBoardController.java detail() 리턴값 변경
        - (Front) boardDetail 정보 받아오기
    


## 나머지
- 추가 개발 필요
    1. 입력화면
    2. 구글 로그인
        - https://console.cloud.google.com/ 구글클라우드 콘솔
        - 프로젝트 생성
        - OAuth 동의화면 설정
        - 개발 계속...
    3. (BACK) 포트 변경
    4. (BACK) Https 사용
    5. (BACK) 파일 업로드
    6. 로그인한 사용자 헤더에 표시


## 계속
- 소셜 로그인(카카오, 네이버 구글)
- 파일 업로드 - AWS s#
- 리액트 적용
- 리액트로 프론트엔드 설정
- thymeleaf - 리액트로 변경
- Spring boot RestAPI 작업

- 서버 접속 프로그램 설정
- 8080 -> 80 서버
- http -> https 변경