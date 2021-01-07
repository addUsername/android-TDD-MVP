package com.addusername.pmddmm_p1.presenter;

import android.util.Log;

import com.addusername.pmddmm_p1.interfaces.ProvidedModelOps;
import com.addusername.pmddmm_p1.interfaces.ProvidedPresenterOps;
import com.addusername.pmddmm_p1.interfaces.RequiredPresenterOps;
import com.addusername.pmddmm_p1.interfaces.RequiredViewOps;
import com.addusername.pmddmm_p1.model.FormPojo;

/**
 *  implementamos la validacion del formulario en la capa de presentacion por esto:
 *  https://stackoverflow.com/questions/36787718/android-mvp-doubts-about-validating
 *  e iniciamos una nueva actividad aqui por esto:
 *  https://stackoverflow.com/questions/36912209/android-mvp-open-activity-from-presenter-anti-pattern
 */
public class MainPresenter implements ProvidedPresenterOps, RequiredPresenterOps {

    private RequiredViewOps rvo;
    private ProvidedModelOps pmo;
    private String URL =  "http://google.com";

    /**
     * Test purposes
     * @param rvo Mock
     * @param pmo Mock
     */
    public MainPresenter(RequiredViewOps rvo, ProvidedModelOps pmo){
        this.rvo = rvo;
        this.pmo = pmo;
    }

    @Override
    public void menu(String item) {
        switch (item){
            case "openGmaps":
                // todo rvo.openGmaps();
                break;
            case "viewOnChrome":
                rvo.navegateToWebView(URL,true);
                break;
            case "viewDocs":
                // todo rvo.navegateToWebView(URLDOCS,false);
                break;
            default:
                //can't reach here
                break;
        }
    }

    @Override
    public void submit(String[] form) {
        FormPojo fp = pmo.parseFrom(form);
        if(ValidatorForm.isFormValid(fp) && pmo.save(fp)){
            rvo.showToast();
            rvo.navegateToWebView(URL,false);
        }
    }
    @Override
    public boolean shouldEnableSubmit(String[] inputs) {

        if(ValidatorForm.isFormValid(pmo.parseFrom(inputs))){
            rvo.enableSubmitButton(true);
            return true;
        }
        rvo.enableSubmitButton(false);
        return false;
    }

    @Override
    public void validate(int componentId, String componentName, String textToValidate) {

        Log.d("changeStatus",componentName+" "+textToValidate+" "+componentId);

        switch (componentName){
            case "name":
            case "surname":
                rvo.changeStatus(componentId,ValidatorForm.isNameValid(textToValidate));
                break;
            case "phone":
                rvo.changeStatus(componentId,ValidatorForm.isPhoneValid(textToValidate));
                break;
            case "email":
                rvo.changeStatus(componentId,ValidatorForm.isEmailValid(textToValidate));
                break;
            case "comments":
                rvo.changeStatus(componentId,ValidatorForm.isCommentValid(textToValidate));
                break;
            default:
                // Can't be reached
        }
    }
}
