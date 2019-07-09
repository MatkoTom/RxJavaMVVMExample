package hr.tomljanovic.matko.rxjavamvvmexample.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.LiveDataReactiveStreams;

import java.util.List;

import hr.tomljanovic.matko.rxjavamvvmexample.adapter.RetrofitAdapter;
import hr.tomljanovic.matko.rxjavamvvmexample.model.Post;
import io.reactivex.schedulers.Schedulers;

public class MainRepository {
    private static MainRepository instance;

    public static MainRepository getInstance() {
        if (instance == null) {
            instance = new MainRepository();
        }
        return instance;
    }

    private MainRepository() {
    }

    public LiveData<List<Post>> makeReactiveQuery() {
        return LiveDataReactiveStreams.fromPublisher(RetrofitAdapter.getRequestApi()
                .getPosts()
                .subscribeOn(Schedulers.io()));
    }

    public LiveData<Post> makeSingleReactiveQuery(int id) {
        return LiveDataReactiveStreams.fromPublisher(RetrofitAdapter.getRequestApi()
                .getSinglePost(id)
                .subscribeOn(Schedulers.io()));
    }
}
