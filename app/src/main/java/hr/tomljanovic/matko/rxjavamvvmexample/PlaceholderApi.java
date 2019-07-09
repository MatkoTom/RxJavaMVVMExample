package hr.tomljanovic.matko.rxjavamvvmexample;

import java.util.List;

import hr.tomljanovic.matko.rxjavamvvmexample.model.Post;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PlaceholderApi {

    @GET("posts")
    Flowable<List<Post>> getPosts();

    @GET("posts/{id}")
    Flowable<Post> getSinglePost(@Path("id") int id);
}
