package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.model.FoodCatalog
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Jayasanka Foods", appName)
  }

  @Test
  fun `catalog contains products`() {
    assertTrue(FoodCatalog.items.isNotEmpty())
    val butterCake = FoodCatalog.findById("cake_butter_1kg")
    assertEquals(1200.0, butterCake?.basePrice)
  }
}
