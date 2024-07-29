package game;

/**
 * Třída implementující příkaz pro pokládání předmětů.
 *
 * @author Jan Říha
 * @author Jakub Mamica
 * @version ZS-2022, 2023-1-10
 */
public class CommandDrop implements ICommand
{
    private Game game;

    /**
     * Konstruktor třídy.
     *
     * @param game hra, ve které se bude příkaz používat
     */
    public CommandDrop(Game game)
    {
        this.game = game;
    }

    /**
     * Metoda vrací název příkazu tj.&nbsp; slovo <b>polož</b>.
     *
     * @return název příkazu
     */
    @Override
    public String getName()
    {
        return "polož";
    }

    /**
     * Metoda se pokusí položit předmět do aktuální oblasti přímo z hráčova
     * inventáře. Nejprve zkontroluje počet parametrů. Pokud nebyl zadán žádný
     * parametr <i>(tj. neznáme požadovaný předmět)</i>, nebo bylo zadáno dva a
     * více parametrů <i>(tj. hráč chce položit více předmětů současně)</i>, vrátí
     * chybové hlášení. Pokud byl zadán právě jeden parametr, zkontroluje, zda
     * v inventáři je předmět s daným názvem . Pokud ne,
     * vrátí chybové hlášení. Pokud všechny kontroly proběhnou v pořádku, provede
     * přesun předmětu z inventáře do oblasti a vrátí informaci o pložení předmětu.
     *
     * @param parameters parametry příkazu <i>(očekává se pole s jedním prvkem)</i>
     * @return informace pro hráče, které hra vypíše na konzoli
     */
    @Override
    public String execute(String[] parameters)
    {
        if (parameters.length < 1) {
            return "Tomu nerozumím, musíš mi říct, co mám položit.";
        }

        if (parameters.length > 1) {
            return "Tomu nerozumím, neumím položit více věcí současně.";  
        }                                                                
                                                                         
        String itemName = parameters[0];

        GameWorld world = game.getWorld();
        
        Bag bag= world.getBag();
        Item item = bag.getItem(itemName);
        if (!bag.containsItem(itemName)) {
            return "Předmět '" + itemName + "' nemáš u sebe není.";
        }

        Area currentArea = world.getCurrentArea();
        currentArea.addItem(item);
        bag.removeItem(itemName);
        return "Položil(a) jsi předmět '" + itemName + "' .";
        
    }
}
