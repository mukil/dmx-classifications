package info.promut.classifications.migrations;

import java.util.List;
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
            // double check if it really does not exist yet
            List<Topic> existingWs = dmx.getTopicsByType("dmx.workspaces.workspace");
            for (Topic topic : existingWs) {
                if (topic.getSimpleValue().toString().equals(CLASSIFICATIONS_WS_NAME)) {
                    csWorkspace = topic;
                }
            }
            if (csWorkspace == null) {
                csWorkspace = workspaces.createWorkspace(CLASSIFICATIONS_WS_NAME, "dmx.classifications.workspace", SharingMode.CONFIDENTIAL);
                as.setWorkspaceOwner(csWorkspace, ADMIN_USERNAME);
            }
        }
        // 1) Assoc Top Level Topic Types
        TopicType classification = dmx.getTopicType("org.purl.classifications.classification");
        TopicType className = dmx.getTopicType("org.purl.classifications.class_name");
        TopicType classId = dmx.getTopicType("org.purl.classifications.class_id");
        TopicType classDescr = dmx.getTopicType("org.purl.classifications.class_descr");
        TopicType classificationSystem = dmx.getTopicType("org.purl.classifications.system");
        TopicType systemDescr = dmx.getTopicType("org.purl.classifications.system_descr");
        TopicType systemName = dmx.getTopicType("org.purl.classifications.system_name");
        TopicType systemId = dmx.getTopicType("org.purl.classifications.system_id");
        TopicType category = dmx.getTopicType("org.purl.classifications.category");
        TopicType categoryDescr = dmx.getTopicType("org.purl.classifications.category_descr");
        TopicType categoryId = dmx.getTopicType("org.purl.classifications.category_id");
        TopicType categoryName = dmx.getTopicType("org.purl.classifications.category_name");
        workspaces.assignToWorkspace(classification, csWorkspace.getId());
        workspaces.assignToWorkspace(className, csWorkspace.getId());
        workspaces.assignToWorkspace(classId, csWorkspace.getId());
        workspaces.assignToWorkspace(classDescr, csWorkspace.getId());
        workspaces.assignToWorkspace(classificationSystem, csWorkspace.getId());
        workspaces.assignToWorkspace(systemDescr, csWorkspace.getId());
        workspaces.assignToWorkspace(systemName, csWorkspace.getId());
        workspaces.assignToWorkspace(systemId, csWorkspace.getId());
        workspaces.assignToWorkspace(category, csWorkspace.getId());
        workspaces.assignToWorkspace(categoryDescr, csWorkspace.getId());
        workspaces.assignToWorkspace(categoryName, csWorkspace.getId());
        workspaces.assignToWorkspace(categoryId, csWorkspace.getId());

    }

}
