package com.momentoustech.catalog.view.activity;

import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.momentoustech.catalog.R;
import com.momentoustech.catalog.domain.model.Results;
import com.momentoustech.catalog.view.Base.View.BaseActivity;
import com.momentoustech.catalog.view.presenter.CatalogDetailPresenter;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by Kleyvert on 10/3/16.
 */
public class CatalogDetailActivity extends BaseActivity implements CatalogDetailPresenter.View {

    @Inject
    CatalogDetailPresenter presenter;

    @Bind(R.id.price)
    TextView price;

    @Bind(R.id.title)
    TextView title;

    @Bind(R.id.condition)
    TextView condition;

    @Bind(R.id.available_quantity)
    TextView available_quantity;

    @Bind(R.id.image)
    ImageView image;


    /**
     * Initialize the view
     */
    @Override public void initView() {
        super.initView();
        initializeDagger();
        initializePresenter();

        presenter.data = getIntent().getExtras();
        presenter.initialize();
    }

    @Override
    public void onDestroy() {
        presenter.destroy();
        super.onDestroy();
    }

    /**
     * Initialize the dependency of the activity
     */
    private void initializeDagger() { getAppComponent().inject(this); }

    /**
     * Initialize the presenter
     */
    private void initializePresenter() {
        presenter.setView(this);
    }

    /**
     * Setup navigation bar
     */
    @Override public void setupToolbar() {
        super.setupToolbar();
        mToolbar.setNavigationIcon(R.mipmap.ic_arrow);

        mToolbar.setTitle(R.string.product_detail);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Close Activity
        if (item.getItemId() == android.R.id.home) {
            presenter.getView().goBack();
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Implementation for to get el layout id
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_product_detail;
    }

    /**
     * Implementation for to show the loader
     */
    @Override
    public void showLoading() {}

    /**
     * Implementation for to hide the loader
     */
    @Override
    public void hideLoading() {}

    /**
     * Implementation for to finish the activity
     */
    @Override
    public void goBack() { finish(); }

    /**
     * Set attributes at the widget
     *
     * @param product {@link Results}
     */
    @Override
    public void setAttributes(Results product) {
        title.setText(product.title);
        price.setText(String.valueOf(product.price));
        condition.setText(product.condition.toUpperCase());
        available_quantity.setText(String.valueOf((new Double(product.available_quantity))
                .intValue()));
        Picasso.with(this).load(product.thumbnail).fit().centerCrop().into(image);
    }
}
