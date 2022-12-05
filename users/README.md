# Users microservice

As part of my educational project for the course "Microservices Architecture" in Skillbox.
Since 21.11.2022

##Stack of Technologies
###MapStruct with Lombok annotations generating
Sources:
Quick Guide to MapStruct https://www.baeldung.com/mapstruct;
Что такое Mapstruct и как правильно настроить его для модульного тестирования в SpringBoot приложениях https://javarush.com/groups/posts/3698-chto-takoe-mapstruct-i-kak-praviljhno-nastroitjh-ego-dlja-moduljhnogo-testirovanija-v-springboo

##Build
###default profile. With only unit test executing.
Unit test names end with Test.
Use the following command: mvn clean install

###integration-test profile. With only integration tests executing.
Integration test names start with ITest.
Use the following command: mvn verify -Pintegration-test

##Should be added
###1. DB Audit
В сущности добавить поля, сохраняющие информацию о создании и последнем обновлении сущности.
Можно средствами аудита:
https://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/Hibernate_User_Guide.html#events-jpa-callbacks
https://www.baeldung.com/database-auditing-jpa
Можно проще - обычно это поля в таблице. Назовем их last_update_date_time и created_date_time. 
Их можно заполнить с помощью аннотаций @CreationTimestamp и @UpdateTimestamp. 
Или с помощью функций помеченными аннотациями @PrePersist и @PreUpdate. 

###2. Separate DTOs to two parts: for requests and for response
Better is to separate them by their functional requirements



