package game;

/**
 * Třída implementující příkaz pro vypsání obsahu inventáře<i>(batohu)</i>.
 *
 * @author Jakub Mamica
 * @version ZS-2022, 2023-1-10
 */
public class CommandBagCheck implements ICommand
{
    private Game game;

    /**
     * Konstruktor třídy.
     *
     * @param game hra, ve které se bude příkaz používat
     */
    public CommandBagCheck(Game game)
    {
        this.game = game;
    }

    /**
     * Metoda vrací název příkazu tj.&nbsp; slovo <b>co</b>.
     *
     * @return název příkazu
     */
    @Override
    public String getName()
    {
        return "co";
    }

    /**
     * Metoda se pokusí vypsat obsah hráčova
     * inventáře. Nejprve zkontroluje počet parametrů. Pokud nebyl zadán žádný
     * parametr , nebo bylo zadáno dva a více parametrů , vrátí
     * chybové hlášení. Pokud byl zadán právě jeden parametr, zkontroluje zda je to slovo "nosím". Pokud ne,
     * vrátí chybové hlášení. Pokud všechny kontroly proběhnou v pořádku, provede
     * výpis inventáře.
     *
     * @param parameters parametry příkazu <i>(očekává se pole s jedním prvkem)</i>
     * @return informace pro hráče, které hra vypíše na konzoli
     */
    @Override
    public String execute(String[] parameters)
    {
        if (parameters.length < 1) {
            return "Co co?";
        }

        if (parameters.length > 1) {
            return "Tomu nerozumím.";  
        }                                                                
                                                                         
        String param = parameters[0];
        
        if(param.equals("nosím")){
        return game.getWorld().getBag().inventoryPrint();
        }
        

        return "Tomu nerozumím, příkaz 'co " + param + "' neznám.";
        
    }
}
