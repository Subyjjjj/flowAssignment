# flowAssignment
JPA를 이용한 JAVA 연습, 파일 업로드 + EC2 배포 테스트

개발 환경
-------------
1. JAVA11
2. Jquery
3. Mysql (8버전)
4. vscode(IDE)
5. gardle
6. JPA
7. springboot 2.6

DB - extansion(table)
-------------
1. seq(int) - PK
2. text(varchar20) - 확장자명
3. flag(varchar1) - 확장자 구분 : 고정 확장자(Y, N) : 가변 확장자(E) 

배포
-------------
AWS EC2(amazon linux 운영체제) : http://3.38.245.185:8080/ (2022-01-28 EC2 서비스 중지)
