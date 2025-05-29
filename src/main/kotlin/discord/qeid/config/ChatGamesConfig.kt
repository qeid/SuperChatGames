package discord.qeid.config

import space.arim.dazzleconf.annote.ConfComments
import space.arim.dazzleconf.annote.ConfDefault
import space.arim.dazzleconf.annote.ConfKey
import space.arim.dazzleconf.sorter.AnnotationBasedSorter

interface ChatGamesConfig {

    // General options
    @ConfComments("Interval in minutes between automatic reactions")
    @ConfKey("automaticReactionsInterval")
    @AnnotationBasedSorter.Order(1)
    @ConfDefault.DefaultInteger(3)
    fun automaticReactionsInterval(): Int

    @ConfComments("Enable or disable automatic reactions")
    @ConfKey("automaticReactionsEnabled")
    @AnnotationBasedSorter.Order(2)
    @ConfDefault.DefaultBoolean(true)
    fun automaticReactionsEnabled(): Boolean

    // Options section
    @ConfComments("Options for math game and rewards")
    @ConfKey("options.mathMin")
    @AnnotationBasedSorter.Order(10)
    @ConfDefault.DefaultInteger(30)
    fun mathMin(): Int

    @ConfKey("options.mathMax")
    @AnnotationBasedSorter.Order(11)
    @ConfDefault.DefaultInteger(250)
    fun mathMax(): Int

    @ConfKey("options.reward")
    @AnnotationBasedSorter.Order(12)
    @ConfDefault.DefaultString("$150,000")
    fun reward(): String

    @ConfKey("options.reward-command")
    @AnnotationBasedSorter.Order(13)
    @ConfDefault.DefaultString("eco give %player% 150000")
    fun rewardCommand(): String

    // Reaction words
    @ConfComments("Words used for scramble/fastest games")
    @ConfKey("reaction.words")
    @AnnotationBasedSorter.Order(20)
    @ConfDefault.DefaultStrings(*arrayOf("example", "words", "here"))
    fun reactionWords(): List<String>

    // Messages section
    @ConfComments("All plugin messages")
    @ConfKey("messages.chat-game-title")
    @AnnotationBasedSorter.Order(30)
    @ConfDefault.DefaultString("&lCHAT GAME!")
    fun chatGameTitle(): String

    @ConfKey("messages.player-answer")
    @AnnotationBasedSorter.Order(31)
    @ConfDefault.DefaultString("&f%player% &fsaid <##e8de6f>%message% &fin <##e8de6f>%time% seconds!")
    fun playerAnswer(): String

    @ConfKey("messages.reward-message")
    @AnnotationBasedSorter.Order(32)
    @ConfDefault.DefaultString("&fReward: <##e8de6f>%reward%")
    fun rewardMessage(): String

    @ConfKey("messages.chat-game-no-answer")
    @AnnotationBasedSorter.Order(33)
    @ConfDefault.DefaultString("&cNo one answered in time. The reaction has ended.")
    fun chatGameNoAnswer(): String

    @ConfKey("messages.math-question")
    @AnnotationBasedSorter.Order(34)
    @ConfDefault.DefaultString("&fFirst to answer %num1% + %num2%")
    fun mathQuestion(): String

    @ConfKey("messages.scramble-question")
    @AnnotationBasedSorter.Order(35)
    @ConfDefault.DefaultString("&fUnscramble the following word: %word%")
    fun scrambleQuestion(): String

    @ConfKey("messages.fastest-question")
    @AnnotationBasedSorter.Order(36)
    @ConfDefault.DefaultString("&fFirst one to type: %word%")
    fun fastestQuestion(): String

    @ConfKey("messages.usage-message")
    @AnnotationBasedSorter.Order(37)
    @ConfDefault.DefaultString("&cUsage: /reaction <start|stop|add|remove|list|reload>")
    fun usageMessage(): String

    @ConfKey("messages.reaction-ended")
    @AnnotationBasedSorter.Order(38)
    @ConfDefault.DefaultString("&aYou have ended the chat reaction.")
    fun reactionEnded(): String

    @ConfKey("messages.no-reaction-active")
    @AnnotationBasedSorter.Order(39)
    @ConfDefault.DefaultString("&cThere is no reaction currently active")
    fun noReactionActive(): String

    @ConfKey("messages.reaction-already-active")
    @AnnotationBasedSorter.Order(40)
    @ConfDefault.DefaultString("&cReaction already active")
    fun reactionAlreadyActive(): String

    @ConfKey("messages.specify-reaction")
    @AnnotationBasedSorter.Order(41)
    @ConfDefault.DefaultString("&cSpecify the type of reaction to start.")
    fun specifyReaction(): String

    @ConfKey("messages.reaction-empty")
    @AnnotationBasedSorter.Order(42)
    @ConfDefault.DefaultString("&cThere are no reaction words")
    fun reactionEmpty(): String

    @ConfKey("messages.word-added")
    @AnnotationBasedSorter.Order(43)
    @ConfDefault.DefaultString("&aAdded word to reactions.")
    fun wordAdded(): String

    @ConfKey("messages.word-already-exists")
    @AnnotationBasedSorter.Order(44)
    @ConfDefault.DefaultString("&cWord is already in the list.")
    fun wordAlreadyExists(): String

    @ConfKey("messages.specify-word")
    @AnnotationBasedSorter.Order(45)
    @ConfDefault.DefaultString("&cSpecify the word to add.")
    fun specifyWord(): String

    @ConfKey("messages.word-removed")
    @AnnotationBasedSorter.Order(46)
    @ConfDefault.DefaultString("&aRemoved %word% from reactions.")
    fun wordRemoved(): String

    @ConfKey("messages.word-not-found")
    @AnnotationBasedSorter.Order(47)
    @ConfDefault.DefaultString("&c%word% is not in the list.")
    fun wordNotFound(): String

    @ConfKey("messages.reaction-words")
    @AnnotationBasedSorter.Order(48)
    @ConfDefault.DefaultString("&aReaction words: ")
    fun reactionWordsMessage(): String

    @ConfKey("messages.config-reloaded")
    @AnnotationBasedSorter.Order(49)
    @ConfDefault.DefaultString("&aReloaded config")
    fun configReloaded(): String

    @ConfKey("messages.unknown-command")
    @AnnotationBasedSorter.Order(50)
    @ConfDefault.DefaultString("&cUnknown command. Usage: /reaction <start|stop|add|remove|list|reload>")
    fun unknownCommand(): String
}
