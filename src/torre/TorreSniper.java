package torre;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.PrintWriter;
import java.util.List;

import bloon.Bloon;
import game.manipulator.ManipuladorOcto;
import game.manipulator.ManipuladorTorre;
import prof.jogos2D.image.*;
import prof.jogos2D.util.ImageLoader;
import torre.projetil.Dardo;
import torre.projetil.Projetil;


public class TorreSniper extends TorreDefault {

   
    private Point mira;

    
    public TorreSniper(BufferedImage img) {
        super(new ComponenteMultiAnimado(new Point(), img, 2, 4, 2),
                20, 0, new Point(20, -3), Integer.MAX_VALUE);
        setAnguloDisparo(0);
    }

    
    public void setAnguloDisparo(float angulo) {
        getComponente().setAngulo(angulo);
        definirMira(Integer.MAX_VALUE);
    }

    
    private void definirMira(double angulo) {
        double cos = Math.cos(angulo);
        double sin = Math.sin(angulo);
        Point centro = getComponente().getPosicaoCentro();
        mira = new Point((int) (centro.x + 2000 * cos), (int) (centro.y + 2000 * sin)); //2000 -> valor elevado
    }


    public Point getMira() {
        return mira;
    }

    @Override
    public void setPosicao(Point p) {
        super.setPosicao(p);
        definirMira(getComponente().getAngulo());
    }

    @Override
    public void desenhaRaioAcao(Graphics2D g) {
        Point centro = getComponente().getPosicaoCentro();
        Point mira = getMira();
        Composite oldComp = g.getComposite();
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        g.setColor(Color.RED);
        Line2D.Float l = new Line2D.Float(centro, mira);
        g.setStroke(new BasicStroke(1));
        g.draw(l);
        g.setColor(Color.WHITE);
        g.setStroke(new BasicStroke(18));
        g.draw(l);
        g.setComposite(oldComp);
    }

    @Override
    public Projetil[] atacar(List<Bloon> bloons) {
               

        List<Bloon> alvosPossiveis = getBloonsInLine(bloons, getComponente().getPosicaoCentro(), getMira());
        Point posAlvo = (alvosPossiveis.size() == 0) ? null : mira;

        if (posAlvo == null)
            return new Projetil[0];

   
        double angle = prepararDisparo(posAlvo);

        Point shoot = calcularPontoDisparo(angle);
     

        Projetil p[] = new Projetil[1];
        ComponenteVisual img = new ComponenteSimples(ImageLoader.getLoader().getImage("data/torres/dardo.gif"));

        p[0] = new Dardo(img, angle, 10, 5);
        p[0].setPosicao(shoot);
        p[0].setAlcance(100); 

        return p;
    }

    @Override
    public Torre clone() {
        TorreSniper copia = (TorreSniper) super.clone();
        copia.mira = new Point(mira);
        return copia;
    }

    @Override
    public void getTipoTorre(PrintWriter out, Torre t) {
        out.print("sniper\t");
        out.println(t.getComponente().getAngulo());
    }

    @Override
    public ManipuladorTorre getManipulador() {
        return new ManipuladorOcto(this);
    }

}
