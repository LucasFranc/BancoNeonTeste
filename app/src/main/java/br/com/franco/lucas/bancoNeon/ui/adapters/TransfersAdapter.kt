package br.com.franco.lucas.bancoNeon.ui.adapters

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.franco.lucas.bancoNeon.R
import br.com.franco.lucas.bancoNeon.model.Transfer
import br.com.franco.lucas.bancoNeon.utils.Constants
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_view_person.view.*

/**
 * Created by lucas on 2/26/18.
 */
class TransfersAdapter(private var activity: Activity,
                       private var transfers: ArrayList<Transfer>) : RecyclerView.Adapter<TransfersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(activity).inflate(R.layout.item_view_person, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val finalPosition = holder!!.adapterPosition
        holder.progress.visibility = View.VISIBLE
        holder.txtName.text = transfers[finalPosition].person.name
        holder.txtPhone.text = transfers[finalPosition].person.phone
        holder.txtMoney.visibility = View.VISIBLE
        holder.txtMoney.text = Constants.REAIS + transfers[finalPosition].Valor.toString()
        Picasso.with(activity).load(transfers[finalPosition].person.avatar).into(holder.imgAvatar,object : Callback {
            override fun onSuccess() { holder.progress.visibility = View.GONE }
            override fun onError() { holder.progress.visibility = View.VISIBLE }
        })
    }

    override fun getItemCount(): Int {
        return transfers.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName = itemView.person_name
        val txtPhone = itemView.person_contact
        val txtMoney = itemView.person_money
        val imgAvatar = itemView.avatar
        val progress = itemView.progress_avatar_person
    }
}