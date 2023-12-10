package com.example.doanck;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
<<<<<<< HEAD
import android.content.res.Configuration;
import android.content.res.Resources;
=======
>>>>>>> 3ec15a0633039bd3e3944de1420744b0f10add8d
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.List;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class sign_up_activity extends AppCompatActivity {
    SharedPreferences prefs;
    EditText username, email, password, re_password;
    Button btn_register, btn_back;
    ImageView img_change;
    TextView txt_reg;
    boolean isChanged = false;

    private void setLanguage() {
        boolean isVietnamese = prefs.getBoolean("isVietnamese", false);
        txt_reg.setText(isVietnamese ? "ĐĂNG KÝ" : "SIGN UP");

        btn_register.setText(isVietnamese ? "ĐĂNG KÝ":"SIGN UP");
        btn_back.setText(isVietnamese ? "QUAY LẠI":"BACK");

    }

    @Override
    protected void onResume() {
        super.onResume();
        setLanguage();
    }
    public String extractFeature(String html, String tag, String attribute) {
        Document document = Jsoup.parse(html);
        Element foundElement = document.select(tag).first();

        if (foundElement != null) {
            String elementValue = foundElement.attr(attribute);
            return elementValue;
        } else {
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);
        prefs = getSharedPreferences("LanguageSettings", MODE_PRIVATE);
        setLanguage();
        username = findViewById(R.id.usernameEditTxt);
        email = findViewById(R.id.emailEditTxt);
        password = findViewById(R.id.passwordEditTxt);
        re_password = findViewById(R.id.reenterPassEditTxt);
        btn_register = findViewById(R.id.button_sign_up);
        btn_back = findViewById(R.id.button_homepage);
        txt_reg = findViewById(R.id.registerTxt);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sign_up_activity.this ,homePageActivity.class);
                startActivity(intent);
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url_UIT = "https://uiot.ixxc.dev/auth/realms/master/protocol/openid-connect/auth?response_type=code&client_id=openremote&redirect_uri=https%3A%2F%2Fuiot.ixxc.dev%2Fswagger%2Foauth2-redirect.html";
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url_UIT)
                        .build();
                Call call = client.newCall(request);

                call.enqueue(new Callback() {
                    public void onResponse(Call call, Response response)
                            throws IOException {
                        String responseBody = response.body().string();
                        Headers headers = response.headers();
                        List<String> cookieValues = headers.values("set-cookie");
                        StringBuilder Cookie = new StringBuilder();

                        for (String cookie : cookieValues) {
                            Cookie.append(cookie).append(";");
                        }

                        if (response.code() == 200) {
                            String RegisterForm_URL = "https://uiot.ixxc.dev" + extractFeature(responseBody,"a", "href");
                            OkHttpClient secondClient = new OkHttpClient();
                            Request request = new Request.Builder()
                                    .url(RegisterForm_URL)
                                    .header("Cookie", Cookie.toString())
                                    .build();
                            Call second_call = secondClient.newCall(request);

                            second_call.enqueue(new Callback() {
                                public void onResponse(Call call, Response response)
                                        throws IOException {
                                    String Register_URL = extractFeature(response.body().string(), "form", "action");
                                    RequestBody data = new FormBody.Builder()
                                            .add("username", username.getText().toString())
                                            .add("email", email.getText().toString())
                                            .add("password", password.getText().toString())
                                            .add("password-confirm", re_password.getText().toString())
                                            .build();

                                    OkHttpClient thirdClient = new OkHttpClient();
                                    Request request = new Request.Builder()
                                            .url(Register_URL)
                                            .header("Cookie", Cookie.toString())
                                            .post(data)
                                            .build();
                                    Call third_call = thirdClient.newCall(request);
                                    third_call.enqueue(new Callback() {
                                        public void onResponse(Call call, Response response)
                                                throws IOException {
                                            Document document = Jsoup.parse(response.body().string());
                                            Elements redTextElements = document.select("span.red-text");
                                            Element dataErrorElement = document.select("span[data-error]").first();
                                            if (!redTextElements.isEmpty()) {
                                                Element errorElement = redTextElements.first();
                                                String errorMessage = errorElement.text();
                                                runOnUiThread(new Runnable() {
                                                    public void run() {
                                                        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                            }

                                            if (dataErrorElement != null) {
                                                runOnUiThread(new Runnable() {
                                                    public void run() {
                                                        Toast.makeText(getApplicationContext(), dataErrorElement.attr("data-error"), Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                            }

                                            if (redTextElements.isEmpty() && dataErrorElement == null)
                                                runOnUiThread(new Runnable() {
                                                    public void run() {
                                                        Toast.makeText(getApplicationContext(), "Register successful.", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                        }

                                        public void onFailure(Call call, IOException e) {
                                            runOnUiThread(new Runnable() {
                                                public void run() {
                                                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        }
                                    });
                                }

                                public void onFailure(Call call, IOException e) {
                                    runOnUiThread(new Runnable() {
                                        public void run() {
                                            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            });

                        }
                    }
                    public void onFailure(Call call, IOException e) {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });
    }

}