package amap.android_multiple_infowindows;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;


public class ShopDetailsActivity extends AppCompatActivity {

    WebView webView;
    String uri = "http://player.bilibili.com/player.html?aid=542806233&bvid=BV1Xi4y1L76s&cid=257770318";
    public Double curLocX = 0.0;
    public Double curLocY = 0.0;

    public Double destLocX = 0.0;
    public Double destLocY = 0.0;

    Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_details);


        //get location data from HomeFragment
        getLocDataToNavi();

        /*获取Intent中的Bundle对象*/
        bundle = this.getIntent().getExtras();

        /*获取Bundle中的数据，注意类型和key*/
        String shopDescp = bundle.getString("shopDesc");

        if(shopDescp!="")
        {
            uri = shopDescp.toString();
        }

        new Thread(new ListenBuyButton()).start();

        webView = (WebView)this.findViewById(R.id.webview);

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress){
                if(newProgress==100){
                    // 这里是设置activity的标题， 也可以根据自己的需求做一些其他的操作
                    //title.setText("加载完成");
                }else{
                    //title.setText("加载中.......");

                }
            }
        });

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, android.net.http.SslError error) {
                // 重写此方法可以让webview处理https请求
                handler.proceed();
                //super.onReceivedSslError(view, handler, error);
            }
        });

        webView.getSettings().setJavaScriptEnabled(true);

        loadURI(uri);
    }

    public void getLocDataToNavi()
    {
        bundle = this.getIntent().getExtras();

        /*获取Bundle中的数据，注意类型和key*/
        curLocX = bundle.getDouble("curLocX");
        curLocY = bundle.getDouble("curLocY");

        destLocX = bundle.getDouble("destLocX");
        destLocY = bundle.getDouble("destLocY");


    }

    public void transLocDataToNavi()
    {
        Intent intent = new Intent();
        ComponentName cn = new ComponentName("amap.android_multiple_infowindows", "amap.android_multiple_infowindows.SimpleNaviActivity");

        intent.setComponent(cn);

        //stopService(intent);

        /* 通过Bundle对象存储需要传递的数据 */
        Bundle bundle = new Bundle();
        /*字符、字符串、布尔、字节数组、浮点数等等，都可以传*/
        bundle.putDouble("curLocX", curLocX);
        bundle.putDouble("curLocY", curLocY);
        bundle.putDouble("destLocX", destLocX);
        bundle.putDouble("destLocY", destLocY);

        /*把bundle对象assign给Intent*/
        intent.putExtras(bundle);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);

        startActivity(intent);
    }

    private void loadURI(String uri)
    {
        //load URI
        webView.loadUrl(uri);

    }

    private String getItemName()
    {
        return "";
    }

    private String getShopID()
    {
        return "";
    }

    private String getUserName()
    {
        return "";
    }

    private String getUserAddr()
    {
        return "";
    }

    private boolean callAliPay()
    {
        return false;
    }

    public void submitDataToMeiTuan(String username, String shopid, String itemname, String useraddr)
    {

    }

    public class ListenBuyButton implements Runnable {

        @Override
        public void run() {

            Button btnBuy = (Button) findViewById(R.id.button_buy);
            btnBuy.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    submitDataToMeiTuan(getUserName(),getShopID(),getItemName(),getUserAddr());
                    if(!callAliPay())
                    {

                    }
                }
            });

            Button btnNavi = (Button) findViewById(R.id.button_navi);
            btnNavi.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    //trans location data to SimpleNaviActivity
                    transLocDataToNavi();
                }
            });
        }
    }
}
