package com.addusername.pmddmm_p1.model;

import com.addusername.pmddmm_p1.interfaces.ProvidedModelOps;

public class MainModel implements ProvidedModelOps {

    public MainModel(){}
    @Override
    public FormPojo parseFrom(String[] formData) {
        return new FormPojo(formData[0],formData[1],formData[2],formData[3],formData[4]);
    }
    @Override
    public boolean save(FormPojo fp) {
        //TODO check if email/phone exists
        // save to firebase statically
        return true;
    }
}
