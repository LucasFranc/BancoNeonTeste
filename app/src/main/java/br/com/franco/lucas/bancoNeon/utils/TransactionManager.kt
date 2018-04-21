package br.com.franco.lucas.bancoNeon.utils

import android.app.Activity
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

/**
 * Created by lucas on 2/24/18.
 */
class TransactionManager {

    fun activityTransaction(activity : Activity, target : Class<*>){
        activity.startActivity(Intent(activity,target))
    }

    fun fragmentsTransaction(container: Int, activity: AppCompatActivity, fragment: Fragment, TAG: String, addToBackStack: Boolean) {
        val manager = activity.supportFragmentManager
        val actualFragment = manager.findFragmentByTag(TAG)
        if (actualFragment == null || !actualFragment.isVisible) {
            val transaction = manager.beginTransaction()
            transaction.replace(container, fragment, TAG)
            if (addToBackStack) transaction.addToBackStack(TAG)
            transaction.commit()
        }
    }

}