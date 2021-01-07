package com.addusername.pmddmm_p1.presenter;

import com.addusername.pmddmm_p1.model.FormPojo;

public class ValidatorForm {
    /**
     * "Add a private constructor to hide the implicit public one" ok
     */
    private ValidatorForm(){}
    /**
     * Valida el email, case-sensitive y max caracteres = 222
     * @param email
     * @return
     */
    public static boolean isEmailValid(String email) {

        return email.matches("^([a-z]|[A-Z]){1,100}@([a-z]|[A-Z]){1,100}\\.([a-z]|[A-Z]){1,10}");
    }
    /**
     * Valida el num. telf., solo números nacionales
     * @param phone
     * @return
     */
    public static boolean isPhoneValid(String phone) {

        return phone.matches("^(\\+34)?[0-9]{9}");
    }
    /**
     * Valida el nombre, case insensitive, espacio = true
     * @param name
     * @return
     */
    public static boolean isNameValid(String name) {
        return name.matches("^[a-zA-Z ]+$");
    }
    /**
     * Valida que el commentario no tenga caracteres extraños.
     * @param comment
     * @return
     */
    public static boolean isCommentValid(String comment){
        return (!comment.matches("^.*[<\"]*.*$"));
    }

    /**
     * TODO fix comment validator
     * @param form1
     * @return
     */
    public static boolean isFormValid(FormPojo form1) {
        boolean toReturn = isNameValid(form1.getName()) &&
                isNameValid(form1.getSurname()) &&
                isPhoneValid(form1.getPhone()) &&
                isEmailValid(form1.getEmail());
        return toReturn;
    }
}
