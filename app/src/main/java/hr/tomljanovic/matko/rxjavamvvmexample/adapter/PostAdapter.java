package hr.tomljanovic.matko.rxjavamvvmexample.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import hr.tomljanovic.matko.rxjavamvvmexample.AdapterCallback;
import hr.tomljanovic.matko.rxjavamvvmexample.R;
import hr.tomljanovic.matko.rxjavamvvmexample.model.Post;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private List<Post> postList;
    private Context context;
    private AdapterCallback listener;

    public PostAdapter() {
    }

    public PostAdapter(List<Post> postList, Context context) {
        this.postList = postList;
        this.context = context;
    }

    public void setListener(AdapterCallback listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        viewHolder.tvTitle.setText(postList.get(position).getTitle());
        viewHolder.tvBody.setText(new StringBuilder(postList.get(position).getBody().substring(0, 20)).append("..."));
        viewHolder.tvId.setText(String.valueOf(postList.get(position).getUserId()));

        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = postList.get(position).getId();
                if (listener != null) {
                    listener.onMethodCallback(id);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvBody;
        TextView tvId;
        LinearLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvId = itemView.findViewById(R.id.tvId);
            parentLayout = itemView.findViewById(R.id.parentLayout);
        }
    }
}
