package com.matrex.whatsapp

import kotlinx.serialization.json.JsonElement

/**
 * Main interface to send WhatsApp messages.
 * Internally delegates to appropriate provider implementation.
 */
class WhatsAppService(
    private val authKey: String,
    private val serviceProvider: ServiceProvider = ServiceProvider.MESSAGE91
) {

    /**
     * Sends a WhatsApp message by delegating to the appropriate provider.
     */
    suspend fun send(requestBody: Map<String, JsonElement>): Boolean {
        return when (serviceProvider) {
            ServiceProvider.MESSAGE91 -> sendViaMsg91(requestBody, authKey)
            ServiceProvider.TWILIO -> sendViaTwilio(requestBody, authKey)
        }
    }
}

/**
 * Enum of supported providers.
 */
enum class ServiceProvider {
    MESSAGE91,
    TWILIO
}
