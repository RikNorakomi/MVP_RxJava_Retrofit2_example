package norakomi.com.MVP_RxJava_Retrofit2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;
import norakomi.com.MVP_RxJava_Retrofit2.controllers.activities.MainActivity;

/**
 * Created by Rik van Velzen, on 28-9-2016.
 */
public class StartUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // init pre-requisites Crashlytics
        Fabric.with(this, new Crashlytics());

        // start main presenter
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
