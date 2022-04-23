package dev.dubrovin.springbootdemo.controllers

import dev.dubrovin.springbootdemo.controllers.exceptions.GroupNotFoundException
import dev.dubrovin.springbootdemo.controllers.exceptions.PersonNotFoundException
import dev.dubrovin.springbootdemo.repositories.GroupRepository
import dev.dubrovin.springbootdemo.repositories.PersonRepository
import dev.dubrovin.springbootdemo.entities.Group
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.logging.Logger

@RestController
@RequestMapping("/api/group")
class GroupController(
    private val repository: GroupRepository,
    private val personRepository: PersonRepository
) {
    val logger = LoggerFactory.getLogger(javaClass)


    @GetMapping("/")
    fun findAll(): MutableIterable<Group> {
        return repository.findAll()
    }

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: Long): Group {
        var res = repository.findById(id)
        if (res.isEmpty) {
            throw GroupNotFoundException(id)
        }
        return res.get()
    }

    @DeleteMapping("/{id}")
    fun deleteOne(@PathVariable id: Long): String {
        if (!repository.existsById(id)) {
            throw GroupNotFoundException(id)
        }
        repository.deleteById(id);
        logger.info("Deleted group with $id")
        return "Done"
    }

    @PostMapping("/", consumes = ["application/json"])
    fun createOne(@RequestBody group: Group): Group {
        repository.save(group)
        logger.info("Created $group")
        return group
    }

    @PostMapping("/{id}/add_person")
    fun addPerson(@PathVariable id: Long, @RequestParam(name = "id") person_id: Long): Group {
        val group = repository.findById(id)
        if (group.isEmpty) {
            throw GroupNotFoundException(id)
        }

        val person = personRepository.findById(person_id)
        if (person.isEmpty) {
            throw PersonNotFoundException(person_id)
        }
        logger.info("Added $person to $group")
        return repository.addPersonToGroup(group.get(), person.get())
    }

    @PostMapping("/{id}/remove_person")
    fun removePerson(@PathVariable id: Long, @RequestParam(name = "id") person_id: Long): Group {
        val group = repository.findById(id)
        if (group.isEmpty) {
            throw GroupNotFoundException(id)
        }

        val person = personRepository.findById(person_id)
        if (person.isEmpty) {
            throw PersonNotFoundException(person_id)
        }

        logger.info("Removed $person from $group")
        return repository.deletePersonFromGroup(group.get(), person.get())
    }

}