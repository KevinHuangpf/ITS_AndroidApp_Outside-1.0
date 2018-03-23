package org.parking.fragment;

import java.util.ArrayList;
import java.util.List;

import org.parking.activity.MainActivity;
import org.parking.bean.MessageBean;
import org.parking.constant.Constant;
import org.parking.fragment.adapter.MessageAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;

import com.example.fragmentproject.R;

public class ParkingFragment extends BaseFragment implements LocationSource, AMapLocationListener {

	private static final String TAG = "ParkingFragment";
	private MainActivity mMainActivity ;
	//	private ListView mListView;
	private MessageAdapter mMsgAdapter;
	private List<MessageBean> mMsgBean = new ArrayList<MessageBean>();
	private AMap aMap;
	private MapView mapView;
	private OnLocationChangedListener mListener;
	private AMapLocationClient mlocationClient;
	private AMapLocationClientOption mLocationOption;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View messageLayout = inflater.inflate(R.layout.parking_layout,
				container, false);
		Log.d(TAG, "onCreateView---->");
		mMainActivity = (MainActivity) getActivity();
		mFragmentManager = getActivity().getFragmentManager();
//		mListView = (ListView)messageLayout.findViewById(R.id.listview_message);
		mapView = (MapView)messageLayout.findViewById(R.id.map);
		mapView.onCreate(savedInstanceState);// 此方法必须重写
		init();
//		mMsgAdapter = new MessageAdapter(mMsgBean, mMainActivity);
//		mapView.setAdapter(mMsgAdapter);
//		mListView.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				// TODO Auto-generated method stub
//				Toast.makeText(mMainActivity, mMsgBean.get(position).toString(),
//						Toast.LENGTH_SHORT).show();
//			}
//
//		});
		return messageLayout;
	}


	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		Log.e(TAG, "onAttach-----");

	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.e(TAG, "onCreate------");

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Log.e(TAG, "onActivityCreated-------");
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.e(TAG, "onStart----->");
	}

//	@Override
//	public void onResume() {
//		// TODO Auto-generated method stub
//		super.onResume();
//		Log.e(TAG, "onResume---->");
//		MainActivity.currFragTag = Constant.FRAGMENT_FLAG_MESSAGE;
//	}

//	@Override
//	public void onPause() {
//		// TODO Auto-generated method stub
//		super.onPause();
//		Log.e(TAG, "onPause");
//	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.e(TAG, "onStop");
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		Log.e(TAG, "onDestroyView");
	}
