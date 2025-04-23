package util;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class IconUtils {
    public static Icon loadIcon(String filename) {
        URL url = IconUtils.class.getClassLoader().getResource(filename);
        if (url != null) {
            ImageIcon original = new ImageIcon(url);
            // Escalado seguro, por ejemplo 24x24
            Image scaled = original.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            return new ImageIcon(scaled);
        } else {
            System.err.println("No se encontró el ícono: " + filename);
            return null;
        }
    }
}
