package com.addusername.pmddmm_p1.interfaces;

/**
 * Operaciones a las cuales tiene acceso la Vista y son implementadas por el Presentador.
 * Permiten que la Vista se comunique con el Presentador.
 */
public interface ProvidedPresenterOps {

    void submit(String[] dataForm);
    void validate(int componentId, String componentName, String textToValidate);
    boolean shouldEnableSubmit(String[] inputs);
}
