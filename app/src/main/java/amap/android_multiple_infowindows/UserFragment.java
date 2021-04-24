package amap.android_multiple_infowindows;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;



public class UserFragment extends Fragment {
    public static Boolean isLogIn = false;
    public View m_view;
    //public static final String URL = "http://192.168.43.75:8080/ServLetTest/";
    public static final String URL = "http://us-la-cn2.sakurafrp.com:12484/";
    public static final String URL_Register = URL + "userRegister.aspx";
    public static final String URL_Login = URL + "userLogin.aspx";
    public String username = "";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(isLogIn == false) {
            RegisterLoginLayoutAndActivity(inflater, container, savedInstanceState);
        }
        ShowMineLayoutAndActivity(inflater, container, savedInstanceState);

        return m_view;
    }

    public void ShowMineLayoutAndActivity(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)  {
        m_view = inflater.inflate(R.layout.activity_my_shops, container, false);
        RelativeLayout re_shop = (RelativeLayout)m_view.findViewById(R.id.re_shop);
        RelativeLayout re_aboutwe = (RelativeLayout)m_view.findViewById(R.id.re_aboutwe);
        RelativeLayout re_help = (RelativeLayout)m_view.findViewById(R.id.re_help);
        RelativeLayout re_quit = (RelativeLayout)m_view.findViewById(R.id.re_quit);
        re_shop.setOnClickListener(new MineInfoClickListener());
        re_aboutwe.setOnClickListener(new MineInfoClickListener());
        re_help.setOnClickListener(new MineInfoClickListener());
        re_quit.setOnClickListener(new MineInfoClickListener());

    }


    class MineInfoClickListener implements OnClickListener{
        @Override
        public void onClick(View view)
        {
            switch (view.getId()) {
                case R.id.re_shop:
                    Toast.makeText( m_view.getContext(), "re_shop", Toast.LENGTH_SHORT).show();
                    //Intent intent = new Intent();
                    //ComponentName cn = new ComponentName("amap.android_multiple_infowindows", "amap.android_multiple_infowindows.SimpleNaviActivity");
                    //param1:Activity所在应用的包名
                    //param2:Activity的包名+类名
                    //intent.setComponent(cn);
                    /* 通过Bundle对象存储需要传递的数据 */
                    //Bundle bundle = new Bundle();
                    /*字符、字符串、布尔、字节数组、浮点数等等，都可以传*/
                    //bundle.putString("username", username);

                    /*把bundle对象assign给Intent*/
                    //intent.putExtras(bundle);

                    //startActivity(intent);
                    break;

                case R.id.re_aboutwe:
                    Toast.makeText( m_view.getContext(), "re_aboutwe", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.re_help:
                    Toast.makeText( m_view.getContext(), "re_help", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.re_quit:
                    Toast.makeText( m_view.getContext(), "re_quit", Toast.LENGTH_SHORT).show();
                    UserFragment.isLogIn = false;
                    RegisterLoginLayoutAndActivity();
                    break;
                default:
                    Log.i("relativeLayout", "当前用户选择 未实现");
            }

        }
    }


    //============================================================================================================================//
    public void RegisterLoginLayoutAndActivity(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)  {
        Intent intent = new Intent();
        ComponentName cn = new ComponentName("amap.android_multiple_infowindows", "amap.android_multiple_infowindows.RegisterActivity");
        intent.setComponent(cn);
        startActivityForResult(intent, 1);
    }

    public void RegisterLoginLayoutAndActivity()  {
        Intent intent = new Intent();
        ComponentName cn = new ComponentName("amap.android_multiple_infowindows", "amap.android_multiple_infowindows.RegisterActivity");
        intent.setComponent(cn);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        Bundle bundle = data.getExtras();
        username = bundle.getString("username");
        TextView tvusername = (TextView)m_view.findViewById(R.id.username);
        tvusername.setText(username);
        UserFragment.isLogIn = true;
    }
}