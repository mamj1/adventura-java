package game;

/**
 * Třída implementující příkaz pro nasazení koruny.
 *
 * @author Jakub Mamica
 * @version ZS-2022, 2023-1-10
 */
public class CommandCrownEquip implements ICommand
{
    private Game game;

    /**
     * Konstruktor třídy.
     *
     * @param game hra, ve které se bude příkaz používat
     */
    public CommandCrownEquip(Game game)
    {
        this.game = game;
    }

    /**
     * Metoda vrací název příkazu tj.&nbsp; slovo <b>nasaď</b>.
     *
     * @return název příkazu
     */
    @Override
    public String getName()
    {
        return "nasaď";
    }

    /**
     * Metoda se pokusí zkontrolovat zda má hráč korunu a pokud ano hru úspěšně ukončit.
     * Nejprve zkontroluje počet parametrů. Pokud nebyl zadán žádný
     * parametr , nebo bylo zadáno dva a více parametrů , vrátí
     * chybové hlášení. Pokud byl zadán právě jeden parametr, zkontroluje, zda
     * je to slovo "korunu" a zda má hráč předmět koruna u sebe.
     *  Pokud ne, vrátí chybové hlášení. Pokud všechny kontroly proběhnou v pořádku, provede
     * ukončení hry.
     *
     * @param parameters parametry příkazu <i>(očekává se pole s jedním prvkem)</i>
     * @return informace pro hráče, které hra vypíše na konzoli
     */
    @Override
    public String execute(String[] parameters)
    {
        if (parameters.length < 1) {
            return "Tomu nerozumím, musíš mi říct, co mám nasadit.";
        }

        if (parameters.length > 1) {
            return "Tomu nerozumím, neumím nasadit více věcí současně.";  
        }                                                                
                                                                         
        String itemName = parameters[0];

        GameWorld world = game.getWorld();
        Bag bag= world.getBag();
        
         if(itemName.equals("korunu"))
        {
            if(bag.containsItem("koruna")){
            world.setVictorious();
            game.setGameOver(true);
        
            return "Nasadil jsi si magickou korunu která tě přenesla mimo vězení.";
            }
            return "Nemáš žádnou korunu u sebe.";
        }

        

        return itemName +" si neumíš nasadit.";
    }
}
