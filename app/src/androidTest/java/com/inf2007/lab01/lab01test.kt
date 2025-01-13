package com.inf2007.lab01

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class lab01test {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun mainScreenTest() {
        composeTestRule.setContent {
            MainScreen()
        }

        // Verify that the TextField exists
        composeTestRule.onNodeWithTag("UserInput").assertIsDisplayed()

        // Verify that the Submit button exists
        composeTestRule.onNodeWithTag("submitButton").assertIsDisplayed()

        // Initially, the greeting message should not exist
        composeTestRule.onNodeWithTag("greeting").assertDoesNotExist()
    }

    @Test
    fun testGreetingAppearsOnSubmit() {
        composeTestRule.setContent {
            MainScreen()
        }

        // Enter a name into the TextField
        composeTestRule.onNodeWithTag("UserInput")
            .performTextInput("John")

        // Click the Submit button
        composeTestRule.onNodeWithTag("submitButton")
            .performClick()

        // Verify the greeting message is displayed with the correct text
        composeTestRule.onNodeWithTag("greeting")
            .assertIsDisplayed()
            .assertTextEquals("Hello John!, Welcome to INF2007!")
    }

    @Test
    fun testGreetingDoesNotAppearForBlankInput() {
        composeTestRule.setContent {
            MainScreen()
        }

        // Click the Submit button without entering a name
        composeTestRule.onNodeWithTag("submitButton")
            .performClick()

        // Verify the greeting message does not exist
        composeTestRule.onNodeWithTag("greeting").assertDoesNotExist()
    }

    @Test
    fun testGreetingUpdatesWithNewInput() {
        composeTestRule.setContent {
            MainScreen()
        }

        // Enter the first name and click Submit
        composeTestRule.onNodeWithTag("UserInput")
            .performTextInput("Alice")
        composeTestRule.onNodeWithTag("submitButton")
            .performClick()

        // Verify the greeting for "Alice"
        composeTestRule.onNodeWithTag("greeting")
            .assertTextEquals("Hello Alice!, Welcome to INF2007!")

        // Enter a new name and click Submit
        composeTestRule.onNodeWithTag("UserInput")
            .performTextClearance()
        composeTestRule.onNodeWithTag("UserInput")
            .performTextInput("Bob")
        composeTestRule.onNodeWithTag("submitButton")
            .performClick()

        // Verify the greeting for "Bob"
        composeTestRule.onNodeWithTag("greeting")
            .assertTextEquals("Hello Bob!, Welcome to INF2007!")
    }
}