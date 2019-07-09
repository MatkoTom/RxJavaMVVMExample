package hr.tomljanovic.matko.rxjavamvvmexample.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import hr.tomljanovic.matko.rxjavamvvmexample.model.Post;
import hr.tomljanovic.matko.rxjavamvvmexample.repository.MainRepository;

public class MainViewModel extends ViewModel {
    private MainRepository repository;

    public MainViewModel() {
        repository = MainRepository.getInstance();
    }

    public LiveData<List<Post>> makeQuery() {
        return repository.makeReactiveQuery();
    }

    public LiveData<Post> makeSingleQuery(int id) {
        return repository.makeSingleReactiveQuery(id);
    }
}
