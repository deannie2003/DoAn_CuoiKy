package com.example.doanck;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.doanck.API.APIClient;
import com.example.doanck.API.ApiInterface;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.doanck.Model.Token;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Map_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Map_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private final int REQUEST_PERMISSIONS_REQUEST_CODE = 1;
    MapView map = null;
    View view;
    ApiInterface apiInterface;
    ArrayList<Double> centerList = new ArrayList<>();
    ArrayList<Double> boundsList = new ArrayList<>();

    String assetIdDefautWeather= "4EqQeQ0L4YNWNNTzvTOqjy";
    Marker Device_1,Device_2;

    private CompassOverlay mCompassOverlay = null;
    public Map_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Map_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Map_Fragment newInstance(String param1, String param2) {
        Map_Fragment fragment = new Map_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_map_, container, false);
        Context ctx = view.getContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));


        map = view.findViewById(R.id.map);
        map.setMultiTouchControls(true);
        map.setTileSource(TileSourceFactory.MAPNIK);

        apiInterface = APIClient.getClient().create(ApiInterface.class);
        Call mapValue = apiInterface.getMap();
        mapValue.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d("API Call", response.code()+"");
                if (response.isSuccessful() && response.body() != null){
                    Log.d("API Call","Successful");
                    Log.d("API Call",response.toString());
                    Log.d("API Call","response: " + new Gson().toJson(response.body()));
                    try {
                        JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                        JSONObject options = jsonObject.getJSONObject("options");
                        JSONObject defaultObject = options.getJSONObject("default");
                        JSONArray centerArray = defaultObject.getJSONArray("center");
                        JSONArray boundsArray = defaultObject.getJSONArray("bounds");
                        Double zoom = defaultObject.getDouble("zoom");
                        Double minZoom = defaultObject.getDouble("minZoom");
                        Double maxZoom = defaultObject.getDouble("maxZoom");

                        for (int i = 0; i < centerArray.length(); i++) {
                            centerList.add(centerArray.getDouble(i));
                        }
                        for (int i = 0; i < boundsArray.length(); i++) {
                            boundsList.add(boundsArray.getDouble(i));
                        }
                        for (double value : centerList) {
                            Log.d("API Call", "Value: " + value);
                        }
                        for (double value : boundsList) {
                            Log.d("API Call", "Value: " + value);
                        }
                        Log.e("API Call",Double.toString(zoom));
                        Log.e("API Call",Double.toString(minZoom));
                        Log.e("API Call",Double.toString(maxZoom));
                        map.setMinZoomLevel(minZoom);
                        map.setMaxZoomLevel(maxZoom);

                        BoundingBox boundingBox = new BoundingBox(boundsList.get(3), boundsList.get(2),boundsList.get(1), boundsList.get(0));
                        map.setScrollableAreaLimitDouble(boundingBox);

                        IMapController mapController = map.getController();
                        mapController.setZoom(zoom);
                        GeoPoint startPoint = new GeoPoint(centerList.get(1), centerList.get(0));
                        mapController.setCenter(startPoint);

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                //CallAssetDevice(apiInterface);
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });

        mCompassOverlay = new CompassOverlay(ctx, new InternalCompassOrientationProvider(ctx),
                map);
        mCompassOverlay.enableCompass();
        map.getOverlays().add(this.mCompassOverlay);

        RotationGestureOverlay mRotationGestureOverlay = new RotationGestureOverlay(map);
        mRotationGestureOverlay.setEnabled(true);
        map.getOverlays().add(mRotationGestureOverlay);

        // Khởi tạo Marker đánh dấu cho hai thiết bị Weather và Light

        Marker WeatherMaker = new Marker(map);
        WeatherMaker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER);
        WeatherMaker.setIcon(getResources().getDrawable(R.drawable.device_weather_logo));
        WeatherMaker.setTitle("Deafault Weather");
        apiInterface = APIClient.getClient().create(ApiInterface.class);
        Call MapDevice = apiInterface.getDevices(assetIdDefautWeather, "Bearer "+ Token.getToken());
        Log.d("tokenmap:" , Token.getToken());
        MapDevice.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d("API Call", response.code()+"");
                if (response.isSuccessful() && response.body() != null){
                    Log.d("API Call Device","Successful");
                    Log.d("API Call Device",response.toString());
                    Log.d("API Call Device","response: " + new Gson().toJson(response.body()));
                    try {
                        JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                        JSONObject attributes = jsonObject.getJSONObject("attributes");
                        JSONObject data = attributes.getJSONObject("data");
                        JSONObject value = data.getJSONObject("value");
                        JSONObject coord = value.getJSONObject("coord");
                        Double lon = coord.getDouble("lon");
                        Double lat = coord.getDouble("lat");

                        Log.d("API Call Device",Double.toString(lon));
                        Log.d("API Call Device",Double.toString(lat));;
//                        map.setMinZoomLevel(minZoom);
//                        map.setMaxZoomLevel(maxZoom);
//
//                        BoundingBox boundingBox = new BoundingBox(boundsList.get(3), boundsList.get(2),boundsList.get(1), boundsList.get(0));
//                        map.setScrollableAreaLimitDouble(boundingBox);
//
//                        IMapController mapController = map.getController();
//                        mapController.setZoom(zoom);
//                        GeoPoint startPoint = new GeoPoint(centerList.get(1), centerList.get(0));
//                        mapController.setCenter(startPoint);

                        WeatherMaker.setPosition(new GeoPoint(lat,lon));
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });

        map.getOverlays().add(WeatherMaker);
        map.invalidate();
        return view;
    }
    @Override
    public void onPause() {
        super.onPause();
        // Lưu trạng thái của OpenStreetMap
        map.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        // Khôi phục trạng thái của OpenStreetMap
        map.onResume();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }
}