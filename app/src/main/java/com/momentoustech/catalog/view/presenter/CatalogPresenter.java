package com.momentoustech.catalog.view.presenter;


import com.momentoustech.catalog.data.Helper.Constant;
import com.momentoustech.catalog.domain.model.Catalog;
import com.momentoustech.catalog.domain.model.Results;
import com.momentoustech.catalog.domain.usecase.GetCatalog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import rx.Observable;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Kleyvert on 10/3/16.
 */
public class CatalogPresenter extends Presenter<CatalogPresenter.View> {

    @Inject
    public GetCatalog getCatalog;

    @Inject
    public CatalogPresenter() {}

    /**
     * Onclick listener when there is a click
     * in a row from the list.
     * @param product {@link Results}
     */
    public void onProductClicked(Results product) { getView().goProductDetailActivity(product); }

    /**
     * Override Initialize method from presenter
     */
    @Override public void initialize() {

        super.initialize();
        // getting the catalog for default
        getCatalog(Constant.PRODUCT_DEFAULT);
    }

    /**
     * Detroy the view
     */
    public void destroy() {
        // stop the service
        getView().stopService();

        setView(null);
    }

    /**
     * Getting the catalog from product
     * @param name {@link String}
     */
    public void getCatalog(String name){
        // show loader
        getView().showLoading();

        getCatalog.setName(name);
        Observable<Catalog> observable = getCatalog.Execute();

        // Observator
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Catalog>() {
                    @Override public void onCompleted() {}

                    @Override public void onError(Throwable e) {
                        getView().hideLoading(); }

                    @Override public void onNext(Catalog catalog) {
                        getView().hideLoading();
                        getView().showCatalog(catalog.results);
                        getView().startService();
                    }
                });
    }

    /**
     * Refreshing the list
     *
     * @param _productList {@link List<Results>}
     * @param newText {@link String}
     */
    public void refreshList(List<Results> _productList, String newText){

        if (newText.isEmpty()){
            getView().startService();
        }else
            getView().stopService();

        if (_productList!= null){
            if (!_productList.isEmpty()) {
                final List<Results> filteredModelList = filter(_productList, newText);
                // Refreshing the adapter
                getView().refreshCatalog(filteredModelList);
            }
        }
    }

    /**
     * Filtramos el texto ingresado con la lista que tenemos
     *
     * @param models {@link List<Results>}
     * @param query {@link String}
     *
     * @return products list
     */
    public List<Results> filter(List<Results> models, String query) {
        query = query.toLowerCase();

        final List<Results> filteredModelList = new ArrayList<>();
        for (Results model : models) {
            final String text = model.title;
            if (text.toLowerCase().contains(query.toLowerCase())) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

    /**
     * Order the list descendet or ascendent
     *
     * @param _productList {@link List<Results>}
     * @param isASC {@link boolean}
     */
    public void orderAlphabetically(List<Results> _productList, boolean isASC){

        if (isASC)
            Collections.sort(_productList);
        else
            Collections.reverse(_productList);

        // refreshing the list
        getView().refreshCatalog(_productList);
    }
    /**
     * Interface from presenter
     */
    public interface View extends Presenter.View {

        void goProductDetailActivity(Results product);

        void showCatalog(List<Results> _productList);

        void refreshCatalog(List<Results> _productList);

        void stopService();

        void startService();

    }
}
