package cn.gavin.card.model.group;

import cn.gavin.card.model.carder.Carder;

/**
 * Created by gluo on 4/21/2017.
 */
public abstract class OwnerGroup implements Group {
    private Carder owner;

    public Carder getOwner() {
        return owner;
    }

    @Override
    public void setOwner(Carder owner) {
        this.owner = owner;
    }
}
