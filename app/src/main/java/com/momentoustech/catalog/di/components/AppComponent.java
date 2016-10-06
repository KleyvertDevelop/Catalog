package com.momentoustech.catalog.di.components;

import com.momentoustech.catalog.di.modules.AppModule;
import com.momentoustech.catalog.domain.usecase.GetCatalog;
import com.momentoustech.catalog.view.activity.CatalogActivity;
import com.momentoustech.catalog.view.activity.CatalogDetailActivity;
import com.momentoustech.catalog.view.presenter.CatalogPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Kleyvert on 10/3/16.
 */
@Singleton
@Component(
        modules = {
                AppModule.class
        }
)
public interface AppComponent {

    void inject(CatalogActivity activity);

    void inject(CatalogDetailActivity activity);

    void inject(CatalogPresenter catalogPresenter);

    void inject(GetCatalog getCatalog);
}
