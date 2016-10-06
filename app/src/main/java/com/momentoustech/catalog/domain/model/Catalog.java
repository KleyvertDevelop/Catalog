package com.momentoustech.catalog.domain.model;

import java.util.Collections;
import java.util.List;

/**
 * Created by Kleyvert on 10/4/16.
 */
public class Catalog extends Entity{

    public String query;

    public List<Results> results = Collections.emptyList();

    /**
     * Constructor
     */
    public Catalog() {
    }
}
