package com.example.demo2.repositories

import com.example.demo2.entities.Group
import com.example.demo2.entities.Person
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


interface GroupRepository : CrudRepository<Group, Long> {


    fun addPersonToGroup(group: Group, person: Person): Group

    fun deletePersonFromGroup(group: Group, person: Person): Group
}