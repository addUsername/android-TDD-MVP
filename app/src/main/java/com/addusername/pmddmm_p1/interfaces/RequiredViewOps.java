package com.addusername.pmddmm_p1.interfaces;

/**
 * Operaciones a las cuales tiene acceso el Presentador y son implementadas por la Vista.
 * Permiten que el Presentador se comunique con la Vista.
 */
public interface RequiredViewOps {

    void showToast();
    void navigateToWebView();
    void changeStatus(int id, boolean b);
}
