package edu.upc.dsa.minim2_github;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ResultActivity extends AppCompatActivity {

    Usuario user;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView fullName = findViewById(R.id.textFullName);
        TextView username = findViewById(R.id.textUsername);
        TextView repos = findViewById(R.id.textRepos);
        TextView following = findViewById(R.id.textFollowing);
        TextView followers = findViewById(R.id.textFollowers);
        recyclerView = (RecyclerView) findViewById(R.id.reposList);

        user = instanciaUsuario.getInstance().getUser();

        Picasso.with(getApplicationContext()).load(user.getAvatar_url()).into((ImageView) findViewById(R.id.avatarImage));
        fullName.setText(user.getName());
        username.setText("Username: " + user.getLogin());
        repos.setText("Repositories: " + String.valueOf(user.getPublic_repos()));
        following.setText("Following: " + String.valueOf(user.getFollowing()));
        followers.setText("Followers: " + String.valueOf(user.getFollowers()));


        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        List<Repo> input = user.getRepos();
        mAdapter = new RecyclerAdapter(input);
        recyclerView.setAdapter(mAdapter);
    }
}