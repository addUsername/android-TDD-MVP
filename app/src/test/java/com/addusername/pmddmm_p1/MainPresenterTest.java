package com.addusername.pmddmm_p1;

import com.addusername.pmddmm_p1.interfaces.ProvidedModelOps;
import com.addusername.pmddmm_p1.interfaces.RequiredViewOps;
import com.addusername.pmddmm_p1.model.FormPojo;
import com.addusername.pmddmm_p1.presenter.MainPresenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * MethodName_StateUnderTest_ExpectedBehavior
 */
public class MainPresenterTest {

    private MainPresenter mp;
    @Mock
    private RequiredViewOps rvo;
    @Mock
    private ProvidedModelOps pmo;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mp = new MainPresenter(rvo,pmo);
    }
    @Test
    public void shouldEnableSubmit_badForm_false(){
        FormPojo fp = new FormPojo("name!","surname","610000000","asdf@asdf.com","ddddd ");

        when(pmo.parseFrom(new String[5])).thenReturn(fp);
        boolean should = mp.shouldEnableSubmit(new String[5]);

        verify(rvo).enableSubmitButton(false);
        Assert.assertFalse("wrong name", should);
    }
    @Test
    public void shouldEnableSubmit_goodForm_true(){
        FormPojo fp = new FormPojo("name","surname","610000000","asdf@asdf.com","ddddd ");

        when(pmo.parseFrom(new String[5])).thenReturn(fp);
        boolean should1 = mp.shouldEnableSubmit(new String[5]);

        verify(rvo).enableSubmitButton(true);
        Assert.assertTrue("ok", should1);
    }
    @Test
    public void submit_viewMethodsAreCalled_true(){

        FormPojo fp = new FormPojo("name","surname","610000000","asdf@asdf.com","ddddd ");

        when(pmo.parseFrom(new String[5])).thenReturn(fp);
        when(pmo.save(fp)).thenReturn(true);

        mp.submit(new String[5]);

        verify(rvo).navegateToWebView();
        verify(rvo).showToast();
    }
    @Test
    public void submit_viewMethodsAreNotCalled_false(){

        FormPojo fp = new FormPojo("name","surname","610000000","asdfasdf.com","ddddd ");

        when(pmo.parseFrom(new String[5])).thenReturn(fp);
        when(pmo.save(fp)).thenReturn(true);

        mp.submit(new String[5]);

        verify(rvo,never()).navegateToWebView();
        verify(rvo, never()).showToast();
    }

}
