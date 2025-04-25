package util;

import java.util.Locale;
import java.util.ResourceBundle;

public class IdiomaUtils {
    private static ResourceBundle bundle;

    public static void cargarIdioma(Locale locale) {
    	bundle = ResourceBundle.getBundle("idiomas.Messages", locale);

    }

    public static String getTexto(String clave) {
        return bundle.getString(clave);
    }

    public static ResourceBundle getBundle() {
        return bundle;
    }
}

