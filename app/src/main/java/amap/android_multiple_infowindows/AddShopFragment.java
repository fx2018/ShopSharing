package amap.android_multiple_infowindows;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class AddShopFragment extends Fragment{

    public View m_view;
    private EditText etShopName;
    private EditText etType;
    private EditText etLocationX;
    private EditText etLocationY;
    private EditText etDetails;
    private TextView tv1;

    //public static final String URL = "http://192.168.43.75:8080/ServLetTest/";
    //public static final String URL = "http://192.168.31.158:8080/ServLetTest/";
    //public static final String URL_ShopRegister = URL + "shopRegisterServlet";
    public static final String URL = "http://us-or-aws.sakurafrp.com:35001/";
    public static final String URL_recShopData = URL + "recShopData.aspx";
    //public static final String URL_Login = URL + "loginServlet";

    public class MyAsyncTask extends AsyncTask<String, Integer, String> {
        private TextView tv;

        public MyAsyncTask(TextView v) {
            tv = v;
        }

        @Override
        protected void onPreExecute() {
            Log.w("feifei", "task onPreExecute()");
        }
        @Override
        protected String doInBackground(String... params) {
            Log.w("WangJ", "task doInBackground()");
            HttpURLConnection connection = null;
            StringBuilder response = new StringBuilder();
            try {
                java.net.URL url = new URL(params[0]); // 声明一个URL,注意如果用百度首页实验，请使用https开头，否则获取不到返回报文
                connection = (HttpURLConnection) url.openConnection(); // 打开该URL连接
                connection.setRequestMethod("GET"); // 设置请求方法，“POST或GET”，我们这里用GET，在说到POST的时候再用POST
                connection.setConnectTimeout(80000); // 设置连接建立的超时时间
                connection.setReadTimeout(80000); // 设置网络报文收发超时时间
                InputStream in = connection.getInputStream();  // 通过连接的输入流获取下发报文，然后就是Java的流处理
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response.toString(); // 这里返回的结果就作为onPostExecute方法的入参
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // 如果在doInBackground方法，那么就会立刻执行本方法
            // 本方法在UI线程中执行，可以更新UI元素，典型的就是更新进度条进度，一般是在下载时候使用
        }

        @Override
        protected void onPostExecute(String s) {
            tv.setText(s);
            if(s.contains("code:200"))
            {
                //Intent intent = new Intent();
                //ComponentName cn = new ComponentName("amap.android_multiple_infowindows", "amap.android_multiple_infowindows.MainActivity_bak");
                //param1:Activity所在应用的包名
                //param2:Activity的包名+类名
                //intent.setComponent(cn);
                //startActivity(intent);
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        m_view = inflater.inflate(R.layout.activity_add_shop, container, false);
        new Thread(new ListenAddShopButton()).start();


        /*获取Intent中的Bundle对象*/
        //Bundle bundle = m_view.getContext();

        /*获取Bundle中的数据，注意类型和key*/
        //double locationX = bundle.getDouble("locationX");
        //double locationY = bundle.getDouble("locationY");

        double locationX = HomeFragment.getLocation_mk().latitude;
        double locationY = HomeFragment.getLocation_mk().longitude;


        etShopName = (EditText) m_view.findViewById(R.id.editText);
        etType = (EditText) m_view.findViewById(R.id.editText4);
        etLocationX = (EditText) m_view.findViewById(R.id.editText2);
        etLocationY = (EditText) m_view.findViewById(R.id.editText3);
        etDetails = (EditText) m_view.findViewById(R.id.editText5);
        tv1 = (TextView) m_view.findViewById(R.id.textView7);

        etLocationX.setText(Double.toString(locationX));
        etLocationY.setText(Double.toString(locationY));

        Toast.makeText(m_view.getContext(), "locationX: "+ locationX + "locationY: "+ locationY, Toast.LENGTH_LONG).show();

        return m_view;

    }

    private void shopRegister(String shopName, String type, String locationX, String locationY, String details) {
        String shopRegisterUrlStr = URL_recShopData + "?companyName=" + shopName + "&type=" + type + "&locationX=" + locationX + "&locationY=" + locationY + "&shopDesc=" + details;
        new MyAsyncTask(tv1).execute(shopRegisterUrlStr);
    }

    public class ListenAddShopButton implements Runnable {

        @Override
        public void run() {

            Button btnSubmit = (Button) m_view.findViewById(R.id.button);
            btnSubmit.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if (!TextUtils.isEmpty(etShopName.getText().toString())
                            && !TextUtils.isEmpty(etType.getText().toString())
                            && !TextUtils.isEmpty(etLocationX.getText().toString())
                            && !TextUtils.isEmpty(etLocationY.getText().toString())
                            && !TextUtils.isEmpty(etDetails.getText().toString())
                    ) {
                        shopRegister(etShopName.getText().toString(), etType.getText().toString(), etLocationX.getText().toString(), etLocationY.getText().toString(), etDetails.getText().toString());
                    } else {
                        //Toast.makeText(this, "都不能为空！", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}