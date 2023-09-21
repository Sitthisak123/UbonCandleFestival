package com.example.uboncandlefestival;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.uboncandlefestival.adapter.TempleAdapter;
import com.example.uboncandlefestival.model.Temple;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TempleAdapter adapter;
    private List<Temple> temples;
    private RecyclerView rcvTemple;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        matchView();
        temples = new ArrayList<Temple>();

        getTemple();
    }

    private void getTemple() {
        String url = "https://terapat5489.pythonanywhere.com/api/temples";
        JsonArrayRequest request = new JsonArrayRequest(
                url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject object = null;
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                object = response.getJSONObject(i);
                                Log.d("temple", object.toString());
                                int id = object.getInt("id");
                                String name = object.getString("name");
                                String description = object.getString("description");

                                temples.add(new Temple(id, name, description));
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        adapter = new TempleAdapter(MainActivity.this, temples);
                        rcvTemple.setAdapter(adapter);
                        rcvTemple.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,
                                error.getMessage(),
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }
        );
        queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(request);
    }

    private void matchView() {
        rcvTemple = findViewById(R.id.rcv_temple);

    }
}