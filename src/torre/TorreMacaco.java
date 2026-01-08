package torre;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.PrintWriter;
import java.util.List;

import bloon.Bloon;
import game.manipulator.ManipuladorTorre;
import game.manipulator.ManipuladorVazio;
import prof.jogos2D.image.*;
import prof.jogos2D.util.ImageLoader;
import torre.projetil.Dardo;
import torre.projetil.Projetil;

/**
 * Classe que representa uma torre Macaco. Esta torre manda um dardo com dano de
 * 2. Só dispara quando tem bloons dentro do seu raio de ação e atira para o
 * bloon de acordo com o seu modo de ataque.
 */
public class TorreMacaco extends TorreDefault {

	/**
	 * Cria a torre macaco
	 * 
	 * @param img a imagem da torre
	 */
	public TorreMacaco(BufferedImage img) {
		super(new ComponenteMultiAnimado(new Point(50, 50), img, 2, 4, 3), 30, 8, new Point(15, 15), 100);
	}

	@Override
	public Projetil[] atacar(List<Bloon> bloons) {

		// atualizar ciclo de disparo
		atualizarCicloDisparo();

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

		// se ainda não está na altura de disparar, não dispara
		if (!podeDisparar())
			return new Projetil[0];

		// disparar
		resetTempoDisparar();

		Point shoot = calcularPontoDisparo(angle);

		// depois criar os projéteis
		Projetil p[] = new Projetil[1];
		ComponenteVisual img = new ComponenteAnimado(new Point(),
				(BufferedImage) ImageLoader.getLoader().getImage("data/torres/dardo.gif"), 2, 2);
		p[0] = new Dardo(img, angle, 10, 2);
		p[0].setPosicao(shoot);
		p[0].setAlcance(getRaioAcao() + 30);
		return p;
	}

	@Override
	public ManipuladorTorre getManipulador() {
		return new ManipuladorVazio(this);
	}

	@Override
	public void getTipoTorre(PrintWriter out, Torre t) {
		out.println("macaco");
	}

}
