package test.aipmedia.com.testaipmed.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * Created by pindziukou on 29.11.14.
 */
public class Utils {
    public static List<String> generateWidth(){
        List<String> ids = new ArrayList<String>();
        Random mRandom = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 50; i++){
            sb.delete(0,sb.length());
            int height = mRandom.nextInt(250)+100;
            sb.append(height);
            ids.add(sb.toString());
        }
        return ids;
    }
}
