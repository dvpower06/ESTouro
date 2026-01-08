package bloon;

import java.awt.Point;
import java.awt.Rectangle;

import prof.jogos2D.image.ComponenteVisual;

public class BloonArmadura extends BloonSimples {

    private final Bloon bloon;
    private int armadura;
    private final ComponenteVisual imgArmadura;

    public BloonArmadura(Bloon interno, ComponenteVisual imgArmadura, int numContactos) {
    
        super(interno.getComponente(), interno.getPopComponente(),
                interno.getVelocidade(), interno.getResistencia(), interno.getValor());
        this.bloon = interno;
        this.imgArmadura = imgArmadura;
        this.armadura = numContactos;
    }

    @Override
    public int pop(int damage) {
        if (armadura > 0) {
            armadura--;
            if (armadura == 0) {
               
                return damage;
            }
            return 0; 
        }
        return bloon.pop(damage);
    }

    @Override
    public void mover() {
        bloon.mover();
        this.setPosicaoNoCaminho(bloon.getPosicaoNoCaminho());
    }

    @Override
    public ComponenteVisual getComponente() {
        
        return armadura > 0 ? imgArmadura : bloon.getComponente();
    }

    @Override
    public void setPosicao(Point p) {
        // Primeiro, movemos o balão interno (essencial para colisões e lógica)
        bloon.setPosicao(p);
        // Depois, movemos a imagem da armadura para acompanhar o balão
        if (imgArmadura != null) {
            imgArmadura.setPosicaoCentro(p);
        }
    }


    @Override
    public Rectangle getBounds() {
        return bloon.getBounds();
    }

    @Override
    public int getResistencia() {
        return armadura > 0 ? armadura : bloon.getResistencia();
    }

    @Override
    public void addBloonObserver(BloonObserver bo) {
        bloon.addBloonObserver(bo);
    }

    

}
