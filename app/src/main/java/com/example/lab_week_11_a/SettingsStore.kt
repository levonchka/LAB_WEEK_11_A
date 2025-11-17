package com.example.lab_week_11_a

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// DataStore instance (extension property)
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settingsStore")

class SettingsStore(private val context: Context) {

    // Flow yang memantau perubahan KEY_TEXT
    val text: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[KEY_TEXT] ?: ""
        }

    // Menyimpan text ke DataStore
    suspend fun saveText(text: String) {
        context.dataStore.edit { preferences ->
            preferences[KEY_TEXT] = text
        }
    }

    companion object {
        val KEY_TEXT = stringPreferencesKey("key_text")
    }
}
