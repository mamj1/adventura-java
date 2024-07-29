package game;

/**
 * Třída implementující příkaz pro průzkum konkrétního předmětu.
 *
 * @author Jan Říha
 * @author Jakub Mamica 
 * @version ZS-2022, 2023-1-10
 */
public class CommandInvestigate implements ICommand
{
    private Game game;

    /**
     * Konstruktor třídy.
     *
     * @param game hra, ve které se bude příkaz používat
     */
    public CommandInvestigate(Game game)
    {
        this.game = game;
    }

    /**
     * Metoda vrací název příkazu tj.&nbsp; slovo <b>prozkoumej</b>.
     *
     * @return název příkazu
     */
    @Override
    public String getName()
    {
        return "prozkoumej";
    }

    /**
     * Metoda vrátí detailní popis vybraného předmětu v inventáři nebo
     * v aktuální oblasti. Pokud předmět obashoval jiný předmět přesune se obsahovaný předmět do
     * aktuálni lokace.
     *
     * @param parameters parametry příkazu <i>(očekává se pole s jedním prvkem)</i>
     * @return informace pro hráče, které hra vypíše na konzoli
     */
    @Override
    public String execute(String[] parameters)
    {
        if (parameters.length < 1) {
            return "Tomu nerozumím, musíš mi říct, co mám prozkoumat.";
        }

        if (parameters.length > 1) {
            return "Tomu nerozumím, neumím prozkoumat více věcí současně.";
        }

        String itemName = parameters[0];

        GameWorld world = game.getWorld();
        Area currentArea = world.getCurrentArea();
        Bag inv= world.getBag();
        
        Item item;
        
        if(currentArea.containsItem(itemName)){
        item = currentArea.getItem(itemName);
        if(!item.contentEmpty()){
        return item.getDescription() + "toto jsi nalez unvitř " + itemName + item.dumpContentToArea(currentArea);
        }
        return item.getDescription();
        }
       
        
        if(inv.containsItem(itemName)){
        item = inv.getItem(itemName);
        if(!item.contentEmpty()){
        return item.getDescription() + "toto jsi nalez unvitř " + itemName + item.dumpContentToArea(currentArea);
        }
        return item.getDescription();
        }
        return "Předmět '" + itemName + "' tady není ani ho nemáš u sebe.";
    }
}
