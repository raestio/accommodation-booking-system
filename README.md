# Accommodation booking system
Inspired by AirBnb

# Run

```sh
$ git clone https://github.com/raestio/accommodation-booking-system.git
$ cd accommodation-booking-system/app/
$ docker-compose up
$ mvn package
$ java -jar target/accommodation-booking-system-1.0.0.beta.jar

Type localhost:8080 in Google Chrome (In Firefox it does not work correctly) 

Some test accounts are created in advance so you can log in with them to test it out:

Admin
  Login email:  test.admin@test.com
  Password:     test.admin

User
  Login email:  test.user@test.com
  Password:     test.user
```

### Tech
* [Spring Boot]
* [Spring Data JPA]
* [Spring Security]
* [Vaadin Flow]
* [PostgreSQL]
* [Cloudinary]

[Spring Boot]: <https://projects.spring.io/spring-boot/>
[Spring Data JPA]: <https://projects.spring.io/spring-data-jpa/>
[Spring Security]: <https://projects.spring.io/spring-security/>
[Vaadin Flow]: <https://vaadin.com/flow>
[PostgreSQL]: <https://www.postgresql.org/>
[Cloudinary]: <https://cloudinary.com/>


# Screenshots
![screenshot_1]
![screenshot_3]
![screenshot_4]
![screenshot_5]


[screenshot_1]: https://res.cloudinary.com/accommodation-booking-system/image/upload/v1525779076/1.jpg "Home page (admin)"
[screenshot_2]: https://res.cloudinary.com/accommodation-booking-system/image/upload/v1525779076/3.jpg "Book accommodation"
[screenshot_3]: https://res.cloudinary.com/accommodation-booking-system/image/upload/v1525779075/4.jpg "Create accommodation"
[screenshot_4]: https://res.cloudinary.com/accommodation-booking-system/image/upload/v1525779076/5.jpg "Your bookings"
[screenshot_5]: https://res.cloudinary.com/accommodation-booking-system/image/upload/v1525779076/6.jpg "Sign Up"
