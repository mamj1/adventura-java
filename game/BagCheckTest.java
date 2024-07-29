package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testovací třída pro komplexní otestování třídy {@link BagCheck}.
 *
 * @author Jakub Mamica
 * @version ZS-2022, 2023-1-10
 */
public class BagCheckTest
{
    private Game game;
    @Test
    public void testBagCheck()
    {
        
        game= new Game();
        Bag bag=game.getWorld().getBag();
        Item koruna1 = new Item("kus_koruny"," kus koruny ");
        
        assertEquals("Co co?", game.process("co"));
        assertEquals("Tomu nerozumím.", game.process("co a b"));
        assertEquals("Tomu nerozumím, příkaz 'co b' neznám.", game.process("co b"));
        assertEquals("Nic u sebe nemáš.", game.process("co nosím"));
        bag.addItem(koruna1);
        assertEquals("Toto máš u sebe.", game.process("co nosím"));
    }

    
}
