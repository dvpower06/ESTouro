package torre;

import java.util.HashMap;
import java.util.Map;

import prof.jogos2D.util.ImageLoader;
import torre.fabricaTorres.FabricaTorre;
import torre.fabricaTorres.FactoryBalista;
import torre.fabricaTorres.FactoryCanhao;
import torre.fabricaTorres.FactoryMacaco;
import torre.fabricaTorres.FactoryMorteiro;
import torre.fabricaTorres.FactoryNinja;
import torre.fabricaTorres.FactoryOcto;
import torre.fabricaTorres.FactorySniper;

/**
 * Classe que trata da criação das várias torres. Esta classe existe que lidar
 * com a criação de todas as torres e assim impedir que as outras classes tenham
 * de ser alteradas quando se criam novas torres.
 */
public class TorreCreator {
	private ImageLoader loader = ImageLoader.getLoader();

	//TODO FEITO suportar também a sniper

    private final Map<String, FabricaTorre> catalogo = new HashMap<>();


    public void registarTorre(String nome, FabricaTorre fabrica) {
        catalogo.put(nome, fabrica);
    }

    public Torre criarTorrePorNome(String nome) {
        FabricaTorre f = catalogo.get(nome);
        return (f != null) ? f.criaTorre(loader) : null;
    }

    public TorreCreator() {
    registarTorre("macaco", new FactoryMacaco());
    registarTorre("octo", new FactoryOcto());
    registarTorre("canhao", new FactoryCanhao());
    registarTorre("morteiro", new FactoryMorteiro());
    registarTorre("balista", new FactoryBalista());
    registarTorre("ninja", new FactoryNinja());
    registarTorre("sniper", new FactorySniper()); 
}


}
