package org.jsapar.constructionkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The entry point of the JsaPar Construction Kit.<br>
 * <br>
 * This application can be used for constructing JsaParSchema files from scratch using a Graphical
 * User Interface. The ease of this tool is that the user doesn't need to have deep technical skills
 * to create the necessary files to be used with the JsaPar Library. The user is guided through the
 * process of creating the JsaParSchema file that fulfills the user's parsing needs step-by-step.
 * 
 * @author JsaPar Developer
 */
public class App extends JFrame implements ActionListener {

    /**
     * The Serial version ID for this class.
     */
    private static final long serialVersionUID = -4288169067158446155L;

    /**
     * The logger for this class.
     */
    private static final Logger log = LoggerFactory.getLogger(App.class);

    /**
     * The main entry point of the application.
     * 
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        App constructionKit = new App();
        constructionKit.setSize(800, 600);
        constructionKit.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        constructionKit.setLocationRelativeTo(null);
        constructionKit.setVisible(true);
    }

    public App() {
        setTitle("JsaPar Library Construction Kit");
        setSize(1000, 800);
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.add(getFileMenu());
        menuBar.add(getConfigMenu());

        addWindowListener(new WindowAdapter() {
            // java jframe close
            public void windowClosing(WindowEvent e) {
                if (JOptionPane.showConfirmDialog(null, "Are you sure ?") == JOptionPane.YES_OPTION) {
                    setVisible(false);
                    dispose(); // jframe exit
                } 
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO
    }

    /**
     * Constructs the File Menu.
     * 
     * @return the file menu.
     */
    private JMenu getFileMenu() {
        JMenu fileMenu = new JMenu("File");

        // Create and add simple menu item to one of the drop down menu
        JMenuItem newAction = new JMenuItem("New");
        newAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                log.debug("You have clicked on the new action");
            }
        });

        JMenuItem openAction = new JMenuItem("Open");
        openAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                log.debug("You have clicked on the open action");
            }
        });

        JMenuItem saveAction = new JMenuItem("Save");
        saveAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                log.debug("You have clicked on the save action");
            }
        });

        JMenuItem exitAction = new JMenuItem("Exit");
        exitAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                log.debug("You have clicked on the exit action");
            }
        });

        fileMenu.add(newAction);
        fileMenu.add(openAction);
        fileMenu.add(saveAction);
        fileMenu.addSeparator();
        fileMenu.add(exitAction);

        return fileMenu;
    }

    /**
     * Constructs the configuration menu.
     * 
     * @return the configuration menu.
     */
    private JMenu getConfigMenu() {
        JMenu configMenu = new JMenu("Config");

        JCheckBoxMenuItem singleStep = new JCheckBoxMenuItem("Single step lines");
        singleStep.setHorizontalTextPosition(JMenuItem.RIGHT);
        singleStep.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                log.debug("You have clicked on the singleStep action");
            }

        });
        configMenu.add(singleStep);
        return configMenu;
    }
}