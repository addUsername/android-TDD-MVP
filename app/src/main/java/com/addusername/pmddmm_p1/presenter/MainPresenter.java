package com.addusername.pmddmm_p1.presenter;

import com.addusername.pmddmm_p1.interfaces.ProvidedModelOps;
import com.addusername.pmddmm_p1.interfaces.ProvidedPresenterOps;
import com.addusername.pmddmm_p1.interfaces.RequiredPresenterOps;
import com.addusername.pmddmm_p1.interfaces.RequiredViewOps;
import com.addusername.pmddmm_p1.model.FormPojo;

/**
 *  El presentador actúa sobre el modelo y la vista. Recupera datos de los repositorios (el modelo),
 *  y los formatea para mostrarlos en la vista.
 *
 *  Implementamos la validacion del formulario en la capa de presentacion por esto:
 *  @see <a href="https://stackoverflow.com/questions/36787718/android-mvp-doubts-about-validating">android-mvp-doubts-about-validating</a>
 *  e iniciamos una nueva actividad aqui por esto:
 *   @see <a href="https://stackoverflow.com/questions/36912209/android-mvp-open-activity-from-presenter-anti-pattern">android-mvp-open-activity-from-presenter-anti-pattern</a> *
 */
public class MainPresenter implements ProvidedPresenterOps, RequiredPresenterOps {

    private RequiredViewOps rvo;
    private ProvidedModelOps pmo;
    private static final String URL =  "https://google.com";
    private static final String POSITION_GMAPS = "46.414382,10.013988";

    /**
     * Constructor
     * @param rvo
     * @param pmo
     */
    public MainPresenter(RequiredViewOps rvo, ProvidedModelOps pmo){
        this.rvo = rvo;
        this.pmo = pmo;
    }

    /**
     * @InheritDoc
     *
     * Controla la carga de {@link android.content.Intent} llamando a {@link RequiredViewOps#openGmaps(String)} o
     * {@link RequiredViewOps#navegateToWebView(String, boolean)}
     * @param item Nombre {@link android.view.MenuItem}
     */
    @Override
    public void menu(String item) {
        switch (item){
            case "openGmaps":
                rvo.openGmaps(POSITION_GMAPS);
                break;
            case "viewOnChrome":
                rvo.navegateToWebView(URL,true);
                break;
            case "viewDocs":
                rvo.navegateToWebView("file:///android_asset/docs/overview-summary.html",false);
                break;
            case "github":
                rvo.navegateToWebView("https://github.com/addUsername/android-TDD-MVP", false);
                break;
            default:
                //can't reach here
                break;
        }
    }

    /**
     * @inheritDoc
     *
     * Parsea el parametro a {@link FormPojo}, lo valida {@link ValidatorForm#isFormValid(FormPojo)}
     * en caso de ser valido lo guarda {@link ProvidedModelOps#save(FormPojo)} y ejecuta los metodos
     * {@link RequiredViewOps#showToast()} y {@link RequiredViewOps#navegateToWebView(String, boolean)}
     * @param form
     */
    @Override
    public void submit(String[] form) {
        FormPojo fp = pmo.parseFrom(form);
        if(ValidatorForm.isFormValid(fp) && pmo.save(fp)){
            rvo.showToast();
            rvo.navegateToWebView(URL,false);
        }
    }

    /**
     * @inheritDoc
     *
     * Valida el formulario {@link ValidatorForm#isFormValid(FormPojo)} y en caso de ser valido
     * {@link RequiredViewOps#enableSubmitButton(boolean)} habilita el boton.
     * @param inputs
     * @return {@link Boolean} Parametro utilizado en {@link RequiredViewOps#enableSubmitButton(boolean)}
     */
    @Override
    public boolean shouldEnableSubmit(String[] inputs) {

        if(ValidatorForm.isFormValid(pmo.parseFrom(inputs))){
            rvo.enableSubmitButton(true);
            return true;
        }
        rvo.enableSubmitButton(false);
        return false;
    }

    /**
     * @inheritDoc
     *
     * Valida cada input y llama a {@link RequiredViewOps#changeStatus(int, boolean)}
     * @param componentId Id del componente  {@link android.widget.EditText#getId()}
     * @param componentName Nombre dle componente {@link android.widget.EditText}
     * @param textToValidate Texto a validar {@link android.widget.EditText#getText()}
     */
    @Override
    public void validate(int componentId, String componentName, String textToValidate) {

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
