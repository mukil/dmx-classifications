package info.promut.classifications;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import systems.dmx.accesscontrol.AccessControlService;
import systems.dmx.core.osgi.PluginActivator;
import systems.dmx.core.service.Inject;
import systems.dmx.files.DirectoryListing;
import systems.dmx.files.DirectoryListing.FileItem;
import systems.dmx.files.FilesService;
import systems.dmx.workspaces.WorkspacesService;

@Path("/classification-systems")
@Consumes("application/json")
public class ClassificationSystems extends PluginActivator  {

    private Logger log = Logger.getLogger(getClass().getName());
    public static final String CLASSIFICATIONS_WS_NAME = "Classification Systems";

    @Inject WorkspacesService ws; 
    @Inject AccessControlService as; 
    @Inject FilesService files;

    // TBD.
    // - Automatic Workspace Assignment Rules
    // - Core Workspace Assignment Facilities

    @GET
    @Path("/files")
    public Response getFiles() {
        log.info("Classification Systems Filerepo Listing...");
        DirectoryListing list = files.getDirectoryListing("/");
        List<FileItem> items = list.getFileItems();
        Iterator<FileItem> iterator = items.iterator();
        while (iterator.hasNext()) {
            FileItem item = iterator.next();
            log.info("File => " + item.getPath() + " " + item.getName());
        }
        return Response.ok().build();
    }

}