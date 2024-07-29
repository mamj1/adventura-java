package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testovací třída pro komplexní otestování třídy {@link CommandPick}.
 *
 * @author Jakub Mamica
 * @version ZS-2022, 2023-1-10
 */
public class CommandPickTest
{
    private Game game;
    @Test
    public void testCommandPick()
    {
        game= new Game();
        Bag bag=game.getWorld().getBag();
        Area kosti = new Area("místnost_s_kostmi", "Kamená místnost s hromadou kostí.");
        game.getWorld().setCurrentArea(kosti);
        Item item1 = new Item("kreslo", "Starožitná polstrovaná sedačka.", false);
        Item item2 = new Item("rum", "Láhev vyzrálého alkoholu.");
        Item item3 = new Item("rum2", "Láhev vyzrálého alkoholu.");
        Item item4 = new Item("rum3", "Láhev vyzrálého alkoholu.");
        Item item5 = new Item("rum4", "Láhev vyzrálého alkoholu.");
        kosti.addItem(item1);
        kosti.addItem(item2);
        kosti.addItem(item3);
        kosti.addItem(item4);
        kosti.addItem(item5);
        
        assertEquals("Tomu nerozumím, musíš mi říct, co mám sebrat.", game.process("vezmi"));
        assertEquals("Tomu nerozumím, neumím sebrat více věcí současně.", game.process("vezmi a b"));
        assertEquals("Předmět 'a' tady není.", game.process("vezmi a"));
        assertEquals("Předmět 'kreslo' fakt neuneseš.", game.process("vezmi kreslo"));
        assertEquals("Sebral(a) jsi předmět 'rum' a uložil(a) ho k sobě.", game.process("vezmi rum"));
        assertEquals("Předmět 'rum' tady není.", game.process("vezmi rum"));
        game.process("vezmi rum2");
        game.process("vezmi rum3");
        assertEquals("už toho víc nepobereš něco odlož. a pak zkus rum4 sebrat znova.", game.process("vezmi rum4"));
    }

    
}
