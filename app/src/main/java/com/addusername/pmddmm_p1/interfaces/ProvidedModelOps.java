package com.addusername.pmddmm_p1.interfaces;

import com.addusername.pmddmm_p1.model.FormPojo;

/**
 * Operaciones a las cuales tiene acceso el Presentador y son implementadas por el Modelo. Permiten
 * que el Presentador se comunique con el Modelo.
 */
public interface ProvidedModelOps {

    FormPojo parseFrom(String[] formData);
    boolean save(FormPojo fp);
}
