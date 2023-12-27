package com.example.doanck;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Hello_user_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Hello_user_fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView textView;

    private TextView timeTextView;
    private TextView dmyTextView;

    private Handler handler;

    public Hello_user_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Hello_user_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Hello_user_fragment newInstance(String param1, String param2) {
        Hello_user_fragment fragment = new Hello_user_fragment();
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
        View view = inflater.inflate(R.layout.fragment_hello_user_fragment, container, false);
        Context ctx = view.getContext();
        textView = view.findViewById(R.id.hi_user_123);
        timeTextView = view.findViewById(R.id.time);
        dmyTextView = view.findViewById(R.id.dmy);
        handler = new Handler(Looper.getMainLooper());
        startUpdatingDateTime();

        // Thực hiện animation
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.hi_user_anim);
        //textView.startAnimation(animation);
        return view;
        }
    private void startUpdatingDateTime() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateDateTime();
                handler.postDelayed(this, 1000); // Update time every second
            }
        }, 1000); // Initial delay 1 second
    }

    private void updateDateTime() {
        Calendar calendar = Calendar.getInstance();

        // Update time
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String currentTime = timeFormat.format(calendar.getTime());
        timeTextView.setText(currentTime);

        // Update date
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MMM dd, yyyy", Locale.getDefault());
        String currentDate = dateFormat.format(calendar.getTime());
        dmyTextView.setText(currentDate);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Stop updating time when the view is destroyed
        handler.removeCallbacksAndMessages(null);
    }
}
