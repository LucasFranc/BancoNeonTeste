package br.com.franco.lucas.bancoNeon.ui.adapters

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.franco.lucas.bancoNeon.R
import br.com.franco.lucas.bancoNeon.callbacks.OnClickCallback
import br.com.franco.lucas.bancoNeon.model.Person
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_view_person.view.*
import kotlin.collections.ArrayList

/**
 * Created by lucas on 2/25/18.
 */
class SendMoneyAdapter(private var activity: Activity,
                       private var people: ArrayList<Person>,
                       private var callback: OnClickCallback) : RecyclerView.Adapter<SendMoneyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(activity).inflate(R.layout.item_view_person, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val finalPosition = holder!!.adapterPosition
        holder.progress.visibility = View.VISIBLE
        holder.txtName.text = people[finalPosition].name
        holder.txtPhone.text = people[finalPosition].phone
        holder.container.setOnClickListener({ callback.onClick(people[finalPosition]) })
        Picasso.with(activity).load(people[finalPosition].avatar).into(holder.imgAvatar,object : Callback{
            override fun onSuccess() { holder.progress.visibility = View.GONE }
            override fun onError() { holder.progress.visibility = View.GONE }
        })
    }

    override fun getItemCount(): Int {
        return people.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val container = itemView
        val txtName = itemView.person_name
        val txtPhone = itemView.person_contact
        val imgAvatar = itemView.avatar
        val progress = itemView.progress_avatar_person
    }
}