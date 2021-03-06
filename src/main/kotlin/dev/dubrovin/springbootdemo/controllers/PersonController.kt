package dev.dubrovin.springbootdemo.controllers

import dev.dubrovin.springbootdemo.controllers.exceptions.GroupNotFoundException
import dev.dubrovin.springbootdemo.controllers.exceptions.PersonNotFoundException
import dev.dubrovin.springbootdemo.repositories.PersonRepository
import dev.dubrovin.springbootdemo.entities.Person
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/person")
class PersonController(
    private val repository: PersonRepository
) {

    val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping("/")
    fun findAll(): MutableIterable<Person> {
        return repository.findAll()
    }

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: Long): Person {
        var res = repository.findById(id)
        if (res.isEmpty) {
            throw PersonNotFoundException(id)
        }
        return res.get()
    }

    @DeleteMapping("/{id}")
    fun deleteOne(@PathVariable id: Long): String {
        if (!repository.existsById(id)) {
            throw PersonNotFoundException(id)
        }
        repository.deleteById(id);
        logger.info("Deleted person with $id")
        return "Done"
    }

    @PostMapping("/", consumes = ["application/json"])
    fun createOne(@RequestBody person: Person): Person {
        repository.save(person)

        logger.info("Created $person")
        return person
    }

    @PostMapping("/{id}/add_phone")
    fun addPhone(@PathVariable id: Long, @RequestParam(name = "phone") phone: String): Person {
        val person = repository.findById(id)
        if (person.isEmpty) {
            throw PersonNotFoundException(id)
        }
        logger.info("Added phone $phone to $person")
        return repository.addPhoneToPerson(person.get(), phone)
    }

    @PostMapping("/{id}/remove_phone")
    fun removePhone(@PathVariable id: Long, @RequestParam(name = "phone") phone: String): Person {
        val person = repository.findById(id)
        if (person.isEmpty) {
            throw PersonNotFoundException(id)
        }
        logger.info("Removed phone $phone from $person")
        return repository.removePhoneFromPerson(person.get(), phone)
    }
}