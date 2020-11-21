package by.bsu.zmicier.window;

import java.io.File;

public class TextEditorModel {
    private String text;
    private File file;
    private boolean modified;

    public TextEditorModel() {
        text = "";
        modified = false;
    }

    public TextEditorModel(String text, File file, boolean modified) {
        this.text = text;
        this.file = file;
        this.modified = modified;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public boolean isModified() {
        return modified;
    }

    public void setModified(boolean modified) {
        this.modified = modified;
    }
}
