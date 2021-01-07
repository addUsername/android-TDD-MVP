package com.addusername.pmddmm_p1;

import com.addusername.pmddmm_p1.model.FormPojo;
import com.addusername.pmddmm_p1.presenter.ValidatorForm;

import org.junit.Assert;
import org.junit.Test;

public class ValidatorFormTest {

    @Test
    public void isFormValid_validForm_true(){
        FormPojo form1 = new FormPojo("name","surname","+34000000000","email@email.com","comment  c s");

        boolean isValid1 = ValidatorForm.isFormValid(form1);

        Assert.assertTrue("default user",isValid1);
    }
    @Test
    public void isFormValid_invalidForm_false(){
        FormPojo form1 = new FormPojo("name","surname","phone","email@email.com","comments");
        FormPojo form2 = new FormPojo("na me","sur name","+34910000000","emailemail.com","comments");
        FormPojo form3 = new FormPojo("nam1!e","surname","phone","email@email.com","comments");

        boolean isValid1 = ValidatorForm.isFormValid(form1);
        boolean isValid2 = ValidatorForm.isFormValid(form2);
        boolean isValid3 = ValidatorForm.isFormValid(form3);

        Assert.assertFalse("wrong phone",isValid1);
        Assert.assertFalse("wrong email",isValid2);
        Assert.assertFalse("wrong name",isValid3);
    }
    @Test
    public void isCommentValid_invalidComment_false(){
        final String comment1 = "AsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdfAsdf";
        final String comment2 = "<img src=''..";

        boolean isValid1 = ValidatorForm.isCommentValid(comment1);
        boolean isValid2 = ValidatorForm.isCommentValid(comment2);

        Assert.assertFalse("length > 400",isValid1);
        Assert.assertFalse("space ",isValid2);
    }
    @Test
    public void isCommentValid_validComment_true(){
        final String comment1 = "asdf \n nññ";

        boolean isValid1 = ValidatorForm.isCommentValid(comment1);

        Assert.assertTrue("normal",isValid1);
    }
    @Test
    public void isNameValid_validName_true(){
        final String name1 = "Asdf";
        final String name2 = "as df";

        boolean isValid1 = ValidatorForm.isNameValid(name1);
        boolean isValid2 = ValidatorForm.isNameValid(name2);

        Assert.assertTrue("upperCase",isValid1);
        Assert.assertTrue("space ",isValid2);
    }
    @Test
    public void isNameValid_invalidName_false(){
        final String name1 = "asdf!";
        final String name2 = "2asdf";

        boolean isValid1 = ValidatorForm.isNameValid(name1);
        boolean isValid2 = ValidatorForm.isNameValid(name2);

        Assert.assertFalse("!",isValid1);
        Assert.assertFalse("num",isValid2);
    }
    @Test
    public void isPhoneValid_invalidPhone_false(){
        final String phone1 = "asdf";
        final String phone2 = "+3491000AA00";

        boolean isValid1 = ValidatorForm.isPhoneValid(phone1);
        boolean isValid2 = ValidatorForm.isPhoneValid(phone2);

        Assert.assertFalse("[a-z]", isValid1);
        Assert.assertFalse("[0-9][a-z]", isValid2);

    }
    @Test
    public void isPhoneValid_validPhone_true(){
        final String phone1 = "+34000000000";
        final String phone2 = "000000000";

        boolean isValid1 = ValidatorForm.isPhoneValid(phone1);
        boolean isValid2 = ValidatorForm.isPhoneValid(phone2);

        Assert.assertTrue("", isValid1);
        Assert.assertTrue("", isValid2);
    }
    @Test
    public void isEmailValid_invalidEmail_false(){

        final String email1 = "abdc";
        final String email2 = "abdc@abdc";
        final String email3 = "abdc@abdc.";
        final String email4 = "abdc@abdc.com.com";

        boolean isValid1 = ValidatorForm.isEmailValid(email1);
        boolean isValid2 = ValidatorForm.isEmailValid(email2);
        boolean isValid3 = ValidatorForm.isEmailValid(email3);
        boolean isValid4 = ValidatorForm.isEmailValid(email4);

        Assert.assertFalse("no @",isValid1);
        Assert.assertFalse("no .",isValid2);
        Assert.assertFalse("no com",isValid3);
        Assert.assertFalse("no double .com",isValid4);
    }
    @Test
    public void isEmailValid_validEmail_true(){

        final String email1 = "asdf@gmail.com";
        boolean isValid1 = ValidatorForm.isEmailValid(email1);
        Assert.assertTrue(email1,isValid1);
    }
}
