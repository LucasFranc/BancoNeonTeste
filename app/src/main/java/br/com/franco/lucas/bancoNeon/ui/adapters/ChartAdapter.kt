package br.com.franco.lucas.bancoNeon.ui.adapters

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import br.com.franco.lucas.bancoNeon.R
import br.com.franco.lucas.bancoNeon.model.MoneySum
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_view_graph.view.*
import java.util.ArrayList

/**
 * Created by lucas on 2/27/18.
 */
class ChartAdapter(private var activity: Activity,
                   private var moneySums: ArrayList<MoneySum>) : RecyclerView.Adapter<ChartAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(activity).inflate(R.layout.item_view_graph, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val finalPosition = holder!!.adapterPosition
        holder.progress.visibility = View.VISIBLE
        holder.txtMoneySum.text = formatSumValue(moneySums[finalPosition].moneySum.toString())
        val params = LinearLayout.LayoutParams(8,
                formatHeight((moneySums[finalPosition].moneySum)))
        params.gravity = Gravity.BOTTOM
        holder.stripeView.layoutParams = params
        Picasso.with(activity).load(moneySums[finalPosition].avatar).into(holder.imgAvatar,object : Callback {
            override fun onSuccess() { holder.progress.visibility = View.GONE }
            override fun onError() { holder.progress.visibility = View.GONE }
        })
    }

    private fun formatHeight(actualValue: Double): Int {
        val maxValue = (moneySums[0].moneySum)
        val realPhoneHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 130F, activity.getResources().getDisplayMetrics())
        if(actualValue.toInt() == maxValue.toInt()){
            return realPhoneHeight.toInt()
        }else{
            val percentage = actualValue.div(maxValue)
            return (realPhoneHeight * percentage).toInt()
        }
    }

    override fun getItemCount(): Int {
        return moneySums.size
    }

    private fun formatSumValue(sum: String): String {
        val changed = sum.replace(".", ",")
        val indexOfComma = changed.indexOf(',')
        if (changed.substring(indexOfComma + 1, changed.length).length == 1)
            return changed
        else
            return changed.substring(0, indexOfComma + 3)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtMoneySum = itemView.txt_money_sum
        val stripeView = itemView.stripe
        val imgAvatar = itemView.avatar
        val progress = itemView.progress
    }
}