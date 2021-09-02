### pom.xml
``` xml
<dependency>
  <groupId>org.mybatis</groupId>
  <artifactId>mybatis</artifactId>
  <version>3.4.6</version>
</dependency>

<dependency>
  <groupId>org.mybatis</groupId>
  <artifactId>mybatis-spring</artifactId>
  <version>1.3.2</version>
</dependency>

<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-jdbc</artifactId>
  <version>${org.springframework-version}</version>
</dependency>

<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-test</artifactId>
  <version>${org.springframework-version}</version>
</dependency>

<dependency>
  <groupId>org.apache.commons</groupId>
  <artifactId>commons-dbcp2</artifactId>
  <version>2.6.0</version>
</dependency>
<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-core</artifactId>
  <version>2.4.3</version>
</dependency>
<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-databind</artifactId>
  <version>2.4.3</version>
</dependency>


<!-- mariaDB -->
<dependency>
  <groupId>org.mariadb.jdbc</groupId>
  <artifactId>mariadb-java-client</artifactId>
  <version>2.3.0</version>
</dependency>

```

### main/webapp/WEB-INF/spring/root-context.xml

``` xml
<bean id="dataSource"
  class="org.springframework.jdbc.datasource.DriverManagerDataSource">
  <property name="driverClassName"
    value="org.mariadb.jdbc.Driver" />
  <property name="url"
    value="jdbc:mariadb://127.0.0.1:3306/board" />
  <property name="username" value="root" />
  <property name="password" value="데이터베이스패스워드" />
</bean>
<bean id="sqlSessionFactory"
  class="org.mybatis.spring.SqlSessionFactoryBean">
  <property name="dataSource" ref="dataSource" />
  <property name="configLocation"
    value="classpath:/mybatis-config.xml"></property>
  <property name="mapperLocations"
    value="classpath:/mappers/**/*Mapper.xml" />
</bean>
<bean id="sqlSession"
  class="org.mybatis.spring.SqlSessionTemplate">
  <constructor-arg index="0" ref="sqlSessionFactory" />
</bean>

```

### main/webapp/WEB-INF/classes/mybatis-config.xml
``` xml
 
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
<settings>
  <!-- 자동으로 카멜케이스 규칙으로 변환 -->
  <setting name="mapUnderscoreToCamelCase" value="true"/>
</settings>

<typeAliases>
  <package name="kr.ac.mjc.model"/>
</typeAliases>
</configuration>
```

### main/webapp/WEB-INF/classes/mappers/boardMapper.xml
``` xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="board">

	
</mapper>
```

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




### Navigator.java

``` java

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Navigator {
	private static final Logger logger = LoggerFactory.getLogger(Navigator.class);
	private int page;	//현재페이지
	private int start;	//navigator의 시작 페이지
	private int end;	//navigator의 끝 페이지
	private int prevPage;	//이전 navigator 클릭시 page번호
	private int nextPage;	//다음 navigator 클릭시 page번호
	private int lastPage;	//다음 navigator 클릭시 page번호
	private int startNum;	//현재 페이지의 첫번째글 번호
	private boolean prev;	//이전 navigator 존재유무
	private boolean next;	//다음 navigator 존재유무

	int count;	//전체 글갯수

	int itemPerPage = 10;	//한개페이지당 글의갯수
	int navCount = 10;	//하단 navigator의 한번에 보여질 page 갯수 
	
	public Navigator() {
	
	}
	public Navigator(int itemPerPage,int navCount) {
		this.itemPerPage=itemPerPage;
		this.navCount=navCount;
	}
	public int getSkip(int page) {
		return (page-1)*itemPerPage;
	}
	
	
	public List<Integer> getNavArr() {
		ArrayList<Integer> arr=new ArrayList();
		for(int i=start;i<=end;i++) {
			arr.add(i);
		}
		return arr;
	}

	 public Navigator getNav(int page,int count) {
		
		setPage(page);
		setCount(count);

		setStart(((int)Math.floor(page-1)/getNavCount())*getNavCount()+1);
		setEnd(getStart()+getNavCount()-1);
		
		
		int totalPage=getTotalPage(count);
		
		//글이 네비게이션셋일경우
		if(getStart()==1) {
			setPrev(false);
		}
		else {
			int prevPage=(page-1)/getNavCount() *getNavCount() -getNavCount()+1;
			setPrevPage(prevPage);
			setPrev(true);
		}
		//네비게이션 마지막이 글전체페이지수를 초과할경우	
		if(getEnd()>=totalPage) {
			setEnd(totalPage);
			setNext(false);
		}
		else {
			setNext(true);
			setNextPage(getStart()+navCount);
		}
		
		int startNum=count-((page-1)*itemPerPage);
		lastPage=getTotalPage(count);
		setStartNum(startNum);
		
		
		return this;
	}

	public int getTotalPage(int count) {
		return (int) Math.ceil((float)count / itemPerPage);
	}
	

	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getStart() {
		return start;
	}
	
	public int getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getItemPerPage() {
		return itemPerPage;
	}

	public void setItemPerPage(int itemPerPage) {
		this.itemPerPage = itemPerPage;
	}

	public int getNavCount() {
		return navCount;
	}

	public void setNavCount(int navCount) {
		this.navCount = navCount;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getStartNum() {
		return startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
```
