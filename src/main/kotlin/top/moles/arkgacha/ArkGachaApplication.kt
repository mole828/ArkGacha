package top.moles.arkgacha

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class ArkGachaApplication

fun main(args: Array<String>) {
    runApplication<ArkGachaApplication>(*args)
}

@RestController
class IndexController {
    var count = 0
    @GetMapping("/")
    fun index(): String {
        count++
        return "Hello World! $count"
    }
}