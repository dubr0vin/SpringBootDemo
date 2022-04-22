package com.example.demo2.entities

import java.util.*
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Person(
    var name: String,

    @ElementCollection
    var phones: MutableSet<String> = HashSet(),

    @Id @GeneratedValue var id: Long? = null
)