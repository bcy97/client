package nju.erpclient.activity.CarAcvivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CarUseActivity extends AppCompatActivity {

    private List<CarUse> cars = new ArrayList<>();

    @BindView(R.id.car_list)
    ListView mCarList;

    @BindView(R.id.add)
    ImageButton mAddButton;
    @BindView(R.id.back)
    ImageButton mBackButton;

    SharedPreferences sharedPreferences;
    String mUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_use);
        ButterKnife.bind(this);

        sharedPreferences = getSharedPreferences("UserInfo", 0);
        mUsername = sharedPreferences.getString("userName", "");

        initView();

    }

    private void initView() {
        mAddButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), CarModifyActivity.class);
            intent.putExtra("model", 0);
            startActivity(intent);
        });
        mBackButton.setOnClickListener(v -> {
            this.finish();
        });
        new GetCarUseTask(mUsername).execute();
    }

    private class GetCarUseTask extends AsyncTask<Void, Void, ArrayList<CarUse>> {

        private final String mUsername;

        GetCarUseTask(String username) {
            mUsername = username;
        }

        @Override
        protected ArrayList<CarUse> doInBackground(Void... params) {

            String url = "http://" + Constants.ip + ":" + Constants.port + "/carUse/getCarUseInfo";

            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("userName", mUsername);
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
                    return gson.fromJson(res, new TypeToken<ArrayList<CarUse>>() {
                    }.getType());
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(final ArrayList<CarUse> carUses) {
            Gson gson = new Gson();
            if (carUses != null) {
                for (CarUse car : carUses) {
                    cars.add(car);
                }

            }
            CarItemAdapter adapter = new CarItemAdapter(CarUseActivity.this, R.layout.car_item, cars);
            mCarList.setAdapter(adapter);

            mCarList.setOnItemClickListener((parent, view, position, id) -> {
                Intent intent = new Intent(getApplicationContext(), CarModifyActivity.class);
                intent.putExtra("carUse", gson.toJson(cars.get(position)));
                intent.putExtra("model", 1);//0 : add; 1 : modify
                startActivity(intent);
            });
        }

    }


}
