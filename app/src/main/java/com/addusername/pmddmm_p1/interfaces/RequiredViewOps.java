package com.addusername.pmddmm_p1.interfaces;

/**
 * Operaciones a las cuales tiene acceso el Presentador y son implementadas por la Vista.
 * Permiten que el Presentador se comunique con la Vista. {@link com.addusername.pmddmm_p1.view.MainView}
 */
public interface RequiredViewOps {
    /**
     * Abre Gmaps app
     * @param position Necesitado para construir la uri
     */
    void openGmaps(String position);
    /**
     * Muesta un {@link android.widget.Toast}
     */
    void showToast();

    /**
     * Inicia {@link com.addusername.pmddmm_p1.view.WebViewActivity}
     * @param url {@link android.webkit.WebView#loadUrl(String)}
     * @param shouldUseChrome
     */
    void navegateToWebView(String url, boolean shouldUseChrome);

    /**
     * Modifica {@link android.widget.EditText#setBackgroundColor(int)}
     * @param id
     * @param b
     */
    void changeStatus(int id, boolean b);

    /**
     * Modifica {@link android.widget.Button#setEnabled(boolean)}
     * @param b
     */
    void enableSubmitButton(boolean b);
}
