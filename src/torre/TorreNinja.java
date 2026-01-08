package torre;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.PrintWriter;
import java.util.List;

import bloon.Bloon;
import game.manipulator.ManipuladorTorre;
import game.manipulator.ManipuladorVazio;
import prof.jogos2D.image.ComponenteAnimado;
import prof.jogos2D.image.ComponenteMultiAnimado;
import prof.jogos2D.image.ComponenteSimples;
import prof.jogos2D.image.ComponenteVisual;
import prof.jogos2D.util.ImageLoader;
import torre.projetil.BombaImpacto;
import torre.projetil.Dardo;
import torre.projetil.Projetil;

/**
 * Classe que representa a torre ninja. Esta torre dispara alternadamente 3
 * dardos ou 1 granada para os bloons de acordo com o seu modo de ataque.
 */
public class TorreNinja extends TorreDefault {

    private boolean dardos = false;

    /**
     * Cria uma torre ninja
     * 
     * @param img a imagem da torre
     */
    public TorreNinja(BufferedImage img) {
        super(new ComponenteMultiAnimado(new Point(50, 50), img, 2, 4, 3), 30, 8, new Point(20, 0), 100);
    }

    @Override
    public Projetil[] atacar(List<Bloon> bloons) {

        // ver quais os bloons que estão ao alcance
        List<Bloon> alvosPossiveis = getBloonsInRadius(bloons, getComponente().getPosicaoCentro(), getRaioAcao());
        if (alvosPossiveis.size() == 0)
            return new Projetil[0];
        // TODO FEITO remover este switch e suportar os restantes modos de ataque
        // ver a posição do centro para o teste de estar perto
        Point centro = getComponente().getPosicaoCentro();
        // determinar a posição do bloon alvo, consoante o método de ataque
        Point posAlvo = getAtaque().escolherPosicao(alvosPossiveis, centro);

        if (posAlvo == null)
            return new Projetil[0];

        // ajustar o ângulo
        double angle = prepararDisparo(posAlvo);

        // disparar
        resetTempoDisparar();

        Point shoot = calcularPontoDisparo(angle);

        // depois criar os projéteis
        dardos = !dardos; // inverter a vez
        if (dardos) {
            Projetil p[] = new Projetil[3];
            for (int i = 0; i < 3; i++) {
                ComponenteVisual img = new ComponenteAnimado(new Point(),
                        (BufferedImage) ImageLoader.getLoader().getImage("data/torres/dardo.gif"), 2, 2);
                p[i] = new Dardo(img, angle + (i - 1) * Math.PI / 6, 10, 3);
                p[i].setPosicao(shoot);
                p[i].setAlcance(getRaioAcao() + 30);
            }
            return p;
        } else {
            Projetil p[] = new Projetil[1];
            ComponenteVisual img = new ComponenteSimples(ImageLoader.getLoader().getImage("data/torres/bomba.gif"));
            p[0] = new BombaImpacto(img, angle, 12, 1, getMundo());
            p[0].setPosicao(shoot);
            p[0].setAlcance(getRaioAcao() + 20);
            return p;
        }
    }

    @Override
    public ManipuladorTorre getManipulador() {
        return new ManipuladorVazio(this);
    }

    @Override
    public void getTipoTorre(PrintWriter out, Torre t) {
        out.println("ninja");
    }
}
