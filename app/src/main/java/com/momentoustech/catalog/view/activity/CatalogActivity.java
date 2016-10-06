package com.momentoustech.catalog.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.momentoustech.catalog.R;
import com.momentoustech.catalog.data.Helper.CountDownService;
import com.momentoustech.catalog.domain.model.Entity;
import com.momentoustech.catalog.domain.model.Results;
import com.momentoustech.catalog.view.Base.View.BaseActivity;
import com.momentoustech.catalog.view.adapter.CatalogAdapter;
import com.momentoustech.catalog.view.presenter.CatalogPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

public class CatalogActivity extends BaseActivity implements CatalogPresenter.View ,SearchView.OnQueryTextListener {

    @Inject
    CatalogPresenter presenter;

    @Bind(R.id.list_products)
    RecyclerView productList;

    private CatalogAdapter adapter;
    private SearchView searchView;
    private String searchItem;
    private List<Results> _productList;

    /**
     * Initialize the view
     */
    @Override public void initView() {
        super.initView();
        initializeDagger();
        initializePresenter();
        initializeAdapter();
        initializeRecyclerView();
        presenter.initialize();
    }

    /**
     * Setup from toolbar
     */
    @Override public void setupToolbar() {
        super.setupToolbar();
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    /**
     * Selector of the elements in the menu
     * @param item {@link MenuItem}
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {

            case R.id.more_optinos_order_Asc:

                if (!_productList.isEmpty())
                    presenter.orderAlphabetically(_productList,true);
                return true;

            case R.id.more_optinos_order_Desc:
                if (!_productList.isEmpty())
                    presenter.orderAlphabetically(_productList,false);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Creation of the elements in the menu
     * @param menu {@link Menu}
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.catalog_menu, menu);
        MenuItem item = menu.findItem(R.id.menu_search);
        searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);

        if (searchItem!=null && !searchItem.isEmpty()){
            searchView.setIconified(false);
            searchView.setQuery(searchItem, false);
        }
        MenuItemCompat.setActionView(item, searchView);

        return true;
    }

    /**
     * Initialize the adapter
     */
    private void initializeAdapter() {
        adapter = new CatalogAdapter(presenter);
    }

    /**
     * Initialize the Recycler view
     */
    private void initializeRecyclerView() {
        productList.setLayoutManager(new LinearLayoutManager(this));
        productList.setHasFixedSize(true);
        productList.setAdapter(adapter);
    }

    /**
     * To begin inyection of the activity
     */
    private void initializeDagger() { getAppComponent().inject(this); }

    /**
     * initialize the presenter
     */
    private void initializePresenter() {
        presenter.setView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // registering the eventBUS
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();

        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroy() {

        presenter.destroy();

        super.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_catalog;
    }

    /**
     * it goes to detail from product
     * @param product {@link Results}
     */
    @Override
    public void goProductDetailActivity(Results product) {

        Intent intet = new Intent(this, CatalogDetailActivity.class);
        intet.putExtra("product", product);

        startActivity(intet);
        // Override animation transition
        overridePendingTransition(R.anim.traslation_right_in, R.anim.traslation_right_out);
    }

    /**
     * Show the catalog
     *
     * @param productList {@link List<Results>}
     */
    @Override
    public void showCatalog(List<Results> productList) {
        _productList = productList;
        adapter.addAll(_productList);
        adapter.notifyDataSetChanged();
    }

    /**
     * Refresh catalog list
     *
     * @param _productList {@link List<Results>}
     */
    @Override
    public void refreshCatalog(List<Results> _productList) {
        adapter.setFilter(_productList);
    }

    /**
     * Stop service for refresh the count
     */
    @Override
    public void stopService() {
        // Stop service
        stopService(new Intent(CatalogActivity.this, CountDownService.class));
    }

    /**
     * Start service for refresh the count
     */
    @Override
    public void startService() {
        // Stop service
        startService(new Intent(CatalogActivity.this, CountDownService.class));
    }

    /**
     * Implementation for to show the loader
     */
    @Override
    public void showLoading() {
        this.showLoading(false);
        productList.setVisibility(View.GONE);
    }

    /**
     * Implementation for to hide the loader
     */
    @Override
    public void hideLoading() {
        this.stopLoading();
        productList.setVisibility(View.VISIBLE);
    }

    /**
     * Implementation from eventBus
     * @param entity {@link Entity}
     */
    @Subscribe
    public void onEvent(Entity entity) {
        // update your UI based on bleInfo
        presenter.getCatalog(entity.getName());
    }

    /**
     * Query for to search and send a text
     *
     * @param query {@link String}
     * @return
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    /**
     * Query for to search for a new text
     * @param newText {@link String}
     * @return
     */
    @Override
    public boolean onQueryTextChange(String newText) {

        searchItem = newText;
        presenter.refreshList(_productList,newText);
        return false;
    }

}
