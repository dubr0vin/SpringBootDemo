package com.example.demo2.repositories

import com.example.demo2.entities.Group
import org.springframework.data.repository.CrudRepository

interface GroupRepository: CrudRepository<Group, Long> {
}