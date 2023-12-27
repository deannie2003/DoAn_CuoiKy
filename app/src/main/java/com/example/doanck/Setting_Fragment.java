package com.example.doanck;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.doanck.ModelLogin.Username;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Setting_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Setting_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText userName,Email;
    public Setting_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Setting_Fragment.
     */
    // TODO: Rename and change types and number of parameters
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_setting_, container, false);
        Button buttonSave = view.findViewById(R.id.log_out);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý khi nút được nhấn ở đây
                logoutUser();
            }
        });
        return  view;
    }
    private void logoutUser() {
        // Xóa token từ SharedPreferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("token");
        editor.apply();

        // Hiển thị thông báo
        Toast.makeText(getActivity(), "Đăng xuất thành công!", Toast.LENGTH_SHORT).show();

        // Chuyển hướng người dùng về màn hình đăng nhập
        // Thay thế `LoginActivity.class` bằng class của màn hình đăng nhập của bạn
        Intent intent = new Intent(getActivity(), sign_in_activity.class);
        startActivity(intent);
        getActivity().finish();
    }
}