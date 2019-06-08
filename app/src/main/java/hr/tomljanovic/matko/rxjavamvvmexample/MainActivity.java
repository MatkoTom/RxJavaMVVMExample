package hr.tomljanovic.matko.rxjavamvvmexample;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.tomljanovic.matko.rxjavamvvmexample.adapter.PostAdapter;
import hr.tomljanovic.matko.rxjavamvvmexample.model.Post;
import hr.tomljanovic.matko.rxjavamvvmexample.viewmodel.MainViewModel;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = "STASEDOGADJA";

    @BindView(R.id.rvPosts)
    RecyclerView rvPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateList();
    }

    private void populateList() {
        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.makeQuery().observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(@Nullable List<Post> posts) {
                Log.d(TAG, "onChanged: date response");
                Log.d(TAG, "onChanged: " + posts);

                PostAdapter adapter = new PostAdapter(posts, getApplicationContext());
                rvPosts.setAdapter(adapter);
            }
        });

        ButterKnife.bind(this);

        rvPosts.setHasFixedSize(true);
        rvPosts.setLayoutManager(new LinearLayoutManager(this));
    }
}
