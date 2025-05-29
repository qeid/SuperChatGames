package discord.qeid.command

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class ChatGamesTabCompleter : TabCompleter {
    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>
    ): List<String>? {
        return when (args.size) {
            1 -> listOf("start", "stop", "add", "remove", "list", "reload")
                .filter { it.startsWith(args[0], ignoreCase = true) }
            2 -> if (args[0].equals("start", ignoreCase = true)) {
                listOf("math", "scramble", "fastest")
                    .filter { it.startsWith(args[1], ignoreCase = true) }
            } else null
            else -> null
        }
    }
}
