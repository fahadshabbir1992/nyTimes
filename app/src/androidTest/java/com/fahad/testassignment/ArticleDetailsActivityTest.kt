package com.fahad.testassignment

import android.view.View
import androidx.test.rule.ActivityTestRule
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class ArticleDetailsActivityTest {

    @Rule
    @JvmField
    public val mActivityRule = ActivityTestRule(ArticleDetailsActivity::class.java)
    public  var mActivity:ArticleDetailsActivity? = null
    @Before
    public fun setUp() {
        mActivity=mActivityRule.activity
    }

    @Test
   public fun testLaunch(){
        var view: View =mActivity!!.findViewById(R.id.title1)
        assertNotNull(view)
    }

    @After
   public fun tearDown() {
        mActivity=null
    }
}