package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textViewResults;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResults = findViewById(R.id.text_view_results);
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
      //  getPosts();
        //onCreate();
       onUpdate();
        //deletePost();
    }
        private void getPosts(){
            Call<List<Posts>> call = jsonPlaceHolderApi.getPosts();
            call.enqueue(new Callback<List<Posts>>() {
                @Override
                public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                    if (!response.isSuccessful()) {
                        textViewResults.setText("Code: " + response.code());
                        return;
                    }
                    List<Posts> posts = response.body();
                    for (Posts post : posts) {
                        String content = "";
                        content += "ID: " + post.getId() + "\n";
                        content += "User Id: " + post.getUserId() + "\n";
                        content += "Title: " + post.getTitle() + "\n";
                        content += "Text: " + post.getText() + "\n";

                        textViewResults.append(content);
                    }
                }

                @Override
                public void onFailure(Call<List<Posts>> call, Throwable t) {
                    textViewResults.setText(t.getMessage());

                }
            });

    }

    public void onCreate(){
       // Posts posts = new Posts(23,"Title","New Text");
        Call<Posts> call = jsonPlaceHolderApi.createPost(23,"Lamborghini","Aventador S");
        call.enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {
                if (!response.isSuccessful()){
                    textViewResults.setText("Code: "+ response.code());
                    return;
                }

                Posts postResponse = response.body();
                String content ="";
                content += "Code: "+ response.code() + "\n";
                content += "ID: " + postResponse.getId()+"\n";
                content += "User Id: " + postResponse.getUserId()+"\n";
                content += "Title: " + postResponse.getTitle()+"\n";
                content += "Text: " + postResponse.getText()+"\n";

                textViewResults.append(content);


            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {
                textViewResults.setText(t.getMessage());

            }
        });
    }
    public void onUpdate(){
        Posts posts = new Posts(23,"Ferrari","LaFerrari");

        Call<Posts> call = jsonPlaceHolderApi.putPost(5,posts);

        call.enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {
                if (!response.isSuccessful()){
                    textViewResults.setText("Code: "+ response.code());
                    return;
                }

                Posts postResponse = response.body();
                String content ="";
                content += "Code: "+ response.code() + "\n";
                content += "ID: " + postResponse.getId()+"\n";
                content += "User Id: " + postResponse.getUserId()+"\n";
                content += "Title: " + postResponse.getTitle()+"\n";
                content += "Text: " + postResponse.getText()+"\n";

                textViewResults.append(content);


            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {
                textViewResults.setText(t.getMessage());

            }
        });
    }
    public void deletePost(){
        Call<Void> call = jsonPlaceHolderApi.deletePost(5);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                textViewResults.setText("Code: "+ response.code());

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                textViewResults.setText(t.getMessage());

            }
        });

    }

}
