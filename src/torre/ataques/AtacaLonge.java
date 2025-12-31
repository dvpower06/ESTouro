package torre.ataques;

import java.awt.Point;
import java.util.List;

import bloon.Bloon;

public class AtacaLonge implements ModoAtaque {

    @Override
    public Point escolherPosicao(List<Bloon> bloons, Point centro) {
        if (bloons == null || bloons.isEmpty())
            return null;
        return bloons.stream().map(b -> b.getComponente().getPosicaoCentro())
                .max((p1, p2) -> Double.compare(p1.distance(centro), p2.distance(centro)))
                .get();
    }
    
}
