package amap.android_multiple_infowindows;

        import android.content.ComponentName;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.EditText;
        import android.widget.RadioButton;
        import android.widget.RadioGroup;
        import android.widget.TextView;

        import com.amap.api.location.AMapLocation;
        import com.amap.api.location.AMapLocationListener;
        import com.amap.api.maps.AMap;
        import com.amap.api.maps.LocationSource;
        import com.amap.api.maps.MapView;
        import com.amap.api.maps.model.LatLng;
        import com.amap.api.services.core.PoiItem;
        import com.amap.api.services.poisearch.PoiResult;
        import com.amap.api.services.poisearch.PoiSearch;

public class FoundFragment extends Fragment{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_register2, container, false);

    }
}