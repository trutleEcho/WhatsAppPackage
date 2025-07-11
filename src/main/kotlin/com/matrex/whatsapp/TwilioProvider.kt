package com.matrex.whatsapp

import kotlinx.serialization.json.JsonElement

/**
 * Placeholder function to send messages via Twilio.
 */
suspend fun sendViaTwilio(
    requestBody: Map<String, JsonElement>,
    authKey: String
): Boolean {
    println("ℹ️ Twilio support not implemented yet.")
    return false
}
