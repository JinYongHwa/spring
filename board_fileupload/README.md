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
