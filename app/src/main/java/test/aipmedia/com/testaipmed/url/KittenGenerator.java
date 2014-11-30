package test.aipmedia.com.testaipmed.url;

import java.util.ArrayList;
import java.util.List;

import test.aipmedia.com.testaipmed.ui.Utils;

/**
 * Created by pindziukou on 30.11.14.
 */
public class KittenGenerator implements UrlGenerator{

    private static final String URL = "http://placekitten.com/g/";

    @Override
    public List<String> generateUrl(int width) {
        List<String> urls = new ArrayList<String>();
        for(String height : Utils.generateWidth()){
            String url = URL+width+"/"+height;
            urls.add(url);
        }
        return urls;
    }
}
