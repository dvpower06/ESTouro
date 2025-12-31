package torre.fabricaTorres;

import java.awt.Image;
import java.awt.image.BufferedImage;
import prof.jogos2D.util.ImageLoader;
import torre.Torre;
import torre.TorreBalista;

public class FactorySniper implements FabricaTorre {

    @Override
    public Torre criaTorre(ImageLoader loader) {
        Image img = loader.getImage("data/torres/sniper/imagem.gif");
        return new TorreBalista((BufferedImage) img);
    }
    
}
