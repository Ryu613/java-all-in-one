# HikariCP介绍及使用
> 来源于Baeldung
> [原址](https://www.baeldung.com/hikaricp)
# 总览
1. HikariCP是一个JDBC连接池框架，由Brett Wooldrige 在2012年左右开发。这个框架非常小，大概130Kb，而且非常快。
2. 性能上，比起类似C3P0,DBCP2,tomcat，vibur等要快好几倍，原因如下:
   1. 字节码级别的实现，原生汇编语言代码
   2. 非常细致的优化，不容易直接体现出来，但是对整体性能有提升
   3. 自定义Collections类，比如，不用java原生ArrayList，而是自定义了FastList,避免了对性能有影响的功能的调用，比如ArrayList的边界检查和元素移除扫描
# 引入
目前支持Java 8-11
```
<dependency>
    <groupId>com.zaxxer</groupId>
    <artifactId>HikariCP</artifactId>
    <version>3.4.5</version>
</dependency>
```
Hikari也支持老版本的JDK，比如6，7
> [java6](https://search.maven.org/search?q=g:com.zaxxer%20AND%20a:HikariCP-java6)

> [java7](https://search.maven.org/search?q=g:com.zaxxer%20AND%20a:HikariCP-java7)

# 创建数据源源码
```java
public class DataSource {

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        config.setJdbcUrl( "jdbc_url" );
        config.setUsername( "database_username" );
        config.setPassword( "database_password" );
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        ds = new HikariDataSource( config );
    }

    private DataSource() {}

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
```
1. 这段代码见Hikari源码中的[HikariConfig](https://github.com/openbouquet/HikariCP/blob/master/src/main/java/com/zaxxer/hikari/HikariConfig.java)
2. 数据源的创建是在static静态代码块中
3. 有四个人尽皆知的参数,用户名，密码，jdcb连接，和数据源类名
4. 对于JDBC连接和数据源类名2个参数，一般只设其中一个即可，但是对于老版本的JDBC驱动，需要两个都设置
5. 除了这些参数以外，Hikari还提供了别的连接池框架可能没有提供的参数，如下:
   - autoCommit
   - connectionTimeout
   - idleTimeout
   - maxLifetime
   - connectionTestQuery
   - connectionInitSql
   - validationTimeout
   - maximumPoolSize
   - poolName
   - allowPoolSuspension
   - readOnly
   - transactionIsolation
   - leakDetectionThreshold

   具体参数含义及使用可以参考[这里](https://github.com/brettwooldridge/HikariCP)
6. 也可以使用配置文件配置
```java
private static HikariConfig config = new HikariConfig("datasource.properties" );
```
配置文件长这样
```
dataSourceClassName= 
dataSource.user= 
//以此类推
```
也可以用基于java.util.Porperties实现
```java
Properties props = new Properties();
props.setProperty( "dataSourceClassName" , //TBD );
props.setProperty( "dataSource.user" , //TBD );
//为其他属性写好setter方法
private static HikariConfig config = new HikariConfig( props );
```
# 使用
1. 建表，插数据，文中使用H2数据库做示例
```sql
create table dept(
  deptno numeric,
  dname  varchar(14),
  loc    varchar(13),
  constraint pk_dept primary key ( deptno )
);
 
create table emp(
  empno    numeric,
  ename    varchar(10),
  job      varchar(9),
  mgr      numeric,
  hiredate date,
  sal      numeric,
  comm     numeric,
  deptno   numeric,
  constraint pk_emp primary key ( empno ),
  constraint fk_deptno foreign key ( deptno ) references dept ( deptno )
);

insert into dept values( 10, 'ACCOUNTING', 'NEW YORK' );
insert into dept values( 20, 'RESEARCH', 'DALLAS' );
insert into dept values( 30, 'SALES', 'CHICAGO' );
insert into dept values( 40, 'OPERATIONS', 'BOSTON' );
 
insert into emp values(
 7839, 'KING', 'PRESIDENT', null,
 to_date( '17-11-1981' , 'dd-mm-yyyy' ),
 7698, null, 10
);
insert into emp values(
 7698, 'BLAKE', 'MANAGER', 7839,
 to_date( '1-5-1981' , 'dd-mm-yyyy' ),
 7782, null, 20
);
insert into emp values(
 7782, 'CLARK', 'MANAGER', 7839,
 to_date( '9-6-1981' , 'dd-mm-yyyy' ),
 7566, null, 30
);
insert into emp values(
 7566, 'JONES', 'MANAGER', 7839,
 to_date( '2-4-1981' , 'dd-mm-yyyy' ),
 7839, null, 40
);
```
2. jdb连接: 
```sql
jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;INIT=runscript from 'classpath:/db.sql'
```
3. 获取数据的方法
关键是DataSource.getConnection()
```java
public static List<Employee> fetchData() throws SQLException {
    String SQL_QUERY = "select * from emp";
    List<Employee> employees = null;
    try (Connection con = DataSource.getConnection();
        PreparedStatement pst = con.prepareStatement( SQL_QUERY );
        ResultSet rs = pst.executeQuery();) {
            employees = new ArrayList<>();
            Employee employee;
            while ( rs.next() ) {
                employee = new Employee();
                employee.setEmpNo( rs.getInt( "empno" ) );
                employee.setEname( rs.getString( "ename" ) );
                employee.setJob( rs.getString( "job" ) );
                employee.setMgr( rs.getInt( "mgr" ) );
                employee.setHiredate( rs.getDate( "hiredate" ) );
                employee.setSal( rs.getInt( "sal" ) );
                employee.setComm( rs.getInt( "comm" ) );
                employee.setDeptno( rs.getInt( "deptno" ) );
                employees.add( employee );
            }
	} 
    return employees;
}
```
4. 测试类
```java
@Test
public void givenConnection_thenFetchDbData() throws SQLException {
    HikariCPDemo.fetchData();
 
    assertEquals( 4, employees.size() );
}
```