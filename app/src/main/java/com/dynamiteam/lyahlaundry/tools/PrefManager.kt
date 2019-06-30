package com.dynamiteam.lyahlaundry.tools

object PrefManager {


    private val preferences = App.prefs

    fun saveLoginSession(phone: String) {
        /*  preferences.edit().putString(PREF_USER_PHONE, phone).putString(PREF_TOKEN, loginResponse.content?.token)
              .putString(PREF_REFRESH_TOKEN, loginResponse.content?.refreshToken).apply()*/
    }

    fun clearSession() {
        preferences.edit().clear().apply()
    }

    var isFirstOpen: Boolean
        get() = preferences.getBoolean(PREF_FIRST_OPEN, true)
        set(value) {
            preferences.edit().putBoolean(PREF_FIRST_OPEN, value).apply()
        }

    var userId: String
        get() = preferences.getString(
            PREF_USER_ID, EMPTY_STRING
        ) ?: EMPTY_STRING
        set(value) {
            preferences.edit().putString(PREF_USER_ID, value).apply()
        }

    var userName: String
        get() = preferences.getString(
            PREF_USER_NAME, EMPTY_STRING
        ) ?: EMPTY_STRING
        set(value) {
            preferences.edit().putString(PREF_USER_NAME, value).apply()
        }

    var userEmail: String
        get() = preferences.getString(
            PREF_USER_EMAIL, EMPTY_STRING
        ) ?: EMPTY_STRING
        set(value) {
            preferences.edit().putString(PREF_USER_EMAIL, value).apply()
        }

    var userPhone: String
        get() = preferences.getString(
            PREF_USER_PHONE, EMPTY_STRING
        ) ?: EMPTY_STRING
        set(value) {
            preferences.edit().putString(PREF_USER_PHONE, value).apply()
        }

    var userImage: String
        get() = preferences.getString(
            PREF_USER_IMAGE, EMPTY_STRING
        ) ?: EMPTY_STRING
        set(value) {
            preferences.edit().putString(PREF_USER_IMAGE, value).apply()
        }

}
