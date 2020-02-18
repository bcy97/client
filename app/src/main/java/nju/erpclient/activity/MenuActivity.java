package nju.erpclient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import nju.erpclient.R;
import nju.erpclient.activity.CarAcvivity.CarUseActivity;
import nju.erpclient.activity.LeaveActivity.LeaveActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuActivity extends AppCompatActivity {

    @BindView(R.id.authority)
    LinearLayout mAuthorityBtn;
    @BindView(R.id.change_password)
    LinearLayout mChangePasswordBtn;
    @BindView(R.id.project)
    LinearLayout mProjectBtn;
    @BindView(R.id.receivables)
    LinearLayout mReceivablesBtn;
    @BindView(R.id.warehousing)
    LinearLayout mWarehousingBtn;
    @BindView(R.id.purchase)
    LinearLayout mPurchaseBtn;
    @BindView(R.id.expenditure)
    LinearLayout mExpenditureBtn;
    @BindView(R.id.reimbursement)
    LinearLayout mReimbursementBtn;
    @BindView(R.id.checkon)
    LinearLayout mCheckonBtn;
    @BindView(R.id.leave)
    LinearLayout mLeaveBtn;
    @BindView(R.id.car)
    LinearLayout mCarBtn;
    @BindView(R.id.work)
    LinearLayout mWorkBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ButterKnife.bind(this);

        initViews();
    }

    public void initViews() {
        mAuthorityBtn.setOnClickListener(v -> {

        });
        mChangePasswordBtn.setOnClickListener(v -> {

        });
        mProjectBtn.setOnClickListener(v -> {

        });
        mReceivablesBtn.setOnClickListener(v -> {

        });
        mWarehousingBtn.setOnClickListener(v -> {

        });
        mPurchaseBtn.setOnClickListener(v -> {

        });
        mExpenditureBtn.setOnClickListener(v -> {

        });
        mReimbursementBtn.setOnClickListener(v -> {

        });
        mCheckonBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), LeaveActivity.class);
            startActivity(intent);
        });
        mLeaveBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), LeaveActivity.class);
            startActivity(intent);
        });
        mCarBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), CarUseActivity.class);
            startActivity(intent);
        });
        mWorkBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), WorkActivity.class);
            startActivity(intent);
        });
    }
}
