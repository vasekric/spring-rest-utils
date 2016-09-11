[![Build Status](https://travis-ci.org/vasekric/spring-rest-utils.svg?branch=master)](https://travis-ci.org/vasekric/spring-rest-utils)

# Spring Utils&Helpers
- Goal is to extract duplicated utils and helpers classes from my projects

## How to add library via maven
- This repository is hosted on https://jitpack.io/
```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
```
```xml
<dependencies>
  <dependency>
    <groupId>com.github.vasekric</groupId>
    <artifactId>spring-rest-utils</artifactId>
    <version>v0.1</version>
  </dependency>
</dependencies>
```


## Pagination and fields filtering
- Goal is to simply provide better APIs with pagination and response field filtering by default.

Example:
```
http://host:port/v1/foos?fields=id,baz&limit=10&offset=30
```
This will return list of 10 objects which contains just id and baz fields and first 30 objects in database are skipped.

### Usage
You can either create your own instances using "new" operator or follow the Spring way with @Autowired

In order to be able to @Autowired util classes to your application, you need to put them into @ComponentScan.
```java
@ComponentScans({
        @ComponentScan("your.package.root"),
        @ComponentScan("cz.vasekric.spring") // spring utils packages
})
```

In @RequestMapping methods you can then simply use
```java
public ResponseEntity someSearch(RequestOptions options) {
            // ...
            }
```
This is going to bind you @RequestParams to RequestOptions object.

For fields validation, there is FieldValidator class you can use.
```java
fieldValidator.validate(Foo.class, options.getFields());
```
* It throws ValidationException when the class does not have all the requested fields.
* It checks all the fields, does not matter if they are private, public, or something else.

### MongoDB integration
For MongoDB users, there is QueryFactory which creates a query from your RequestOptions.
```java
final Query query = this.queryFactory.createQuery(options);
final List<Foo> foos = this.mongoTemplate.find(query, Foo.class);
```
* It puts default limit of 1000 into the query if you do not specify any limit. You can change it by setting: QueryFactory.defaultLimit = N
