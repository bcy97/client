package nju.erpclient.activity.LeaveActivity;

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
import nju.erpclient.vo.Leave;
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

public class LeaveActivity extends AppCompatActivity {


    @BindView(R.id.leave_list)
    ListView mLeaveList;
    @BindView(R.id.add)
    ImageButton mAddButton;
    @BindView(R.id.back)
    ImageButton mBackBUtton;

    SharedPreferences sharedPreferences;
    String mUsername;

    private List<Leave> leaves = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);
        ButterKnife.bind(this);

        sharedPreferences = getSharedPreferences("UserInfo", 0);
        mUsername = sharedPreferences.getString("userName", "");

        initViews();

    }

    private void initViews() {

        mAddButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), LeaveModifyActivity.class);
            intent.putExtra("mode", "add");
            startActivity(intent);
        });

        mBackBUtton.setOnClickListener(v -> {
            this.finish();
        });

        new GetLeaveTask(mUsername).execute();
    }

    private class GetLeaveTask extends AsyncTask<Void, Void, ArrayList<Leave>> {

        private final String mUsername;

        GetLeaveTask(String username) {
            mUsername = username;
        }

        @Override
        protected ArrayList<Leave> doInBackground(Void... params) {

            String url = "http://" + Constants.ip + ":" + Constants.port + "/leave/getLeaveInfo";

            Map<String, String> requestBody = new HashMap<>();
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
                    return gson.fromJson(res, new TypeToken<ArrayList<Leave>>() {
                    }.getType());
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(final ArrayList<Leave> data) {
            Gson gson = new Gson();
            if (data != null) {
                leaves = data;
            }
            LeaveItemAdapter adapter = new LeaveItemAdapter(LeaveActivity.this, R.layout.leave_item, leaves);
            mLeaveList.setAdapter(adapter);

            mLeaveList.setOnItemClickListener((parent, view, position, id) -> {
                Intent intent = new Intent(getApplicationContext(), LeaveModifyActivity.class);
                intent.putExtra("mode", "modify");
                intent.putExtra("leave", gson.toJson(leaves.get(position)));
                startActivity(intent);
            });
        }

    }

}
