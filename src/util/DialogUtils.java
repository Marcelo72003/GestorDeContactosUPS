package util;

import javax.swing.*;
import java.awt.*;

public class DialogUtils {

    public static void showInfo(Component parent, String message) {
        aplicarColoresTemporales();
        JOptionPane.showMessageDialog(parent, message, "Información", JOptionPane.INFORMATION_MESSAGE);
        restaurarColoresOriginales();
    }

    public static void showWarning(Component parent, String message) {
        aplicarColoresTemporales();
        JOptionPane.showMessageDialog(parent, message, "Advertencia", JOptionPane.WARNING_MESSAGE);
        restaurarColoresOriginales();
    }

    public static void showError(Component parent, String message) {
        aplicarColoresTemporales();
        JOptionPane.showMessageDialog(parent, message, "Error", JOptionPane.ERROR_MESSAGE);
        restaurarColoresOriginales();
    }

    // Guarda valores originales
    private static Color oldPanelBg, oldOptBg, oldMsgFg, oldBtnBg, oldBtnFg;

    private static void aplicarColoresTemporales() {
        oldPanelBg = UIManager.getColor("Panel.background");
        oldOptBg   = UIManager.getColor("OptionPane.background");
        oldMsgFg   = UIManager.getColor("OptionPane.messageForeground");
        oldBtnBg   = UIManager.getColor("Button.background");
        oldBtnFg   = UIManager.getColor("Button.foreground");

        UIManager.put("Panel.background", Theme.BACKGROUND);
        UIManager.put("OptionPane.background", Theme.BACKGROUND);
        UIManager.put("OptionPane.messageForeground", Theme.TEXT_PRIMARY);
        UIManager.put("Button.background", Theme.ACCENT);
        UIManager.put("Button.foreground", Theme.TEXT_PRIMARY);
        UIManager.put("OptionPane.buttonForeground", Theme.TEXT_PRIMARY);
    }

    private static void restaurarColoresOriginales() {
        UIManager.put("Panel.background", oldPanelBg);
        UIManager.put("OptionPane.background", oldOptBg);
        UIManager.put("OptionPane.messageForeground", oldMsgFg);
        UIManager.put("Button.background", oldBtnBg);
        UIManager.put("Button.foreground", oldBtnFg);
    }
}

