package com.fight

import com.fight.dao.DatabaseFactory
import com.fight.plugins.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        DatabaseFactory.init()
        configureSerialization()
        configureHTTP()
        configureSecurity()
        configureRouting()
        configureTemplating()
    }.start(wait = true)
}
