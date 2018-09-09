package test.login.com.login;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;



public class G extends Application {
    private static Typeface font;
    private Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        font = Typeface.createFromAsset(getAssets(),"irsans_font.ttf");
        context = getApplicationContext();
    }
    public Typeface getFont(){
        return font;
    }
}
