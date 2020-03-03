package de.renebaeumer.test.runner

import android.os.Bundle
import cucumber.api.CucumberOptions
import cucumber.api.android.CucumberAndroidJUnitRunner
import java.io.File

@CucumberOptions(
        features = ["features"],
        glue = ["de.renebaeumer.test"]
)
open class CucumberTestRunner : CucumberAndroidJUnitRunner() {

    override fun onCreate(bundle: Bundle) {
//        val tags = BuildConfig.TEST_TAGS
//        if (!tags.isEmpty()) {
//            bundle.putString(CUCUMBER_TAGS_KEY, tags.replace("\\s".toRegex(), ""))
//        }
//        var scenario = BuildConfig.TEST_SCENARIO
//        if (!scenario.isEmpty()) {
//            scenario = scenario.replace(" ".toRegex(), "\\\\s")
//            bundle.putString(CUCUMBER_SCENARIO_KEY, scenario)
//        }
        bundle.putString("plugin", getPluginConfigurationString()) // we programmatically create the plugin configuration

        super.onCreate(bundle)
    }

//    override fun onStart() {
////        waitForIdleSync()
//
//    }

    /**
     * Since we want to checkout the external storage directory programmatically, we create the plugin configuration
     * here, instead of the [CucumberOptions] annotation.
     *
     * @return the plugin string for the configuration, which contains XML, HTML and JSON paths
     */
    private fun getPluginConfigurationString(): String {
        val cucumber = "cucumber"
        val separator = "--"
        return "junit:" + getAbsoluteFilesPath() + "/" + cucumber + ".xml" + separator +
                "html:" + getAbsoluteFilesPath() + "/" + cucumber + ".html"
    }

    /**
     * The path which is used for the report files.
     *
     * @return the absolute path for the report files
     */
    private fun getAbsoluteFilesPath(): String {

        //sdcard/Android/data/cucumber.cukeulator
        val directory = targetContext.getExternalFilesDir(null)
        return File(directory, "reports").getAbsolutePath()
    }

    companion object {
        private val CUCUMBER_TAGS_KEY = "tags"
        private val CUCUMBER_SCENARIO_KEY = "name"
    }
}