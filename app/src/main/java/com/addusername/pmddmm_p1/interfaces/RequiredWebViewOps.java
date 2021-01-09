package com.addusername.pmddmm_p1.interfaces;

/**
 * Operaciones a las cuales tiene acceso el Presentador y son implementadas por
 * {@link com.addusername.pmddmm_p1.view.WebViewActivity}.
 * Permiten que el Presentador se comunique con la Vista.
 */
public interface RequiredWebViewOps {
    /**
     * Expone el método {@link android.webkit.WebView#loadUrl(String)} en la vista WebViewActivity
     * @param url url a cargar en el WebView
     */
    void loadUrl(String url);
}
