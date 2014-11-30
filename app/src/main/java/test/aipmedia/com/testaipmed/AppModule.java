package test.aipmedia.com.testaipmed;

import android.app.Application;
import android.content.SharedPreferences;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import test.aipmedia.com.testaipmed.ui.KittenLayout;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by pindziukou on 29.11.14.
 */
@Module(injects = {
        AipApp.class,
        KittenLayout.class
},
        library = true)
public class AppModule {
    private AipApp mAipApp;

    public AppModule(AipApp aipApp) {
        this.mAipApp = aipApp;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mAipApp;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Application app) {
        return app.getSharedPreferences("aipapp", MODE_PRIVATE);
    }

    @Provides
    @Singleton
    Picasso providePicasso(Application app) {
        return new Picasso.Builder(app).build();
    }

}
