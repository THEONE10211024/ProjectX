package theone.medusa.pers.projectx.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import de.greenrobot.event.EventBus;
import theone.medusa.pers.projectx.R;
import theone.medusa.pers.projectx.event.JourneyEvent;

public class InfoActivity extends AppCompatActivity {

    @Bind(R.id.et_start_place)
    EditText etStartPlace;
    @Bind(R.id.et_end_place)
    EditText etEndPlace;
    @Bind(R.id.et_start_time)
    EditText etStartTime;
    @Bind(R.id.et_end_time)
    EditText etEndTime;
    @Bind(R.id.et_days)
    EditText etDays;
    @Bind(R.id.btn_journey_start)
    Button btnJourneyStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ButterKnife.bind(this);
    }

    @OnFocusChange({R.id.et_start_time, R.id.et_end_time})
    public void onFocusChange(View view, boolean hasFocus) {
        if (hasFocus) {
            this.showDialog(view.getId());
        }
    }

    @OnClick(R.id.btn_journey_start)
    public void startJourneyListActivity(){
        startActivity(new Intent(this,JourneyListActivity.class));
        EventBus.getDefault().postSticky(getJourenyInfo());
    }
    private JourneyEvent getJourenyInfo(){
        JourneyEvent journeyEvent = new JourneyEvent();
        journeyEvent.setStartPlace(etStartPlace.getText().toString());
        journeyEvent.setEndPlace(etEndPlace.getText().toString());
        journeyEvent.setStartTime(etStartTime.getText().toString());
        journeyEvent.setEndTime(etEndTime.getText().toString());
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
                String date = String.format("%d-%02d-%02d",datePicker.getYear(),datePicker.getMonth()+1,datePicker.getDayOfMonth());
                if (etStartTime.getId() == id) {
                    etStartTime.setText(date);
//                    etStartTime.setText("2015-12-23");
                } else {
                    etEndTime.setText(date);
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
}
