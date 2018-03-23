package org.parking.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.fragmentproject.R;

import org.parking.activity.LoginResultActivity;
import org.parking.activity.MainActivity;
import org.parking.constant.Constant;

public class MyServiceFragment extends BaseFragment {

	private Button btnLogin;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View newsLayout = inflater.inflate(R.layout.near_service_layout, container,
				false);
		btnLogin = (Button) newsLayout.findViewById(R.id.btnLogin);
		btnLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(),LoginResultActivity.class);
				startActivity(intent);
			}
		});

		return newsLayout;

	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	
		MainActivity.currFragTag = Constant.FRAGMENT_FLAG_NEWS;
	}
	

}
