package nju.erpclient.activity.CarAcvivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import nju.erpclient.R;
import nju.erpclient.vo.CarUse;

import java.util.List;

public class CarItemAdapter extends ArrayAdapter<CarUse> {

    private int resourceId;

    public CarItemAdapter(Context context, int resource, List<CarUse> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CarUse carUse = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        ((TextView) view.findViewById(R.id.reason)).setText(carUse.getUseReason());
        ((TextView) view.findViewById(R.id.user)).setText(carUse.getUser());
        ((TextView) view.findViewById(R.id.car_id)).setText(carUse.getCarNo());
        ((TextView) view.findViewById(R.id.date)).setText(carUse.getDate());
        ((TextView) view.findViewById(R.id.start)).setText(Float.toString(carUse.getStartMileage()));
        ((TextView) view.findViewById(R.id.end)).setText(Float.toString(carUse.getEndMileage()));
        ((TextView) view.findViewById(R.id.used)).setText(Float.toString(carUse.getUseMileage()));
        return view;
    }
}
