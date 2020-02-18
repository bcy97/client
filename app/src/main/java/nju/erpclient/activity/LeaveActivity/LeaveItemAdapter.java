package nju.erpclient.activity.LeaveActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import nju.erpclient.R;
import nju.erpclient.vo.Leave;

import java.util.List;

public class LeaveItemAdapter extends ArrayAdapter<Leave> {
    private int resourceId;

    public LeaveItemAdapter(Context context, int resource, List<Leave> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Leave leave = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        ((TextView) view.findViewById(R.id.type)).setText(leave.getType());
        ((TextView) view.findViewById(R.id.company)).setText(leave.getSubsidiary());
        ((TextView) view.findViewById(R.id.start)).setText(leave.getStartTime());
        ((TextView) view.findViewById(R.id.end)).setText(leave.getEndTime());
        ((TextView) view.findViewById(R.id.remarks)).setText(leave.getRemark());

        return view;
    }
}
