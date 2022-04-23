package dev.dubrovin.springbootdemo

import dev.dubrovin.springbootdemo.entities.Group
import dev.dubrovin.springbootdemo.entities.Person
import dev.dubrovin.springbootdemo.repositories.GroupRepository
import dev.dubrovin.springbootdemo.repositories.PersonRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

@DataJpaTest
class RepositoriesTests @Autowired constructor(
    val entityManager: TestEntityManager,
    val groupRepository: GroupRepository,
    val personRepository: PersonRepository
) {
    @Test
    fun `Add person to group`() {
        val group = Group("Test group")
        val person = Person("Test person")
        entityManager.persist(group)
        entityManager.persist(person)
        entityManager.flush()
        groupRepository.addPersonToGroup(group, person)
        assert(group.people.size == 1)
        assert(group.people[0].equals(person))
    }

    @Test
    fun `Remove person from group`() {
        val group = Group("Test group")
        val person = Person("Test person")
        entityManager.persist(group)
        entityManager.persist(person)
        entityManager.flush()
        groupRepository.addPersonToGroup(group, person)

        groupRepository.deletePersonFromGroup(group, person)
        assert(group.people.size == 0)
    }

    @Test
    fun `Add phone to person`() {
        val person = Person("Test person")
        val phone = "+71234567890"
        entityManager.persist(person)
        entityManager.flush()

        personRepository.addPhoneToPerson(person, phone)

        assert(person.phones.size == 1)
        assert(person.phones.contains(phone))
    }

    @Test
    fun `Remove phone from person`() {
        val person = Person("Test person")
        val phone = "+71234567890"
        entityManager.persist(person)
        entityManager.flush()

        personRepository.addPhoneToPerson(person, phone)

        personRepository.removePhoneFromPerson(person, phone)

        assert(person.phones.size == 0)
    }
}