package theone.medusa.pers.projectx.activity;

import android.app.Dialog;
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

import java.util.zip.Inflater;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnFocusChange;
import theone.medusa.pers.projectx.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ButterKnife.bind(this);
    }

    @OnFocusChange({R.id.et_start_time,R.id.et_end_time})
    public void onFocusChange(View view,Boolean hasFocus) {
        if(hasFocus){
            this.showDialog(view.getId());
        }
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
                int y = datePicker.getYear();
                if(etStartTime.getId() == id){
                    etStartTime.setText(y+"");
                }else{
                    etEndTime.setText(y+"");
                }
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
