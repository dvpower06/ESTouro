package bloon;

import prof.jogos2D.image.ComponenteVisual;

public class BloonPreto extends BloonMultiCamada {

    public BloonPreto(ComponenteVisual img, ComponenteVisual imgPop, float veloc, int resist, int valor) {
        super(img, imgPop, veloc, resist, valor);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void explode(int estrago) {
        return;
    }    
}
