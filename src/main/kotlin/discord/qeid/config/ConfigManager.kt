package discord.qeid.config

import space.arim.dazzleconf.ConfigurationFactory
import space.arim.dazzleconf.ConfigurationOptions
import space.arim.dazzleconf.error.ConfigFormatSyntaxException
import space.arim.dazzleconf.error.InvalidConfigException
import space.arim.dazzleconf.ext.snakeyaml.CommentMode
import space.arim.dazzleconf.ext.snakeyaml.SnakeYamlConfigurationFactory
import space.arim.dazzleconf.ext.snakeyaml.SnakeYamlOptions
import space.arim.dazzleconf.helper.ConfigurationHelper
import space.arim.dazzleconf.sorter.AnnotationBasedSorter
import java.io.File
import java.io.IOException
import java.util.logging.Logger

class ConfigManager(pluginFolder: File) {

    private val logger = Logger.getLogger("SuperChatGames ConfigManager")
    private val configHelper: ConfigurationHelper<ChatGamesConfig> =
        createHelper(ChatGamesConfig::class.java, File(pluginFolder, "config.yml"))

    var config: ChatGamesConfig? = null
        private set

    companion object {
        private fun <T> createHelper(configClass: Class<T>, file: File): ConfigurationHelper<T> {
            val yamlOptions = SnakeYamlOptions.Builder()
                .commentMode(CommentMode.fullComments())
                .build()
            val optionBuilder = ConfigurationOptions.Builder()
            optionBuilder.sorter(AnnotationBasedSorter())
            val configFactory: ConfigurationFactory<T> =
                SnakeYamlConfigurationFactory.create(configClass, optionBuilder.build(), yamlOptions)
            return ConfigurationHelper(file.parentFile.toPath(), file.name, configFactory)
        }
    }

    fun reload() {
        try {
            config = configHelper.reloadConfigData()
        } catch (e: IOException) {
            logger.severe("Couldn't open config file!")
            e.printStackTrace()
        } catch (e: ConfigFormatSyntaxException) {
            logger.severe("Invalid config syntax!")
            e.printStackTrace()
        } catch (e: InvalidConfigException) {
            logger.severe("Invalid config value!")
            e.printStackTrace()
        }
    }

    fun getConfig(): ChatGamesConfig {
        return config ?: throw IllegalStateException("Config not loaded yet!")
    }
}
