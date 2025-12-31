package torre.ataques;

import java.awt.Point;
import java.util.List;

import bloon.Bloon;


public interface ModoAtaque {

    

    Point escolherPosicao(List<Bloon> bloons, Point centro);
   


}
