package com.example.lab_week_11_a

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


class PreferenceWrapper(private val sharedPreferences: SharedPreferences) {

    // LiveData untuk memberi tahu ViewModel jika ada perubahan text
    private val textLiveData = MutableLiveData<String>()

    init {
        // Listener ketika shared preferences berubah
        sharedPreferences.registerOnSharedPreferenceChangeListener { _, key ->
            when (key) {
                KEY_TEXT -> {
                    // Update LiveData jika KEY_TEXT berubah
                    textLiveData.postValue(
                        sharedPreferences.getString(KEY_TEXT, "")
                    )
                }
            }
        }
    }

    // Menyimpan text ke shared preferences
    fun saveText(text: String) {
        sharedPreferences.edit()
            .putString(KEY_TEXT, text)
            .apply()
    }

    // Mengambil text dari shared preferences
    fun getText(): LiveData<String> {
        textLiveData.postValue(sharedPreferences.getString(KEY_TEXT, ""))
        return textLiveData
    }

    companion object {
        const val KEY_TEXT = "keyText"
    }
}
