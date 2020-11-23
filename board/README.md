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
