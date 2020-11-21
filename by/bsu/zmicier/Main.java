package by.bsu.zmicier;

import by.bsu.zmicier.window.TextEditorController;
import by.bsu.zmicier.window.TextEditorModel;
import by.bsu.zmicier.window.TextEditorView;

public class Main {

    public static void main(String[] args) {
        new TextEditorController(
                new TextEditorView("Super Text Editor"),
                new TextEditorModel()
        );
    }
}
