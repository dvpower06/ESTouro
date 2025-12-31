package bloon;

import prof.jogos2D.image.ComponenteVisual;

public class BloonPretoZep extends BloonFabricante {

    public BloonPretoZep(ComponenteVisual imagem, ComponenteVisual imagemPop, float veloc, int resist, int valor,
            int ritmoCriacao) {
        super(imagem, imagemPop, veloc, resist, valor, ritmoCriacao);
        //TODO Auto-generated constructor stub
    }
    
    @Override
    public void explode(int estrago) {
        return;
    }
}
