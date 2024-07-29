package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testovací třída pro komplexní otestování celé hry. Měli byste v ní otestovat
 * všechny herní scénáře <i>(způsoby, kterými lze hru projít a ukončit, ať už
 * výhrou, nebo prohrou)</i>.
 *
 * @author Jan Říha
 * @version ZS-2022, 2022-12-14
 */
public class GameTest
{
    private Game game;

    @BeforeEach
    public void setUp()
    {
        game = new Game();
    }

    @Test
    public void testBasicScenario()
    {
        assertEquals("místnost_s_hlavou", game.getWorld().getCurrentArea().getName());
        assertEquals("Hlava na tebe pohledí a spustí: Jsi uvězněn v magickém vězení, \n aby jsi unikl musíš najít tři kusy magické koruny a složit je na oltáři.\n K nalezení kusů koruny ti pomohou tři následující nápovědy. \n Ta pravá truhla je ta pravá. \n Povstane jako fénix. \n Pozor, nezakopni o ni.", game.process("oslov létající_hlava"));
        assertFalse(game.isGameOver());

        game.process("jdi místnost_s_kusem_koruny");
        game.process("vezmi část_koruny");
        assertTrue(game.getWorld().getBag().containsItem("část_koruny"));
        game.process("jdi místnost_s_hlavou");
        game.process("jdi místnost_s_ohništěm");
        assertFalse(game.getWorld().getCurrentArea().containsItem("kousek_koruny"));
        game.process("prozkoumej ohniště");
        assertTrue(game.getWorld().getCurrentArea().containsItem("kousek_koruny"));
        game.process("vezmi kousek_koruny");
        assertTrue(game.getWorld().getBag().containsItem("kousek_koruny"));
        game.process("jdi místnost_s_dřevem");
        game.process("jdi místnost_s_truhlama");
        game.process("prozkoumej železná_truhla");
        game.process("vezmi kus_koruny");
        assertTrue(game.getWorld().getBag().containsItem("kus_koruny"));
        game.process("jdi brána_s_oltářem");
        game.process("slož korunu");
        game.process("nasaď korunu");
        
        assertTrue(game.isGameOver());
    }
}
