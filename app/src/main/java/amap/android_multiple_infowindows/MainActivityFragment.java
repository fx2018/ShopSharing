package amap.android_multiple_infowindows;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivityFragment extends FragmentActivity implements OnClickListener {
    /*
    // 底部菜单4个Linearlayout
    private LinearLayout ll_home;
    private LinearLayout ll_address;
    private LinearLayout ll_friend;
    private LinearLayout ll_setting;

    // 底部菜单4个ImageView
    private ImageView iv_home;
    private ImageView iv_address;
    private ImageView iv_friend;
    private ImageView iv_setting;

    // 底部菜单4个菜单标题
    private TextView tv_home;
    private TextView tv_address;
    private TextView tv_friend;
    private TextView tv_setting;
    */

    // 4个Fragment
    private Fragment homeFragment;
    private Fragment addressFragment;
    private Fragment friendFragment;
    private Fragment settingFragment;

    private RadioButton ll_map;
    private RadioButton ll_friend;
    private RadioButton ll_find;
    private RadioButton ll_mine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化控件
        initView();
        // 初始化底部按钮事件
        initEvent();
        // 初始化并设置当前Fragment
        initFragment(0);

    }

    private void initFragment(int index) {
        // 由于是引用了V4包下的Fragment，所以这里的管理器要用getSupportFragmentManager获取
        FragmentManager fragmentManager = getSupportFragmentManager();
        // 开启事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 隐藏所有Fragment
        hideFragment(transaction);
        switch (index) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.fl_content, homeFragment);
                } else {
                    transaction.show(homeFragment);
                }
                break;
            case 1:
                if (addressFragment == null) {
                    //addressFragment = new AddressFragment();
                    transaction.add(R.id.fl_content, addressFragment);
                } else {
                    transaction.show(addressFragment);
                }

                break;
            case 2:
                if (friendFragment == null) {
                    //friendFragment = new FriendFragment();
                    transaction.add(R.id.fl_content, friendFragment);
                } else {
                    transaction.show(friendFragment);
                }

                break;
            case 3:
                if (settingFragment == null) {
                    //settingFragment = new SettingFragment();
                    transaction.add(R.id.fl_content, settingFragment);
                } else {
                    transaction.show(settingFragment);
                }

                break;

            default:
                break;
        }

        // 提交事务
        transaction.commit();

    }

    //隐藏Fragment
    private void hideFragment(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (addressFragment != null) {
            transaction.hide(addressFragment);
        }
        if (friendFragment != null) {
            transaction.hide(friendFragment);
        }
        if (settingFragment != null) {
            transaction.hide(settingFragment);
        }

    }

    private void initEvent() {
        // 设置按钮监听
        ll_map.setOnClickListener(this);
        ll_friend.setOnClickListener(this);
        ll_find.setOnClickListener(this);
        ll_mine.setOnClickListener(this);

    }

    private void initView() {

        /*
        // 底部菜单4个Linearlayout
        this.ll_home = (RadioButton) findViewById(R.id.radio_map);
        this.ll_address = (LinearLayout) findViewById(R.id.radio_friend);
        this.ll_friend = (LinearLayout) findViewById(R.id.radio_find);
        this.ll_setting = (LinearLayout) findViewById(R.id.radio_mine);

        // 底部菜单4个ImageView
        this.iv_home = (ImageView) findViewById(R.id.iv_home);
        this.iv_address = (ImageView) findViewById(R.id.iv_address);
        this.iv_friend = (ImageView) findViewById(R.id.iv_friend);
        this.iv_setting = (ImageView) findViewById(R.id.iv_setting);

        // 底部菜单4个菜单标题
        this.tv_home = (TextView) findViewById(R.id.tv_home);
        this.tv_address = (TextView) findViewById(R.id.tv_address);
        this.tv_friend = (TextView) findViewById(R.id.tv_friend);
        this.tv_setting = (TextView) findViewById(R.id.tv_setting);
        */
        this.ll_map = (RadioButton) findViewById(R.id.radio_map);;
        this.ll_friend = (RadioButton) findViewById(R.id.radio_friend);;
        this.ll_find = (RadioButton) findViewById(R.id.radio_find);;
        this.ll_mine = (RadioButton) findViewById(R.id.radio_mine);;
    }

    @Override
    public void onClick(View v) {

        // 在每次点击后将所有的底部按钮(ImageView,TextView)颜色改为灰色，然后根据点击着色
        restartBotton();
        // ImageView和TetxView置为绿色，页面随之跳转
        switch (v.getId()) {
            case R.id.radio_map:
                //iv_home.setImageResource(R.drawable.tab_weixin_pressed);
                //tv_home.setTextColor(0xff1B940A);
                initFragment(0);
                break;
            case R.id.radio_friend:
                //iv_address.setImageResource(R.drawable.tab_address_pressed);
                //tv_address.setTextColor(0xff1B940A);
                initFragment(1);
                break;
            case R.id.radio_find:
                //iv_friend.setImageResource(R.drawable.tab_find_frd_pressed);
                //tv_friend.setTextColor(0xff1B940A);
                initFragment(2);
                break;
            case R.id.radio_mine:
                //iv_setting.setImageResource(R.drawable.tab_find_frd_pressed);
                //tv_setting.setTextColor(0xff1B940A);
                initFragment(3);
                break;

            default:
                break;
        }

    }

    private void restartBotton() {
        /*
        // ImageView置为灰色
        iv_home.setImageResource(R.drawable.tab_weixin_normal);
        iv_address.setImageResource(R.drawable.tab_address_normal);
        iv_friend.setImageResource(R.drawable.tab_find_frd_normal);
        iv_setting.setImageResource(R.drawable.tab_settings_normal);
        // TextView置为白色
        tv_home.setTextColor(0xffffffff);
        tv_address.setTextColor(0xffffffff);
        tv_friend.setTextColor(0xffffffff);
        tv_setting.setTextColor(0xffffffff);

         */
    }

}