package torre;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.PrintWriter;
import java.util.List;

import bloon.Bloon;
import game.manipulator.ManipuladorMorteiro;
import game.manipulator.ManipuladorTorre;
import prof.jogos2D.image.*;
import prof.jogos2D.util.DetectorColisoes;
import prof.jogos2D.util.ImageLoader;
import torre.projetil.BombaDirigida;
import torre.projetil.Projetil;

/**
 * Classe que representa a torre morteiro. Esta torre dispara uma bomba para um
 * local concreto. A bomba explode quando chega ao destino. Ataca sempre, quer
 * no local de ataque existam bloons ou não.
 */
public class TorreMorteiro extends TorreDefault {

	private Point areaAlvo; // destino das bombas
	private int alcance; // alcance máximo da torre

	/**
	 * Cria a torre Morteiro
	 * 
	 * @param img
	 */
	public TorreMorteiro(BufferedImage img) {
		super(new ComponenteMultiAnimado(new Point(50, 50), img, 2, 4, 3),
				30, 0, new Point(30, 15), 0);
		areaAlvo = new Point(100, 100);
		alcance = 200;
	}

	/**
	 * devolve a zona para onde atira as bombas
	 * 
	 * @return a zona para onde atira as bombas
	 */
	public Point getAreaAlvo() {
		return areaAlvo;
	}

	@Override
	public void setPosicao(Point p) {
		super.setPosicao(p);
		setAreaAlvo(getAreaAlvo()); // reajusta a área de ataque
	}

	/**
	 * Define a posição para onde atirar as bombas, se esta estiver dentro de
	 * alcance. Se não estiver dentro de alcance, escolhe uma posição na mesma
	 * direção que esteja dentro de alcance.
	 * 
	 * @param area a nova posição para onde atirar as bombas
	 */
	public void setAreaAlvo(Point area) {
		// o centro do morteiro
		Point centro = getComponente().getPosicaoCentro();
		// determinar o ângulo de disparo
		double angle = DetectorColisoes.getAngulo(area, centro);
		if (area.distance(centro) > alcance) {
			area = new Point((int) (centro.x + alcance * Math.cos(angle)),
					(int) (centro.y + alcance * Math.sin(angle)));
		}
		areaAlvo = area;
		getComponente().setAngulo(angle);
	}

	@Override
	public Projetil[] atacar(List<Bloon> bloons) {


		// determinar a posição do bloon alvo, consoante o método de ataque
		Point posAlvo = areaAlvo;
		

		// ver o ângulo que o alvo faz com a torre, para assim rodar esta
		double angle = prepararDisparo(posAlvo);


		// primeiro calcular o ponto de disparo
		Point shoot = calcularPontoDisparo(angle);

		// depois criar os projéteis
		Projetil p[] = new Projetil[1];
		// reajustar o ângulo para ser desde o ponto de disparo até ao ponto de destino
		angle = DetectorColisoes.getAngulo(areaAlvo, shoot);
		ComponenteVisual img = new ComponenteSimples(ImageLoader.getLoader().getImage("data/torres/bomba.gif"));
		BombaDirigida bm = new BombaDirigida(img, angle, 12, 2, getMundo());
		bm.setDestino(areaAlvo);
		p[0] = bm;
		p[0].setPosicao(shoot);
		p[0].setAlcance(800); // com este alcance nunca pára
		return p;
	}

	@Override
	public Torre clone() {
		TorreMorteiro copia = (TorreMorteiro) super.clone();
		copia.areaAlvo = new Point(areaAlvo);
		return copia;
	}

	@Override
	public ManipuladorTorre getManipulador() {
		return new ManipuladorMorteiro(this);
	}

	@Override
	public void getTipoTorre(PrintWriter out, Torre t) {
		out.print("morteiro\t");
		Point ataque = ((TorreMorteiro) t).getAreaAlvo();
		out.println(ataque.x + "\t" + ataque.y);
	}

}
