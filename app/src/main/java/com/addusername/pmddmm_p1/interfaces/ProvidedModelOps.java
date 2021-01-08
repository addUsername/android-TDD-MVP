package com.addusername.pmddmm_p1.interfaces;

import com.addusername.pmddmm_p1.model.FormPojo;

/**
 * Operaciones a las cuales tiene acceso el Presentador y son implementadas por el Modelo. Permiten
 * que el Presentador se comunique con el Modelo.
 */
public interface ProvidedModelOps {

    /**
     * Parsea {@link String}[] a {@link FormPojo}
     * @param formData
     * @return
     */
    FormPojo parseFrom(String[] formData);

    /**
     * Guarda el  {@link FormPojo}
     * TODO implementar firebase y mirar duplicados
     * @param fp
     * @return
     */
    boolean save(FormPojo fp);
}
