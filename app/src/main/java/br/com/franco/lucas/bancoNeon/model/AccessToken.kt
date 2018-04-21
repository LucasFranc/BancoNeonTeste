package br.com.franco.lucas.bancoNeon.model

import android.app.Activity
import android.content.Context

import br.com.franco.lucas.bancoNeon.utils.Constants

/**
 * Created by lucas on 2/23/18.
 */

class AccessToken {

            fun saveToken(activity : Activity, token: String) {
                val editor = activity.getSharedPreferences(Constants.ACCESS_TOKEN_SHARED_PREFERENCES,
                        Context.MODE_PRIVATE).edit()
                editor.clear()
                editor.putString(Constants.ACCESS_TOKEN, token)
                editor.apply()
            }

            fun loadToken(activity : Activity): String {
                val preferences = activity.getSharedPreferences(Constants.ACCESS_TOKEN_SHARED_PREFERENCES,
                        Context.MODE_PRIVATE)
                return preferences.getString(Constants.ACCESS_TOKEN, null)
            }

            fun clearToken(activity : Activity) {
                activity.getSharedPreferences(Constants.ACCESS_TOKEN_SHARED_PREFERENCES, Context.MODE_PRIVATE)
                        .edit().clear().apply()
            }
        }
