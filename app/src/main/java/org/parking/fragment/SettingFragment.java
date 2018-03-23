package org.parking.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fragmentproject.R;

import org.parking.activity.LoginResultActivity;
import org.parking.activity.MainActivity;
import org.parking.activity.pageForwardActivity;
import org.parking.constant.Constant;

public class SettingFragment extends BaseFragment {
	private TextView btnInto1;
	private TextView btnInto2;
	private TextView btnInto3;
	private TextView btnInto4;
	private TextView btnInto5;
	private TextView btnInto6;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View settingLayout = inflater.inflate(R.layout.setting_layout,
				container, false);
		btnInto1 = (TextView) settingLayout.findViewById(R.id.btnInto1);
		btnInto2 = (TextView) settingLayout.findViewById(R.id.btnInto2);
		btnInto3 = (TextView) settingLayout.findViewById(R.id.btnInto3);
		btnInto4 = (TextView) settingLayout.findViewById(R.id.btnInto4);
		btnInto5 = (TextView) settingLayout.findViewById(R.id.btnInto5);
		btnInto6 = (TextView) settingLayout.findViewById(R.id.btnInto6);
		btnInto1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(),"当前页面即将转入：流量统计",Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(getActivity(),pageForwardActivity.class);
				startActivity(intent);
			}
		});
		btnInto2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(),"当前页面即将转入：消息通知",Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(getActivity(),pageForwardActivity.class);
				startActivity(intent);
			}
		});
		btnInto3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(),"当前页面即将转入：清除缓存",Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(getActivity(),pageForwardActivity.class);
				startActivity(intent);
			}
		});
		btnInto4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(),"当前页面即将转入：反馈与建议",Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(getActivity(),pageForwardActivity.class);
				startActivity(intent);
			}
		});
		btnInto5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(),"当前页面即将转入：分享给朋友",Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(getActivity(),pageForwardActivity.class);
				startActivity(intent);
			}
		});
		btnInto6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(),"当前页面即将转入：关于",Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(getActivity(),pageForwardActivity.class);
				startActivity(intent);
			}
		});

		return settingLayout;
	}
	
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	
		MainActivity.currFragTag = Constant.FRAGMENT_FLAG_SETTING;
		
	}


	public static class MyServiceFragment extends BaseFragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View newsLayout = inflater.inflate(R.layout.near_service_layout, container,
                    false);
            return newsLayout;
        }


        @Override
        public void onResume() {
            // TODO Auto-generated method stub
            super.onResume();

            MainActivity.currFragTag = Constant.FRAGMENT_FLAG_NEWS;
        }


    }
}
