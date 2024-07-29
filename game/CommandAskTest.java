package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testovací třída pro komplexní otestování třídy {@link CommandAsk}.
 *
 * @author Jakub Mamica
 * @version ZS-2022, 2023-1-10
 */
public class CommandAskTest
{
    private Game game;
    @Test
    public void testCommandAsk()
    {
        game= new Game();
        Npc npc=new Npc("a",2);
        npc.addAnswer("b");
        npc.addAnswer("c");
        
        Area kosti = new Area("místnost_s_kostmi", "Kamená místnost s hromadou kostí.");
        game.getWorld().setCurrentArea(kosti);
        kosti.setNpc(npc);
        
         assertEquals("Tomu nerozumím, musíš mi říct, co mám oslovit.", game.process("oslov"));
        assertEquals("Tomu nerozumím, neumím oslovit více věcí současně.", game.process("oslov a b"));
        assertEquals("... nikdo takový tu není", game.process("oslov b"));
        assertEquals("b", game.process("oslov a"));
        assertEquals("c", game.process("oslov a"));
        assertEquals("b", game.process("oslov a"));
    }

    
}
