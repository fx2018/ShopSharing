package amap.android_multiple_infowindows;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.amap.api.navi.AMapNavi;
import com.amap.api.navi.AMapNaviListener;
import com.amap.api.navi.AMapNaviView;
import com.amap.api.navi.enums.NaviType;
import com.amap.api.navi.model.AMapCalcRouteResult;
import com.amap.api.navi.model.AMapLaneInfo;
import com.amap.api.navi.model.AMapModelCross;
import com.amap.api.navi.model.AMapNaviCameraInfo;
import com.amap.api.navi.model.AMapNaviCross;
import com.amap.api.navi.model.AMapNaviLocation;
import com.amap.api.navi.model.AMapNaviRouteNotifyData;
import com.amap.api.navi.model.AMapNaviTrafficFacilityInfo;
import com.amap.api.navi.model.AMapServiceAreaInfo;
import com.amap.api.navi.model.AimLessModeCongestionInfo;
import com.amap.api.navi.model.AimLessModeStat;
import com.amap.api.navi.model.NaviInfo;
import com.amap.api.navi.model.NaviLatLng;
import com.amap.api.navi.model.AMapCarInfo;
import com.mysql.cj.log.NullLogger;

import java.util.ArrayList;
import java.util.List;

public class SimpleNaviActivity extends Activity implements AMapNaviListener {

    private AMapNaviView mAMapNaviView;
    private AMapNavi mAMapNavi;
    private boolean isGps = false;
    public static NaviLatLng mEndLatlng = null;
    public static NaviLatLng mStartLatlng = null;
    protected final List<NaviLatLng> sList = new ArrayList<NaviLatLng>();
    protected final List<NaviLatLng> eList = new ArrayList<NaviLatLng>();
    protected List<NaviLatLng> mWayPointList = new ArrayList<NaviLatLng>();

    public Double curLocX = 0.0;
    public Double curLocY = 0.0;

    public Double destLocX = 0.0;
    public Double destLocY = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        curLocX = 0.0;
        curLocY = 0.0;

        destLocX = 0.0;
        destLocY = 0.0;

        getNaviParams();
        generateNaviData();

        setContentView(R.layout.activity_basic_navi);
        mAMapNaviView = (AMapNaviView) findViewById(R.id.navi_view);
        mAMapNaviView.onCreate(savedInstanceState);

        mAMapNavi = AMapNavi.getInstance(getApplicationContext());
        mAMapNavi.addAMapNaviListener(this);
        mAMapNavi.setUseInnerVoice(true);

