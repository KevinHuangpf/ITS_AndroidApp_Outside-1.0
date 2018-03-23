package org.parking.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fragmentproject.R;

import org.parking.activity.LoginResultActivity;
import org.parking.activity.MainActivity;
import org.parking.activity.itemInfoActivity;
import org.parking.bean.MessageBean;
import org.parking.constant.Constant;
import org.parking.fragment.adapter.MessageAdapter;

import java.util.ArrayList;
import java.util.List;

public class CarportInfoFragment extends BaseFragment {

	private static final String TAG = "CarportInfoFragment";
	private MainActivity mMainActivity ;
	private ListView mListView;
	private MessageAdapter mMsgAdapter;
	private List<MessageBean> mMsgBean = new ArrayList<MessageBean>();
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View messageLayout = inflater.inflate(R.layout.carportinfolayout,
				container, false);
		Log.d(TAG, "onCreateView---->");
		mMainActivity = (MainActivity) getActivity();
		mFragmentManager = getActivity().getFragmentManager();
		mListView = (ListView)messageLayout.findViewById(R.id.listview_message);
		mMsgAdapter = new MessageAdapter(mMsgBean, mMainActivity);
		mListView.setAdapter(mMsgAdapter);
		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),itemInfoActivity.class);
				startActivity(intent);
				Toast.makeText(mMainActivity, mMsgBean.get(position).toString(),
						Toast.LENGTH_SHORT).show();

			}

		});
		return messageLayout;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.e(TAG, "onCreate------");
		mMsgBean.add(new MessageBean(R.drawable.images3, " 印象城A区停车场", " 规划车位:500 当前空闲车位:073 ", "距离80m"));
		mMsgBean.add(new MessageBean(R.drawable.imgres1, " 印象城B区停车场", " 规划车位:500 当前空闲车位:024 ", "距离95m"));
		mMsgBean.add(new MessageBean(R.drawable.imgres2, " 宏景停车场", " 规划车位:300 当前空闲车位:07 ", "距离133m"));
		mMsgBean.add(new MessageBean(R.drawable.images3, " 百盛BS停车场", " 停车场规划车位:450 当前空闲车位:123 ", "距离180m"));
		mMsgBean.add(new MessageBean(R.drawable.imgres4, "赛诺有限公司地下停车场", " 停车场规划车位:500 当前空闲车位:013 ", "距离230m"));
		mMsgBean.add(new MessageBean(R.drawable.images5, "启虹1区停车场", " 停车场规划车位:300 当前空闲车位:143 ", "距离800m"));
		mMsgBean.add(new MessageBean(R.drawable.images6, "电子城停车场1区", " 停车场规划车位:500 当前空闲车位:213 ", "距离1.2Km"));
		mMsgBean.add(new MessageBean(R.drawable.imgres7, "世纪金源大饭店-停车场", " 停车场规划车位:1500 当前空闲车位:333 ", "距离1.2Km"));
		mMsgBean.add(new MessageBean(R.drawable.imgres8, "电子城停车场2区", " 停车场规划车位:600 当前空闲车位:343 ", "距离1.6Km"));
		mMsgBean.add(new MessageBean(R.drawable.imgres9, "金花路 停车场", " 停车场规划车位:200 当前空闲车位:073 ", "距离1.9Km"));
		mMsgBean.add(new MessageBean(R.drawable.images3, "雁塔赛格室内停车场", " 停车场规划车位:1500 当前空闲车位:623 ", "距离2.2Km"));
		mMsgBean.add(new MessageBean(R.drawable.images10, "Sink Parking"," 停车场规划车位:300 当前空闲车位:223 ", "距离3.1Km"));
		mMsgBean.add(new MessageBean(R.drawable.images3, "钟鼓楼博物馆-停车场", " 停车场规划车位:1200 当前空闲车位:823 ", "距离5Km"));
		mMsgBean.add(new MessageBean(R.drawable.imgres4, "世纪金源大饭店-停车场", " 停车场规划车位:2000 当前空闲车位:1033 ", "距离9Km"));
		mMsgBean.add(new MessageBean(R.drawable.images5, "汤峪温泉碧水湾-停车场", " 停车场规划车位:500 当前空闲车位:123 ", "距离10Km"));

	}


	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		MainActivity.currFragTag = Constant.FRAGMENT_FLAG_CONTACTS;
	}



}
