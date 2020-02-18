package nju.erpclient.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import nju.erpclient.R;
import nju.erpclient.utils.Constants;
import nju.erpclient.vo.Result;
import nju.erpclient.vo.UserInfo;
import com.okhttplib.HttpInfo;
import com.okhttplib.OkHttpUtil;
import com.okhttplib.annotation.ContentType;
import com.okhttplib.annotation.Encoding;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.username)
    EditText mUsernameInput;
    @BindView(R.id.password)
    EditText mPasswordInput;
    @BindView(R.id.login)
    Button mLoginBtn;

    AlertDialog.Builder alertDialog;

    String mUsername;
    String mPassword;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        initViews();
    }

    public void initViews() {
        alertDialog = new AlertDialog.Builder(LoginActivity.this);
        mUsernameInput.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                mUsernameInput.setFocusable(true);
                return true;
            }
            return false;
        });

        mPasswordInput.setOnEditorActionListener((textView, id, keyEvent) -> {
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptLogin();
                return true;
            }
            return false;
        });
        mLoginBtn.setOnClickListener(v -> {
            attemptLogin();
        });
    }

    private void attemptLogin() {
        mUsername = String.valueOf(mUsernameInput.getText());
        mPassword = String.valueOf(mPasswordInput.getText());

        if (mUsername == null || mUsername.equals("")) {
            Toast.makeText(LoginActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
//            alertDialog.setTitle("请输入用户名");
//            alertDialog.setPositiveButton("确定", (dialog, which) -> mUsernameInput.setFocusable(true));
//            alertDialog.show();
            return;
        }
        if (mPassword == null || mPassword.equals("")) {
            Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
//            alertDialog.setTitle("请输入密码");
//            alertDialog.setPositiveButton("确定", (dialog, which) -> mPasswordInput.setFocusable(true));
//            alertDialog.show();
            return;
        }


        new UserLoginTask(mUsername, mPassword).execute();
    }

    /**
     * 用户登录异步请求
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Result<UserInfo>> {

        private final String mUsername;
        private final String mPassword;

        UserLoginTask(String username, String password) {
            mUsername = username;
            mPassword = password;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Result doInBackground(Void... params) {

            String url = "http://" + Constants.ip + ":" + Constants.port + "/user/login";

            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("userName", mUsername);
            requestBody.put("password", mPassword);
            Gson gson = new Gson();

            HttpInfo request = HttpInfo.Builder()
                    .setUrl(url)
                    .setContentType(ContentType.JSON)
                    .setResponseEncoding(Encoding.UTF_8)
                    .addParamJson(gson.toJson(requestBody))
                    .build();

            HttpInfo response = OkHttpUtil.getDefault().doPostSync(request);

            if (response.isSuccessful() && response.getRetDetail().equals("") || response.getRetDetail() == null) {
                return null;
            } else {
                return gson.fromJson(response.getRetDetail(), new TypeToken<Result<UserInfo>>() {
                }.getType());
            }
        }

        @Override
        protected void onPostExecute(final Result<UserInfo> result) {

            //如果成功跳转到加载配置的activity
            if (result.getCode() == 1) {
                UserInfo userInfo = result.getData();
                sharedPreferences = getSharedPreferences("UserInfo", 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("userName", userInfo.getUserName());
                editor.putString("role", userInfo.getRole());
                editor.putString("level", userInfo.getLevel());
                editor.putString("remark", userInfo.getRemark());
                editor.putString("subsidiary", userInfo.getSubsidiary());
                editor.commit();
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, result.getMsg(), Toast.LENGTH_SHORT).show();
                mPasswordInput.requestFocus();
            }
        }

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                if (hideInputMethod(this, v)) {
                    return true; //隐藏键盘时，其他控件不响应点击事件==》注释则不拦截点击事件
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 判断是不是点击了小键盘其他位置
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            v.getLocationInWindow(leftTop);
            int left = leftTop[0], top = leftTop[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 隐藏软键盘
     *
     * @param context
     * @param v
     * @return
     */
    private boolean hideInputMethod(Context context, View v) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            return imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
        return false;
    }

}
