package dev.dubrovin.springbootdemo.repositories

import dev.dubrovin.springbootdemo.entities.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Component
class PersonRepositoryImpl {
    @PersistenceContext
    var entityManager: EntityManager? = null;

    @Autowired
    @Lazy
    var personRepository: PersonRepository? = null;


    fun addPhoneToPerson(person: Person, phone: String): Person {
        person.phones.add(phone)
        personRepository!!.save(person)
        return person
    }

    fun removePhoneFromPerson(person: Person, phone: String): Person {
        person.phones.remove(phone)
        personRepository!!.save(person)
        return person
    }
}