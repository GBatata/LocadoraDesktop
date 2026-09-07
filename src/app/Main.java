package app;

import view.TelaLogin;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {

  public static void main(String[] args) {
    
    SwingUtilities.invokeLater(
      new Runnable() {
        public void run() {
          try {
            UIManager.setLookAndFeel(
              UIManager.getSystemLookAndFeelClassName()
            );
          } catch (Exception e) {
            
            e.printStackTrace();
          }

          new TelaLogin().setVisible(true);
        }
      }
    );
  }
}