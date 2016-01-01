
package theone.medusa.pers.projectx.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import de.greenrobot.event.EventBus;
import theone.medusa.pers.projectx.R;
import theone.medusa.pers.projectx.event.JourneyEvent;

public class JourneyListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_journey_list);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
    public void onEventMainThread(JourneyEvent event){

    }
}
