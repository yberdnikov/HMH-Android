import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

fun PluginDependenciesSpec.hmh(pluginName: String): PluginDependencySpec {
    return id("com.hmh.hamyeonham.$pluginName")
}
