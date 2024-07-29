package game;

import java.util.*;

/**
 * Třída představuje inventář <i>(prostor pro uchovávání předmětů)</i>. Prostor kde se dá uskladnit několik itemů.
 * Odkazy na všechny itemy jsou uložené v množině. 
 * Inventář má předem omezenou velikostreprezentovanou int size.
 * Inventář dokáže se svůj obsah měnit získávat informace o své plnosti a vypsat svůj obsah
 * 
 * @author Jakub Mamica
 * @version ZS-2022, 2023-1-10
 */
public class Bag 
{
    public int size;
    private Map<String, Item> items;

   /**
     * Konstruktor třídy, vytvoří inventář se zadanou velikostí.
     *
     * @param size počet předmětů jaký bude moci inventář pojmout
     */
    public Bag(int size)
    {
        this.size=size;
        this.items = new TreeMap<>();
    }
 
   
   
     /**
     * Metoda která zkontroluje zda je inventář naplněný a podle toho vrací true či false.
     * @return {@code true}, pokud inventář má nezaplněné místo; jinak {@code false}
     */
    public boolean bagCheckSpace()
    {
      if(items.size()<size){return true;}
        return false;
    }
    
    /**
     * Metoda která zkontroluje zda je inventář prázdný a podle toho vrací true či false.
     * @return {@code true}, pokud je inventář prázdný; jinak {@code false}
     */
    public boolean bagCheckEmpty()
    {
         if(items.size()==0){return true;}
        return false;
    }
    
    /**
     * Metoda vypisuje obsah inventáře. Pokud dotyčný nic nemá je mu to oznámeno.
     * @return Pokud inventář něco obsahuje tak popis obsahu jinak informaci o absenci obsahu
     */
    public String inventoryPrint()
    {
        if(!bagCheckEmpty())
        {
            
            for(Map.Entry<String,Item> key: items.entrySet())
            {
                System.out.println( key.getKey() + " => " + key.getValue().getDescription() );
            }
            return"Toto máš u sebe.";
        }
        else
        {
            return"Nic u sebe nemáš.";
        }
    }


   /**
     * Metoda zkontroluje, zda inventář obsahuje předmět s daným názvem.
     *
     * @param itemName název předmětu
     * @return {@code true}, pokud inventář obsahuje předmět s daným názvem; jinak {@code false}
     */
    public boolean containsItem(String itemName)
    {
        return items.containsKey(itemName);
    }
    
    /**
     * Metoda přidá předmět <i>(objekt třídy {@link Item})</i> do inventáře.
     *
     * @param item předmět, který bude do oblasti přidán
     */
     public void addItem(Item item)
    {
        items.put(item.getName(), item);
    }

    /**
     * Metoda vyhledá v inventáři předmět s daným názvem a vrátí na něj odkaz.
     *
     * @param itemName název předmětu
     * @return předmět s daným názvem; {@code null}, pokud v oblasti není
     */
    public Item getItem(String itemName)
    {
        return items.get(itemName);
    }

    /**
     * Metoda vyhledá v inventáři předmět s daným názvem, odstraní ho z inventáře a vrátí na něj odkaz.
     *
     * @param itemName název předmětu
     * @return odstraněný předmět; {@code null}, pokud předmět v oblasti není
     */
    public Item removeItem(String itemName)
    {
        return items.remove(itemName);
    }

    
}
