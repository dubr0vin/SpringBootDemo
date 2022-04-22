package com.example.demo2

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "group_table")
class Group(
    var title: String,

    @OneToMany
    var people: MutableList<Person> = LinkedList(),

    @Id @GeneratedValue var id: Long? = null
)

@Entity
class Person(
    var name:String,

    @ElementCollection
    var phones: MutableSet<String> = HashSet(),

    @Id @GeneratedValue var id: Long? = null
)