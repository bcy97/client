package nju.erpclient.activity.LeaveActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import nju.erpclient.R;
import nju.erpclient.utils.Constants;
import nju.erpclient.vo.Leave;
import com.okhttplib.HttpInfo;
import com.okhttplib.OkHttpUtil;
import com.okhttplib.annotation.ContentType;
import com.okhttplib.annotation.Encoding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import jsc.kit.wheel.dialog.DateTimeWheelDialog;

public class LeaveModifyActivity extends AppCompatActivity {


    @BindView(R.id.type)
    EditText mTypeInput;
    @BindView(R.id.company)
    EditText mCompanyInput;
    @BindView(R.id.relevant_people)
    EditText mRelevantPeopleInput;
    @BindView(R.id.ratify)
    EditText mRatifyInput;
    @BindView(R.id.start)
    TextView mStartInput;
    @BindView(R.id.end)
    TextView mEndInput;
    @BindView(R.id.remark)
    EditText mRemarkInput;
    @BindView(R.id.save)
    ImageButton mSaveButton;
    @BindView(R.id.back)
    ImageButton mBackButton;

    Leave data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_modify);
        ButterKnife.bind(this);


        initView();
    }

    private void initView() {
        mBackButton.setOnClickListener(v -> {
            this.finish();
        });

        mSaveButton.setOnClickListener(v -> {
            new UpdateLeaveTask(data).execute();
        });

        mStartInput.setOnClickListener(v -> {
            createDialog().show();
        });


        Gson gson = new Gson();
        Intent intent = getIntent();
        if (intent.getStringExtra("mode").equals("modify")) {
            String json = intent.getStringExtra("leave");
            if (json != null || !json.equals("")) {
                data = gson.fromJson(intent.getStringExtra("leave"), new TypeToken<Leave>() {
                }.getType());
                mTypeInput.setText(data.getType());
                mCompanyInput.setText(data.getSubsidiary());
                mRelevantPeopleInput.setText(data.getRelevantPeople());
                mRatifyInput.setText(data.getRatify());
                mStartInput.setText(data.getStartTime());
                mEndInput.setText(data.getEndTime());
                mRemarkInput.setText(data.getRemark());
            }
        }
    }

    private DateTimeWheelDialog createDialog() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2015);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        Date startDate = calendar.getTime();
        calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2025);
        Date endDate = calendar.getTime();

        DateTimeWheelDialog dialog = new DateTimeWheelDialog(this);
//        dialog.setShowCount(7);
//        dialog.setItemVerticalSpace(24);
        dialog.show();
        dialog.setTitle("选择时间");
        int config = DateTimeWheelDialog.SHOW_YEAR_MONTH_DAY_HOUR_MINUTE;
        dialog.configShowUI(config);
        dialog.setCancelButton("取消", null);
        dialog.setOKButton("确定", (v, selectedDate) -> {
            selectedDate.setMonth(selectedDate.getMonth() - 1);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            mStartInput.setText(format.format(selectedDate));
            return false;
        });
        dialog.setDateArea(startDate, endDate, true);
        dialog.updateSelectedDate(new Date());
        return dialog;
    }

    public class UpdateLeaveTask extends AsyncTask<Void, Void, Boolean> {

        private final Leave leave;

        UpdateLeaveTask(Leave data) {
            leave = data;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            String url = "http://" + Constants.ip + ":" + Constants.port + "/leave/updateLeaveInfo";

            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("id", leave.getId());
            requestBody.put("subsidiary", leave.getSubsidiary());
            requestBody.put("relevantPeople", leave.getRelevantPeople());
            requestBody.put("type", leave.getType());
            requestBody.put("startTime", leave.getStartTime());
            requestBody.put("endTime", leave.getEndTime());
            requestBody.put("remark", leave.getRemark());

            Gson gson = new Gson();

            HttpInfo request = HttpInfo.Builder()
                    .setUrl(url)
                    .setContentType(ContentType.JSON)
                    .setResponseEncoding(Encoding.UTF_8)
                    .addParamJson(gson.toJson(requestBody))
                    .build();

            HttpInfo response = OkHttpUtil.getDefault().doPostSync(request);


            if (response.isSuccessful()) {
                return true;
            } else {
                return false;
            }

        }

        @Override
        protected void onPostExecute(final Boolean result) {
            if (result) {
                Toast.makeText(getApplicationContext(), "修改成功！", Toast.LENGTH_SHORT);
            }
        }

    }
}
