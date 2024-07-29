package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testovací třída pro komplexní otestování třídy {@link CommandInvestigate}.
 *
 * @author Jan Říha
 * @version ZS-2022, 2022-12-14
 */
public class CommandInvestigateTest
{
    private Game game;

    private Item item1;
    private Item item2;

    @BeforeEach
    public void setUp()
    {
        game = new Game();

        Area area1 = new Area("hala", "Toto je vstupní hala Nové budovy VŠE v Praze.");
        Area area2 = new Area("bufet", "Toto je bufet, v případě nouze zde najdeš pohotovostní zásobu krmiva.");

        item1 = new Item("kreslo", "Starožitná polstrovaná sedačka.", false);
        item2 = new Item("rum", "Láhev vyzrálého alkoholu.");

        area1.addExit(area2);
        area1.addItem(item1);
        area2.addItem(item2);

        game.getWorld().setCurrentArea(area1);
    }

    @Test
    public void testIncorrectParameters()
    {
        assertEquals("Tomu nerozumím, musíš mi říct, co mám prozkoumat.", game.process("prozkoumej"));
        assertEquals("Tomu nerozumím, neumím prozkoumat více věcí současně.", game.process("prozkoumej a b"));
        assertEquals("Předmět 'neco' tady není ani ho nemáš u sebe.", game.process("prozkoumej neco"));

        assertEquals("Předmět '" + item2.getName() + "' tady není ani ho nemáš u sebe.", game.process("prozkoumej " + item2.getName()));

        game.process("jdi bufet");
        assertEquals("Předmět '" + item1.getName() + "' tady není ani ho nemáš u sebe.", game.process("prozkoumej " + item1.getName()));
    }

    @Test
    public void testCorrectParameters()
    {
        assertEquals(item1.getDescription(), game.process("prozkoumej " + item1.getName()));

        game.process("jdi bufet");
        assertEquals(item2.getDescription(), game.process("prozkoumej " + item2.getName()));
    }
}
