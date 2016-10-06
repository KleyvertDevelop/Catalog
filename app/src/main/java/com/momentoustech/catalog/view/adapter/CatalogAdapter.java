package com.momentoustech.catalog.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.momentoustech.catalog.R;
import com.momentoustech.catalog.domain.model.Results;
import com.momentoustech.catalog.view.presenter.CatalogPresenter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Kleyvert on 10/3/16.
 */
public class CatalogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final CatalogPresenter presenter;
    private List<Results> productList;

    /**
     * Constructor
     * @param presenter {@link CatalogPresenter}
     */
    public CatalogAdapter(@NonNull CatalogPresenter presenter) {
        this.presenter = presenter;
        productList = new ArrayList<>();
    }

    /**
     * Creation from holder
     * @param parent {@link ViewGroup}
     * @param viewType {@link int}
     * @return
     */
    @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_product, parent, false);
        return new CatalogViewHolder(view, presenter);
    }

    /**
     * Bind from holder
     * @param holder {@link RecyclerView.ViewHolder}
     * @param position {@link int}
     */
    @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CatalogViewHolder catalogViewHolder = (CatalogViewHolder) holder;
        Results product = productList.get(position);
        catalogViewHolder.render(product);
    }

    /**
     * Item count
     * @return
     */
    @Override
    public int getItemCount() {
        return productList.size();
    }

    /**
     * Add new collection or list
     * @param collection {@link Collection}
     */
    public void addAll(Collection<Results> collection) {
        productList.addAll(collection);
    }

    /**
     * Filter from search
     * @param products {@link List<Results>}
     */
    public void setFilter(List<Results> products) {
        productList = new ArrayList<>();
        productList.addAll(products);
        notifyDataSetChanged();
    }
}
