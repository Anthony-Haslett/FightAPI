package com.fight.plugins

import com.fight.dao.dao
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

fun Application.configureRouting() {

    routing {
        // region GET
        get("/") {
            call.respondText("Hello World!")
        }
        get("/fight-card") {
            call.respondText("Fight card")
        }
        get("articles") {
            call.respondText(mapOf("articles" to dao.allArticles()).toString())
        }

        get("{id}") {
            val id = call.parameters.getOrFail<Int>("id").toInt()
            call.respondText(mapOf("article" to dao.article(id)).toString())
        }
        get("{id}/edit") {
            val id = call.parameters.getOrFail<Int>("id").toInt()
            call.respondText(mapOf("article" to dao.article(id)).toString())
        }
        // endregion

        // region POST
        post {
            val formParameters = call.receiveParameters()
            val title = formParameters.getOrFail("title")
            val body = formParameters.getOrFail("body")
            val article = dao.addNewArticle(title, body)
            call.respondRedirect("/articles/${article?.id}")
        }

        post("{id}") {
            val id = call.parameters.getOrFail<Int>("id").toInt()
            val formParameters = call.receiveParameters()
            when (formParameters.getOrFail("_action")) {
                "update" -> {
                    val title = formParameters.getOrFail("title")
                    val body = formParameters.getOrFail("body")
                    dao.editArticle(id, title, body)
                    call.respondRedirect("/articles/$id")
                }

                "delete" -> {
                    dao.deleteArticle(id)
                    call.respondRedirect("/articles")
                }
            }
        }
        // endregion
    }
}
