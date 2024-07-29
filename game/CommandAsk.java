package game;

/**
 * Třída implementující příkaz pro oslovení nehráčské postavy.
 *
 * @author Jakub Mamica 
 * @version ZS-2022, 2023-1-10
 */
public class CommandAsk implements ICommand
{
    private Game game;

    /**
     * Konstruktor třídy.
     *
     * @param game hra, ve které se bude příkaz používat
     */
    public CommandAsk(Game game)
    {
        this.game = game;
    }

    /**
     * Metoda vrací název příkazu tj.&nbsp; slovo <b>oslov</b>.
     *
     * @return název příkazu
     */
    @Override
    public String getName()
    {
        return "oslov";
    }

    /**
     * Metoda se pokusí oslovit npc.
     * Nejprve zkontroluje počet parametrů. Pokud nebyl zadán žádný
     * parametr , nebo bylo zadáno dva a více parametrů , vrátí chybové hlášení.
     *  Pokud byl zadán právě jeden parametr, zkontroluje, zda
     * v aktuální oblasti je npc s daným názvem. Pokud ne,vrátí chybové hlášení.
     *  Pokud všechny kontroly proběhnou v pořádku, oslový npc a vrátí jeho odpověď
     *
     * @param parameters parametry příkazu <i>(očekává se pole s jedním prvkem)</i>
     * @return informace pro hráče, které hra vypíše na konzoli
     */
    @Override
    public String execute(String[] parameters)
    {
        if (parameters.length < 1) {
            return "Tomu nerozumím, musíš mi říct, co mám oslovit.";
        }

        if (parameters.length > 1) {
            return "Tomu nerozumím, neumím oslovit více věcí současně.";  
        }                                                                
                                                                         
        String npcName = parameters[0];

        GameWorld world = game.getWorld();
        Area currentArea = world.getCurrentArea();

        if(currentArea.getNpc().getName().equals(npcName)){
        return currentArea.getNpc().getAnswer();
        }
        
        

        

        return "... nikdo takový tu není";
    }
}
