# Github 주소
https://github.com/jinyonghwa/spring


# Spring Framework
- mvc 구조 설명 https://www.slideshare.net/ymtech/spring-mvc-37262862

![mvc](https://t1.daumcdn.net/cfile/tistory/992B234C5C807FD114?download)

## MyBatis
- mybatis는 별도의 xml 파일을 통해 SQL 문의 정의 부분을 독립적으로 유지할 수 있다
- SQL문을 보다 유연하게 처리할 수 있다는 문법 구문을 제공
- 이터베이스 연결시 발생하는 부하를 줄이기 위한 Connection Pool을 자체적으로 제공
- JDBC를 직접 사용함에 따라 발생하는 복잡한 try .. catch .. finally 코드 부분이 제거되어 코드의 가독성이 향상



### Param Encoding 
### web.xml
``` xml
<filter>
  <filter-name>encodingFilter</filter-name>
  <filter-class>
    org.springframework.web.filter.CharacterEncodingFilter
  </filter-class>
  <init-param>
    <param-name>encoding</param-name>
    <param-value>UTF-8</param-value>
  </init-param>
  <init-param>
    <param-name>forceEncoding</param-name>
    <param-value>true</param-value>
  </init-param>
</filter>
<filter-mapping>
  <filter-name>encodingFilter</filter-name>
  <url-pattern>/*</url-pattern>
</filter-mapping>
```
