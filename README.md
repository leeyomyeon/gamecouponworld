# Coupon Free World

> 모바일 게임 쿠폰 자동등록 서비스 구성
>
> SNS기본기능(자유게시판, 피드, 알림, 프로필) 서비스 구성

## 0. 프로젝트 개요

많은 모바일 게임들이 사용자들에게 무료로 쿠폰을 제공하곤 합니다.

하지만 이러한 서비스와는 달리 해당 쿠폰을 게임 내에서 등록하는 것이 아니라, 외부 커뮤니티를 통해 쿠폰 번호를 제공받고 또다른 웹 사이트를 통해 쿠폰을 등록해야하는 복잡한 과정을 갖습니다.

이를 하나의 과정으로 통합하므로써 사용자에게 편리함을 제공할 뿐만 아니라, 자신이 어떤 쿠폰을 등록했는지 안했는지 고민할 필요를 없애줍니다. 

또한 복잡한 과정을 따로 공부할 필요 없이 신규 유저에게도 게임을 좀 더 쉽게 다가가게 할 것을 기대합니다.



## 1. 개발 도구
- Vue
- HTML
- JavaScript
- CSS
- Spring Boot
- AWS
- Jira SoftWare
- Git

## 2. ERD

![image](https://user-images.githubusercontent.com/44407364/186398585-2779a805-5e45-4bc9-bd65-0b4c28970869.png)


## 3. 컴포넌트 구조

![image](https://user-images.githubusercontent.com/44407364/186398610-60af26d8-92bf-4c5f-b435-5dc8137a00c0.png)

![image](https://user-images.githubusercontent.com/44407364/186398634-608450f1-eb94-4a05-9fac-998f4719f456.png)

## 4. 기능
### 4.1 회원관리

- 회원가입

  ![회원가입](https://user-images.githubusercontent.com/44407364/186399155-118413db-9406-47c2-96c8-9d5ed1782549.gif)


  - 회원가입시 이메일 인증을 진행합니다. 
  - 이메일 인증 성공시 회원가입 페이지로 이동하게 됩니다.

  - 회원가입시 ID 중복검사를 진행하여 똑같은 ID가 존재한다면 다른 ID로 가입을 진행합니다.

- 로그인

  ![로그인](https://user-images.githubusercontent.com/44407364/186399274-aca374cd-544b-4b0b-be8b-c8f8e7272786.gif)


  - DB에 저장된 데이터와 비교하여 ID,PW가 일치한 Data가 존재한다면 JWT토큰을 발행하고 JWT토큰을 웹 브라우저 쿠키에 저장.



- 회원수정

  ![회원정보수정](https://user-images.githubusercontent.com/44407364/186399297-90ec2125-8278-4fc4-9749-8ecc559a4cb3.gif)


  - 회원 정보 수정을 통해 닉네임, 자기소개, 비밀번호, 사용자의 프로필 사진등을 변경할 수 있습니다.

  

### 4.2 메인(쿠폰 자동등록)

- 회원 코드 관리

  ![회원코드](https://user-images.githubusercontent.com/44407364/186399346-23deac45-9df5-4d44-85d6-85991f9c80d8.gif)

  메인 화면의 환경 설정(톱니 바퀴) 버튼을 통해

  각 게임당 1계정씩 회원코드를 등록할 수 있습니다.

  한 번 등록해 놓으면 수정 시까지 저장된 채로 유지됩니다. 

  

- 쿠폰 등록 자동화

![쿠폰등록](https://user-images.githubusercontent.com/44407364/186399382-45655acd-f751-4080-96ed-fb167597b722.gif)


"자동 등록" 버튼을 누르면 자신이 위에서 저장해놓은 회원코드에 

유효기간이 남아있지만 아직 회원이 등록하지 않은 쿠폰들을 자동으로 전부 등록합니다.

(단, AFK 아레나 게임은 게임 내에서 인증번호를 확인해야 합니다.)


![보상획득](https://user-images.githubusercontent.com/44407364/186399397-fff08b96-e755-4157-9f4a-77aa2c58d8f2.png)



위 과정 두 가지를 거치면 다음과 같이 쿠폰이 정상적으로 모두 등록되었음을 확인할 수 있습니다.



### 4.3 자유게시판

- 게시판 CRUD

  ![board_CRUD](https://user-images.githubusercontent.com/44407364/186399422-4dc54b26-247e-470b-b2e2-15bb7f9e46cd.gif)


  글쓰기 버튼을 통해 글을 등록합니다.

  글을 쓸 때 카테고리를 설정하지않으면 "일반" 카테고리를 갖으며, 일반 카테고리 혹은 특정 게임 카테고리를 선택하여 글을 작성할 수 있습니다.



- 게시판 댓글

  ![comment](https://user-images.githubusercontent.com/44407364/186399431-c373b09c-3de6-47f0-991c-446d8156b563.gif)


  댓글을 작성, 삭제 할 수 있으며 기존 댓글에 답글을 달 수 있습니다.

  답글일 달린 댓글이 부모, 답글이 자식이라고 할 때 부모 댓글은 또다른 부모를 가질 수 없습니다.

  즉 원 댓글은 단 하나이며, 그에 대한 여러 개의 답글이 달릴 수 있습니다.


- 게시판 검색

  ![search_board](https://user-images.githubusercontent.com/44407364/186399447-5776ba32-b841-437a-8b87-2aff321ecafe.gif)


  게시판 메인 화면 상단에 위치한 검색어 창에 키워드를 넣고 엔터 혹은 버튼을 누를 시 검색어와 일치하는 제목을 가진 글들이 나타납니다. sql으로 column을 제목을 설정하고, 해당 column 에 키워드가 있는지 검색하는 api 를 구현하였습니다.





### 4.4 피드

- 피드 CRUD

  ![feed_C](https://user-images.githubusercontent.com/44407364/186399470-a3a32134-ef8d-4b83-aa03-bcafee77a380.gif)


  대표 사진 (필수) 하나를 포함한 사진과 함께 간단한 글을 작성할 수 있습니다.

  작성된 게시물의 사진이 여러 개 일 경우 좌 우로 사진을 넘길 수 있습니다.

  
  ![팔로우피드](https://user-images.githubusercontent.com/44407364/186399487-1ddd9741-7098-4029-9c60-3d3f6f186d10.gif)
  

  작성된 피드는 모든 피드 리스트 혹은 자신이 팔로우한 사용자의 피드 두 가지로 선택하여 피드 목록을 볼 수 있습니다. 자신이 작성한 피드만 보고싶을 경우에는 "프로필" 에서 확인해야 합니다.



### 4.5 프로필&알림

- 프로필
  - 유저 검색(Create, Read, Delete 로직이 필요)

    ![userSearch](https://user-images.githubusercontent.com/44407364/186399520-c1fd023f-1884-4114-a081-8c18c41263b5.gif)


    - 입력된 값과 유사한 유저아이디를 보여줍니다.
    - 검색된 값을 클릭시 해당 유저의 프로필로 이동하며, 최근검색에 최신순으로 기록이 남습니다.
    - 최근검색된 유저아이디를 클릭시 해당 유저의 프로필로 이동합니다.
    - 최근 검색값들은 삭제도 가능합니다.  
    
  - 프로필화면 기능(Read 로직 필요)
  
    ![profile](https://user-images.githubusercontent.com/44407364/186399539-2bcf7b88-88af-42a8-9cc9-a6db020be103.gif)

    
    - 다른 유저에게 팔로우 요청및 요청취소를 할 수 있습니다.
    - 해당 유저의 작성한 피드와 좋아요 누른 피드를 확인할 수 있습니다.
    - 본인 프로필에서는 회원정보 수정이 가능합니다.
  
- 알림및 팔로우

  - 팔로우 요청에 대한 알림 및 수락(CRD로직&Socket[1] 필요)

    ![alarm](https://user-images.githubusercontent.com/44407364/186399560-0c62a7ee-78f8-4f53-8878-6a31f9ddcfea.gif)

    ![alarm](https://user-images.githubusercontent.com/44407364/186399576-ad651902-79e1-40fb-b255-dde03f4cc6b0.gif) 

    
    - 'cksdn135' 유저가 'cksdnwkd135'에게 팔로우 요청시 화면 하단에 알림 메시지가 나옵니다.
    - 오른쪽 상단 알림 아이콘 클릭시 새로운 알림이 들어와 있는것을 확인할 수 있습니다.
    - 해당 알림을 클릭시 그 알림내용은 삭제가 됩니다.
    - 더보기를 클릭시 팔로우 수락할 수 있는 페이지로 이동합니다.


## Bibliography

[1] https://m.blog.naver.com/ppuagirls/221491066847
