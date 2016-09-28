package norakomi.com.MVP_RxJava_Retrofit2.errorHandling;

import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by Rik van Velzen, on 28-9-2016.
 */
public class UIResolver {

    private final View rootView;

    private Snackbar persistentSnackbar;

    public UIResolver(View rootView) {
        this.rootView = rootView;
    }

    public void showSnackBar(@StringRes int messageResource) {
        Snackbar.make(rootView, messageResource, Snackbar.LENGTH_LONG).show();
    }

    public void showPersistentSnackBar(@StringRes int messageResource) {
        persistentSnackbar = Snackbar.make(rootView, messageResource, Snackbar.LENGTH_INDEFINITE);
        persistentSnackbar.show();
    }

    public void hidePersistentSnackBar() {
        persistentSnackbar.dismiss();
    }
}
