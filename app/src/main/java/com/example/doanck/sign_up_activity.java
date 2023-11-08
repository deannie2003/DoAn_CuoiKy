package com.example.doanck;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class sign_up_activity extends AppCompatActivity {

    EditText username, email, password, re_password;
    Button btn_register, btn_back;
    ImageView img_change;
    TextView txt_reg;
    boolean isChanged = false;

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
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sign_up_activity.this ,homePageActivity.class);
                startActivity(intent);
            }
        });
        img_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeLanguage();
            }
        });


        username = findViewById(R.id.usernameEditTxt);
        email = findViewById(R.id.emailEditTxt);
        password = findViewById(R.id.passwordEditTxt);
        re_password = findViewById(R.id.reenterPassEditTxt);
        btn_register = findViewById(R.id.signupBtn);
        btn_back = findViewById(R.id.backBtn);
        img_change = findViewById(R.id.imgChange);
        txt_reg = findViewById(R.id.registerTxt);



        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://uiot.ixxc.dev/auth/realms/master/protocol/openid-connect/auth?response_type=code&client_id=openremote&redirect_uri=https%3A%2F%2Fuiot.ixxc.dev%2Fswagger%2Foauth2-redirect.html";

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .build();

                Call call = client.newCall(request);

                call.enqueue(new Callback() {
                    public void onResponse(Call call, Response response)
                            throws IOException {
                        String responseBody = response.body().string();
                        Headers headers = response.headers();
                        List<String> cookieValues = headers.values("Set-Cookie");
                        StringBuilder Cookie = new StringBuilder();

                        for (String cookie : cookieValues) {
                            Cookie.append(cookie).append(";");
                        }

                        if (response.code() == 200) {
                            String RegForm_URL = "https://uiot.ixxc.dev" + extractFeature(responseBody,"a", "href");

                            OkHttpClient secondClient = new OkHttpClient();
                            Request request = new Request.Builder()
                                    .url(RegForm_URL)
                                    .header("Cookie", Cookie.toString())
                                    .build();

                            Log.d("MainActivity", "Cookie: " + Cookie.toString());

                            Call secondCall = secondClient.newCall(request);

                            secondCall.enqueue(new Callback() {
                                public void onResponse(Call call, Response response)
                                        throws IOException {
                                    String RegURL = extractFeature(response.body().string(), "form", "action");
                                    Log.d("MainActivity", Cookie.toString());

                                    RequestBody data = new FormBody.Builder()
                                            .add("username", username.getText().toString())
                                            .add("email", email.getText().toString())
                                            .add("password", password.getText().toString())
                                            .add("password-confirm", re_password.getText().toString())
                                            .add("register", "")
                                            .build();


                                    OkHttpClient thirdClient = new OkHttpClient();
                                    Request request = new Request.Builder()
                                            .url(RegURL)
                                            .header("Cookie", Cookie.toString())
                                            .post(data)
                                            .build();
                                    Call thirdCall = thirdClient.newCall(request);
                                    thirdCall.enqueue(new Callback() {
                                        public void onResponse(Call call, Response response)
                                                throws IOException {
                                            Log.d("MainActivity", response.body().string());
                                        }

                                        public void onFailure(Call call, IOException e) {
                                            Log.d("MainActivity", e.toString());
                                        }
                                    });
                                }

                                public void onFailure(Call call, IOException e) {
                                    Log.d("MainActivity", e.toString());
                                }
                            });

                        }
                    }

                    public void onFailure(Call call, IOException e) {
                        Log.d("MainActivity", e.toString());
                    }
                });
            }
        });
    }
    private void ChangeLanguage() {
        isChanged = !isChanged;
        if (isChanged) {
            img_change.setImageResource(R.drawable.icon_vietnam);
            username.setText("Tên người dùng");
            email.setText("Email");
            password.setText("Mật khẩu");
            re_password.setText("Nhập lại mật khẩu");
            btn_register.setText("ĐĂNG KÝ");
            btn_back.setText("QUAY VỀ");
            txt_reg.setText("ĐĂNG KÝ");
        } else {
            img_change.setImageResource(R.drawable.icon_uk);
            username.setText("Username");
            email.setText("Email");
            password.setText("Password");
            re_password.setText("Re-enter the password");
            btn_register.setText("SIGN UP");
            btn_back.setText("BACK");
            txt_reg.setText("REGISTER");

        }
    }
}