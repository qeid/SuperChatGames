package discord.qeid.core

import org.bukkit.entity.Player

interface Game {
    val name: String
    fun start()
    fun checkAnswer(player: Player, message: String): Boolean
    fun end()
    fun isActive(): Boolean
}
