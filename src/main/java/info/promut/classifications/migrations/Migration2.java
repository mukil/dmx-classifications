package info.promut.classifications.migrations;

import systems.dmx.core.Topic;
import systems.dmx.core.service.Inject;
import systems.dmx.core.service.Migration;
import systems.dmx.accesscontrol.AccessControlService;
import systems.dmx.workspaces.WorkspacesService;
import java.util.logging.Logger;
import systems.dmx.core.TopicType;
import systems.dmx.core.model.SimpleValue;
import systems.dmx.core.service.accesscontrol.SharingMode;

public class Migration2 extends Migration {

    private Logger logger = Logger.getLogger(getClass().getName());
    static final String CLASSIFICATIONS_WS_NAME = "Classification Systems";
    static final String ADMIN_USERNAME = "admin";

    @Inject
    private WorkspacesService workspaces;
    @Inject AccessControlService as; 

    @Override
    public void run() {

        // 0) Check for WS, Make Sure Custom Plugin Workspace Does Exist
        Topic csWorkspace = dmx.getTopicByValue("dmx.workspaces.name", new SimpleValue(CLASSIFICATIONS_WS_NAME));
        if (csWorkspace == null) {
                csWorkspace = workspaces.createWorkspace(CLASSIFICATIONS_WS_NAME, "dmx.classifications.workspace", SharingMode.CONFIDENTIAL);
                as.setWorkspaceOwner(csWorkspace, ADMIN_USERNAME);
        }
        // 1) Assoc Top Level Topic Types
        TopicType classification = dmx.getTopicType("org.purl.classifications.classification");
        TopicType classificationSystem = dmx.getTopicType("org.purl.classifications.system");
        TopicType category = dmx.getTopicType("org.purl.classifications.category");
        workspaces.assignToWorkspace(classification, csWorkspace.getId());
        workspaces.assignToWorkspace(classificationSystem, csWorkspace.getId());
        workspaces.assignToWorkspace(category, csWorkspace.getId());

    }

}
