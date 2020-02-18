package nju.erpclient.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import nju.erpclient.R;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        String title = "test";

        Fragment actionBar = getSupportFragmentManager().findFragmentById(R.id.ttt);
    }
}
