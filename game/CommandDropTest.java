package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testovací třída pro komplexní otestování třídy {@link CommandDrop}.
 *
 * @author Jakub Mamica
 * @version ZS-2022, 2023-1-10
 */
public class CommandDropTest
{
    private Game game;
    @Test
    public void testCommandDrop()
    {
        game= new Game();
        Bag bag=game.getWorld().getBag();
        Area kosti = new Area("místnost_s_kostmi", "Kamená místnost s hromadou kostí.");
        game.getWorld().setCurrentArea(kosti);
        Item item1 = new Item("kreslo", "Starožitná polstrovaná sedačka.");
        bag.addItem(item1);
        
        assertEquals("Tomu nerozumím, musíš mi říct, co mám položit.", game.process("polož"));
        assertEquals("Tomu nerozumím, neumím položit více věcí současně.", game.process("polož a b"));
        assertEquals("Předmět 'a' nemáš u sebe není.", game.process("polož a"));
        assertEquals("Položil(a) jsi předmět 'kreslo' .", game.process("polož kreslo"));
    }

    
}
