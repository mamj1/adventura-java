package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testovací třída pro komplexní otestování třídy {@link CommandLookAround}.
 *
 * @author Jan Říha
 * @version ZS-2022, 2022-12-14
 */
public class CommandLookAroundTest
{
    private Game game;

    private Area area1;
    private Area area2;

    @BeforeEach
    public void setUp()
    {
        game = new Game();

        area1 = new Area("hala", "Toto je vstupní hala Nové budovy VŠE v Praze.");
        area2 = new Area("bufet", "Toto je bufet, v případě nouze zde najdeš pohotovostní zásobu krmiva.");

        game.getWorld().setCurrentArea(area1);
    }

    @Test
    public void testIncorrectParameters()
    {
        assertEquals("Tomu nerozumím, neumím se rozhlédnout 'na něco'.", game.process("rozhledni_se x"));
        assertEquals("Tomu nerozumím, neumím se rozhlédnout 'na něco'.", game.process("rozhledni_se x y"));
    }

    @Test
    public void testCorrectParameters()
    {
        assertEquals(area1.getFullDescription(), game.process("rozhledni_se"));

        game.getWorld().setCurrentArea(area2);
        assertEquals(area2.getFullDescription(), game.process("rozhledni_se"));
    }
}
