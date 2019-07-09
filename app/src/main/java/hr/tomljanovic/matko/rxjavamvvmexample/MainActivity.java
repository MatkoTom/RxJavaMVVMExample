package hr.tomljanovic.matko.rxjavamvvmexample;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.tomljanovic.matko.rxjavamvvmexample.adapter.PostAdapter;
import hr.tomljanovic.matko.rxjavamvvmexample.model.Post;
import hr.tomljanovic.matko.rxjavamvvmexample.viewmodel.MainViewModel;
import io.reactivex.disposables.CompositeDisposable;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivityLog";
    private CompositeDisposable disposable = new CompositeDisposable();
    private PostAdapter adapter;

    @BindView(R.id.rvPosts)
    RecyclerView rvPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        rvPosts.setHasFixedSize(true);
        rvPosts.setLayoutManager(new LinearLayoutManager(this));

        populateList();
    }

    private void populateList() {
        MainViewModel viewModel = ViewModelProviders.of(MainActivity.this).get(MainViewModel.class);
        viewModel.makeQuery().observe(MainActivity.this, new Observer<List<Post>>() {
            @Override
            public void onChanged(@Nullable List<Post> posts) {
                adapter = new PostAdapter(posts, getApplicationContext());
                rvPosts.setAdapter(adapter);
                logItems();
            }
        });
    }

    public void logItems() {
        adapter.setListener(new AdapterCallback() {
            @Override
            public void onMethodCallback(int id) {
                MainViewModel viewModel = ViewModelProviders.of(MainActivity.this).get(MainViewModel.class);
                viewModel.makeSingleQuery(id).observe(MainActivity.this, new Observer<Post>() {
                    @Override
                    public void onChanged(@Nullable final Post post) {
                        Log.d(TAG, "onChanged: data response");
                        Log.d(TAG, "onChanged: " + post);
                        Toast.makeText(MainActivity.this, post.getTitle(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}



