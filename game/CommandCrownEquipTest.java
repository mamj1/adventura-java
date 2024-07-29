package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testovací třída pro komplexní otestování třídy {@link CommandCrownEquip}.
 *
 * @author Jakub Mamica
 * @version ZS-2022, 2023-1-10
 */
public class CommandCrownEquipTest
{
    private Game game;
    @Test
    public void testBagCheck()
    {
        
        game= new Game();
        Bag bag=game.getWorld().getBag();
        Item koruna = new Item("koruna", "magická koruna, nasaď si ji a jsi volný");
        
        assertEquals("Tomu nerozumím, musíš mi říct, co mám nasadit.", game.process("nasaď"));
        assertEquals("Tomu nerozumím, neumím nasadit více věcí současně.", game.process("nasaď a b"));
        assertEquals("a si neumíš nasadit.", game.process("nasaď a"));
        assertEquals("Nemáš žádnou korunu u sebe.", game.process("nasaď korunu"));
        bag.addItem(koruna);
        assertFalse(game.isGameOver());
        assertEquals("Nasadil jsi si magickou korunu která tě přenesla mimo vězení.", game.process("nasaď korunu"));
        assertTrue(game.isGameOver());
    }

    
}
