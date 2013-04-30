package vooga.scroller.level_editor.exceptions;

@SuppressWarnings("serial")
public class LevelEditorException extends Exception{

    
    private String myMessage;
    
    public LevelEditorException (String message) {
        myMessage = message;
    }

    @Override
    public String toString() {
        return myMessage;
    }
    
}
