package amap.android_multiple_infowindows;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class MyShopsActivity extends AppCompatActivity {

    TextView tv_username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_shops);

        tv_username = (TextView) findViewById(R.id.username);
        /*获取Intent中的Bundle对象*/
        Bundle bundle = this.getIntent().getExtras();

        /*获取Bundle中的数据，注意类型和key*/
        String username = bundle.getString("username");

        tv_username.setText(username);
    }
}