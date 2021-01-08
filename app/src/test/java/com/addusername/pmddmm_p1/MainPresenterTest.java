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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
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
    public void menu_openGmapsViewMethodIsCalled_true(){
        String item1 = "openGmaps";
        mp.menu(item1);

        verify(rvo).openGmaps(any(String.class));
        verify(rvo, never()).navegateToWebView(any(String.class), any(boolean.class));
    }
    @Test
    public void menu_openInBrowser_true(){

        String item2 = "viewOnChrome";
        mp.menu(item2);

        verify(rvo, never()).openGmaps(any(String.class));
        verify(rvo).navegateToWebView(any(String.class), eq(true));
    }
    @Test
    public void menu_webviewViewMethodIsCalled_true(){
        String item3 = "viewDocs";
        String item4 = "github";

        mp.menu(item3);
        mp.menu(item4);

        verify(rvo, never()).openGmaps(any(String.class));
        verify(rvo, times(2)).navegateToWebView(any(String.class), eq(false));
    }
    @Test
    public void validate_viewChangeStatusIsCalled_true(){
            //todo or not?
    }
    @Test
    public void shouldEnableSubmit_badForm_false(){
        FormPojo fp = new FormPojo("name!","surname","610000000","asdf@asdf.com","ddddd ","online");

        when(pmo.parseFrom(new String[5])).thenReturn(fp);
        boolean should = mp.shouldEnableSubmit(new String[5]);

        verify(rvo).enableSubmitButton(false);
        Assert.assertFalse("wrong name", should);
    }
    @Test
    public void shouldEnableSubmit_goodForm_true(){
        FormPojo fp = new FormPojo("name","surname","610000000","asdf@asdf.com","ddddd ","online");

        when(pmo.parseFrom(new String[5])).thenReturn(fp);
        boolean should1 = mp.shouldEnableSubmit(new String[5]);

        verify(rvo).enableSubmitButton(true);
        Assert.assertTrue("ok", should1);
    }
    @Test
    public void submit_viewMethodsAreCalled_true(){

        FormPojo fp = new FormPojo("name","surname","610000000","asdf@asdf.com","ddddd ","online");

        when(pmo.parseFrom(new String[5])).thenReturn(fp);
        when(pmo.save(fp)).thenReturn(true);

        mp.submit(new String[5]);

        verify(rvo).navegateToWebView(any(String.class),any(boolean.class));
        verify(rvo).showToast();
    }
    @Test
    public void submit_viewMethodsAreNotCalled_false(){

        FormPojo fp = new FormPojo("name","surname","610000000","asdfasdf.com","ddddd ","online");

        when(pmo.parseFrom(new String[5])).thenReturn(fp);
        when(pmo.save(fp)).thenReturn(true);

        mp.submit(new String[5]);

        verify(rvo,never()).navegateToWebView(any(String.class),any(boolean.class));
        verify(rvo, never()).showToast();
    }

}
