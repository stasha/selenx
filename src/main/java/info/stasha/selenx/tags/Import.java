package info.stasha.selenx.tags;

import info.stasha.selenx.actions.Action;

/**
 *
 * @author stasha
 */
public class Import extends Action {

    private String file;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Override
    public void execute(Page page) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
