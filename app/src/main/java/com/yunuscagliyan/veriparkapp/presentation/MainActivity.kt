package com.yunuscagliyan.veriparkapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.yunuscagliyan.veriparkapp.R
import com.yunuscagliyan.veriparkapp.data.remote.model.request.stock.StockPeriodRequest
import com.yunuscagliyan.veriparkapp.databinding.ActivityMainBinding
import com.yunuscagliyan.veriparkapp.domain.event.NavigateLoginEvent
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private var navController: NavController? = null
    private val viewModel:MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        initUI()
    }

    private fun initUI() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        this.navController = navHostFragment.navController

        binding?.navigationDrawer?.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_period_all->{
                    viewModel.changePeriod(StockPeriodRequest.All)
                }
                R.id.menu_period_increasing->{
                    viewModel.changePeriod(StockPeriodRequest.Increasing)
                }
                R.id.menu_period_decreasing->{
                    viewModel.changePeriod(StockPeriodRequest.Decreasing)
                }
                R.id.menu_period_volume_30->{
                    viewModel.changePeriod(StockPeriodRequest.Volume30)
                }
                R.id.menu_period_volume_50->{
                    viewModel.changePeriod(StockPeriodRequest.Volume50)
                }
                R.id.menu_period_volume_100->{
                    viewModel.changePeriod(StockPeriodRequest.Volume100)
                }
                else->{

                }
            }
            true
        }

    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    fun setUpToolbar(toolbar: Toolbar) {
        val appBarConfiguration = AppBarConfiguration.Builder(
            setOf<Int>(
                R.id.home_destination
            )
        ).setOpenableLayout(binding?.navigationLayout).build()
        setSupportActionBar(toolbar)
        navController?.let {
            NavigationUI.setupWithNavController(toolbar, it, appBarConfiguration)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun navigateLogin(event: NavigateLoginEvent) {
        navController?.navigate(R.id.action_global_login_destination)
    }
}