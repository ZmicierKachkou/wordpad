package by.bsu.zmicier.window;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

public class TextEditorView extends JFrame {
    private JMenuItem newButton, open, save, saveAs, close;
    private JTextArea textArea;
    private JLabel fileName;

    public TextEditorView(String title) {
        super(title);

        this.setLayout(new BorderLayout());

        textArea = new JTextArea();
        this.add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel status = new JPanel();
        status.setLayout(new FlowLayout(FlowLayout.RIGHT));
        fileName = new JLabel();
        status.add(fileName);
        status.setBorder(new BevelBorder(BevelBorder.LOWERED));
        this.add(status, BorderLayout.SOUTH);

        this.setJMenuBar(createMenuBar());
        this.setSize(500, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public JMenuItem getNewButton() {
        return newButton;
    }

    public JMenuItem getOpen() {
        return open;
    }

    public JMenuItem getSave() {
        return save;
    }

    public JMenuItem getSaveAs() {
        return saveAs;
    }

    public JMenuItem getClose() {
        return close;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public JLabel getFileName() {
        return fileName;
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        newButton = new JMenuItem("New");
        open = new JMenuItem("Open");
        save = new JMenuItem("Save");
        saveAs = new JMenuItem("Save as");
        close = new JMenuItem("Close");

        file.add(newButton);
        file.add(open);
        file.add(save);
        file.add(saveAs);
        file.addSeparator();
        file.add(close);

        menuBar.add(file);
        return menuBar;
    }
}
