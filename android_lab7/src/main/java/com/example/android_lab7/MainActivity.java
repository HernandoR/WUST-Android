package com.example.android_lab7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;


/*
    百度地图应用，包含定位信息和地图显示
    一般需要打开定位服务，选择高精度定位模式，有网络连接
    需要在清单文件里使用百度云服务（参见清单文件service标签）
    需要创建应用（模块）的Key，并写入清单文件（参见清单文件meta标签）
*/
public class MainActivity extends AppCompatActivity {

    LocationClient mLocationClient;  //定位客户端
    MapView mapView;  //Android Widget地图控件
    BaiduMap baiduMap;
    boolean isFirstLocate = true;

    TextView tv_Lat;  //纬度
    TextView tv_Lon;  //经度
    TextView tv_Add;  //地址

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //如果没有定位权限，动态请求用户允许使用该权限
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            this.requestLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "没有定位权限！", Toast.LENGTH_LONG).show();
                    this.finish();
                } else {
                    this.requestLocation();
                }
        }
    }

    private void requestLocation() {
        this.initLocation();
        this.mLocationClient.start();
    }

    private void initLocation() {  //初始化
        this.mLocationClient = new LocationClient(this.getApplicationContext());
        this.mLocationClient.registerLocationListener(new MyLocationListener());
        SDKInitializer.initialize(this.getApplicationContext());
        this.setContentView(R.layout.activity_main);

        this.mapView = this.findViewById(R.id.bmapView);
        this.baiduMap = this.mapView.getMap();
        this.tv_Lat = this.findViewById(R.id.tv_Lat);
        this.tv_Lon = this.findViewById(R.id.tv_Lon);
        this.tv_Add = this.findViewById(R.id.tv_Add);

        LocationClientOption option = new LocationClientOption();
        //设置扫描时间间隔
        option.setScanSpan(1000);
        //设置定位模式，三选一
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        /*option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);
        option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);*/
        //设置需要地址信息
        option.setIsNeedAddress(true);
        //保存定位参数
        this.mLocationClient.setLocOption(option);
    }

    private void navigateTo(BDLocation bdLocation) {
        if (this.isFirstLocate) {
            LatLng ll = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
            this.baiduMap.animateMapStatus(update);
            this.isFirstLocate = false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.mapView = (MapView) this.findViewById(R.id.bmapView);
        this.mapView.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        this.mapView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.mLocationClient.stop();
        this.mapView.onDestroy();
    }

    //内部类，百度位置监听器
    private class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            MainActivity.this.tv_Lat.setText(bdLocation.getLatitude() + "");
            MainActivity.this.tv_Lon.setText(bdLocation.getLongitude() + "");
            MainActivity.this.tv_Add.setText(bdLocation.getAddrStr());
            if (bdLocation.getLocType() == BDLocation.TypeGpsLocation || bdLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
                MainActivity.this.navigateTo(bdLocation);
            }
        }
    }
}