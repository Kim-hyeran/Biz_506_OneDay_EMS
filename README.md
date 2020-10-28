# Oneday Project : Email 관리 어플리케이션
 * 첨부파일 기능(최대 2개)이 존재하는 게시판

## 기능 수행 내용

#### INSERT 수행 시
 * 첨부된 파일의 이름을 변경하여 upload를 수행
 * 변경된 파일 이름을 VO에 저장한 후 Table에 insert

#### UPDATE 수행 시
 * 새로운 파일이 upload되면 기존 파일을 삭제하고 upload 수행
 * 새로운 파일이 선택되지 않으면 기존 파일을 유지한 채로 update 수행

#### DELETE 수행 시
 * 게시물의 모든 첨부파일을 먼저 삭제
 * Table의 데이터(첨부파일을 제외한 게시물 내용) 삭제