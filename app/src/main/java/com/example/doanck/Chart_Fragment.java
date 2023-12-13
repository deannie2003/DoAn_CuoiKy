package com.example.doanck;

import android.app.DatePickerDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.doanck.API.APIClient;
import com.example.doanck.API.ApiInterface;
import com.example.doanck.Model.Datapoint;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.doanck.Model.Token;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Chart_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Chart_Fragment extends Fragment {
    private VideoView videoView;
    private boolean isVideoLooping = true; // Biến để kiểm soát việc lặp video

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String assetId = "5zI6XqkQVSfdgOrZ1MyWEf";

    private String attributeName="";

    private String fromTime="";
    private String toTime= "";

    private ApiInterface apiService;

    private LineChart chart;

    private float lineWidth = 4f,
            valueTextSize = 10f;


    private TextInputLayout textInputAttributes,
            textInputTimer;
    private MaterialAutoCompleteTextView autoCompleteTextViewAtrributes,
            autoCompleteTextViewTimer,autoCompleteTextViewDialogTimer;

    private Button btnShow;

    private String typeTempAttribute ="Temperature",
            typeHumidityAttribute ="Humidity",
            typeWindSpeedAttribute="WindSpeed",
            typeRainFallAttribute="RainFall";

    private String typeTimeHour ="Hour",
            typeTimeWeek ="Week",
            typeTimeDay ="Day",
            typeTimeMonth="Month",
            typeTimeYear="Year";





    TextView textView;


    public Chart_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Account_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Chart_Fragment newInstance(String param1, String param2) {
        Chart_Fragment fragment = new Chart_Fragment();
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
        View view = inflater.inflate(R.layout.fragment_chart_, container, false);
        chart = view.findViewById(R.id.lineChart);
        textInputAttributes = view.findViewById(R.id.inputAttributteName);
        textInputTimer = view.findViewById(R.id.inputLayoutTimer);

        autoCompleteTextViewAtrributes =view.findViewById(R.id.inputTVAttributteName);
        autoCompleteTextViewTimer = view.findViewById(R.id.inputTVTimer);
        autoCompleteTextViewDialogTimer = view.findViewById(R.id.inputTVDialogTimer);

        btnShow = view.findViewById(R.id.btnShowChart);
        DisplayTimeCurrent();

        autoCompleteTextViewDialogTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectTimer();
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowChart();
            }
        });
        videoView = view.findViewById(R.id.videoViewSplash);
        String videoPath = "android.resource://" + requireContext().getPackageName() + "/" + R.raw.background_night;
        Uri videoUri = Uri.parse(videoPath);
        videoView.setVideoURI(videoUri);
        videoView.start();

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // Kiểm tra biến để xem có lặp lại video hay không
                if (isVideoLooping) {
                    videoView.start(); // Nếu lặp lại, bắt đầu lại video
                }
            }
        });
        return view;
    }
    private void ShowChart(){
        SelectAtributeToDrawChart();
        SelectTimerToDrawChart();
        GetDataPointFromJson(Token.getToken(),fromTime,toTime,assetId,attributeName);
    }

    private void SelectTimerToDrawChart(){
        String typeTimer = autoCompleteTextViewTimer.getText().toString();
        String selectTime = autoCompleteTextViewDialogTimer.getText().toString();
        if(typeTimer.equals(typeTimeHour)) getTimes(selectTime,typeTimeHour);
        if(typeTimer.equals(typeTimeWeek)) getTimes(selectTime,typeTimeWeek);
        if(typeTimer.equals(typeTimeDay)) getTimes(selectTime,typeTimeDay);
        if(typeTimer.equals(typeTimeMonth)) getTimes(selectTime,typeTimeMonth);
        if(typeTimer.equals(typeTimeYear)) getTimes(selectTime,typeTimeYear);
    }


    private void SelectAtributeToDrawChart(){
        String typeAttribute = autoCompleteTextViewAtrributes.getText().toString();
        if(typeAttribute.equals(typeTempAttribute))
            attributeName = "temperature";
        if(typeAttribute.equals(typeHumidityAttribute))
            attributeName ="humidity";
        if(typeAttribute.equals(typeWindSpeedAttribute))
            attributeName ="windSpeed";
        if(typeAttribute.equals(typeRainFallAttribute))
            attributeName ="rainfall";
    }

    public void GetDataPointFromJson(String token,String fromTime, String toTime, String assetId,String attributeName) {

        apiService = APIClient.getClient().create(ApiInterface.class);
        String json = "{ \"fromTimestamp\": 0, " +
                "\"toTimestamp\": 0, " +
                "\"fromTime\": \"" + fromTime + "\", " +
                "\"toTime\": \"" + toTime + "\", " +
                "\"type\": \"string\" }";

        // Tạo request body trong raw để post (PostMan)
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json);

        // Truyền các tham số cần thiết vào
        Call<List<Datapoint>> call = apiService.getDataPoints("application/json", "Bearer " + token, "application/json", assetId, attributeName, requestBody);
        call.enqueue(new Callback<List<Datapoint>>() {
            @Override
            public void onResponse(Call<List<Datapoint>> call, Response<List<Datapoint>> response) {
                if (response.isSuccessful()) {
                    List<Datapoint> dataPoints = response.body();
                    DrawLineChart(dataPoints);
                } else {
                    // Xử lý lỗi
                }
            }

            @Override
            public void onFailure(Call<List<Datapoint>> call, Throwable t) {

            }
        });
    }

    private void DrawLineChart(List<Datapoint> dataPoints){

        List<Entry> entries = new ArrayList<>();
        for (int i =dataPoints.size()-1;i>=0;i--) {
            // Chuyển đổi dữ liệu thành Entry và thêm vào danh sách
            entries.add(new Entry(dataPoints.get(i).getX(), dataPoints.get(i).getY()));
        }

        LineDataSet lineDataSet = new LineDataSet(entries, "Nhiet do");

        lineDataSet.setLineWidth(lineWidth);
        lineDataSet.setValueTextSize(valueTextSize);



        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);
        LineData data = new LineData(dataSets);


        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new ValueFormatter() {
            private final SimpleDateFormat format = new SimpleDateFormat("HH:mm", Locale.getDefault());

            @Override
            public String getFormattedValue(float value) {
                return format.format(new Date((long) value));
            }
        });

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setGranularity(10f);

        chart.setData(data);
        chart.invalidate();
    }




    public void getTimes(String ISO8601Time,String typeTimer) {
        toTime = ISO8601Time;
        ZonedDateTime endingTime = null;
        DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
        ZonedDateTime startTime = ZonedDateTime.parse(ISO8601Time, formatter);
        if(typeTimer.equals("Day")) endingTime = startTime.minus(1, ChronoUnit.DAYS);
        if(typeTimer.equals("Week")) endingTime = startTime.minus(1, ChronoUnit.WEEKS);
        if(typeTimer.equals("Hour")) endingTime = startTime.minus(1, ChronoUnit.HOURS);
        if(typeTimer.equals("Month")) endingTime = startTime.minus(1, ChronoUnit.MONTHS);
        if(typeTimer.equals("Year")) endingTime = startTime.minus(1, ChronoUnit.YEARS);
        fromTime = endingTime.format(formatter);

    }


    private void DisplayTimeCurrent(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        autoCompleteTextViewDialogTimer.setText(simpleDateFormat.format(calendar.getTime()));
    }

    private void SelectTimer(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity());
        datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                LocalDate date = LocalDate.of(year, month , dayOfMonth+1);
                ZonedDateTime zonedDateTime = date.atStartOfDay(ZoneId.of("Asia/Ho_Chi_Minh"));
                String formattedDate = zonedDateTime.format(DateTimeFormatter.ISO_INSTANT);
                autoCompleteTextViewDialogTimer.setText(formattedDate);
            }
        });

        // Hiển thị DatePickerDialog
        datePickerDialog.show();
    }

}