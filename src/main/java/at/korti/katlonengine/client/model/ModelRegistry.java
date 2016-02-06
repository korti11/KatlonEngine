package at.korti.katlonengine.client.model;

import at.korti.katlonengine.KatlonEngine;
import at.korti.katlonengine.client.model.parser.WavefrontParser;
import at.korti.katlonengine.util.helper.ResourceHelper;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Korti on 08.01.2016.
 */
public class ModelRegistry {

    private static final Logger logger = KatlonEngine.logger;
    private static ModelRegistry instance;

    private Map<String, Model> registry;

    private ModelRegistry() {
        registry = new HashMap<>();
    }

    public static ModelRegistry instance() {
        if (instance == null) {
            instance = new ModelRegistry();
        }
        return instance;
    }

    /**
     * Register a model object for the id.
     *
     * @param id    ID for the model.
     * @param model Model
     */
    public static void addModel(String id, Model model) {
        if (!isKeyInUse(id)) {
            instance().registry.put(id, model);
        }
    }

    /**
     * Parse a model of the .obj file and register it for the id.
     * @param id ID for the model.
     * @param pathToObjFile Path in the resource folder to the .obj file.
     */
    public static void addModelForWaveFrontFile(String id, String pathToObjFile) {
        if(!isKeyInUse(id)) {
            WavefrontParser parser = new WavefrontParser(pathToObjFile);
            addModel(id, parser.parse());
        }
    }

    /**
     * Parse a textured model of the .obj file, load the texture file for the model
     * and register it for the id.
     *
     * @param id                ID for the model
     * @param pathToObjFile     Path in the resource folder to the .obj file
     * @param pathToTextureFile Path in the resource folder to the .png file
     */
    public static void addTexturedModelForWaveFrontFile(String id, String pathToObjFile, String pathToTextureFile) {
        if (!isKeyInUse(id)) {
            WavefrontParser parser = new WavefrontParser(pathToObjFile);
            TexturedModel model = parser.parseTexturedModel();
            try {
                model.setTexture(ResourceHelper.getTexture(pathToTextureFile));
            } catch (IOException e) {
                logger.error("Couldn't find texture: " + pathToTextureFile, e);
                return;
            }
            instance().registry.put(id, model);
        }
    }

    /**
     * Get the model of the id.
     * @param id ID of the model that you want.
     * @return Model that is registered for the id.
     */
    public static Model getModel(String id) {
        if (isKeyInUse(id)) {
            return instance().registry.get(id);
        }
        return null;
    }

    /**
     * Check if the id is already registered.
     * @param id ID of a model you have registered or want to register.
     * @return If the id is already in use.
     */
    public static boolean isKeyInUse(String id) {
        return instance().registry.containsKey(id);
    }

}
