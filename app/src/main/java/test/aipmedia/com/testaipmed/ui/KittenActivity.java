package test.aipmedia.com.testaipmed.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import test.aipmedia.com.testaipmed.R;

/**
 * Created by pindziukou on 29.11.14.
 */
public class KittenActivity extends Activity{

    private ViewGroup container;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        container = (ViewGroup) findViewById(android.R.id.content);
        getLayoutInflater().inflate(R.layout.kitten_layout,container);
    }


}
