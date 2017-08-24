package com.example.soman.samplefirstproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static String var = "/users/somanpradhan/repos";

    private static boolean checker = true;


    private RecyclerView recyclerView;

    private RecyclerView.Adapter adapter;

    private ArrayList<ListItem> listItems;

    private ApiInterface apiInterface;

    private RecyclerView.LayoutManager layoutManager;

    private EditText editText1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);



        editText1 = (EditText) findViewById(R.id.editText);

        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {



            }

            @Override
            public void afterTextChanged(Editable s) {

//                listItems.clear();
//                var = editText1.getText().toString();
//                connect();
            }
        });

        editText1.setOnEditorActionListener(new TextView.OnEditorActionListener() {


            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {


                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    var =   "/users/"+editText1.getText().toString()+"/repos";
                    connect();
                    return true;
                }




                return false;
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        listItems = new ArrayList<ListItem>();


        connect();





        /*listItems = new ArrayList<ListItem>();
        for(int i = 0;i<10;i++){
            int a = i +1;
            ListItem listItem = new ListItem("Heading "+a,"new data "+a,"Description "+a);
            Log.d("this is error",listItem.getOwnerName());
            listItems.add(listItem);

        }
        adapter = new RecycleAdapter(listItems,this);
        recyclerView.setAdapter(adapter);*/


    }


    private void connect() {

        checker = false;

        editText1.setEnabled(false);

               apiInterface = ApiClient.getApiClient().create(ApiInterface.class);


        Call<ResponseBody> call = apiInterface.getApiInterface(var);
        Log.d("connect", call.toString());

        recyclerView.setAdapter(null);
        findViewById(R.id.progressbar).setVisibility(View.VISIBLE);


        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.body() == null) {
                    Log.d("Response is null ", "no response");
                    editText1.setEnabled(true);

                    Toast.makeText(getApplication(), "No Such username", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        JSONArray jsonArray = null;
                        jsonArray = new JSONArray(response.body().string());
                        findViewById(R.id.progressbar).setVisibility(View.GONE);
                        listItems.clear();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            JSONObject jsonobj = jsonObject.getJSONObject("owner");
                            Log.d("this is JSON", jsonObject.getString("name"));

                            String descrip = "";
                            if (jsonObject.getString("description") != null)
                                descrip = jsonObject.getString("description").toString();

                            ListItem listItem = new ListItem(jsonObject.getString("name"), jsonobj.getString("login"), descrip, jsonobj.getString("avatar_url"));
                            listItems.add(listItem);

                        }

                        editText1.setEnabled(true);

                        adapter = new RecycleAdapter(listItems, getApplicationContext());

                        recyclerView.setAdapter(adapter);


                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Error In Retrieving Data", Toast.LENGTH_LONG);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });

        //editText1.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        outState.putSerializable("list", listItems);


    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        listItems = (ArrayList<ListItem>) savedInstanceState.getSerializable("list");
        Log.d("error", "working");
        findViewById(R.id.progressbar).setVisibility(View.GONE);
        adapter = new RecycleAdapter(listItems, getApplicationContext());
        recyclerView.setAdapter(adapter);

    }


}
