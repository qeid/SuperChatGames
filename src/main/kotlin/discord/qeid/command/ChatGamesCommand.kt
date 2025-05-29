package discord.qeid.command

import discord.qeid.config.ConfigManager
import discord.qeid.core.GameManager
import discord.qeid.core.GameType
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class ChatGamesCommand(
    private val plugin: JavaPlugin,
    private val configManager: ConfigManager,
    private val gameManager: GameManager
) : CommandExecutor {

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        val config = configManager.getConfig()

        if (args.isEmpty()) {
            sender.sendMessage(config.usageMessage())
            return true
        }

        when (args[0].lowercase()) {
            "start" -> {
                if (args.size < 2) {
                    sender.sendMessage(config.specifyReaction())
                    return true
                }
                if (gameManager.isGameActive()) {
                    sender.sendMessage(config.reactionAlreadyActive())
                    return true
                }
                val type = args[1].lowercase()
                val game = when (type) {
                    "math" -> /* math inst */
                        null
                    "scramble" -> /* scramble inst */
                        null
                    "fastest" -> /* fastest inst */
                        null
                    else -> null
                }
                if (game == null) {
                    sender.sendMessage(config.unknownCommand())
                    return true
                }
                gameManager.startGame(game)
                sender.sendMessage("&aStarted $type game!")
            }
            "stop" -> {
                if (!gameManager.isGameActive()) {
                    sender.sendMessage(config.noReactionActive())
                    return true
                }
                gameManager.endGame()
                sender.sendMessage(config.reactionEnded())
            }
            "add" -> {
                if (args.size < 2) {
                    sender.sendMessage(config.specifyWord())
                    return true
                }
                val word = args.drop(1).joinToString(" ")
                val words = config.reactionWords().toMutableList()
                if (words.contains(word)) {
                    sender.sendMessage(config.wordAlreadyExists())
                } else {
                    words.add(word)
                    // Save back to config (you'll need to implement this)
                    sender.sendMessage(config.wordAdded())
                }
            }
            "remove" -> {
                if (args.size < 2) {
                    sender.sendMessage(config.specifyWord())
                    return true
                }
                val word = args.drop(1).joinToString(" ")
                val words = config.reactionWords().toMutableList()
                if (words.contains(word)) {
                    words.remove(word)
                    // Save back to config (you'll need to implement this)
                    sender.sendMessage(config.wordRemoved().replace("%word%", word))
                } else {
                    sender.sendMessage(config.wordNotFound().replace("%word%", word))
                }
            }
            "list" -> {
                val words = config.reactionWords()
                sender.sendMessage(config.reactionWordsMessage() + words.joinToString(", "))
            }
            "reload" -> {
                configManager.reload()
                sender.sendMessage(config.configReloaded())
            }
            else -> {
                sender.sendMessage(config.unknownCommand())
            }
        }
        return true
    }
}
