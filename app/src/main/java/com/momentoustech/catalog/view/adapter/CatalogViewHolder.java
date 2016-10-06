package com.momentoustech.catalog.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.momentoustech.catalog.domain.model.Results;
import com.momentoustech.catalog.view.presenter.CatalogPresenter;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.momentoustech.catalog.R;

/**
 * Created by Kleyvert on 10/3/16.
 */
public class CatalogViewHolder extends RecyclerView.ViewHolder {

    private final CatalogPresenter catalogPresenter;

    @Bind(R.id.product_img)
    ImageView Image;

    @Bind(R.id.product_name)
    TextView nameLabel;

    /**
     * Constructor
     *
     * @param itemView {@link View}
     * @param catalogPresenter {@link CatalogPresenter}
     */
    public CatalogViewHolder(@NonNull View itemView, @NonNull CatalogPresenter catalogPresenter) {
        super(itemView);
        this.catalogPresenter = catalogPresenter;
        ButterKnife.bind(this, itemView);
    }

    /**
     * Render the holder
     * @param product {@link Results}
     */
    public void render(Results product) {
        onItemClick(product);
        renderProductImage(product.thumbnail);
        renderProductName(product.title);
    }

    private void onItemClick(final Results product) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                catalogPresenter.onProductClicked(product);
            }
        });
    }

    /**
     * Render product image
     *
     * @param urlHeaderImage {@link String}
     */
    private void renderProductImage(String urlHeaderImage) {
        getImage(urlHeaderImage, Image);
    }

    /**
     * Edit product name
     * @param name {@link String}
     */
    private void renderProductName(String name) {
        nameLabel.setText(name);
    }

    /**
     * Render image
     *
     * @param photo {@link String}
     * @param photoImageView {@link ImageView}
     */
    private void getImage(String photo, ImageView photoImageView) {
        Picasso.with(getContext()).load(photo).fit().centerCrop().into(photoImageView);
    }

    private Context getContext() {
        return itemView.getContext();
    }
}
