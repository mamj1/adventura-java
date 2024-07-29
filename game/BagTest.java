package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testovací třída pro komplexní otestování třídy {@link Bag}.
 *
 * @author Jakub Mamica
 * @version ZS-2022, 2023-1-10
 */
public class BagTest
{
    @Test
    public void testBag()
    {
        Bag bag= new Bag(3);
        
        Item koruna1 = new Item("kus_koruny"," kus koruny ");
        Item koruna2= new Item("kousek_koruny"," kus koruny ");
        Item koruna3= new Item("část_koruny"," kus koruny ");
        
        assertTrue(bag.bagCheckEmpty());
        assertTrue(bag.bagCheckSpace());
        assertEquals("Nic u sebe nemáš.", bag.inventoryPrint());
        
        bag.addItem(koruna1);
        
        assertEquals("Toto máš u sebe.", bag.inventoryPrint());
        assertFalse(bag.bagCheckEmpty());
        assertTrue(bag.bagCheckSpace());
        assertTrue(bag.containsItem("kus_koruny"));
        
        bag.removeItem("kus_koruny");
        assertTrue(bag.bagCheckEmpty());
        assertTrue(bag.bagCheckSpace());
        assertFalse(bag.containsItem("kus_koruny"));
        
        bag.addItem(koruna1);
        bag.addItem(koruna2);
        bag.addItem(koruna3);
        assertFalse(bag.bagCheckSpace());
        
        
        assertEquals(koruna1, bag.getItem("kus_koruny"));
        
    }

    
}
