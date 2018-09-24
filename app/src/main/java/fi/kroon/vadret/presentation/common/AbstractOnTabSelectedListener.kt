package fi.kroon.vadret.presentation.common

import com.google.android.material.tabs.TabLayout

abstract class AbstractOnTabSelectedListener : TabLayout.OnTabSelectedListener {
    override fun onTabReselected(tab: TabLayout.Tab?) {}

    override fun onTabUnselected(tab: TabLayout.Tab?) {}

    override fun onTabSelected(tab: TabLayout.Tab?) {}
}