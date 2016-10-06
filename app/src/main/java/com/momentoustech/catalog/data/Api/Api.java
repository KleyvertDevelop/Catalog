package com.momentoustech.catalog.data.Api;

import com.momentoustech.catalog.domain.model.Catalog;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Kleyvert on 10/3/16.
 */
public interface Api {

    @GET("/sites/MLA/search")
    Observable<Catalog> getCatalog(@Query("q") String name,
                                   @Query("offset") Integer offset,
                                   @Query("limit") Integer limit);

}
