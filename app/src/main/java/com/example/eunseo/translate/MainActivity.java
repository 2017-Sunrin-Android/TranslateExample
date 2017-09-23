package com.example.eunseo.translate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.eunseo.translate.RetrofitConnection.*;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    Retrofit retrofit;
    EditText translateText;
    EditText translatedText;
    Spinner to_language;
    Spinner from_language;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retrofit=RetrofitInstance.getInstance(this);

        translateText=(EditText)findViewById(R.id.translateText);
        translatedText=(EditText)findViewById(R.id.translatedText);

        to_language=(Spinner)findViewById(R.id.to_language);
        from_language=(Spinner)findViewById(R.id.from_language);
    }

    public void translateClicked(View v){
        final String translateText_str=translateText.getText().toString();
        final Request request = retrofit.create(Request.class);

        String from_language_str=from_language.getSelectedItem().toString();
        String to_language_str=to_language.getSelectedItem().toString();

        if(from_language_str.equals("한국어")){
            from_language_str="ko";
        }
        if(from_language_str.equals("영어")) {
            from_language_str = "en";
        }
        if(to_language_str.equals("한국어")){
            to_language_str="ko";
        }
        if(to_language_str.equals("영어")){
            to_language_str="en";
        }

        Call<Message> translation = request.Translation(from_language_str,to_language_str, translateText_str);
        translation.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if(response.code()==200){
                    translatedText.setText(response.body().message.result.translatedText);
                }
                else{
                    Toast.makeText(getApplicationContext(), "오류 발생 : "+response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Log.d("TAG", t.getLocalizedMessage());
                Toast.makeText(getApplicationContext(), "요청 실패하셨습니다",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
