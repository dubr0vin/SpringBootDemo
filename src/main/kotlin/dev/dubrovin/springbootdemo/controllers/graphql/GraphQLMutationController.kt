package dev.dubrovin.springbootdemo.controllers.graphql

import dev.dubrovin.springbootdemo.controllers.exceptions.GroupNotFoundException
import dev.dubrovin.springbootdemo.controllers.exceptions.PersonNotFoundException
import dev.dubrovin.springbootdemo.entities.Group
import dev.dubrovin.springbootdemo.entities.Person
import dev.dubrovin.springbootdemo.repositories.GroupRepository
import dev.dubrovin.springbootdemo.repositories.PersonRepository
import org.slf4j.LoggerFactory
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller

@Controller
class GraphQLMutationController(
    var groupRepository: GroupRepository,
    var personRepository: PersonRepository
) {
    val logger = LoggerFactory.getLogger(javaClass)

    @MutationMapping
    fun createGroup(@Argument title: String): Group {
        val group = Group(title)
        groupRepository.save(group)
        logger.info("Created $group")
        return group
    }

    @MutationMapping
    fun addPersonToGroup(@Argument group_id: Long, @Argument person_id: Long): Group {
        val group = groupRepository.findById(group_id)
        if (group.isEmpty) {
            throw GroupNotFoundException(group_id)
        }

        val person = personRepository.findById(person_id)
        if (person.isEmpty) {
            throw PersonNotFoundException(person_id)
        }
        logger.info("Added $person to $group")
        return groupRepository.addPersonToGroup(group.get(), person.get())
    }

    @MutationMapping
    fun removePersonFromGroup(@Argument group_id: Long, @Argument person_id: Long): Group {
        val group = groupRepository.findById(group_id)
        if (group.isEmpty) {
            throw GroupNotFoundException(group_id)
        }

        val person = personRepository.findById(person_id)
        if (person.isEmpty) {
            throw PersonNotFoundException(person_id)
        }
        logger.info("Removed $person from $group")
        return groupRepository.deletePersonFromGroup(group.get(), person.get())
    }

    @MutationMapping
    fun deleteGroup(@Argument group_id: Long): Long {
        if (!groupRepository.existsById(group_id)) {
            throw GroupNotFoundException(group_id)
        }
        groupRepository.deleteById(group_id);
        logger.info("Deleted group with $group_id")
        return group_id
    }

    @MutationMapping
    fun createPerson(@Argument name: String): Person {
        var person = Person(name)
        personRepository.save(person)
        logger.info("Created $person")
        return person
    }

    @MutationMapping
    fun addPhoneToPerson(person_id: Long, phone: String): Person {
        val person = personRepository.findById(person_id)
        if (person.isEmpty) {
            throw PersonNotFoundException(person_id)
        }
        logger.info("Added phone $phone to $person")
        return personRepository.addPhoneToPerson(person.get(), phone)
    }

    @MutationMapping
    fun removePhoneFromPerson(person_id: Long, phone: String): Person {
        val person = personRepository.findById(person_id)
        if (person.isEmpty) {
            throw PersonNotFoundException(person_id)
        }
        logger.info("Removed phone $phone from $person")
        return personRepository.removePhoneFromPerson(person.get(), phone)
    }

    @MutationMapping
    fun deletePerson(person_id: Long): Long {
        if (!personRepository.existsById(person_id)) {
            throw PersonNotFoundException(person_id)
        }
        personRepository.deleteById(person_id);
        logger.info("Deleted person with $person_id")
        return person_id
    }
}