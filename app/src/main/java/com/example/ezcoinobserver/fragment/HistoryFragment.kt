package com.example.ezcoinobserver.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ezcoinobserver.R
import com.example.ezcoinobserver.adapter.HistoryAdapter
import com.example.ezcoinobserver.event.ObserveCoinModel
import com.example.ezcoinobserver.model.ResponseCoinModel
import kotlinx.android.synthetic.main.fragment_history_price.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class HistoryFragment : BaseFragment() {
    private var historyAdapter:HistoryAdapter? = null

    override fun layoutId(): Int {
        return R.layout.fragment_history_price
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            initializeComponent()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager =  LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, true)
        layoutManager.stackFromEnd = true
        rvHistory.layoutManager = layoutManager
        rvHistory.adapter = historyAdapter
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun observeResponseData(event: ObserveCoinModel) {
        updateView(event.responseCoinModel)
    }

    private fun initializeComponent(){
        historyAdapter = HistoryAdapter()
    }

    private fun updateView(response: ArrayList<ResponseCoinModel>)
    {
        historyAdapter?.setInitialData(response)
        historyAdapter?.notifyDataSetChanged()
    }

    companion object {
        fun newInstance(): HistoryFragment {
            return HistoryFragment()
        }
    }
}