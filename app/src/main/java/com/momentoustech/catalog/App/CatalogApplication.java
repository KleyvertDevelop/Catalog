package com.momentoustech.catalog.App;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.momentoustech.catalog.di.components.AppComponent;
import com.momentoustech.catalog.di.components.DaggerAppComponent;
import com.momentoustech.catalog.di.modules.AppModule;

/**
 * Created by Kleyvert on 10/3/16.
 */
public class CatalogApplication extends Application {

    private AppComponent component;

    @Override public void onCreate() {
        super.onCreate();
        setupGraph();
    }

    /**
     * setting the component
     */
    private void setupGraph() {
        this.component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    /**
     * Getting the component
     * @return {@link AppComponent}
     */
    public AppComponent getAppComponent() {
        return component;
    }

    protected void attachBaseContext(Context base)
    {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
