package at.korti.katlonengine.scene;

import at.korti.katlonengine.KatlonEngine;
import at.korti.katlonengine.entity.Entity;
import at.korti.katlonengine.scene.exception.SceneLoadException;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Korti on 08.02.2016.
 */
public final class SceneManager {

    private static final Logger logger = KatlonEngine.logger;
    private static SceneManager instance;
    private Map<String, AbstractScene> scenes;
    private String currentSceneId = "";
    private AbstractScene currentScene;

    private SceneManager() {
        scenes = new HashMap<>();
        currentScene = null;
    }

    public static SceneManager instance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }

    public static void registerScene(String id, AbstractScene scene) {
        Map<String, AbstractScene> scenes = instance().scenes;
        if (!scenes.containsKey(id)) {
            scenes.put(id, scene);
        }
    }

    public static void loadScene(String id) {
        AbstractScene currentScene;
        Map<String, AbstractScene> scenes = instance().scenes;

        unloadCurrentScene();

        instance().currentScene = currentScene = scenes.get(id);

        if (currentScene == null) {
            throw new SceneLoadException("Couldn't find a scene with the id: " + id);
        }

        instance().currentSceneId = id;

        try {
            if (!currentScene.isInit()) {
                currentScene.init();
                currentScene.postInit();
            } else {
                currentScene.reload();
            }
        } catch (Exception e) {
            logger.error("Couldn't load the scene with the id: " + id, e);
            currentScene.cleanUp();
            System.exit(-1);
        }
    }

    public static AbstractScene getCurrentScene() {
        return instance().currentScene;
    }

    public static String getCurrentSceneId() {
        return instance().currentSceneId;
    }

    //region Management of the current scene
    public static void addEntityToCurrentScene(Entity entity) {
        AbstractScene currentScene = instance().currentScene;
        if (currentScene != null) {
            currentScene.addEntity(entity);
        } else {
            logger.error("Couldn't add entity to the current scene, because there is no scene loaded!", Thread.currentThread().getStackTrace());
            System.exit(-1);
        }
    }

    public static void renderCurrentScene() {
        AbstractScene currentScene = instance().currentScene;
        if (currentScene != null) {
            currentScene.render();
        } else {
            logger.error("Couldn't render the current scene, because there is no scene loaded!", Thread.currentThread().getStackTrace());
            System.exit(-1);
        }
    }

    public static void updateCurrentScene() {
        AbstractScene currentScene = instance().currentScene;
        if (currentScene != null) {
            currentScene.update();
        } else {
            logger.error("Couldn't update the current scene, because there is no scene loaded!", Thread.currentThread().getStackTrace());
            System.exit(-1);
        }
    }

    public static void unloadCurrentScene() {
        AbstractScene currentScene = instance().currentScene;
        if (currentScene != null) {
            currentScene.cleanUp();
            instance().currentSceneId = "";
        } else {
            logger.error("Couldn't unload the current scene, because there is no scene loaded!", Thread.currentThread().getStackTrace());
        }
    }
    //endregion
}
