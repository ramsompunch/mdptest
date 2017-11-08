package mdp3.mdptest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by JBTS-02-05 on 2017-10-23.
 */

public class ManualActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manual_main);
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}
