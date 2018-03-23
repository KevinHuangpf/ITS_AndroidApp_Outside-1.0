package org.parking.activity;


/**
 * 机构：西安电子科技大学
 * 人员：黄鹏飞
 * 功能：parking
 * 日期：2017.3~2017.6
 */

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.Toast;

import com.example.fragmentproject.R;

import org.parking.constant.Constant;
import org.parking.fragment.BaseFragment;
import org.parking.ui.BottomControlPanel;
import org.parking.ui.BottomControlPanel.BottomPanelCallback;
import org.parking.ui.HeadControlPanel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends Activity implements BottomPanelCallback {
    BottomControlPanel bottomPanel = null;
    HeadControlPanel headPanel = null;

    private FragmentManager fragmentManager = null;
    private FragmentTransaction fragmentTransaction = null;
    private final String DEBUG_TAG = "SOCKET";
    //GG
//	private ParkingFragment messageFragment;
//	private CarportInfoFragment contactsFragment;
//	private MyServiceFragment myServiceFragment;
//	private SettingFragment settingFragment;
//GG
    public static String currFragTag = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        fragmentManager = getFragmentManager();
        setDefaultFirstFragment(Constant.FRAGMENT_FLAG_MESSAGE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void initUI() {
        bottomPanel = (BottomControlPanel) findViewById(R.id.bottom_layout);
        if (bottomPanel != null) {
            bottomPanel.initBottomPanel();
            bottomPanel.setBottomCallback(this);
        }
        headPanel = (HeadControlPanel) findViewById(R.id.head_layout);
        if (headPanel != null) {
            headPanel.initHeadPanel();
        }
    }

    /* 处理BottomControlPanel的回调
     * @see org.yanzi.ui.BottomControlPanel.BottomPanelCallback#onBottomPanelClick(int)
     */
    @Override
    public void onBottomPanelClick(int itemId) {
        // TODO Auto-generated method stub
        String tag = "";
        if ((itemId & Constant.BTN_FLAG_MESSAGE) != 0) {
            tag = Constant.FRAGMENT_FLAG_MESSAGE;
        } else if ((itemId & Constant.BTN_FLAG_CONTACTS) != 0) {

            new Thread(){
                @Override
                public void run()
                {
                    Socket socket = null;
                    String sedMsg ="REQ";
                    try {

                        socket = new Socket("1675sy9233.iask.in", 40703);
                        //向服务器发送消息
                        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                        out.println(sedMsg);
                        //接收来自服务器的消息
                        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        String revMsg = br.readLine();

                        //关闭流
                        out.close();
                        br.close();
                        //关闭Socket
                        socket.close();
                    } catch (Exception e) {
                        // TODO: handle exception
                        Log.e(DEBUG_TAG, e.toString());
                    }
                }
            }.start();

            tag = Constant.FRAGMENT_FLAG_CONTACTS;
        } else if ((itemId & Constant.BTN_FLAG_NEWS) != 0) {
            tag = Constant.FRAGMENT_FLAG_NEWS;
        } else if ((itemId & Constant.BTN_FLAG_SETTING) != 0) {
            tag = Constant.FRAGMENT_FLAG_SETTING;
        }
        setTabSelection(tag); //切换Fragment
        headPanel.setMiddleTitle(tag);//切换标题
    }

    private void setDefaultFirstFragment(String tag) {
        Log.i("yan", "setDefaultFirstFragment enter... currFragTag = " + currFragTag);
        setTabSelection(tag);
        bottomPanel.defaultBtnChecked();
        Log.i("yan", "setDefaultFirstFragment exit...");
    }

    private void commitTransactions(String tag) {
        if (fragmentTransaction != null && !fragmentTransaction.isEmpty()) {
            fragmentTransaction.commit();
            currFragTag = tag;
            fragmentTransaction = null;
        }
    }

    private FragmentTransaction ensureTransaction() {
        if (fragmentTransaction == null) {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

        }
        return fragmentTransaction;

    }

    private void attachFragment(int layout, Fragment f, String tag) {
        if (f != null) {
            if (f.isDetached()) {
                ensureTransaction();
                fragmentTransaction.attach(f);

            } else if (!f.isAdded()) {
                ensureTransaction();
                fragmentTransaction.add(layout, f, tag);
            }
        }
    }

    private Fragment getFragment(String tag) {

        Fragment f = fragmentManager.findFragmentByTag(tag);

        if (f == null) {
//            Toast.makeText(getApplicationContext(), "fragment = null tag = " + tag, Toast.LENGTH_SHORT).show();
            f = BaseFragment.newInstance(getApplicationContext(), tag);
        }
        return f;

    }

    private void detachFragment(Fragment f) {

        if (f != null && !f.isDetached()) {
            ensureTransaction();
            fragmentTransaction.detach(f);
        }
    }

    /**切换fragment
     * @param tag
     */
    private void switchFragment(String tag) {
        if (TextUtils.equals(tag, currFragTag)) {
            return;
        }
        //把上一个fragment detach掉
        if (currFragTag != null && !currFragTag.equals("")) {
            detachFragment(getFragment(currFragTag));
        }
        attachFragment(R.id.fragment_content, getFragment(tag), tag);
        commitTransactions(tag);
    }

    /**设置选中的Tag
     * @param tag
     */
    public void setTabSelection(String tag) {
        // 开启一个Fragment事务
        fragmentTransaction = fragmentManager.beginTransaction();
        //GG
//		 if(TextUtils.equals(tag, Constant.FRAGMENT_FLAG_MESSAGE)){
//		   if (messageFragment == null) {
//				messageFragment = new ParkingFragment();
//			}
//
//		}else if(TextUtils.equals(tag, Constant.FRAGMENT_FLAG_CONTACTS)){
//			if (contactsFragment == null) {
//				contactsFragment = new CarportInfoFragment();
//			}
//
//		}else if(TextUtils.equals(tag, Constant.FRAGMENT_FLAG_NEWS)){
//			if (myServiceFragment == null) {
//				myServiceFragment = new MyServiceFragment();
//			}
//
//		}else if(TextUtils.equals(tag,Constant.FRAGMENT_FLAG_SETTING)){
//			if (settingFragment == null) {
//				settingFragment = new SettingFragment();
//			}
//		}else if(TextUtils.equals(tag, Constant.FRAGMENT_FLAG_SIMPLE)){
//			if (simpleFragment == null) {
//				simpleFragment = new SimpleFragment();
//			}
//
//		}
        //GG
        switchFragment(tag);

    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        currFragTag = "";
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
    }

    private  long exitTime = 0;
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK){
//            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
//            alertDialog.setTitle("提示");
//            alertDialog.setMessage("确认退出吗");
//            alertDialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    finish();
//                }
//            });
//            alertDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    return;
//                }
//            });
//            alertDialog.show();
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if ((System.currentTimeMillis()- exitTime)>2000){
                Toast.makeText(this,"再按一次退出应用程序",Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }


        return super.onKeyDown(keyCode, event);
    }
}
