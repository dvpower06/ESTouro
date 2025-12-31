package bloon;

import prof.jogos2D.image.ComponenteVisual;

public class BloonMetalZep extends BloonFabricante {

    public BloonMetalZep(ComponenteVisual imagem, ComponenteVisual imagemPop, float veloc, int resist, int valor,
            int ritmoCriacao) {
        super(imagem, imagemPop, veloc, resist, valor, ritmoCriacao);
        //TODO Auto-generated constructor stub
    }

    @Override
    public int pop(int damage) {
        return damage;
    }
    
}
