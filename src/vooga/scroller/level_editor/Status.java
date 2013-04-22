
package vooga.scroller.level_editor;

public class Status {

    String myErrorMessage;
    
    public Status(){
        //myErrorMessage = "";
    }

    public String getErrorMessage () {
        return myErrorMessage;
    }

    public void setErrorMessage (String errorMessage) {
        myErrorMessage = errorMessage;
    }
    
}