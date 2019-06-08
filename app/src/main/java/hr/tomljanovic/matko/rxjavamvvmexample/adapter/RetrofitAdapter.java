package hr.tomljanovic.matko.rxjavamvvmexample.adapter;

import hr.tomljanovic.matko.rxjavamvvmexample.PlaceholderApi;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAdapter {

    private static final String URL = "https://jsonplaceholder.typicode.com/";

    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static PlaceholderApi requestApi = retrofit.create(PlaceholderApi.class);

    public static PlaceholderApi getRequestApi() {
        return requestApi;
    }

}
