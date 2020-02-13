# Read Me First

The following was discovered as part of building this project:

* The original package name 'com.allianz.auth.token-converter' is invalid and this project uses 'com.allianz.auth.tokenconverter' instead.

# Getting Started

### Test the project

Fake request from epac to itmp

``` shell
curl \ 
--location --request GET 'localhost:8080/get' \
--header 'Host: itmp.allianz.com' \
--header 'Authorization: Bearer THE-BEARER'
```

Fake request from itmp to epac

``` shell
curl \
--location --request GET 'localhost:8080/get' \
--header 'Host: epac.allianz.com' \
--header 'Authorization: Bearer TOKEN-FROM-ITMP'
```

No Auth request from anywhere

``` shell
curl --location --request GET 'localhost:8080/get'
```

Request with Auth token but no epac nor itmp

``` shell
curl \--location --request GET 'localhost:8080/get' \
--header 'Host: another.host.com' \
--header 'Authorization: Bearer TOKEN-NO-MODFIED-BECAUSE-NO-EPAC-OR-ITMP'
```


All the requests are routed to http://httpbin.org to allow visualize the resulting headers.



### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/maven-plugin/)

### Guides
The following guides illustrate how to use some features concretely:

* [Using Spring Cloud Gateway](https://github.com/spring-cloud-samples/spring-cloud-gateway-sample)

