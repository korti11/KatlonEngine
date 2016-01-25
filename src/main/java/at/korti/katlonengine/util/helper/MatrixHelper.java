package at.korti.katlonengine.util.helper;

import at.korti.katlonengine.client.display.Camera;
import at.korti.katlonengine.config.EngineSettings;
import at.korti.katlonengine.util.matrix.Matrix4f;
import at.korti.katlonengine.util.vector.Vector3f;

/**
 * Created by Korti on 12.01.2016.
 */
public class MatrixHelper {

    private static final float NEAR_PLANE = 0.1f;
    private static final float FAR_PLANE = 100;

    public static Matrix4f createTransformationMatrix(Vector3f translation, float rx, float ry, float rz, float scale) {
        Matrix4f matrix = new Matrix4f();
        matrix.setIdentity();
        Matrix4f.translate(translation, matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(rx), new Vector3f(1, 0, 0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(ry), new Vector3f(0, 1, 0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(rz), new Vector3f(0, 0, 1), matrix, matrix);
        Matrix4f.scale(new Vector3f(scale, scale, scale), matrix, matrix);
        return matrix;
    }

    public static Matrix4f projectionMatrix() {
        float aspectRatio = (float) EngineSettings.width / (float) EngineSettings.height;
        float y_scale = (float) ((1f / Math.tan(Math.toRadians(EngineSettings.fieldOfView / 2f))) * aspectRatio);
        float x_scale = y_scale / aspectRatio;
        float frustum_length = FAR_PLANE - NEAR_PLANE;

        Matrix4f matrix = new Matrix4f();

        matrix.mat00 = x_scale;
        matrix.mat11 = y_scale;
        matrix.mat22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
        matrix.mat23 = -1;
        matrix.mat32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
        matrix.mat33 = 0;
        return matrix;
    }

    public static Matrix4f createViewMatrix(Camera camera) {
        Matrix4f matrix = new Matrix4f();
        matrix.setIdentity();
        Matrix4f.rotate((float) Math.toRadians(camera.getPitch()), new Vector3f(1, 0, 0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(camera.getYaw()), new Vector3f(0, 1, 0), matrix, matrix);
        Vector3f cameraPos = camera.getPosition();
        Vector3f negativeCameraPos = new Vector3f(-cameraPos.x, -cameraPos.y, -cameraPos.z);
        Matrix4f.translate(negativeCameraPos, matrix, matrix);
        return matrix;
    }

}
