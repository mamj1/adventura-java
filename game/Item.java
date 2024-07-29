package game;
import java.util.*;
/**
 * Třída představuje předmět .
 * Předmět má jméno,popis a může či nemusí být přenositelný. 
 * 
 * @author Jan Říha
 * @author Jakub Mamica
 * @version ZS-2022, 2023-1-10
 */
public class Item implements Comparable<Item>
{
    private String name;
    private String description;
    private boolean moveable;
    private Map<String, Item> content;
    /**
     * Konstruktor třídy, vytvoří předmět se zadaným jménem popisem a pohyblivostí přednastavenou na true.
     *
     * @param name jméno předmětu
     * @param description informace o předmětu
     * 
     */
    public Item(String name, String description)
    {
        this(name, description, true);
    }

    /**
     * Konstruktor třídy, vytvoří předmět se zadaným jménem popisem a pohyblivostí.
     *
     * @param name jméno předmětu
     * @param description informace o předmětu
     * @param moveable zda se s předmětem dá pohnout true-dá false-nedá
     */
    public Item(String name, String description, boolean moveable)
    {
        this.name = name;
        this.description = description;
        this.moveable = moveable;
        content = new TreeMap<>();
    }
    
   /**
     * Metoda přidá předmět <i>(objekt třídy {@link Item})</i> do inventáře předmětu.
     *
     * @param item předmět, který bude do inventáře předmětu přidán
     */
    public void addItem(Item item)
    {
        content.put(item.getName(), item);
    }

    /**
     * Metoda zkontroluje, zda inventář předmětu obsahuje předmět s daným názvem.
     *
     * @param itemName název předmětu
     * @return {@code true}, pokud inventář předmětu obsahuje předmět s daným názvem; jinak {@code false}
     */
    public boolean containsItem(String itemName)
    {
        return content.containsKey(itemName);
    }

    /**
     * Metoda vyhledá v inventáři předmětu předmět s daným názvem a vrátí na něj odkaz.
     *
     * @param itemName název předmětu
     * @return předmět s daným názvem; {@code null}, pokud v inventáři předmětu není
     */
    public Item getItem(String itemName)
    {
        return content.get(itemName);
    }

    /**
     * Metoda vyhledá v inventáři předmětu předmět s daným názvem, odstraní ho z inventáře předmětu a vrátí na něj odkaz.
     *
     * @param itemName název předmětu
     * @return odstraněný předmět; {@code null}, pokud předmět v inventáři předmětu není
     */
    public Item removeItem(String itemName)
    {
        return content.remove(itemName);
    }
    
    /**
     * Metoda která zkontroluje zda je inventář předmětu prázdný a podle toho vrací true či false.
     * @return {@code true}, pokud je inventář prázdný; jinak {@code false}
     */
    public boolean contentEmpty(){
    return content.isEmpty();
    }
    
    /**
     * Metoda vyskládá obsah inventáře předmětu do dané oblasti a vrátí jejich výpis. Pokud předmět nic neobsahuje takse vrítá nic.
     * @return Pokud inventář předmětu něco obsahuje tak popis obsahu jinak informaci o absenci obsahu
     */
    public String dumpContentToArea(Area area){
     if(!contentEmpty())
        {
            String x= new String();
            for(Map.Entry<String,Item> key: content.entrySet())
            {
                 this.removeItem(key.getKey());
                 area.addItem(key.getValue());
                 x= x + " " + key.getKey();
            }
            return x;
        }
        return "nic";
    }
    
    /**
     * Metoda vrací název předmětu, který byl zadán při vytváření instance jako
     * parametr konstruktoru. Jedná se o jednoznačný identifikátor předmětu
     * <i>(ve hře nemůže existovat více předmětů se stejným názvem)</i>. Aby
     * hra správně fungovala, název předmětu nesmí obsahovat mezery, v případě
     * potřeby můžete více slov oddělit pomlčkami, použít camel-case apod.
     *
     * @return název předmětu
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Metoda vrací popis předmětu.
     *
     * @return popis předmětu
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Metoda nastaví popis předmětu.
     *
     * @param description nový popis předmětu
     */
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    /**
     * Metoda vrací zda je předmět přenositelný.
     *
     * @return hodnotu přenositelnosti
     */
    public boolean isMoveable()
    {
        return moveable;
    }
    
    /**
     * Metoda nastaví přenositelnost.
     *
     * @param description nová přenositelnost
     */
    public void setMoveable(boolean moveable)
    {
        this.moveable = moveable;
    }
    
    /**
     * Metoda porovnává dva předměty . Předměty jsou shodné,
     * pokud mají stejný název . Tato metoda
     * 
     * <p>
     * Podrobnější popis metody najdete v dokumentaci třídy {@linkplain Object}.
     *
     * @param o objekt, který bude porovnán s aktuálním
     * @return {@code true}, pokud mají obě předměty stejný název; jinak {@code false}
     *
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(final Object o)
    {
        if (o == this) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (o instanceof Item) {
            Item item = (Item) o;

            return name.equals(item.getName());
        }

        return false;
    }
    
    /**
     * Metoda vrací číselný identifikátor instance, který se používá
     * pro optimalizaci ukládání v dynamických datových strukturách
     * <i>(např.&nbsp;{@linkplain HashSet})</i>. Při překrytí metody
     * {@link #equals(Object) equals} je vždy nutné překrýt i tuto
     * metodu.
     * <p>
     * Podrobnější popis pravidel pro implementaci metody najdete
     * v dokumentaci třídy {@linkplain Object}.
     *
     * @return číselný identifikátor instance
     *
     * @see Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        return name.hashCode();
    }
    
    /**
     * Metoda porovná předmět s druhým předmětem předané v parametru abecedně
     * dle jejich názvů a vrátí kladné číslo, nulu, nebo záporné číslo
     * v závislosti na tom, zda je název této oblasti větší, stejný, nebo
     * menší než název druhé oblasti.
     * <p>
     * Metoda se používá pro řazení sousedních oblastí v atributu {@link #exits}.
     *
     * @param Item předmět, jejíž název bude porovnán s názvem tohoto předmětu
     * @return kladné číslo, nula, nebo záporné číslo v závislosti na porovnání názvů oblastí
     */
    @Override
    public int compareTo(Item item)
    {
        return name.compareTo(item.getName());
    }
}
