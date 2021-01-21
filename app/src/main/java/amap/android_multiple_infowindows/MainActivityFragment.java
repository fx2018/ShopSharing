package amap.android_multiple_infowindows;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;




public class MainActivityFragment extends FragmentActivity implements OnClickListener {

    // 底部菜单4个Linearlayout
    private LinearLayout ll_home;
    private LinearLayout ll_fav;
    private LinearLayout ll_adshop;
    private LinearLayout ll_mine;

    // 底部菜单4个ImageView
    private ImageView iv_home;
    private ImageView iv_fav;
    private ImageView iv_adshop;
    private ImageView iv_mine;

    // 4个Fragment
    private Fragment homeFragment;
    private Fragment favFragment;
    private Fragment adshopFragment;
    private Fragment mineFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment);

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
                if (favFragment == null) {
                    favFragment = new FavFragment();
                    transaction.add(R.id.fl_content, favFragment);
                } else {
                    transaction.show(favFragment);
                }

                break;
            case 2:
                if (adshopFragment == null) {
                    adshopFragment = new AddShopFragment();
                    transaction.add(R.id.fl_content, adshopFragment);
                } else {
                    transaction.show(adshopFragment);
                }

                break;
            case 3:
                if (mineFragment == null) {
                    mineFragment = new UserFragment();
                    transaction.add(R.id.fl_content, mineFragment);
                } else {
                    transaction.show(mineFragment);
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
        if (favFragment != null) {
            transaction.hide(favFragment);
        }
        if (adshopFragment != null) {
            transaction.hide(adshopFragment);
        }
        if (mineFragment != null) {
            transaction.hide(mineFragment);
        }

    }

    private void initEvent() {
        // 设置按钮监听
        ll_home.setOnClickListener(this);
        ll_fav.setOnClickListener(this);
        ll_adshop.setOnClickListener(this);
        ll_mine.setOnClickListener(this);

    }

    private void initView() {


        // 底部菜单4个Linearlayout

        // 底部菜单4个ImageView
        this.iv_home = (ImageView) findViewById(R.id.iv_home);
        this.iv_fav = (ImageView) findViewById(R.id.iv_fav);
        this.iv_adshop = (ImageView) findViewById(R.id.iv_adshop);
        this.iv_mine = (ImageView) findViewById(R.id.iv_mine);

        // 底部菜单4个菜单标题
        //this.tv_home = (TextView) findViewById(R.id.tv_home);
        //this.tv_friends = (TextView) findViewById(R.id.tv_friends);
        //this.tv_found = (TextView) findViewById(R.id.tv_found);
        //this.tv_mine = (TextView) findViewById(R.id.tv_mine);

        this.ll_home = (LinearLayout) findViewById(R.id.ll_home);;
        this.ll_fav = (LinearLayout) findViewById(R.id.ll_fav);;
        this.ll_adshop = (LinearLayout) findViewById(R.id.ll_adshop);;
        this.ll_mine = (LinearLayout) findViewById(R.id.ll_mine);;
    }

    @Override
    public void onClick(View v) {

        // 在每次点击后将所有的底部按钮(ImageView,TextView)颜色改为灰色，然后根据点击着色
        restartBotton();
        // ImageView和TetxView置为绿色，页面随之跳转
        switch (v.getId()) {
            case R.id.ll_home:
                //iv_home.setImageResource(R.drawable.tab_weixin_pressed);
                //tv_home.setTextColor(0xff1B940A);
                initFragment(0);
                break;
            case R.id.ll_fav:
                //iv_address.setImageResource(R.drawable.tab_address_pressed);
                //tv_address.setTextColor(0xff1B940A);
                if(UserFragment.isLogIn == true) {
                    //initFragment(1);
                }
                break;
            case R.id.ll_adshop:
                //iv_friend.setImageResource(R.drawable.tab_find_frd_pressed);
                //tv_friend.setTextColor(0xff1B940A);
                if((UserFragment.isLogIn == true)&&(HomeFragment.isMapClicked == true)) {
                    initFragment(2);
                }
                break;
            case R.id.ll_mine:
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
        tv_friends.setTextColor(0xffffffff);
        tv_found.setTextColor(0xffffffff);
        tv_mine.setTextColor(0xffffffff);
 */

    }

}