package discord.qeid;

import discord.qeid.command.ChatGamesCommand
import discord.qeid.command.ChatGamesTabCompleter
import discord.qeid.config.ConfigManager
import discord.qeid.core.GameManager
import discord.qeid.listener.ChatListener
import org.bukkit.plugin.java.JavaPlugin

class SuperChatGames : JavaPlugin() {

    lateinit var configManager: ConfigManager
        private set

    override fun onEnable() {

        configManager = ConfigManager(dataFolder)
        configManager.reload()

        val gameManager = GameManager()
        server.pluginManager.registerEvents(ChatListener(gameManager, this), this)


        getCommand("chatgames")?.setExecutor(ChatGamesCommand(this, configManager, gameManager))
        getCommand("chatgames")?.tabCompleter = ChatGamesTabCompleter()

        val config = configManager.getConfig()
        logger.info("Loaded config: interval = ${config.automaticReactionsInterval()}")

    }


    override fun onDisable() {
        // Plugin shutdown logic
    }
}
