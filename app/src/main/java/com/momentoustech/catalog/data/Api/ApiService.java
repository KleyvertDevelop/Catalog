package com.momentoustech.catalog.data.Api;

import com.momentoustech.catalog.data.Helper.Constant;
import com.momentoustech.catalog.domain.model.Catalog;

import retrofit.RestAdapter;
import rx.Observable;

/**
 * Created by Kleyvert on 10/3/16.
 */
public class ApiService {

    private static ApiService sInstance;

    private Api mApi;

    /**
     * Private constructor
     */
    private ApiService() {

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constant.ENDPOINT)
                .build();

        mApi = restAdapter.create(Api.class);
    }

    /**
     * Singlenton Pattern
     *
     * @return ApiService {@link ApiService}
     */
    public static final synchronized ApiService getInstance() {
        if (sInstance == null) {
            sInstance = new ApiService();
        }
        return sInstance;
    }

    /**
     * Getting the product from catalog
     *
     * @param title {@link String}
     * @return Observable<Catalog> {@link Observable<Catalog>}
     */
    public Observable<Catalog> getCatalog(String title) {
        return mApi.getCatalog( title, Constant.OFFSET, Constant.LIMIT );
    }

}
