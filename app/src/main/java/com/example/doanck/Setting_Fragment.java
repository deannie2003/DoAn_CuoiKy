package com.example.doanck;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class Setting_Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public Setting_Fragment() {
        // Required empty public constructor
    }

    public static Setting_Fragment newInstance(String param1, String param2) {
        Setting_Fragment fragment = new Setting_Fragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting_, container, false);

        sharedPreferences = getActivity().getSharedPreferences("LanguagePrefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        SwitchCompat languageSwitch = view.findViewById(R.id.changeLangue);
        languageSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String selectedLanguage = isChecked ? "English" : "Vietnamese";
                editor.putString("selectedLanguage", selectedLanguage);
                editor.apply();
                updateLanguageForFragments(selectedLanguage);
            }
        });
        return view;
    }

    private void updateLanguageForFragments(String selectedLanguage) {
        // Gửi thông báo về việc thay đổi ngôn ngữ
        Intent intent = new Intent("LanguageChanged");
        intent.putExtra("selectedLanguage", selectedLanguage);
        LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent);
    }
}
