# Users microservice

As part of my educational project for the course "Microservices Architecture" in Skillbox.
Since 21.11.2022

#Stack of Technologies
##MapStruct with Lombok annotations generating
Sources:
Quick Guide to MapStruct https://www.baeldung.com/mapstruct;
Что такое Mapstruct и как правильно настроить его для модульного тестирования в SpringBoot приложениях https://javarush.com/groups/posts/3698-chto-takoe-mapstruct-i-kak-praviljhno-nastroitjh-ego-dlja-moduljhnogo-testirovanija-v-springboo

#Should be added.
##1. DB Audit. 
В сущности добавить поля, сохраняющие информацию о создании и последнем обновлении сущности.
Можно средствами аудита:
https://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/Hibernate_User_Guide.html#events-jpa-callbacks
https://www.baeldung.com/database-auditing-jpa
Можно проще - обычно это поля в таблице. Назовем их last_update_date_time и created_date_time. 
Их можно заполнить с помощью аннотаций @CreationTimestamp и @UpdateTimestamp. 
Или с помощью функций помеченными аннотациями @PrePersist и @PreUpdate. 


