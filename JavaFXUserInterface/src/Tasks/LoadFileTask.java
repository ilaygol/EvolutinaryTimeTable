package Tasks;

import Manager.LogicEngineManager;
import javafx.concurrent.Task;

import javax.xml.bind.JAXBException;
import java.io.File;

public  class LoadFileTask extends Task<Boolean> {

    private LogicEngineManager m_Engine;
    private File m_File;

    public LoadFileTask(LogicEngineManager i_Engine,File i_File)
    {
        m_Engine=i_Engine;
        m_File=i_File;
    }

    @Override
    protected Boolean call() throws Exception {
        updateMessage("Loading File...");
        Thread.sleep(2000);
        updateMessage("Checking File Content...");
        Thread.sleep(2000);
        try {
            m_Engine.LoadFile(m_File);
            updateMessage("File was Loaded successfully!");
            Thread.sleep(3000);
            updateMessage(m_File.getAbsolutePath());
        } catch(RuntimeException e) {
            updateMessage(e.getMessage());
            return false;
        }
        catch (JAXBException e) {
            updateMessage(e.getMessage());
            return false;
        }
        return true;
    }
}
