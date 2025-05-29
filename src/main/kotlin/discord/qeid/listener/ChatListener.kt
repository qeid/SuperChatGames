package discord.qeid.listener

import discord.qeid.core.GameManager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class ChatListener(
    private val gameManager: GameManager,
    private val plugin: JavaPlugin

) : Listener {

    @EventHandler
    fun onPlayerChat(event: AsyncPlayerChatEvent) {
        if (gameManager.isGameActive()) {
            Bukkit.getScheduler().runTask(plugin, Runnable {
                val player = event.player
                val message = event.message
                val correct = gameManager.checkAnswer(player, message)
                if (correct) {
                    event.isCancelled = true
                }
            })
        }
    }
}
