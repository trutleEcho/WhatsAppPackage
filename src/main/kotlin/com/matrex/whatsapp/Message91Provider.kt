package com.matrex.whatsapp

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement

/**
 * Sends WhatsApp messages via MSG91.
 */
suspend fun sendViaMsg91(
    requestBody: Map<String, JsonElement>,
    authKey: String,
    client: HttpClient = httpsClient
): Boolean = withContext(Dispatchers.IO) {
    try {
        val serializedRequestBody = Json.encodeToString(requestBody)

        val response: HttpResponse = client.post("https://api.msg91.com/api/v5/whatsapp/whatsapp-outbound-message/bulk/") {
            contentType(ContentType.Application.Json)
            header("authkey", authKey)
            setBody(serializedRequestBody)
        }

        if (response.status != HttpStatusCode.OK) {
            val errorText = response.bodyAsText()
            println("❌ MSG91 WhatsApp Error: ${response.status} - $errorText")
            return@withContext false
        }

        println("✅ MSG91 WhatsApp message sent successfully.")
        return@withContext true
    } catch (e: Exception) {
        println("❌ MSG91 exception: ${e.message}")
        return@withContext false
    }
}
