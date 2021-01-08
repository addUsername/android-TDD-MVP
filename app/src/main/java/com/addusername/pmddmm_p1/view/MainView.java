package com.addusername.pmddmm_p1.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.addusername.pmddmm_p1.R;
import com.addusername.pmddmm_p1.interfaces.RequiredViewOps;
import com.addusername.pmddmm_p1.model.MainModel;
import com.addusername.pmddmm_p1.presenter.MainPresenter;

import java.util.HashMap;

public class MainView extends AppCompatActivity implements RequiredViewOps {

    /**
     * Alamcena todos los campos {@link EditText}
     */
    private HashMap<Integer, EditText> components;
    /**
     * {@link Button} submit
     */
    private Button submit;
    private Spinner spinner;
    private MainPresenter mp;

    /**
     * @InheritDoc
     *
     * Crea el menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu , menu);
        return true;
    }
    /**
     * @InheritDoc
     *
     * {@Link #setup} {@link #setupListeners()}
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
        setupListeners();
    }

    /**
     * Instancia {@link MainPresenter}, {@link MainModel} y {@link #components}
     */
    private void setup() {
        submit = findViewById(R.id.button);
        spinner = findViewById(R.id.spinner);

        mp = new MainPresenter(this, new MainModel());

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
                if(!hasFocus || hasFocus){
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

    /**
     * Parsea {@link MenuItem#getItemId()} a {@link String} y llama a {@link MainPresenter#menu(String)}
     * @param item
     */
    public void menuClick(MenuItem item) {

        switch (item.getItemId()){
            case R.id.viewOnChrome:
                mp.menu("viewOnChrome");
                break;
            case R.id.openGmaps:
                mp.menu("openGmaps");
                break;
            case R.id.viewDocs:
                mp.menu("viewDocs");
                break;
            case R.id.github:
                mp.menu("github");
            default:
                break;
        }
        mp.menu(item.toString());
    }

    /**
     * todo delete method
     * LLama a {@link #showAlertDialog()}
     * @param view
     */
    public void submit(View view){
        showAlertDialog();
    }

    /**
     * Muestra un dialogo al pulsar el boton submit, si la respuesta es positiva llama a
     * {@link MainPresenter#submit(String[])}
     */
    private void showAlertDialog() {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        //adb.setView(new AlertDialogLayout());
        adb.setTitle(getString(R.string.dialogTitle));
        adb.setIcon(android.R.drawable.ic_dialog_alert);
        adb.setPositiveButton(getString(R.string.dialogOk), new DialogInterface.OnClickListener() {
            @Override
            public void onClick (DialogInterface dialog, int which) {
                mp.submit(getInputs());
            }
        });
        adb.setNegativeButton(getString(R.string.dialogCancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        adb.show();
    }

    /**
     * Parse los textos de los componentes a {@link String}[]
     * @return
     */
    private String[] getInputs(){
        String[] formData = new String[6];
        formData[0] = components.get(R.id.name).getText().toString();
        formData[1] = components.get(R.id.surname).getText().toString();
        formData[2] = components.get(R.id.phone).getText().toString();
        formData[3] = components.get(R.id.email).getText().toString();
        formData[4] = components.get(R.id.comments).getText().toString();
        formData[5] = spinner.getSelectedItem().toString();
        return formData;
    }

    /**
     * @InheritDoc
     * https://developers.google.com/maps/documentation/urls/android-intents
     * @param position
     */
    @Override
    public void openGmaps(String position) {

        Uri gmmIntentUri = Uri.parse("google.streetview:cbll="+position);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    /**
     * @InheritDoc
     */
    @Override
    public void showToast() {
        Toast.makeText(getBaseContext(), R.string.booked,Toast.LENGTH_SHORT).show();
    }

    /**
     * @InheritDoc
     *
     * @param url {@link android.webkit.WebView#loadUrl(String)}
     * @param shouldUseChrome
     */
    @Override
    public void navegateToWebView(String url, boolean shouldUseChrome) {

        Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
        intent.putExtra("URL", url);
        intent.putExtra("shouldUseChrome", shouldUseChrome);
        startActivityForResult(intent, 1);
    }

    /**
     * @InheritDoc
     *
     * @param id
     * @param b
     */
    @Override
    public void changeStatus(int id, boolean b) {
        components.get(id).setBackgroundColor( (b)? Color.GREEN: Color.RED );
    }

    /**
     * @InheritDoc
     *
     * @param b
     */
    @Override
    public void enableSubmitButton(boolean b) {
        submit.setEnabled(b);
    }
}