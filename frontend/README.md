# frontend



## 초기 세팅

1. 마켓(ctrl+shift+x)에서 ESLint, Prettier 설치

2. 설치 후 마켓에서 Prettier검색 후 톱니바퀴 클릭 -> 사용안함 클릭 -> 로드 필요 클릭

3. ctrl + , -> 설정 검색에 eslint -> 쭉 내려서 eslint: validate 항복에서 settings.json에서 편집 클릭후, 다음과 같이 추가 or 작성

   ```json
   {
   	// 위에 내용 생략
       
   	"eslint.validate": [
     
       {
         "language": "vue",
         "autoFix": true
       },
       {
         "language": "javascript",
         "autoFix": true
       },
       {
         "language": "javascriptreact",
         "autoFix": true
       },
       {
         "language": "typescript",
         "autoFix": true
       },
       {
         "language": "typescriptreact",
         "autoFix": true
       }
     ],
     "editor.codeActionsOnSave": {
       "source.fixAll.eslint": true
     },
     "eslint.workingDirectories": [
       {"mode": "auto"}
     ],
   }
   ```

4. 이제 Vue에서 ctrl + s로 저장할 때마다 자동으로 문법검사와 코드를 자동 정렬 시켜줄 수 있습니다.

   그리고 오류로 인해서 localhost:포트번호/ 에서 실행 못하는것을 풀어주었습니다. 끝!

## Project setup
```
yarn install
```

### Compiles and hot-reloads for development
```
yarn serve
```

### Compiles and minifies for production
```
yarn build
```

### Run your unit tests
```
yarn test:unit
```

### Lints and fixes files
```
yarn lint
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).

### board CRUD
Pages -> views폴더에 4개를 생성
1. 모든 게시글 보여주는 Page
2. 게시글 생성 Page
2. @클릭시 게시글 상세 Page
3. 수정Page

## 모든 게시글 보여주는 Page
> 컴포넌트 구조
1. 게시글list 보여주기 - 우선순위: 1
4. 관리자만 가능한 공지사항(나중에)

## @클릭시 게시글 상세 Page
> 컴포넌트 구조
1. 해당 boadID에대한 item - 우선순위: 2
2. 해당 boadID에 대한 댓글 list (댓글 삭제,수정 포함(비동기로))
3. 댓글 작성 폼

## 수정Page
> 컴포넌트 구조
1. 수정폼 - 우선순위: 3

## 기타
1. 게시글 삭제(상세페이지에서) - 우선순위: 4
2. 페이지네이션 버튼
3. 게시글 검색 컴포넌트