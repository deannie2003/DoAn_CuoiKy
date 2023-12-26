package com.example.doanck;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.doanck.API.APIClient;
import com.example.doanck.API.ApiInterface;
import com.example.doanck.Model.Token;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Weather_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Weather_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View view;
    ApiInterface apiInterface;
    String assetIdDefautWeather= "4EqQeQ0L4YNWNNTzvTOqjy";

    TextView txtHumidity,txtPlace,txtTempInfor,txtWindDirection,txtWindSpeed,txtPressure;

    public Weather_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Weather_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Weather_Fragment newInstance(String param1, String param2) {
        Weather_Fragment fragment = new Weather_Fragment();
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
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_weather_, container, false);
        Context ctx = view.getContext();
        getElement();

        apiInterface = APIClient.getClient().create(ApiInterface.class);
        Call MapDevice = apiInterface.getDevices(assetIdDefautWeather, "Bearer "+ Token.getToken());
        Log.d("tokenmap:" , Token.getToken());
        MapDevice.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d("API Call", response.code()+"");
                if (response.isSuccessful() && response.body() != null){
                    Log.d("API Weather","Successful");
                    Log.d("API Weather",response.toString());
                    Log.d("API Weather","response: " + new Gson().toJson(response.body()));
                    try {
                        JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                        JSONObject attributes = jsonObject.getJSONObject("attributes");
                        JSONObject data = attributes.getJSONObject("data");
                        JSONObject value = data.getJSONObject("value");
                        JSONObject coord = value.getJSONObject("coord");
                        JSONObject main = value.getJSONObject("main");

                        JSONObject wind = value.getJSONObject("wind");

                        String humidity = String.valueOf(main.getDouble("humidity"));
                        String temp = String.valueOf(main.getDouble("temp"));
                        String pressure = String.valueOf(main.getDouble("pressure"));
                        String winSpeed = String.valueOf(wind.getDouble("speed"));
                        String winDer = String.valueOf(wind.getDouble("deg"));
                        String place = value.getString("name");
                        Log.d("Weather", humidity + temp + pressure + winDer + winSpeed
                                +place);

                        txtTempInfor.setText(temp);
                        txtWindDirection.setText(winDer);
                        txtWindSpeed.setText(winSpeed);
                        txtPressure.setText(pressure);
                        txtHumidity.setText(humidity);

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
        return  view;
    }
    void getElement(){
        txtTempInfor = view.findViewById(R.id.nhietdo);
        txtWindDirection = view.findViewById(R.id.huonggio);
        txtWindSpeed = view.findViewById(R.id.tocdogio);
        txtPressure = view.findViewById(R.id.apsuat);
        txtHumidity = view.findViewById(R.id.doam);
    }
}