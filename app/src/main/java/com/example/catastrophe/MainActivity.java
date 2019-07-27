package com.example.catastrophe;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView bgapp,clover,test_btn,profile_pic;
    Animation bganim,clovernim,frombottom,fromup;
    LinearLayout textsplash,texthome,menus,login_signup_form;
    GridLayout grid_3;
    Button login_tab,signup_tab,login_btn,signup_btn;
    EditText input_email,input_password;

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bgapp = (ImageView) findViewById(R.id.bgapp);
        clover = (ImageView) findViewById(R.id.clover);
        profile_pic = (ImageView) findViewById(R.id.profile_pic);


        textsplash = (LinearLayout) findViewById(R.id.textsplash);
        texthome = (LinearLayout) findViewById(R.id.texthome);
        texthome.setAlpha(0);
        login_signup_form = (LinearLayout) findViewById(R.id.login_signup_form);
        grid_3 = (GridLayout) findViewById(R.id.grid_3);
//        menus = (LinearLayout) findViewById(R.id.menus);

        login_tab = (Button) findViewById(R.id.logintab);
        signup_tab = (Button) findViewById(R.id.signuptab);
        login_btn = (Button) findViewById(R.id.btn_login);
        signup_btn = (Button) findViewById(R.id.btn_signup);
//        test_btn = (Button) findViewById(R.id.test_btn);
//        test_btn = (ImageView) findViewById(R.id.test_btn);

        input_email = (EditText) findViewById(R.id.input_email);
        input_password = (EditText) findViewById(R.id.input_password);

        bganim = AnimationUtils.loadAnimation(this,R.anim.bganim);
        clovernim = AnimationUtils.loadAnimation(this,R.anim.clovernim);
        frombottom = AnimationUtils.loadAnimation(this,R.anim.frombottom);
        fromup = AnimationUtils.loadAnimation(this,R.anim.fromup);

        signup_btn.setVisibility(View.GONE);
        login_btn.setVisibility(View.VISIBLE);
        signup_tab.setClickable(true);
        login_tab.setClickable(false);
        signup_tab.setBackgroundColor(Color.argb(0,0,0,0));
        login_tab.setBackgroundColor(Color.argb(15,255,0,0));


//        bgapp.animate().translationY(-2250).setDuration(800).setStartDelay(800);
//        clover.animate().translationX(-300).alpha(0).setDuration(800).setStartDelay(800);
//        textsplash.animate().translationY(140).alpha(0).setDuration(800).setStartDelay(800);
//        texthome.startAnimation(frombottom);
//        menus.startAnimation(frombottom);

//        ((TextView)getWindow().getDecorView().findViewById(android.R.id.title)).setGravity(Gravity.CENTER);
        mQueue = Volley.newRequestQueue(this);
    }

    boolean Validate(String email,String password){
        //Check with the backend
        // TODO: 7/23/2019

        return true;
    }

    void AddToDatabase(String email, String password){
        //Add this user to the database
        // TODO: 7/23/2019
    }

    public void Login(View view){
        String email = input_email.getText().toString();
        String password = input_password.getText().toString();

//        boolean valid = Validate(email,password);
//
//        if (valid){
//            //animate the scene
//            bgapp.animate().translationY(-2250).setDuration(800);
//            clover.animate().translationX(-300).alpha(0).setDuration(800);
//            texthome.setAlpha(1);
//            texthome.startAnimation(frombottom);
//            grid_3.startAnimation(frombottom);
//            textsplash.animate().translationY(140).alpha(0).setDuration(800);
//            login_signup_form.animate().translationY(-2250).alpha(0).setDuration(800);
//
//        }
//        else{
//            Toast toast = Toast.makeText(getApplicationContext(),"Wrong Email or Password",Toast.LENGTH_SHORT);
//            toast.setMargin(0,0.6f);
//            toast.show();
//        }

        String url = "http://www.mocky.io/v2/5d3c07b030000018a4a2a19a";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Boolean result = false;
                        try {
                            result = response.getBoolean("response");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (result){
                            LoginSuccess();
                        }else{
                            LoginFailure();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Login","Failure");
                        error.printStackTrace();
                    }
                });
        mQueue.add(request);
    }

    public void LoginSuccess(){
        Log.d("Login","Success");
        bgapp.animate().translationY(-2250).setDuration(800);
        clover.animate().translationX(-300).alpha(0).setDuration(800);
        texthome.setAlpha(1);
        texthome.startAnimation(frombottom);
        grid_3.startAnimation(frombottom);
        textsplash.animate().translationY(140).alpha(0).setDuration(800);
        login_signup_form.animate().translationY(-2250).alpha(0).setDuration(800);
    }

    public void LoginFailure(){
        Toast toast = Toast.makeText(getApplicationContext(),"Wrong Email or Password",Toast.LENGTH_SHORT);
        toast.setMargin(0,0.6f);
        toast.show();
    }

    public void SignUp(View view){
        String email = input_email.getText().toString();
        String password = input_password.getText().toString();

        AddToDatabase(email,password);

        //if sign up was successful
        Toast toast = Toast.makeText(getApplicationContext(),"Sign Up Successful",Toast.LENGTH_SHORT);
        toast.setMargin(0,0.6f);
        toast.show();

        Login_tab(view);
    }

    public void Login_tab(View view){
        login_tab.setClickable(false);
        signup_tab.setClickable(true);
        login_btn.setVisibility(View.VISIBLE);
        signup_btn.setVisibility(View.GONE);
        login_tab.setBackgroundColor(Color.argb(15,255,0,0));
        signup_tab.setBackgroundColor(Color.argb(0,0,0,0));
    }
    public void Signup_tab(View view){
        login_tab.setClickable(true);
        signup_tab.setClickable(false);
        login_btn.setVisibility(View.GONE);
        signup_btn.setVisibility(View.VISIBLE);
        login_tab.setBackgroundColor(Color.argb(0,0,0,0));
        signup_tab.setBackgroundColor(Color.argb(15,255,0,0));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void View_plant(View view){
//        test_btn.setVisibility(View.GONE);
//        bgapp.animate().translationYBy(200).setDuration(800);
//        texthome.animate().translationYBy(-200).alpha(0).setDuration(800);
        Intent intent = new Intent(this, PlantProfileActivity.class);
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                // Do something after 5s = 5000ms
//                startActivity(intent);
//            }
//        }, 1200);
        Pair[] pairs = new Pair[2];
        pairs[0] = new Pair<View,String>(view,"transition_profile_1");
        pairs[1] = new Pair<View,String>(bgapp,"transition_profile_2");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this
                ,pairs);

        startActivity(intent,options.toBundle());
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void Add_plant(View view){
        Intent intent = new Intent(this, AddPlantActivity.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View,String>(bgapp,"transition_profile_2");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this
                ,pairs);

        startActivity(intent,options.toBundle());
    }

    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void Add_Or_View_Plant(View view){
        Log.d("Image1", ((ImageView)view).getDrawable().getCurrent().toString());
        Log.d("Image2", getDrawable(R.drawable.ic_add_box_black_24dp).getCurrent().toString());

        if (((ImageView)view).getDrawable().isFilterBitmap() == false){
            Add_plant(view);
        }
        else{
            View_plant(view);
        }
    }




//    public void OpenProfiles(View view) {
//        Intent intent = new Intent(this, ProfilesActivity.class);
//        startActivity(intent);
//    }
}