package com.dorokhov.RESTapi.dao;

import com.dorokhov.RESTapi.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    /*
    Мы создаем бин PersonRepository, аннотируя его с помощью @Repository.
     Полученный бин реализует все методы интерфейса, можно ничего не писать самостоятельно,
     если не нужны какие-то особые запросы к базе. А стандартные операции поиска,
     добавления и удаления тут все реализованы.
     */
}
