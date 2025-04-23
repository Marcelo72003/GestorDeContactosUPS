package util;

import javax.swing.*;
import java.awt.*;

/**
 * Helper para mostrar diálogos con los colores de Theme.
 */
public class DialogUtils {
    public static void showInfo(Component parent, String message) {
        // Guardar valores originales
        Color oldPanelBg = UIManager.getColor("Panel.background");
        Color oldOptBg   = UIManager.getColor("OptionPane.background");
        Color oldMsgFg   = UIManager.getColor("OptionPane.messageForeground");
        Color oldBtnBg   = UIManager.getColor("Button.background");
        Color oldBtnFg   = UIManager.getColor("Button.foreground");

        // Aplicar tema personalizado sólo para este diálogo
        UIManager.put("Panel.background", Theme.BACKGROUND);
        UIManager.put("OptionPane.background", Theme.BACKGROUND);
        UIManager.put("OptionPane.messageForeground", Theme.TEXT_PRIMARY);
        UIManager.put("Button.background", Theme.ACCENT);
        UIManager.put("Button.foreground", Theme.TEXT_PRIMARY);
        UIManager.put("OptionPane.buttonForeground", Theme.TEXT_PRIMARY);

        // Mostrar diálogo
        JOptionPane.showMessageDialog(parent, message, "Información", JOptionPane.INFORMATION_MESSAGE);

        // Restaurar valores originales
        UIManager.put("Panel.background", oldPanelBg);
        UIManager.put("OptionPane.background", oldOptBg);
        UIManager.put("OptionPane.messageForeground", oldMsgFg);
        UIManager.put("Button.background", oldBtnBg);
        UIManager.put("Button.foreground", oldBtnFg);
    }
}
