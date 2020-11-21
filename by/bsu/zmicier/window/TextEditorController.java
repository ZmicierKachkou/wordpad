package by.bsu.zmicier.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class TextEditorController {
    private final TextEditorView view;
    private final TextEditorModel model;

    public TextEditorController(TextEditorView view, TextEditorModel model) {
        this.view = view;
        this.model = model;

        view.getNewButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkTextWasModified()) {
                    model.setText("");
                    model.setFile(null);
                    model.setModified(false);
                    updateView();
                }
            }
        });

        view.getOpen().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkTextWasModified()) {
                    JFileChooser chooser = new JFileChooser();
                    int result = chooser.showOpenDialog(view);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File chosenFile = chooser.getSelectedFile();
                        try {
                            String value = Files.readString(Path.of(chosenFile.toURI()));
                            model.setText(value);
                            model.setFile(chosenFile);
                            model.setModified(false);
                            updateView();
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(view, "Sorry cannot read file!");
                        }
                    }
                }
            }
        });

        view.getSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });

        view.getSaveAs().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateModel();
                saveAs();
                updateView();
            }
        });

        view.getClose().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkTextWasModified()) {
                    System.exit(0);
                }
            }
        });

        view.getTextArea().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() != KeyEvent.CHAR_UNDEFINED) {
                    model.setModified(true);
                }
            }
        });
        updateView();
    }

    public void updateView() {
        view.getTextArea().setText(model.getText());
        File currentFile = model.getFile();
        view.getFileName().setText(currentFile == null ? "----" : currentFile.getPath());
    }

    public void updateModel() {
        model.setText(view.getTextArea().getText());
    }

    private boolean checkTextWasModified() {
        if (model.isModified()) {
            int result = JOptionPane.showConfirmDialog(view, "Your changes were not saved! Do you want to save them?");
            boolean processed = false;
            if (result == JOptionPane.YES_OPTION) {
                processed = save();
            } else if (result == JOptionPane.NO_OPTION) {
                processed = true;
            }
            return processed;
        }
        return true;
    }

    private boolean save() {
        updateModel();
        boolean wasUpdated;
        if (model.getFile() != null) {
            saveToFile(model.getFile());
            wasUpdated = true;
        } else {
            wasUpdated = saveAs();
        }
        updateView();
        return wasUpdated;
    }

    private boolean saveAs() {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showSaveDialog(view);
        if (result == JFileChooser.APPROVE_OPTION) {
            saveToFile(chooser.getSelectedFile());
            return true;
        }
        return false;
    }

    private void saveToFile(File chosenFile) {
        try {
            Files.writeString(Paths.get(chosenFile.toURI()), model.getText());
            model.setFile(chosenFile);
            model.setModified(false);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(view, "Sorry cannot write file!");
        }
    }
}
