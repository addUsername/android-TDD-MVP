package com.addusername.pmddmm_p1.interfaces;

import android.widget.EditText;

/**
 * Operaciones a las cuales tiene acceso la Vista y son implementadas por el Presentador.
 * Permiten que la Vista se comunique con el Presentador.
 */
public interface ProvidedPresenterOps {

    /**
     * Causa: Evento {@link android.view.View.OnClickListener}, recibe texto sin validar de la vista.
     * @param dataForm inputs formulario {@link com.addusername.pmddmm_p1.view.MainView}
     */
    void submit(String[] dataForm);

    /**
     * Causa: Evento {@link android.view.View.OnFocusChangeListener}, valida el texto de los inputs
     * @param componentId Id del componente  {@link EditText#getId()}
     * @param componentName Nombre dle componente {@link android.widget.EditText}
     * @param textToValidate Texto a validar {@link EditText#getText()}
     */
    void validate(int componentId, String componentName, String textToValidate);

    /**
     * Causa: Evento {@link android.view.View.OnFocusChangeListener}, indica cuando habilitar el boton
     * de submit
     * @param inputs
     * @return Test
     */
    boolean shouldEnableSubmit(String[] inputs);

    /** TODO implementar el onclick en el .java no el .xml
     * Causa: Evento {@link android.view.View.OnClickListener}
     * @param item Nombre {@link android.view.MenuItem}
     */
    void menu(String item);
}
