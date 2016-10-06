package com.momentoustech.catalog.view.Base.View;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;

import com.momentoustech.catalog.App.CatalogApplication;
import com.momentoustech.catalog.R;
import com.momentoustech.catalog.di.components.AppComponent;

import butterknife.ButterKnife;

/**
 * <p>
 *     BaseActivity contains some modifications to the native AppCompatActivity.
 *     Mainly, it use ButterKnife for view binding and it automatically check if
 *     toolbar exists.
 * </p>
 * Created by Chasqui on 10/3/16.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    protected Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        setupToolbar();
        bindViews();
        initView();
    }

    /**
     * Use this method to initialize view components. This method is called after {@link BaseActivity#bindViews()}
     */
    public void initView() {
    }

    /**
     * Its common use a toolbar within activity, if it exists in the
     * layout this will be configured
     */
    public void setupToolbar() {
        mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
    }

    /**
     * Every object annotated with {@link butterknife.Bind} its gonna injected trough butterknife
     */
    private void bindViews() {
        ButterKnife.bind(this);
    }

    public AppComponent getAppComponent() {

        return ((CatalogApplication) getApplication()).getAppComponent();
    }


    @Nullable
    public Toolbar getToolbar() {
        return mToolbar;
    }

    /**
     * @return The layout id that's gonna be the activity view.
     */
    protected abstract int getLayoutId();


    protected void stopLoading() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    protected void showLoading(boolean cancelable) {
        showLoading(cancelable, getString(R.string.loading));
    }

    public void showLoading(boolean cancelable, String message) {
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(this, null, message, true, cancelable);
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    progressDialog = null;
                }
            });
            progressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    progressDialog = null;
                }
            });
        } else {
            Log.d("baseActivity", "Already showing progress dialog.");
        }
    }
}