//	@Override
//	public void onDestroy() {
//		// TODO Auto-generated method stub
//		super.onDestroy();
//		Log.e(TAG, "onDestroy");
//	}

	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
		Log.d(TAG, "onDetach------");

	}
	/**
	 * 初始化AMap对象
	 */
	private void init() {
		if (aMap == null) {
			aMap = mapView.getMap();
			setUpMap();
		}
	}

	/**
	 * 设置一些amap的属性
	 */
	private void setUpMap() {
		// 自定义系统定位小蓝点
		MyLocationStyle myLocationStyle = new MyLocationStyle();
		myLocationStyle.myLocationIcon(BitmapDescriptorFactory
				.fromResource(R.drawable.location_marker));// 设置小蓝点的图标
		myLocationStyle.strokeColor(Color.BLACK);// 设置圆形的边框颜色
		myLocationStyle.radiusFillColor(Color.argb(100, 0, 0, 180));// 设置圆形的填充颜色
		// myLocationStyle.anchor(int,int)//设置小蓝点的锚点
		myLocationStyle.strokeWidth(1.0f);// 设置圆形的边框粗细
		aMap.setMyLocationStyle(myLocationStyle);
		aMap.setLocationSource(this);// 设置定位监听
		aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
		aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
		aMap.addMarker(new MarkerOptions().position(new LatLng(34.341568, 108.940174)).title("印象城A区停车场 \n规划车位:500 \n可用车位:73"));
		aMap.addMarker(new MarkerOptions().position(new LatLng(34.331568, 108.930174)).title("百盛BS停车场 \n规划车位:450 \n可用车位:123"));
		aMap.addMarker(new MarkerOptions().position(new LatLng(34.231568, 108.830174)).title("印象城A区停车场 \n规划车位:500 \n可用车位:73"));
		aMap.addMarker(new MarkerOptions().position(new LatLng(34.341568, 108.830174)).title("赛诺有限公司地下停车场 \n规划车位:500 \n可用车位:13"));
		aMap.addMarker(new MarkerOptions().position(new LatLng(34.141568, 108.830174)).title("印象城A区停车场 \n规划车位:500 \n可用车位:73"));
		aMap.addMarker(new MarkerOptions().position(new LatLng(34.241568, 108.920174)).title("印象城B区停车场 \n规划车位:500 \n可用车位:24"));
		aMap.addMarker(new MarkerOptions().position(new LatLng(34.201568, 108.920174)).title("宏景停车场 \n规划车位:300 \n可用车位:7"));
		aMap.addMarker(new MarkerOptions().position(new LatLng(34.131568, 108.920174)).title("印象城A区停车场 \n规划车位:500 \n可用车位:73"));
		aMap.addMarker(new MarkerOptions().position(new LatLng(34.341568, 109.040174)).title("Sink Parking \n规划车位:300 \n可用车位:223"));
		aMap.addMarker(new MarkerOptions().position(new LatLng(34.331568, 109.030174)).title("印象城A区停车场 \n规划车位:500 \n可用车位:73"));
		aMap.addMarker(new MarkerOptions().position(new LatLng(34.231568, 109.130174)).title("世纪金源大饭店-停车场 \n规划车位:2000 \n可用车位:1033"));
		aMap.addMarker(new MarkerOptions().position(new LatLng(34.341568, 109.130174)).title("印象城A区停车场 \n规划车位:500 \n可用车位:73"));
		aMap.addMarker(new MarkerOptions().position(new LatLng(34.141568, 109.230174)).title("金花路 停车场 \n规划车位:200 \n可用车位:73"));
		aMap.addMarker(new MarkerOptions().position(new LatLng(34.241568, 109.220174)).title("汤峪温泉碧水湾-停车场 \n规划车位:500 \n可用车位:123"));
		aMap.addMarker(new MarkerOptions().position(new LatLng(34.201568, 109.020174)).title("印象城A区停车场 \n规划车位:500 \n可用车位:73"));
		aMap.addMarker(new MarkerOptions().position(new LatLng(34.131568, 109.420174)).title("启虹1区停车场 \n规划车位:300 \n可用车位:143"));

		// aMap.setMyLocationType()
	}

	/**
	 * 方法必须重写
	 */
	@Override
	public void onResume() {
		super.onResume();
		aMap = mapView.getMap();
		setUpMap();
//		mapView.onResume();
	}

	/**
	 * 方法必须重写
	 */
	@Override
	public void onPause() {
		super.onPause();
		mapView.onPause();
		deactivate();
	}

	/**
	 * 方法必须重写
	 */
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mapView.onSaveInstanceState(outState);
	}

	/**
	 * 方法必须重写
	 */
	@Override
	public void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
	}

	/**
	 * 定位成功后回调函数
	 */
	@Override
	public void onLocationChanged(AMapLocation amapLocation) {
		if (mListener != null && amapLocation != null) {
			if (amapLocation != null
					&& amapLocation.getErrorCode() == 0) {
				mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
			} else {
				String errText = "定位失败," + amapLocation.getErrorCode() + ": " + amapLocation.getErrorInfo();
				Log.e("AmapErr", errText);
			}
		}
	}

	/**
	 * 激活定位
	 */
	@Override
	public void activate(OnLocationChangedListener listener) {
		mListener = listener;
		if (mlocationClient == null) {
			mlocationClient = new AMapLocationClient(mMainActivity);
			mLocationOption = new AMapLocationClientOption();
			//设置定位监听
			mlocationClient.setLocationListener(this);
			//设置为高精度定位模式
			mLocationOption.setLocationMode(AMapLocationMode.Hight_Accuracy);
			//设置定位参数
			mlocationClient.setLocationOption(mLocationOption);
			// 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
			// 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
			// 在定位结束后，在合适的生命周期调用onDestroy()方法
			// 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
			mlocationClient.startLocation();
		}
	}

	/**
	 * 停止定位
	 */
	@Override
	public void deactivate() {
		mListener = null;
		if (mlocationClient != null) {
			mlocationClient.stopLocation();
			mlocationClient.onDestroy();
		}
		mlocationClient = null;
	}



}
