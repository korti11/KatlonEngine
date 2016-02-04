package at.korti.katlonengine.client.model;

import at.korti.katlonengine.client.model.parser.WavefrontParser;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Korti on 08.01.2016.
 */
public class ModelRegistry {

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
