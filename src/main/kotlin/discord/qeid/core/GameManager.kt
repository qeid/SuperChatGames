package discord.qeid.core

import org.bukkit.Bukkit

class GameManager {
    var currentGame: Game? = null
        private set

    fun startGame(game: Game) {
        currentGame?.end()
        currentGame = game
        game.start()
    }

    fun endGame() {
        currentGame?.end()
        currentGame = null
    }

    fun isGameActive(): Boolean = currentGame?.isActive() == true

    fun checkAnswer(player: org.bukkit.entity.Player, message: String): Boolean {
        return currentGame?.checkAnswer(player, message) ?: false
    }
}
