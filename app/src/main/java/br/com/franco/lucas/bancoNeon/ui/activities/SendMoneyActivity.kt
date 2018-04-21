package br.com.franco.lucas.bancoNeon.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import br.com.franco.lucas.bancoNeon.utils.Constants
import br.com.franco.lucas.bancoNeon.R
import br.com.franco.lucas.bancoNeon.ui.PeopleFragment
import br.com.franco.lucas.bancoNeon.utils.TransactionManager

/**
 * Created by lucas on 2/24/18.
 */

class SendMoneyActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_money)
        bindToolbar()
        bindFragment()
    }

    private fun bindFragment() {
        val fragment = PeopleFragment()
        val b = Bundle()
        b.putInt(Constants.TYPE,Constants.TYPE_SEND_MONEY)
        fragment.arguments = b
        TransactionManager().fragmentsTransaction(R.id.fragment, this, fragment,
                Constants.PEOPLE_FRAGMENT_TAG, false)
    }

    private fun bindToolbar() {
        findViewById<AppCompatTextView>(R.id.toolbar_title).text = resources.getText(R.string.send_money)
        findViewById<AppCompatImageView>(R.id.back_arrow).setOnClickListener({finish()})
    }

}
