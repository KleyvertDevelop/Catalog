package com.momentoustech.catalog.di.modules;

import android.app.Application;
import android.content.Context;

import com.momentoustech.catalog.App.CatalogApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Kleyvert on 10/3/16.
 */
@Module
public class AppModule {

    private CatalogApplication app;

    public AppModule(CatalogApplication app) {
        this.app = app;
    }

    // Application
    @Provides
    @Singleton
    public Application provideApplication() {
        return app;
    }

    // Application context
    @Provides @Singleton
    public Context provideApplicationContext() {
        return app;
    }
}
