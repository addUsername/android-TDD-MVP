package com.addusername.pmddmm_p1.model;

import com.addusername.pmddmm_p1.interfaces.ProvidedModelOps;

/**
 *  Separa los datos y la logica de negocio de una aplicación de la interfaz de usuario y el módulo
 *  encargado de gestionar los eventos y las comunicaciones.
 */
public class MainModel implements ProvidedModelOps {

    public MainModel(){}

    /**
     * @ineheritDoc
     *
     * @param formData
     * @return
     */
    @Override
    public FormPojo parseFrom(String[] formData) {
        return new FormPojo(formData[0],formData[1],formData[2],formData[3],formData[4], formData[5]);
    }

    /**
     * @InheritDoc
     *  TODO implement all this thing.
     * Store object in firebase
     * @param fp
     * @return
     */
    @Override
    public boolean save(FormPojo fp) {
        //TODO check if email/phone exists
        // save to firebase statically
        return true;
    }
}
