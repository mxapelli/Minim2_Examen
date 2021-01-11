package edu.upc.dsa.minim2_github;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.SharedPreferences;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InitialActivity extends AppCompatActivity {

    APIInterface apiInterface;
    public static final String MY_PREFS_NAME = "user_pref";
    EditText uname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        uname = (EditText) findViewById(R.id.user);
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        String username = prefs.getString("username", "");

        //String name =getIntent().getExtras().getString("usuario");
        //uname.setText(name);

    }

    public void sendUser(View view){
        String username = uname.getText().toString();
        if (username.equals(""))
            Toast.makeText(getApplicationContext(), "Enter the username", Toast.LENGTH_LONG).show();
        else {
            Call<Usuario> userCall = apiInterface.getUserInfo(username);
            userCall.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    //progressBar.setVisibility(View.VISIBLE);
                    Log.d("TAG",response.code()+"");
                    if (response.code() == 200) {
                        Usuario user1 = response.body();
                        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("username", username);
                        editor.commit();
                        Call<List<Repo>> reposCall = apiInterface.getPublic_repos(username);
                        reposCall.enqueue(new Callback<List<Repo>>() {
                            @Override
                            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                                user1.setRepos(response.body());
                                instanciaUsuario.getInstance().setUser(user1);
                                //progressBar.setVisibility(View.GONE);
                                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                                startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<List<Repo>> call, Throwable t) {

                            }
                        });
                    }
                    else{
                        Toast toast = Toast.makeText(getApplicationContext(),"User does not exist", Toast.LENGTH_LONG);
                        toast.show();

                    }
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    call.cancel();
                    Log.d("Error","Failure");
                }
            });
        }
    }
}