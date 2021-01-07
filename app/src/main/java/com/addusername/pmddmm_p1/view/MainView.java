package com.addusername.pmddmm_p1.view;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.addusername.pmddmm_p1.R;
import com.addusername.pmddmm_p1.interfaces.RequiredViewOps;
import com.addusername.pmddmm_p1.presenter.MainPresenter;

import java.util.HashMap;

public class MainView extends AppCompatActivity implements RequiredViewOps {

    private HashMap<Integer, EditText> components;
    private MainPresenter mp;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu , menu);
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
        setupListeners();
    }
    private void setup() {
        mp = new MainPresenter(this);

        components = new HashMap<>(5);
        components.put(R.id.name, findViewById(R.id.name));
        components.put(R.id.surname, findViewById(R.id.surname));
        components.put(R.id.phone, findViewById(R.id.phone));
        components.put(R.id.email, findViewById(R.id.email));
        components.put(R.id.comments, findViewById(R.id.comments));
    }
    /**
     * Set onFocusChange listener to all inputs from {@link #components}, it triggers at focus loss
     */
    private void setupListeners() {
        View.OnFocusChangeListener listener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    mp.validate(
                            v.getId(),
                            v.getResources().getResourceName(v.getId()).split("id/")[1],
                            components.get(v.getId()).getText().toString());
                }
                mp.shouldEnableSubmit(getInputs());
            }
        };
        for(EditText input: components.values()){
            input.setOnFocusChangeListener(listener);
        }
    }
    public void submit(View view){

        mp.submit(getInputs());
    }
    public boolean menuClick(MenuItem item) {

        return true;
    }
    private String[] getInputs(){
        String[] formData = new String[5];
        formData[0] = components.get(R.id.name).getText().toString();
        formData[1] = components.get(R.id.surname).getText().toString();
        formData[2] = components.get(R.id.phone).getText().toString();
        formData[3] = components.get(R.id.email).getText().toString();
        formData[4] = components.get(R.id.comments).getText().toString();
        return formData;
    }
    @Override
    public void showToast() {
        Toast.makeText(getBaseContext(),R.string.toast,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void navigateToWebView() {

    }
    @Override
    public void changeStatus(int id, boolean b) {
        components.get(id).setBackgroundColor( (b)? Color.GREEN: Color.RED );
    }
}