package info.promut.classifications;

import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import systems.dmx.accesscontrol.AccessControlService;
import static systems.dmx.core.Constants.ASSOCIATION;
import static systems.dmx.core.Constants.CHILD;
import static systems.dmx.core.Constants.DEFAULT;
import static systems.dmx.core.Constants.PARENT;
import systems.dmx.core.DMXObject;
import systems.dmx.core.model.AssocModel;
import systems.dmx.core.model.PlayerModel;
import systems.dmx.core.osgi.PluginActivator;
import systems.dmx.core.service.Inject;
import systems.dmx.core.service.event.PreCreateAssoc;
import systems.dmx.core.util.DMXUtils;
import systems.dmx.files.FilesService;
import systems.dmx.workspaces.WorkspacesService;

@Path("/classification-systems")
@Consumes("application/json")
public class ClassificationSystems extends PluginActivator implements PreCreateAssoc {

    private Logger log = Logger.getLogger(getClass().getName());
    public static final String CLASSIFICATIONS_WS_NAME = "Classification Systems";
    public static final String CLASSIFICATION_WS_URI = "dmx.classifications.workspace";

    @Inject WorkspacesService ws; 
    @Inject AccessControlService as; 
    @Inject FilesService files;

    // Entity Types
    public static final String CLASS = "org.purl.classifications.classification";
    public static final String CLASS_SYSTEM = "org.purl.classifications.system";
    public static final String CLASS_CATEGORY = "org.purl.classifications.category";
    // Association Types
    public static final String SIMILAR_CLASS = "org.purl.classifications.similar";
    public static final String CONTAINS_CLASS = "org.purl.classifications.contains";
    public static final String DEFINES_CLASS = "org.purl.classifications.defines";
    public static final String CATEGORIZES_CLASS = "org.purl.classifications.categorizes";
    public static final String EQUIVALENT_CLASS = "org.purl.classifications.equivalence";
    public static final String WEIGHT_IN_CATEGORY = "org.purl.classifications.weight_in_category";

    @Override
    public void preCreateAssoc(AssocModel am) {
        if (am.getTypeUri().equals(ASSOCIATION)) {
            PlayerModel player1 = am.getPlayer1();
            PlayerModel player2 = am.getPlayer2();
            DMXObject topic1 = dmx.getObject(player1.getId());
            DMXObject topic2 = dmx.getObject(player2.getId());
            // Class <-> Classification System
            if (topic1.getTypeUri().equals(CLASS) && topic2.getTypeUri().equals(CLASS_SYSTEM)) {
                DMXUtils.assocAutoTyping(am, CLASS, CLASS_SYSTEM, DEFINES_CLASS, CHILD, PARENT);
            // Class <-> Category (+ default weight 1.0)
            } else if (topic1.getTypeUri().equals(CLASS) && topic2.getTypeUri().equals(CLASS_CATEGORY)) {
                am.getChildTopics().set(WEIGHT_IN_CATEGORY, 1.0);
                DMXUtils.assocAutoTyping(am, CLASS, CLASS_CATEGORY, CATEGORIZES_CLASS, PARENT, CHILD);
            // TBD.
            } else if (topic2.getTypeUri().equals(CLASS) && topic1.getTypeUri().equals(CLASS_SYSTEM)) {
                DMXUtils.assocAutoTyping(am, CLASS_SYSTEM, CLASS, DEFINES_CLASS, PARENT, CHILD);
            } else if (topic2.getTypeUri().equals(CLASS) && topic1.getTypeUri().equals(CLASS_CATEGORY)) {
                am.getChildTopics().set(WEIGHT_IN_CATEGORY, 1.0);
                DMXUtils.assocAutoTyping(am, CLASS_CATEGORY, CLASS, CATEGORIZES_CLASS, CHILD, PARENT);
            // Defaults to "Similar"
            } else if (topic1.getTypeUri().equals(CLASS) && topic2.getTypeUri().equals(CLASS)) {
                DMXUtils.assocAutoTyping(am, CLASS, CLASS, SIMILAR_CLASS, DEFAULT, DEFAULT);
            }
        }
    }
    // - CSV-Export utilities

}