package torre.fabricaTorres;

import java.awt.Image;
import java.awt.image.BufferedImage;
import prof.jogos2D.util.ImageLoader;
import torre.Torre;
import torre.TorreMorteiro;

public class FactoryMorteiro implements FabricaTorre {

    @Override
    public Torre criaTorre(ImageLoader loader) {
        Image img = loader.getImage("data/torres/morteiro/imagem.gif");
        return new TorreMorteiro((BufferedImage) img);
    }
    
}
