package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testovací třída pro komplexní otestování třídy {@link CommandMove}.
 *
 * @author Jan Říha
 * @version ZS-2022, 2022-12-14
 */
public class CommandMoveTest
{
    private Game game;

    @BeforeEach
    public void setUp()
    {
        game = new Game();

        Area area1 = new Area("hala", "Toto je vstupní hala Nové budovy VŠE v Praze.");
        Area area2 = new Area("bufet", "Toto je bufet, v případě nouze zde najdeš pohotovostní zásobu krmiva.");

        area1.addExit(area2);
        area2.addExit(area1);

        game.getWorld().setCurrentArea(area1);
    }

    @Test
    public void testIncorrectParameters()
    {
        assertEquals("Tomu nerozumím, musíš mi říct, kam mám jít.", game.process("jdi"));
        assertEquals("hala", game.getWorld().getCurrentArea().getName());

        assertEquals("Tomu nerozumím, neumím jít do více oblastí současně.", game.process("jdi a b"));
        assertEquals("hala", game.getWorld().getCurrentArea().getName());

        assertEquals("Do oblasti 'nekam' se odsud jít nedá.", game.process("jdi nekam"));
        assertEquals("hala", game.getWorld().getCurrentArea().getName());
    }

    @Test
    public void testCorrectParameters()
    {
        assertTrue(game.process("jdi bufet").toLowerCase().contains("toto je bufet"));
        assertEquals("bufet", game.getWorld().getCurrentArea().getName());

        assertTrue(game.process("jdi hala").toLowerCase().contains("toto je vstupní hala"));
        assertEquals("hala", game.getWorld().getCurrentArea().getName());
    }
}