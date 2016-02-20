package at.korti.katlonengine.util.helper;

/**
 * Created by Korti on 20.02.2016.
 */
public class PhysicsHelper {

    public static final float GRAVITY_ACCELERATION = 9.81f;

    public static float calculateFallVelocity(float mass, float drag, float time, float startVelocity) {
        float result = -((mass * GRAVITY_ACCELERATION) / drag);
        result = result * (1f - (float) Math.exp(-((drag * time) / mass)));
        result = result + (startVelocity * (float) Math.exp(-((drag * time) / mass)));
        return result;
    }

}
