package theone.medusa.pers.projectx.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func3;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import rx.functions.Func5;
import theone.medusa.pers.projectx.R;
import theone.medusa.pers.projectx.event.JourneyEvent;

public class InfoActivity extends AppCompatActivity {

    @Bind(R.id.et_start_place)
    EditText etStartPlace;
    @Bind(R.id.et_end_place)
    EditText etEndPlace;
    @Bind(R.id.et_start_time)
    TextView tvStartTime;
    @Bind(R.id.et_end_time)
    TextView tvEndTime;
    @Bind(R.id.et_days)
    EditText etDays;
    @Bind(R.id.btn_journey_start)
    Button btnJourneyStart;

    private Observable<CharSequence> startPlaceObservable;
    private Observable<CharSequence> endPlaceObservable;
    private Observable<CharSequence> dayObservable;
    private Observable<CharSequence> startTimeObservable;
    private Observable<CharSequence> endTimeObservable;
    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ButterKnife.bind(this);
        startPlaceObservable = RxTextView.textChanges(etStartPlace).skip(1);
        endPlaceObservable = RxTextView.textChanges(etEndPlace).skip(1);
        dayObservable = RxTextView.textChanges(etDays).skip(1);
        startTimeObservable = RxTextView.textChanges(tvStartTime).skip(1);
        endTimeObservable = RxTextView.textChanges(tvEndTime).skip(1);
        combineLatestEvents();
    }

    private void combineLatestEvents() {
        subscription = Observable.combineLatest(startPlaceObservable,
                endPlaceObservable,
                startTimeObservable,
                endTimeObservable,
                dayObservable, new Func5<CharSequence, CharSequence, CharSequence, CharSequence, CharSequence, Boolean>() {
                    @Override
                    public Boolean call(CharSequence startPlace, CharSequence endPlace, CharSequence startTime, CharSequence endTime, CharSequence dayCount) {
                        boolean valid1 = !TextUtils.isEmpty(startPlace.toString().trim());
                        boolean valid2 = !TextUtils.isEmpty(endPlace.toString().trim());
                        boolean valid3 = !TextUtils.isEmpty(startTime.toString().trim());
                        boolean valid4 = !TextUtils.isEmpty(endTime.toString().trim());
                        Pattern p = Pattern.compile("^[1-9]\\d*$");
                        Matcher m = p.matcher(dayCount.toString().trim());
                        boolean valid5 = m.matches();
                        //开始时间必须小于结束时间
                        boolean valid6 = startTime.toString().compareTo(endTime.toString())<0;

                        if(!valid1){
                            etStartPlace.setError("起始地不能为空");
                        }
                        if(!valid2){
                            etEndPlace.setError("目的地不能为空");
                        }
                        if(!valid3){
                            tvStartTime.setError("起始时间不能为空");
                        }
                        if(!valid4){
                            tvEndTime.setError("结束时间不能为空");
                        }
                        if(!valid5){
                            etDays.setError("天数必须为正整数！");
                        }
                        if(!valid6){
                            tvEndTime.setError("结束时间必须晚于起始时间");
                        }

                        return valid1 && valid2 && valid3 && valid4 && valid5 && valid6;
                    }
                }).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                btnJourneyStart.setEnabled(aBoolean);
            }
        });
    }

    @OnClick({R.id.et_start_time, R.id.et_end_time})
    public void changeTime(View view) {
        this.showDialog(view.getId());
    }

    @OnClick(R.id.btn_journey_start)
    public void startJourneyListActivity() {
        startActivity(new Intent(this, JourneyListActivity.class));
        EventBus.getDefault().postSticky(getJourenyInfo());
    }


    private JourneyEvent getJourenyInfo() {
        JourneyEvent journeyEvent = new JourneyEvent();
        journeyEvent.setStartPlace(etStartPlace.getText().toString());
        journeyEvent.setEndPlace(etEndPlace.getText().toString());
        journeyEvent.setStartTime(tvStartTime.getText().toString());
        journeyEvent.setEndTime(tvEndTime.getText().toString());
        journeyEvent.setDayCount(Integer.parseInt(etDays.getText().toString()));
        return journeyEvent;
    }

    @Override
    protected Dialog onCreateDialog(final int id) {
        Dialog dialog = new Dialog(this);
        View layout = LayoutInflater.from(this).inflate(R.layout.layout_popdialog, null);
        Button btnOk = (Button) layout.findViewById(R.id.btnOK);
        Button btnCancel = (Button) layout.findViewById(R.id.btnCancel);
        final DatePicker datePicker = (DatePicker) layout.findViewById(R.id.datePicker);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissDialog(id);
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = String.format("%d-%02d-%02d", datePicker.getYear(), datePicker.getMonth() + 1, datePicker.getDayOfMonth());
                if (tvStartTime.getId() == id) {
                    tvStartTime.setText(date);
                } else {
                    tvEndTime.setText(date);
                }
                dismissDialog(id);
            }
        });
        dialog.setContentView(layout);
        Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.popuStyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.width = getWindowManager().getDefaultDisplay().getWidth();
        window.setGravity(Gravity.BOTTOM);
        dialog.onWindowAttributesChanged(wl);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

    @Override
    protected void onDestroy() {
        subscription.unsubscribe();
        super.onDestroy();
    }
}
