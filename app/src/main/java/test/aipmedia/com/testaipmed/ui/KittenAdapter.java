package test.aipmedia.com.testaipmed.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import test.aipmedia.com.testaipmed.R;

/**
 * Created by pindziukou on 29.11.14.
 */
public class KittenAdapter extends BaseAdapter{
    private final Picasso mPicasso;
    private List<String> mUrls = Collections.EMPTY_LIST;

    public KittenAdapter(Picasso picasso){
        this.mPicasso = picasso;
    }

    public void setData(List<String> urls){
        this.mUrls = urls;
    }

    @Override
    public int getCount() {
        return mUrls.size();
    }

    @Override
    public Object getItem(int position) {
        return mUrls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.griditem,parent,false);
        }
        final ImageView imageView = (ImageView) convertView;

        mPicasso.load(mUrls.get(position)).into(imageView);
        return convertView;
    }
}
