package com.example.myapplication

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myapplication.ui.MainFragment
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationTest {

    @Test
    fun navigation_main_fragment_to_new_element_fragment() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        val mainFragment =
            launchFragmentInContainer<MainFragment>(themeResId = R.style.Theme_MyApplication)

        mainFragment.onFragment { fragment ->
            navController.setGraph(R.navigation.navigation)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        onView(withId(R.id.menu_fab))
            .perform(click())

        onView(withId(R.id.add))
            .perform(click())

        assertEquals(navController.currentDestination?.id, R.id.newElementFragment)
    }

}