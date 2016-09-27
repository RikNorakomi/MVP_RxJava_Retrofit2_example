package norakomi.com.MVP_RxJava_Retrofit2.controllers.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import norakomi.com.MVP_RxJava_Retrofit2.R;
import norakomi.com.MVP_RxJava_Retrofit2.controllers.fragments.AbstractFragment;
import norakomi.com.MVP_RxJava_Retrofit2.controllers.fragments.PosterOverviewFragment;
import norakomi.com.MVP_RxJava_Retrofit2.utils.App;
import norakomi.com.MVP_RxJava_Retrofit2.views.root.RootViewMvcImpl;

public class MainActivity extends AppCompatActivity implements AbstractFragment.AbstractFragmentCallback {

    private final String TAG = MainActivity.class.getSimpleName();

    RootViewMvcImpl mViewMVC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

          /*
        The MVC view associated with this activity is very simple, therefore the below lines could
        be replaced with:
        setContentView(R.layout.mvc_view_frame_layout);
        and there would be no need for FrameLayoutViewMvc class.
        However, for the sake of consistency, we should try to stick to a single coding style,
        therefore we employ full MVC approach even in this simple case!
        */

        // Instantiate MVC view associated with this activity
        mViewMVC = new RootViewMvcImpl(this, null);

        // Set the root view of the associated MVC view as the content of this activity
        setContentView(mViewMVC.getRootView());

        // Show the default fragment if the application is not restored
        if (savedInstanceState == null) {
            replaceFragment(PosterOverviewFragment.class, false, null);
        }
    }

    // ****************************************
    // Fragments management

    @Override
    public void replaceFragment(Class<? extends android.support.v4.app.Fragment> clazz,
                                boolean addToBackStack,
                                Bundle args) {


        // If the required fragment is already shown - do nothing
        if (isFragmentShown(clazz)) {
            return;
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment newFragment;

        try {
            // Create new fragment
            newFragment = clazz.newInstance();
            if (args != null) newFragment.setArguments(args);
        } catch (InstantiationException e) {
            App.logError(TAG, "InstantiationException in replaceFragment()", e);
            return;
        } catch (IllegalAccessException e) {
            App.logError(TAG, "IllegalAccessException in replaceFragment()", e);
            return;
        }

        if (addToBackStack) {
            // Add this transaction to the back stack
            ft.addToBackStack(null);
        }

        // Change to a new fragment
        ft.replace(R.id.frame_contents, newFragment, clazz.getClass().getSimpleName());
        ft.commit();
    }

    /**
     * Check whether a fragment of a specific class is currently shown
     * @param clazz class of fragment to test. Null considered as "test no fragment shown"
     * @return true if fragment of the same class (or a superclass) is currently shown
     */
    private boolean isFragmentShown(Class<? extends android.support.v4.app.Fragment> clazz) {
        Fragment currFragment = getSupportFragmentManager().findFragmentById(R.id.frame_contents);

        return (currFragment == null && clazz == null) ||
                (currFragment != null && clazz.isInstance(currFragment));
    }

    // End of fragments management
    // **********************************************

}
