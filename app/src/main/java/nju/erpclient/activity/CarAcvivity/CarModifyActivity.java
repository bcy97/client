package nju.erpclient.activity.CarAcvivity;

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
import nju.erpclient.vo.CarUse;
import com.okhttplib.HttpInfo;
import com.okhttplib.OkHttpUtil;
import com.okhttplib.annotation.ContentType;
import com.okhttplib.annotation.Encoding;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CarModifyActivity extends AppCompatActivity {


    @BindView(R.id.title_text)
    TextView mTitleText;
    @BindView(R.id.company)
    EditText mCompanyInput;
    @BindView(R.id.carNo)
    EditText mCarNoInput;
    @BindView(R.id.date)
    EditText mDateInput;
    @BindView(R.id.user)
    EditText mUserInput;
    @BindView(R.id.start_mileage)
    EditText mStartMileageInput;
    @BindView(R.id.end_mileage)
    EditText mEndMileageInput;
    @BindView(R.id.used_mileage)
    EditText mUsedMileageInput;
    @BindView(R.id.use_reason)
    EditText mUseReasonInput;

    @BindView(R.id.save)
    ImageButton mSaveButton;
    @BindView(R.id.back)
    ImageButton mBackButton;

    CarUse data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_modify);

        ButterKnife.bind(this);


        Gson gson = new Gson();
        Intent intent = getIntent();
        int model = intent.getIntExtra("model", 0);
        data = gson.fromJson(intent.getStringExtra("carUse"), new TypeToken<CarUse>() {
        }.getType());
        initView(model);

    }

    private void initView(int model) {
        if (model == 1) {
            mTitleText.setText("修改");
            System.out.println(data);
            mCompanyInput.setText(data.getSubsidiary());
            mCarNoInput.setText(data.getCarNo());
            mDateInput.setText(data.getDate());
            mUserInput.setText(data.getUser());
            mStartMileageInput.setText("" + data.getStartMileage());
            mEndMileageInput.setText("" + data.getEndMileage());
            mUsedMileageInput.setText("" + data.getUseMileage());
            mUseReasonInput.setText(data.getUseReason());
        } else {
            mTitleText.setText("新增");
        }

        mBackButton.setOnClickListener(v -> {
            this.finish();
        });

        mSaveButton.setOnClickListener(v -> {
            data.setSubsidiary(String.valueOf(mCompanyInput.getText()));
            data.setCarNo(String.valueOf(mCarNoInput.getText()));
            data.setStartMileage(Integer.parseInt(String.valueOf(mStartMileageInput.getText())));
            data.setEndMileage(Integer.parseInt(String.valueOf(mEndMileageInput.getText())));
            data.setUseMileage(Integer.parseInt(String.valueOf(mUseReasonInput.getText())));
            data.setUseReason(String.valueOf(mUseReasonInput.getText()));
            data.setUser(String.valueOf(mUserInput.getText()));
            if (model == 0) {


            } else {
                new UpdateCarUseTask(data).execute();
            }
        });


    }

    public class UpdateCarUseTask extends AsyncTask<Void, Void, Boolean> {

        private final CarUse carUse;

        UpdateCarUseTask(CarUse data) {
            carUse = data;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            String url = "http://" + Constants.ip + ":" + Constants.port + "/carUse/updateCarUseInfo";

            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("subsidiary", carUse.getSubsidiary());
            requestBody.put("carNo", carUse.getCarNo());
            requestBody.put("date", carUse.getDate());
            requestBody.put("startMileage", String.valueOf(carUse.getStartMileage()));
            requestBody.put("endMileage", String.valueOf(carUse.getEndMileage()));
            requestBody.put("useMileage", String.valueOf(carUse.getUseMileage()));
            requestBody.put("useReason", carUse.getUseReason());
            requestBody.put("user", carUse.getUser());

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
