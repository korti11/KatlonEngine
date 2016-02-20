package at.korti.katlonengine.components;

import at.korti.katlonengine.entity.Entity;

/**
 * Created by Korti on 09.02.2016.
 */
public interface IComponent {

    void init(Entity entity);

    void update();

}
