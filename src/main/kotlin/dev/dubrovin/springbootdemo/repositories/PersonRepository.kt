package dev.dubrovin.springbootdemo.repositories

import dev.dubrovin.springbootdemo.entities.Person
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


interface PersonRepository : CrudRepository<Person, Long> {
    fun addPhoneToPerson(person: Person, phone: String): Person
    fun removePhoneFromPerson(person: Person, phone: String): Person
}
