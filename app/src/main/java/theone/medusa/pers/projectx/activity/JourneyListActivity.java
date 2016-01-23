package theone.medusa.pers.projectx.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import theone.medusa.pers.projectx.R;
import theone.medusa.pers.projectx.adapter.JourneyListAdapter;
import theone.medusa.pers.projectx.adapter.JourneyListAdapter2;
import theone.medusa.pers.projectx.bean.JourneyBean;
import theone.medusa.pers.projectx.event.JourneyEvent;
import theone.medusa.pers.projectx.utils.RandomUtils;

public class JourneyListActivity extends AppCompatActivity {

    @Bind(R.id.rv_journey_list)
    RecyclerView rvJourneyList;

    private JourneyListAdapter journeyListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey_list);
        ButterKnife.bind(this);
        EventBus.getDefault().registerSticky(this);
    }

    public void onEventMainThread(JourneyEvent event) {
        journeyListAdapter = new JourneyListAdapter(createMockData(event));
        rvJourneyList.setAdapter(journeyListAdapter);
        rvJourneyList.setLayoutManager(new LinearLayoutManager(this));
    }

    private List<JourneyBean> createMockData(JourneyEvent event) {
        Calendar startTime = string2Calendar(event.getStartTime(), "yyyy-MM-dd");
        Calendar endTime = string2Calendar(event.getEndTime(), "yyyy-MM-dd");
        List<String> days = new ArrayList<>();
        Calendar timeIterator = startTime;
        while (!timeIterator.after(endTime)){
            int week = timeIterator.get(Calendar.DAY_OF_WEEK)-1;
            if(week != 6 && week != 0){
                days.add(calendar2String(startTime, "MM月dd日"));
            }
            timeIterator.add(Calendar.DAY_OF_MONTH, 1);
        }

        int[] randomIndex = RandomUtils.randomSelectIndex(days.size(),event.getDayCount());
        Arrays.sort(randomIndex);
        List<JourneyBean> journeyBeans = new ArrayList<>();
        for (int i:randomIndex) {
            JourneyBean journeyBean = new JourneyBean();
            journeyBean.setStartPlace(event.getStartPlace());
            journeyBean.setEndPlace(event.getEndPlace());
            journeyBean.setCarTypeSrcId(getCarType());
            journeyBean.setTime(getFormattedTime(days.get(i)));
            journeyBeans.add(journeyBean);
        }
        return journeyBeans;
    }

    /**
     * 按权重产生时间
     * 9点-10点：80%
     * 10点-11点：15%
     * 11点-2点：5%
     *
     * @param time
     * @return
     */
    private String getFormattedTime(String time) {
        Random random = new Random();
        int x = random.nextInt(100);
        if (x < 80) {
            return String.format(time + " 21:%02d", random.nextInt(60));
        } else if (x < 95) {
            return String.format(time + " 22:%02d", random.nextInt(60));
        } else {
            return String.format(time + " %02d:%02d", (random.nextInt(4) + 23) % 24, random.nextInt(60));
        }
    }

    /**
     * 车型
     * 快车：70%
     * 专车：30%
     *
     * @return
     */
    private int getCarType() {
        Random random = new Random();
        int x = random.nextInt(100);
        return x < 70 ? R.drawable.label_fastcar :R.drawable.label_premium;
    }

    private Calendar string2Calendar(String time, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            Date date = sdf.parse(time);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String calendar2String(Calendar calendar, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(calendar.getTime());
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
