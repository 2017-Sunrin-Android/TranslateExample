package com.example.eunseo.translate;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by eunseo on 2017-09-23.
 */

public class RetrofitConnection {
    public class Message{
        public Result message;

        class Result{
            public Text result;
        }
        class Text{
            public String translatedText;
        }
    }

    public interface Request{
        @FormUrlEncoded
        @Headers({
                "X-Naver-Client-Id: rBFoo2jEvCE75A1DuUv5",
                "X-Naver-Client-Secret: 0hTHzwbQuS",
                "Content-Type: application/x-www-form-urlencoded; charset=UTF-8"
        })
        @POST("v1/papago/n2mt")
        Call<Message> Translation(
                @Field("source") String source,
                @Field("target") String target,
                @Field("text") String text
        );
    }
}
