package com.samir.testapptexis

import android.R
import android.widget.Button
import android.widget.EditText
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.samir.testapptexis.ui.LoginActivity
import org.junit.*
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @Rule
    var mActivityTestRule: ActivityTestRule<LoginActivity> = ActivityTestRule<LoginActivity>(LoginActivity::class.java)

    var loginActivity: LoginActivity? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        loginActivity = mActivityTestRule.getActivity()
    }

    @Test
    fun testLogin() {
        InstrumentationRegistry.getInstrumentation().runOnMainSync(Runnable {
            val username = loginActivity?.binding?.username
            val pass = loginActivity?.binding?.password
            username?.setText("morty")
            pass?.setText("smith")
            Assert.assertTrue(username?.text?.toString() == "morty")
            Assert.assertTrue(pass?.text?.toString() == "smith")
        })
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        loginActivity = null
    }
}
