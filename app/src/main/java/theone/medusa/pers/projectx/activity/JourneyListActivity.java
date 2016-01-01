package theone.medusa.pers.projectx.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import theone.medusa.pers.projectx.R;
import theone.medusa.pers.projectx.event.JourneyEvent;

public class JourneyListActivity extends AppCompatActivity {

    @Bind(R.id.rv_journey_list)
    RecyclerView rvJourneyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_journey_list);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    public void onEventMainThread(JourneyEvent event) {

    }
}
