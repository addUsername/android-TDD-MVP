package com.addusername.pmddmm_p1;

import com.addusername.pmddmm_p1.model.FormPojo;
import com.addusername.pmddmm_p1.model.MainModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MainModelTest {

    private MainModel mm;
    @Before
    public void init() {
        mm = new MainModel();
    }

    @Test
    public void parseFrom_validForm_notNull(){
        String[] formData = {"name","surname","610000000","email@email.com","comments"};
        FormPojo fp = mm.parseFrom(formData);
        Assert.assertNotNull("not null",fp);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void parseFrom_inValidForm_exception(){
        String[] formData = {"name","surname","610000000","comments"};
        mm.parseFrom(formData);
    }

    //todo save()
}
