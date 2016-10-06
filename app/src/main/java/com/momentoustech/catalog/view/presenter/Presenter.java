package com.momentoustech.catalog.view.presenter;

/**
 * Created by Kleyvert on 10/3/16.
 */
public class Presenter<T extends Presenter.View> {

    private T view;

    public void setView(T view) {
        this.view = view;
    }

    public T getView() {
        return view;
    }

    /**
     * initialize
     */
    public void initialize() {}

    /**
     * View Interface
     */
    public interface View {

        void showLoading();

        void hideLoading();

    }
}

