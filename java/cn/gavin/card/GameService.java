package cn.gavin.card;

import cn.gavin.card.model.Card;
import cn.gavin.card.model.carder.Carder;

/**
 * Created by gluo on 4/20/2017.
 */
public class GameService {
    private Carder carder1;
    private Carder carder2;
    public void prepare(Carder carder){

    }

    public Carder getCarder2() {
        return carder2;
    }

    public void setCarder2(Carder carder2) {
        this.carder2 = carder2;
    }

    public Carder getCarder1() {
        return carder1;
    }

    public void setCarder1(Carder carder1) {
        this.carder1 = carder1;
    }
}