        //isGps = getIntent().getBooleanExtra("gps", false);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mAMapNaviView.onResume();
        Toast.makeText(SimpleNaviActivity.this, "onResume", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onPause() {
        super.onPause();
        mAMapNaviView.onPause();
        Toast.makeText(SimpleNaviActivity.this, "onPause", Toast.LENGTH_LONG).show();
//        停止导航之后，会触及底层stop，然后就不会再有回调了，但是讯飞当前还是没有说完的半句话还是会说完
        //mAMapNavi.stopNavi();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAMapNaviView.onDestroy();
        Toast.makeText(SimpleNaviActivity.this, "onDestroy", Toast.LENGTH_LONG).show();

    }

    /*
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                //时间相隔大于2s吐司提醒
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                //退出应用
                finish();
            }

            //mAMapNaviView.onDestroy();
            //mAMapNavi.stopNavi();

            Intent intent = new Intent();
            ComponentName cn = new ComponentName("amap.android_multiple_infowindows", "amap.android_multiple_infowindows.ShopDetailsActivity");

            intent.setComponent(cn);

            startActivity(intent);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    */

    @Override
    protected void onStop() {

        super.onStop();
        Toast.makeText(SimpleNaviActivity.this, "onStop", Toast.LENGTH_LONG).show();

        mAMapNavi.stopNavi();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //getNaviParams();
        sList.add(mStartLatlng);
        eList.add(mEndLatlng);
        int strategy = 0;
        strategy = mAMapNavi.strategyConvert(true, false, false, false, false);
        AMapCarInfo carInfo = new AMapCarInfo();
        mAMapNavi.calculateDriveRoute(sList, eList, mWayPointList, strategy);

        if (isGps) {
            //this.onStart();
            mAMapNavi.startNavi(NaviType.GPS);
        } else {
            mAMapNavi.startNavi(NaviType.EMULATOR);
        }
    }

    public void getNaviParams()
    {
        /*获取Intent中的Bundle对象*/
        Bundle bundle = this.getIntent().getExtras();

        /*获取Bundle中的数据，注意类型和key*/
        curLocX = bundle.getDouble("curLocX");
        curLocY = bundle.getDouble("curLocY");

        destLocX = bundle.getDouble("destLocX");
        destLocY = bundle.getDouble("destLocY");

        Toast.makeText(SimpleNaviActivity.this, "destLocX: " + destLocX.toString()+ ", " + "destLocY: " + destLocY.toString(), Toast.LENGTH_LONG).show();
    }

    public void generateNaviData()
    {
        if((destLocX != 0.0)&&(destLocY != 0.0)&&(curLocX !=0.0)&&(curLocY!=0.0)) {
            mEndLatlng = new NaviLatLng(destLocX, destLocY);
            mStartLatlng = new NaviLatLng(curLocX, curLocY);
            Toast.makeText(SimpleNaviActivity.this, "mStartLatlng: " + mStartLatlng.toString() + ", "+"mEndLatlng: " + mEndLatlng.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onInitNaviSuccess() {
        //super.onInitNaviSuccess();

        //getNaviParams();
        //generateNaviData();
        /*
        sList.add(mStartLatlng);
        eList.add(mEndLatlng);
        int strategy = 0;
        strategy = mAMapNavi.strategyConvert(true, false, false, false, false);
        AMapCarInfo carInfo = new AMapCarInfo();
        mAMapNavi.calculateDriveRoute(sList, eList, mWayPointList, strategy);
        */
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        //getNaviParams();

    }

    @Override
    public void onInitNaviFailure() {

    }

    @Override
    public void onStartNavi(int i) {

    }

    @Override
    public void onTrafficStatusUpdate() {

    }

    @Override
    public void onLocationChange(AMapNaviLocation aMapNaviLocation) {

    }

    @Override
    public void onGetNavigationText(int i, String s) {

    }

    @Override
    public void onGetNavigationText(String s) {

    }

    @Override
    public void onEndEmulatorNavi() {

    }

    @Override
    public void onArriveDestination() {

    }

    @Override
    public void onCalculateRouteFailure(int i) {

    }

    @Override
    public void onReCalculateRouteForYaw() {

    }

    @Override
    public void onReCalculateRouteForTrafficJam() {

    }

    @Override
    public void onArrivedWayPoint(int i) {

    }

    @Override
    public void onGpsOpenStatus(boolean b) {

    }

    @Override
    public void updateCameraInfo(AMapNaviCameraInfo[] aMapCameraInfos) {

    }

    @Override
    public void onServiceAreaUpdate(AMapServiceAreaInfo[] amapServiceAreaInfos) {

    }

    @Override
    public void onNaviInfoUpdate(NaviInfo naviInfo) {

    }

    @Override
    public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo aMapNaviTrafficFacilityInfo) {

    }

    @Override
    public void showCross(AMapNaviCross aMapNaviCross) {

    }

    @Override
    public void hideCross() {

    }

    @Override
    public void showLaneInfo(AMapLaneInfo[] aMapLaneInfos, byte[] bytes, byte[] bytes1) {

    }

    @Override
    public void hideLaneInfo() {

    }

    @Override
    public void onCalculateRouteSuccess(int[] ints) {
        mAMapNavi.startNavi(NaviType.EMULATOR);
    }

    @Override
    public void notifyParallelRoad(int i) {

    }

    @Override
    public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo[] aMapNaviTrafficFacilityInfos) {

    }

    @Override
    public void updateAimlessModeStatistics(AimLessModeStat aimLessModeStat) {

    }


    @Override
    public void updateAimlessModeCongestionInfo(AimLessModeCongestionInfo aimLessModeCongestionInfo) {

    }

    @Override
    public void onPlayRing(int i) {

    }

    @Override
    public void showModeCross(AMapModelCross aMapModelCross) {

    }

    @Override
    public void hideModeCross() {

    }

    @Override
    public void updateIntervalCameraInfo(AMapNaviCameraInfo aMapNaviCameraInfo, AMapNaviCameraInfo aMapNaviCameraInfo1, int i) {

    }

    @Override
    public void showLaneInfo(AMapLaneInfo aMapLaneInfo) {

    }

    @Override
    public void onCalculateRouteSuccess(AMapCalcRouteResult aMapCalcRouteResult) {

    }

    @Override
    public void onCalculateRouteFailure(AMapCalcRouteResult aMapCalcRouteResult) {

    }

    @Override
    public void onNaviRouteNotify(AMapNaviRouteNotifyData aMapNaviRouteNotifyData) {

    }

    @Override
    public void onGpsSignalWeak(boolean b) {

    }

}


