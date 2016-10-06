package com.momentoustech.catalog.view.presenter;

import android.os.Bundle;
import com.momentoustech.catalog.domain.model.Results;

import javax.inject.Inject;

/**
 * Created by Kleyvert on 10/3/16.
 */
public class CatalogDetailPresenter extends Presenter<CatalogDetailPresenter.View> {

    public Bundle data;

    @Inject
    public CatalogDetailPresenter() {}

    /**
     * Initialize the presenter
     */
    @Override
    public void initialize() {
        super.initialize();

        if (data!=null) {
            Results product = (Results) data.get("product");
            getView().setAttributes(product);
        }
    }

    /**
     * Destroy View
     */
    public void destroy() {
        setView(null);
    }

    /**
     * Interface from presenter
     */
    public interface View extends Presenter.View {

        void goBack();

        void setAttributes(Results product);
    }
}