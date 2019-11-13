package info.promut.classifications;

import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import systems.dmx.core.osgi.PluginActivator;

@Path("/classification-systems")
@Consumes("application/json")
public class ClassificationSystems extends PluginActivator  {

    private Logger log = Logger.getLogger(getClass().getName());
    
    // TBD.

}