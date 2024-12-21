package com.example.slasscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.slasscreen.Adapter.Adapter_1;
import com.example.slasscreen.models.Models_1;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SearchView searchView;
    RecyclerView recyclerView;
    List<Models_1> itemlist= new ArrayList<>();

    Adapter_1 adapter1;
    RequestQueue requestQueue;
    private Runnable searchRunable;
    Handler handler = new Handler(Looper.getMainLooper());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView =findViewById(R.id.searchView);
        recyclerView=findViewById(R.id.recyclerView);

        itemlist= new ArrayList<>();
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter1 =new Adapter_1(itemlist,this);
        recyclerView.setAdapter(adapter1);


        requestQueue=Volley.newRequestQueue(this);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                fatchData(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (searchRunable!=null){
                    handler.removeCallbacks(searchRunable);
                }

                searchRunable=()->fatchData(newText);
                handler.postDelayed(searchRunable,100);
                return true;
            }
        });


    }


    private void fatchData(String query){

        if (query.isEmpty()){
            itemlist.clear();
            adapter1.notifyDataSetChanged();
            return;
        }

        String url = "https://api.github.com/search/users?q=" + query;

        JsonObjectRequest  request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                itemlist.clear();
                try {
                    JSONArray array = response.getJSONArray("items");
                    for (int i=0;i<response.length();i++){
                        JSONObject jsonObject = array.getJSONObject(i);

                        String login = jsonObject.getString("login");
                        String avatarUrl = jsonObject.getString("avatar_url");
                        String htmlUrl = jsonObject.getString("html_url");
                        itemlist.add(new Models_1(login,avatarUrl,htmlUrl));

                    }
                    adapter1.notifyDataSetChanged();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });
        requestQueue.add(request);


    }
}