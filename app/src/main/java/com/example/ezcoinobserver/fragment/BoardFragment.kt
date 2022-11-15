package com.example.ezcoinobserver.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ezcoinobserver.R
import com.example.ezcoinobserver.adapter.BoardAdapter
import com.example.ezcoinobserver.event.ObserveCoinModel
import com.example.ezcoinobserver.model.ResponseCoinModel
import com.example.ezcoinobserver.widget.DecimalDigitsInputFilter
import kotlinx.android.synthetic.main.fragment_board_bpi.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.lang.Float.parseFloat

class BoardFragment : BaseFragment(),View.OnClickListener {
    private var boardAdapter:BoardAdapter? = null
    private lateinit var responseCoinModel:ResponseCoinModel

    override fun layoutId(): Int {
        return R.layout.fragment_board_bpi
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeComponent()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun observeResponseData(event: ObserveCoinModel) {
        updateView(event.responseCoinModel)
        responseCoinModel = event.responseCoinModel[0]
    }

    private fun updateView(response: ArrayList<ResponseCoinModel>) {
        if (response.isNotEmpty()) {
            boardAdapter?.setInitialData(response[response.size - 1])
            boardAdapter?.notifyDataSetChanged()
        }
    }

    private fun initializeComponent(){
        boardAdapter = BoardAdapter()
    }

    private fun initView() {
        ivDelete.setOnClickListener(this)
        btnUSD.setOnClickListener(this)
        btnGBP.setOnClickListener(this)
        btnEUR.setOnClickListener(this)

        rvBoardBpi.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
        rvBoardBpi.adapter = boardAdapter

        etInputAmount.filters = arrayOf(DecimalDigitsInputFilter(14,2))
    }

    override fun onClick(view: View?) {
        when(view){
            ivDelete->{
                etInputAmount.setText("")
                tvShowResultCalculating.text = ""
                tvEnding.visibility = View.GONE
                tvApproximatelySign.visibility = View.GONE
            }
            btnUSD->{
                if (etInputAmount.text.toString().isEmpty()) return
                val resultValue:Float = parseFloat(etInputAmount.text.toString()) / responseCoinModel.bpi.usd.rateFloat
                tvShowResultCalculating.text =  String.format("%.8f",resultValue)
                tvEnding.visibility = View.VISIBLE
                tvApproximatelySign.visibility = View.VISIBLE
            }
            btnGBP->{
                if (etInputAmount.text.toString().isEmpty()) return
                val resultValue:Float = parseFloat(etInputAmount.text.toString()) / responseCoinModel.bpi.gbp.rateFloat
                tvShowResultCalculating.text =  String.format("%.8f",resultValue)
                tvEnding.visibility = View.VISIBLE
                tvApproximatelySign.visibility = View.VISIBLE
            }
            btnEUR->{
                if (etInputAmount.text.toString().isEmpty()) return
                val resultValue:Float = parseFloat(etInputAmount.text.toString()) / responseCoinModel.bpi.eur.rateFloat
                tvShowResultCalculating.text =  String.format("%.8f",resultValue)
                tvEnding.visibility = View.VISIBLE
                tvApproximatelySign.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        fun newInstance(): BoardFragment {
            return BoardFragment()
        }
    }
}