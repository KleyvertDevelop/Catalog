package com.momentoustech.catalog.domain.usecase;

import com.momentoustech.catalog.data.Api.ApiService;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Kleyvert on 10/3/16.
 *
 * @author Kleyvert Martinez
 * Implementation of the command pattern
 */
public class GetCatalog extends UseCase<Observable> {

    private String name;

    // Getters & setters
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    @Inject
    public GetCatalog() {}

    /**
     * Method to execute
     *
     * @return Observable {@link Observable}
     */
    @Override
    public Observable Execute() {
        return ApiService.getInstance().getCatalog(name);
    }

}
