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
public class CommandCraftCrownTest
{
    private Game game;

    Item koruna1 = new Item("kus_koruny"," kus koruny ");
    Item koruna2= new Item("kousek_koruny"," kus koruny ");
    Item koruna3= new Item("část_koruny"," kus koruny ");
    Item koruna4= new Item("zlomek_koruny"," kus koruny ");
    Item altar= new Item("oltář","Oltář pro magické rituály.",false);
    
    
    @BeforeEach
    public void setUp()
    {
        game = new Game();

        
        Area oltar = new Area("brána_s_oltářem", "Kamená místnost s oltářem a bránou.");
        Area ohen= new Area("místnost_s_ohništěm", "Kamená místnost s pohaslím ohništěm.");
        oltar.addExit(ohen);
        ohen.addExit(oltar);
        
        
        oltar.addItem(altar);
        
        game.getWorld().setCurrentArea(oltar);
        game.getWorld().getBag().addItem(koruna1);
        game.getWorld().getBag().addItem(koruna2);
    }

    @Test
    public void testIncorrectParameters()
    {
        assertEquals("Tomu nerozumím, musíš mi říct, co mám složit.", game.process("slož"));
        assertEquals("Tomu nerozumím, neumím složit více věcí současně.", game.process("slož a b"));
        assertEquals("Můžeš složit pouze korunu u oltáře.", game.process("slož neco"));

        assertEquals("Nemáš všechny části koruny, potřebuješ tři části koruny.", game.process("slož korunu"));
        game.getWorld().getBag().addItem(koruna4);
        assertEquals("Kus koruny který jsi použil je nesprávný, zůstaneš již navždy vězněm.", game.process("slož korunu"));

        game.process("jdi místnost_s_ohništěm");
        assertEquals("Nejsi u oltáře, nemůžeš skládat korunu.", game.process("slož korunu" ));
    }

    @Test
    public void testCorrectParameters()
    {
        game.getWorld().getBag().addItem(koruna3);
        assertEquals("Kusy koruny se vznesly nad oltář a složily dohromady, jsi majitelem magické koruny.", game.process("slož korunu" ));
    }
}
