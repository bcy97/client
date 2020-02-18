package nju.erpclient.activity;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import nju.erpclient.R;
import nju.erpclient.utils.Constants;
import nju.erpclient.vo.WeekWorkReport;
import com.okhttplib.HttpInfo;
import com.okhttplib.OkHttpUtil;
import com.okhttplib.annotation.ContentType;
import com.okhttplib.annotation.Encoding;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkActivity extends AppCompatActivity {


    @BindView(R.id.back)
    ImageButton mBackButton;
    @BindView(R.id.save)
    ImageButton mSaveButton;

    @BindView(R.id.month)
    TextView mMonthText;
    @BindView(R.id.relevant_people)
    EditText mRelevantPeopleInput;
    @BindView(R.id.subsidiary)
    EditText mSubsidiary;

    @BindView(R.id.calendarView)
    CalendarView mCalendar;

    @BindView(R.id.work_content)
    EditText mWorkContentInput;


    SharedPreferences sharedPreferences;

    int year, month, dayOfWeek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {

        sharedPreferences = getSharedPreferences("UserInfo", 0);

        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH) + 1;
        dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        mMonthText.setText(year + "年" + month + "月");

        mRelevantPeopleInput.setText(sharedPreferences.getString("userName", ""));

        mSubsidiary.setText(sharedPreferences.getString("subsidiary", ""));

        mBackButton.setOnClickListener(v -> {
            this.finish();
        });

        new GetWeekWorkTask(sharedPreferences.getString("userName", ""), year, month, dayOfWeek).execute();

    }

    private class GetWeekWorkTask extends AsyncTask<Void, Void, WeekWorkReport> {

        private final String mUsername;
        private final int mYear;
        private final int mWeeklyNum;
        private final int mDayOfWeek;

        GetWeekWorkTask(String username, int year, int weeklyNum, int dayOfWeek) {
            mUsername = username;
            mYear = year;
            mWeeklyNum = weeklyNum;
            mDayOfWeek = dayOfWeek;
        }

        @Override
        protected WeekWorkReport doInBackground(Void... params) {

            String url = "http://" + Constants.ip + ":" + Constants.port + "/weekWorkContent/getWeekWorkContent";

            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("year", String.valueOf(mYear));
            requestBody.put("weeklyNum", String.valueOf(mWeeklyNum));
            requestBody.put("relevantPeople", mUsername);
            Gson gson = new Gson();

            HttpInfo request = HttpInfo.Builder()
                    .setUrl(url)
                    .setContentType(ContentType.JSON)
                    .setResponseEncoding(Encoding.UTF_8)
                    .addParamJson(gson.toJson(requestBody))
                    .build();

            HttpInfo response = OkHttpUtil.getDefault().doPostSync(request);


            if (response.isSuccessful()) {
                String res = response.getRetDetail();
                if (res.equals("") || res == "") {
                    return null;
                } else {
                    return gson.fromJson(res, new TypeToken<WeekWorkReport>() {
                    }.getType());
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(final WeekWorkReport data) {
            if (data != null) {
                String workContent = "";
                switch (mDayOfWeek) {
                    case 0:
                        workContent = data.getSundayWorkContent();
                    case 1:
                        workContent = data.getMondayWorkContent();
                    case 2:
                        workContent = data.getTuesdayWorkContent();
                    case 3:
                        workContent = data.getWednesdayWorkContent();
                    case 4:
                        workContent = data.getThursdayWorkContent();
                    case 5:
                        workContent = data.getFridayWorkContent();
                    case 6:
                        workContent = data.getSaturdayWorkContent();
                }
                mWorkContentInput.setText(workContent);
            }
        }

    }
}
