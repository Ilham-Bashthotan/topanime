package com.example.top_anime

import android.app.Application
import com.example.top_anime.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TopAnimeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        
        startKoin {
            androidLogger()
            androidContext(this@TopAnimeApplication)
            modules(appModules)
        }
    }
}
