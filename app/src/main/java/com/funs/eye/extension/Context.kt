package com.funs.eye.extension

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.funs.eye.FunsEyeApplication

/**
 * 获取DataStore实例
 *
 */

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = FunsEyeApplication.context.packageName + "_preferences",
    produceMigrations = { context ->
        listOf(
            SharedPreferencesMigration(
                context,
                FunsEyeApplication.context.packageName + "_preferences"
            )
        )
    })