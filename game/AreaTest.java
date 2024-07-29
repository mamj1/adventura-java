package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testovací třída pro komplexní otestování třídy {@link Area}.
 *
 * @author Jan Říha
 * @version ZS-2022, 2022-12-14
 */
public class AreaTest
{
    @Test
    public void testExits()
    {
        Area area1 = new Area("hala", "Toto je vstupní hala Nové budovy VŠE v Praze.");
        Area area2 = new Area("bufet", "Toto je bufet, v případě nouze zde najdeš pohotovostní zásobu krmiva.");

        assertFalse(area1.hasExit(area1.getName()));
        assertFalse(area1.hasExit(area2.getName()));
        assertFalse(area2.hasExit(area1.getName()));

        assertNull(area1.getExit(area1.getName()));
        assertNull(area1.getExit(area2.getName()));
        assertNull(area2.getExit(area1.getName()));

        area1.addExit(area1);
        area1.addExit(area2);
        area2.addExit(area1);

        assertTrue(area1.hasExit(area1.getName()));
        assertTrue(area1.hasExit(area2.getName()));
        assertTrue(area2.hasExit(area1.getName()));
        assertFalse(area1.hasExit("ucebna"));
        assertFalse(area2.hasExit("ucebna"));

        assertEquals(area1, area1.getExit(area1.getName()));
        assertEquals(area1, area2.getExit(area1.getName()));
        assertEquals(area2, area1.getExit(area2.getName()));
        assertNull(area1.getExit("ucebna"));
        assertNull(area2.getExit("ucebna"));
    }

    @Test
    public void testItems()
    {
        Area area1 = new Area("hala", "Toto je vstupní hala Nové budovy VŠE v Praze.");

        Item item1 = new Item("stul", "Těžký dubový stůl.", false);
        Item item2 = new Item("rum", "Láhev vyzrálého rumu.");

        assertFalse(area1.containsItem(item1.getName()));
        assertFalse(area1.containsItem(item2.getName()));

        assertNull(area1.getItem(item1.getName()));
        assertNull(area1.getItem(item2.getName()));

        area1.addItem(item1);
        area1.addItem(item2);

        assertTrue(area1.containsItem(item1.getName()));
        assertTrue(area1.containsItem(item2.getName()));
        assertFalse(area1.containsItem("pc"));

        assertEquals(item1, area1.getItem(item1.getName()));
        assertEquals(item2, area1.getItem(item2.getName()));
        assertNull(area1.getItem("pc"));

        assertEquals(item1, area1.removeItem(item1.getName()));
        assertEquals(item2, area1.removeItem(item2.getName()));
        assertNull(area1.removeItem("pc"));

        assertFalse(area1.containsItem(item1.getName()));
        assertFalse(area1.containsItem(item2.getName()));
        assertFalse(area1.containsItem("pc"));

        assertNull(area1.getItem(item1.getName()));
        assertNull(area1.getItem(item2.getName()));
        assertNull(area1.getItem("pc"));
    }

    @Test
    public void testFullDescription()
    {
        Area area = new Area("hala", "Toto je vstupní hala Nové budovy VŠE v Praze.");

        Area exit1 = new Area("bufet", "Toto je bufet, v případě nouze zde najdeš pohotovostní zásobu krmiva.");
        Area exit2 = new Area("ucebna", "Toto je učebna, přepadá Tě tu neodbytný pocit zmaru a neštěstí.");

        Item item1 = new Item("stul", "Těžký dubový stůl.", false);
        Item item2 = new Item("rum", "Láhev vyzrálého rumu.");

        area.addExit(exit1);
        area.addExit(exit2);

        area.addItem(item1);
        area.addItem(item2);

        String fullDescription = area.getFullDescription();

        assertNotNull(fullDescription);
        assertTrue(fullDescription.contains(area.getName()));
        assertTrue(fullDescription.contains(area.getDescription()));
        assertTrue(fullDescription.contains(exit1.getName()));
        assertTrue(fullDescription.contains(exit2.getName()));
        assertTrue(fullDescription.contains(item1.getName()));
        assertTrue(fullDescription.contains(item2.getName()));
    }
}
