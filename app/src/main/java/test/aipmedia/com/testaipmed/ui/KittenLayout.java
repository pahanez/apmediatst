package test.aipmedia.com.testaipmed.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.etiennelawlor.quickreturn.library.enums.QuickReturnType;
import com.etiennelawlor.quickreturn.library.listeners.QuickReturnListViewOnScrollListener;
import com.etsy.android.grid.StaggeredGridView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import test.aipmedia.com.testaipmed.AipApp;
import test.aipmedia.com.testaipmed.Constants;
import test.aipmedia.com.testaipmed.R;
import test.aipmedia.com.testaipmed.url.KittenGenerator;
import test.aipmedia.com.testaipmed.url.PlaceholdGenerator;
import test.aipmedia.com.testaipmed.url.UrlGenerator;


/**
 * Created by pindziukou on 29.11.14.
 */
public class KittenLayout extends RelativeLayout {

    @InjectView(R.id.gallery_grid) StaggeredGridView mGalleryView;
    @InjectView(R.id.footer) TextView mFooter;



    @Inject
    Picasso mPicasso;
    @Inject
    SharedPreferences mPreferences;

    private KittenAdapter mAdapter;
    private int mColumnWidth = -1;
    private UrlGenerator mUrlGenerator;

    public KittenLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        AipApp.get(context).inject(this);
        mAdapter = new KittenAdapter(mPicasso);

        //choose between PlaceHold and PlaceKitten here. {@link PlaceholdGenerator} or {@link KittenGenerator}
        mUrlGenerator = new PlaceholdGenerator();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.inject(this);
        mGalleryView.setAdapter(mAdapter);

        QuickReturnListViewOnScrollListener scrollListener = new QuickReturnListViewOnScrollListener(QuickReturnType.FOOTER,
                null, 0, mFooter, getResources().getDimensionPixelSize(R.dimen.footer_height));
        scrollListener.setCanSlideInIdleScrollState(true);
        mGalleryView.setOnScrollListener(scrollListener);

        mFooter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                KittenLayout.this.updateAdapterData();
            }
        });
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ButterKnife.reset(this);
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        resetColumnWidth();
    }

    private void resetColumnWidth() {
        mColumnWidth = -1;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!isConfigured()){
            mColumnWidth = mGalleryView.getColumnWidth();
            addDataToAdapter();
        }
    }

    private void addDataToAdapter() {
        Set<String> savedUrls = mPreferences.getStringSet(Constants.PREFS_CONSTANT,null);
        if(savedUrls != null){
            setDataToAdapter(new ArrayList<String>(savedUrls));
        }else{
            updateAdapterData();
        }
    }

    private void updateAdapterData(){
        List<String> urls = generateUrls();
        setDataToAdapter(urls);
    }

    private void setDataToAdapter(List<String> urls){
        KittenLayout.this.mAdapter.setData(urls);
        KittenLayout.this.mAdapter.notifyDataSetChanged();
    }

    private List<String> generateUrls(){
        List<String> urls = mUrlGenerator.generateUrl(mColumnWidth);
        mPreferences.edit().putStringSet(Constants.PREFS_CONSTANT,new HashSet<String>(urls)).commit();
        return urls;
    }

    private boolean isConfigured() {
        return mColumnWidth == mGalleryView.getColumnWidth();
    }
}
