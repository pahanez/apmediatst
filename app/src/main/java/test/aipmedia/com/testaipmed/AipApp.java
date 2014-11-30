package test.aipmedia.com.testaipmed;

import android.app.Application;
import android.content.Context;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;

/**
 * Created by pindziukou on 29.11.14.
 */
public class AipApp extends Application{

    private ObjectGraph mObjectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        mObjectGraph = ObjectGraph.create(getModules().toArray());
    }

    public List<Object> getModules() {
        return Arrays.<Object>asList(new AppModule(this));
    }

    public void inject(Object object){
        mObjectGraph.inject(object);
    }

    public ObjectGraph createScopedGraph(Object... modules) {
        return mObjectGraph.plus(modules);
    }

    public static AipApp get(Context context) {
        return (AipApp)context.getApplicationContext();
    }
}
