package top.moles.arkgacha.kotlin

import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.jsonMapper
import kotlin.test.Test

class ValueClassTest {
    @JvmInline
    value class UserId(val value: ULong)

    data class User(
        val id: UserId,
        val name: String,
    )
    data class User1(
        val id: UserId?,
        val name: String?,
    )

    private val objectMapper = jsonMapper { addModule(KotlinModule.Builder().apply {
        this.enable(KotlinFeature.KotlinPropertyNameAsImplicitName)
    }.build()) }

    @Test
    fun testSerialization() {
        val user0 = User(id = UserId(1u), name = "bob")
        val jsonStr0 = objectMapper.writeValueAsString(user0)
        println(jsonStr0) // {"id":1,"name":"bob"}
        val user0re = objectMapper.readValue(jsonStr0, User::class.java)
        println(user0re) // User(id=UserId(value=1), name=bob)

        val user1 = User1(id = UserId(1u), name = "bob")
        val jsonStr1 = objectMapper.writeValueAsString(user1)
        println(jsonStr1) // {"id":1,"name":"bob"}
        val user1re = objectMapper.readValue(jsonStr1, User1::class.java) // at com.fasterxml.jackson.databind.exc.ValueInstantiationException.from(ValueInstantiationException.java:47)
        println(user1re)
    }
}