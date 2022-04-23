package dev.dubrovin.springbootdemo.repositories

import dev.dubrovin.springbootdemo.entities.Group
import dev.dubrovin.springbootdemo.entities.Person
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


interface GroupRepository : CrudRepository<Group, Long> {


    fun addPersonToGroup(group: Group, person: Person): Group

    fun deletePersonFromGroup(group: Group, person: Person): Group
}