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

    public static void addModel(String id, Model model) {
        if (!isKeyInUse(id)) {
            instance().registry.put(id, model);
        }
    }

    public static void addModelForWaveFrontFile(String id, String pathToObjFile) {
        if(!isKeyInUse(id)) {
            WavefrontParser parser = new WavefrontParser(pathToObjFile);
            addModel(id, parser.parse());
        }
    }

    public static Model getModel(String id) {
        if (isKeyInUse(id)) {
            return instance().registry.get(id);
        }
        return null;
    }

    public static boolean isKeyInUse(String id) {
        return instance().registry.containsKey(id);
    }

}
