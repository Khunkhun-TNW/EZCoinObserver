package com.example.ezcoinobserver.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.ezcoinobserver.R
import com.example.ezcoinobserver.boardModule
import com.example.ezcoinobserver.event.ObserveCoinModel
import com.example.ezcoinobserver.fragment.BoardFragment
import com.example.ezcoinobserver.fragment.HistoryFragment
import com.example.ezcoinobserver.model.ResponseCoinModel
import com.example.ezcoinobserver.viewmodel.CurrencyViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.KoinContextHandler
import org.koin.core.context.startKoin
import java.util.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(),CoroutineScope {
    private val currencyViewModel:CurrencyViewModel by viewModel()
    private var appBarConfiguration:AppBarConfiguration? = null

    private val boardFragment = BoardFragment()
    private val historyFragment = HistoryFragment()
    private val fragmentManager = supportFragmentManager
    private var activeFragment: Fragment = boardFragment
    private val lstCurrency = arrayListOf<ResponseCoinModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initKoin()
        startRepeatingCallAPI()
        initView()

        launch{
            currencyViewModel.coinPrice.observe(this@MainActivity) { response->
                lstCurrency.add(response)
                EventBus.getDefault().post(ObserveCoinModel(lstCurrency))
            }
        }
    }
    private fun startRepeatingCallAPI(){
            val timer = Timer()
            val minuteTask: TimerTask = object : TimerTask() {
                override fun run() {
                    getCoinInfo()
                }
            }
            timer.schedule(minuteTask, 0L, 1000*61)
    }


    private fun initView() {
            val navController = findNavController(R.id.nav_host_fragment_activity_main)
            appBarConfiguration = AppBarConfiguration.Builder(
                setOf(R.id.navigation_board, R.id.navigation_history
                )
            ).build()

            setupActionBarWithNavController(navController, appBarConfiguration!!)
            nav_view.setupWithNavController(navController)


        fragmentManager.beginTransaction().apply {
            add(R.id.container, boardFragment, getString(R.string.text_board_fragment))
            add(R.id.container, historyFragment, getString(R.string.text_history_fragment)).hide(historyFragment)
        }.commit()

        nav_view.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_board -> {
                    fragmentManager.beginTransaction().hide(activeFragment).show(boardFragment).commit()
                    activeFragment = boardFragment
                    true
                }
                R.id.navigation_history -> {
                    fragmentManager.beginTransaction().hide(activeFragment).show(historyFragment).commit()
                    activeFragment = historyFragment
                    true
                }
                else -> false
            }
        }
    }

    private fun initKoin(){
        startKoin{
            androidContext(this@MainActivity)
            modules(boardModule())
        }
    }

    private fun getCoinInfo(){
        currencyViewModel.getCoinPrice()
    }

    override fun onDestroy() {
        KoinContextHandler.stop()
        super.onDestroy()
    }
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

}