package game;

/**
 * Třída implementující příkaz pro sbírání předmětů.
 *
 * @author Jan Říha
 * @author Jakub Mamica 
 * @version ZS-2022, 2023-1-10
 */
public class CommandPick implements ICommand
{
    private Game game;

    /**
     * Konstruktor třídy.
     *
     * @param game hra, ve které se bude příkaz používat
     */
    public CommandPick(Game game)
    {
        this.game = game;
    }

    /**
     * Metoda vrací název příkazu tj.&nbsp; slovo <b>vezmi</b>.
     *
     * @return název příkazu
     */
    @Override
    public String getName()
    {
        return "vezmi";
    }

    /**
     * Metoda se pokusí sebrat předmět z aktuální oblasti a uložit ho do hráčova
     * inventáře. Nejprve zkontroluje počet parametrů. Pokud nebyl zadán žádný
     * parametr <i>(tj. neznáme požadovaný předmět)</i>, nebo bylo zadáno dva a
     * více parametrů <i>(tj. hráč chce sebrat více předmětů současně)</i>, vrátí
     * chybové hlášení. Pokud byl zadán právě jeden parametr, zkontroluje, zda
     * v aktuální oblasti je předmět s daným názvem, zda je přenositelný a zda
     * na něj hráč má v inventáři místo <i>(tj. zda ho lze sebrat)</i>. Pokud ne,
     * vrátí chybové hlášení. Pokud všechny kontroly proběhnou v pořádku, provede
     * přesun předmětu z oblasti do inventáře a vrátí informaci o sebrání předmětu.
     *
     * @param parameters parametry příkazu <i>(očekává se pole s jedním prvkem)</i>
     * @return informace pro hráče, které hra vypíše na konzoli
     */
    @Override
    public String execute(String[] parameters)
    {
        if (parameters.length < 1) {
            return "Tomu nerozumím, musíš mi říct, co mám sebrat.";
        }

        if (parameters.length > 1) {
            return "Tomu nerozumím, neumím sebrat více věcí současně.";  
        }                                                                
                                                                         
        String itemName = parameters[0];

        GameWorld world = game.getWorld();
        Area currentArea = world.getCurrentArea();

        if (!currentArea.containsItem(itemName)) {
            return "Předmět '" + itemName + "' tady není.";
        }

        Item item = currentArea.getItem(itemName);

        if (!item.isMoveable()) {
            return "Předmět '" + itemName + "' fakt neuneseš.";
        }
        
        Bag bag= world.getBag();
        
         if(bag.bagCheckSpace())
        {
        bag.addItem(item);
        currentArea.removeItem(itemName);
        return "Sebral(a) jsi předmět '" + itemName + "' a uložil(a) ho k sobě.";
        
        }

        

        return "už toho víc nepobereš něco odlož. a pak zkus " + itemName + " sebrat znova.";
    }
}
