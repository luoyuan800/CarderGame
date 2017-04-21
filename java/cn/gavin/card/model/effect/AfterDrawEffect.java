package cn.gavin.card.model.effect;

import java.util.Map;

/**
 * Created by gluo on 4/21/2017.
 */
public interface AfterDrawEffect extends Effect {
    void invokeAfterDraw(Map<String, Object> para);
}
