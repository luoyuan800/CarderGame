package cn.gavin.card.model.effect;

import java.util.Map;

/**
 * Created by gluo on 8/29/2016.
 */
public interface CoverEffect extends Effect {
    void invokeWhileTurn(Map<String, Object> para);
}
