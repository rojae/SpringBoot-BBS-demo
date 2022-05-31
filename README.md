# SpringBoot-BBS-demo
simple bbs demo 

RestController 방식의 api 통신이 가능하다.

Controller 방식의 views를 통한 jsp 출력이 가능하다.

해당 소스코드의 주석 제거시
PostMan을 통한 테스트 및 ui 작업이 가능하다.

------
## 이슈사항
### 1. org.apache.ibatis.binding.BindingException ...
제가 만들었던 프로젝트는 "이클립스" 기반이였고

문제가 발생한 개발환경은 "인텔리제이"입니다.

인텔리제이 같은 경우는 /resources과 xml 파일을 자동으로 잡아주지 않습니다.

검색하다보니, 제가 2년 전에 "블로그에 기록했던 글"이 있더라구요.

따라했습니다.

(사실 제가 쓴 줄도 몰랐습니다)

주소 : https://redcoder.tistory.com/184?category=828494

### 2. ERROR 1366 (HY000) : incorrect string value : ''\xED\x95\x9C\xEC\x9A\xB0...' for column ...
인코딩 문제 발생으로 데이터 추가 불가능

데이터베이스 인코딩에서 UTF8이 되어 있지 않았습니다.

이에 아래 명령어로 처리했습니다.

```sql
ALTER TABLE board convert to charset utf8;
```
