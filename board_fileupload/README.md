# Spring multipart 파일 업로드를 위한 설정


## pom.xml
> dependency 에 아래 라이브러리 추가
``` xml
<dependency>
	<groupId>commons-fileupload</groupId>
	<artifactId>commons-fileupload</artifactId>
	<version>1.2.2</version>
</dependency>

<dependency>
	<groupId>org.apache.commons</groupId>
	<artifactId>commons-io</artifactId>
	<version>1.3.2</version>
</dependency>
```

## WEB-INF/spring/root-context.xml

``` xml
<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
	<property name="maxUploadSize" value="524288000" />
</bean>
  ```

## Content-Dispoisition 한글처리
``` java
String header = request.getHeader("User-Agent");
String fileNameOrg=attachFile.getOriginalFileName();
try {
	if (header.contains("MSIE") || header.contains("Trident")) {
		fileNameOrg = URLEncoder.encode(fileNameOrg, "UTF-8").replaceAll("\\+", "%20");
		response.setHeader("Content-Disposition", "attachment;filename=" + fileNameOrg + ";");
	} else {
		fileNameOrg = new String(fileNameOrg.getBytes("UTF-8"), "ISO-8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileNameOrg + "\"");
	}
}catch(UnsupportedEncodingException e) {}
```

# jsonView 를 위한설정

## pom.xml
``` xml
<dependency>
	<groupId>net.sf.json-lib</groupId>
	<artifactId>json-lib</artifactId>
	<version>2.4</version>
	<classifier>jdk15</classifier>
</dependency>

<dependency>
	<groupId>org.codehaus.jackson</groupId>
	<artifactId>jackson-mapper-asl</artifactId>
	<version>1.6.4</version>
</dependency>
```

## WEB-INF/spring/root-context.xml

``` xml

<bean
	class="org.springframework.web.servlet.view.BeanNameViewResolver">
	<property name="order" value="0" />
</bean>

<bean id="jsonView"
	class="org.springframework.web.servlet.view.json.MappingJacksonJsonView">
	<property name="contentType"
		value="application/json;charset=UTF-8">
	</property>
</bean>
```


# 패스워드 암호화시키기 
``` java
public  String sha256(String msg)  throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    md.update(msg.getBytes());
    return byteToHexString(md.digest());
}

public  String byteToHexString(byte[] data) {
    StringBuilder sb = new StringBuilder();
    for(byte b : data) {
	sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
    }
    return sb.toString();
}
```

# CorsFilter

``` java
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
/**
 *
 * @author ljo
 *
 */
public class CORSFilter implements Filter{
 
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
 
    }
 
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Methods","GET, OPTIONS, HEAD, PUT, POST");
        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if (request.getMethod().equals("OPTIONS")) {
            resp.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        chain.doFilter(request, servletResponse);
    }
 
    @Override
    public void destroy() {
 
    }
 
}
```


# web.xml

``` xml
<filter>
    <filter-name>cors</filter-name>
    <filter-class>com.jyh.board.CORSFilter</filter-class>
</filter>

<filter-mapping>
    <filter-name>cors</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>

```


