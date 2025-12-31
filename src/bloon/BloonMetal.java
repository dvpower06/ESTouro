package bloon;

import prof.jogos2D.image.ComponenteVisual;

public class BloonMetal extends BloonMultiCamada {

    public BloonMetal(ComponenteVisual img, ComponenteVisual imgPop, float veloc, int resist, int valor) {
        super(img, imgPop, veloc, resist, valor);
        //TODO Auto-generated constructor stub
    }

    @Override
    public int pop(int damage) {
        return damage;
    }
    
}
