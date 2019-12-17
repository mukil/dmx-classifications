package info.promut.classifications;

import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import systems.dmx.accesscontrol.AccessControlService;
import systems.dmx.core.osgi.PluginActivator;
import systems.dmx.core.service.Inject;
import systems.dmx.workspaces.WorkspacesService;

@Path("/classification-systems")
@Consumes("application/json")
public class ClassificationSystems extends PluginActivator  {

    private Logger log = Logger.getLogger(getClass().getName());
    public static final String CLASSIFICATIONS_WS_NAME = "Classification Systems";
    
    @Inject WorkspacesService ws; 
    @Inject AccessControlService as; 

    // TBD.
    // - Automatic Workspace Assignment Rules
    // - Core Workspace Assignment Facilities

}