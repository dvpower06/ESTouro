package torre.ataques;

import java.awt.Point;
import java.util.List;

import bloon.Bloon;

public class AtacaForte implements ModoAtaque {

    @Override
    public Point escolherPosicao(List<Bloon> bloons, Point centro) {
        if (bloons == null || bloons.isEmpty())
            return null;
        Bloon bf = bloons.stream().max((b1, b2) -> Double.compare(b1.getValor(), b2.getValor())).get();
        return bf.getComponente().getPosicaoCentro();

    }
}