package de.renebaeumer.test.steps

import android.app.Activity
import androidx.test.rule.ActivityTestRule
import cucumber.api.Scenario
import cucumber.api.java8.En
import de.renebaeumer.test.espresso.LoginScreenRobot
import de.renebaeumer.ui.login.LoginActivity
import org.junit.Assert


class LoginDetailsSteps : En {
    private val robot = LoginScreenRobot()
    private val activityRule = ActivityTestRule(LoginActivity::class.java, false, false)

    init {
        Before{ scenario -> robot.launchLoginScreen(activityRule) }

        After{ scenario: Scenario ->
            getActivity().finish()
        }

        Given("I have an activity") {
            Assert.assertNotNull(getActivity())
        }

        When("I click email field") {
            robot.selectEmailField()
        }

        And("I close the keyboard") {
            robot.closeKeyboard()
        }

        And("I enter valid email (\\S+)") { email: String ->
            robot.enterEmail(email)
        }

        And("I click password field") {
            robot.selectPasswordField()
        }

        And("I enter valid password (\\S+)") { password: String ->

            robot.enterPassword(password)
        }

        And("I click sign in button") {
            robot.clickSignInButton()
        }

        Then("I expect to see successful login message") {
            robot.isSuccessfulLogin()
        }

    }


    fun getActivity(): Activity {
        return activityRule.activity
    }

}