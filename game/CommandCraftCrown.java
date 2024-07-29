package game;

/**
 * Třída implementující příkaz pro tvorbu koruny.
 *
 * @author Jakub Mamica
 * @version ZS-2022, 2023-1-10
 */
public class CommandCraftCrown implements ICommand
{
    private Game game;

    /**
     * Konstruktor třídy.
     *
     * @param game hra, ve které se bude příkaz používat
     */
    public CommandCraftCrown(Game game)
    {
        this.game = game;
    }

    /**
     * Metoda vrací název příkazu tj.&nbsp; slovo <b>slož/b>.
     *
     * @return název příkazu
     */
    @Override
    public String getName()
    {
        return "slož";
    }

    /**
     * Metoda zkontroluje zadaný vstup a při spráném vstupu a podmínkách hry vytvoří korunu a vratí o tom zprávu.
     * metodakontroluje podmínky vstupu. Při špatném vstupu vrací zprávu co bylo špatně. 
     * Kontroluje podmínky hry zda je hráč ve sprívné pozici pokud ano složí korunu a vrátí zprávu o úspěchu. 
     * Jinak hráči oznámí co mu k úspěchu chybý.
     *
     *
     * @param parameters parametry příkazu <i>(očekává se pole s jedním prvkem)</i>
     * @return informace pro hráče, které hra vypíše na konzoli
     */
    @Override
    public String execute(String[] parameters)
    {
        if (parameters.length < 1) {
            return "Tomu nerozumím, musíš mi říct, co mám složit.";
        }

        if (parameters.length > 1) {
            return "Tomu nerozumím, neumím složit více věcí současně.";  
        }                                                                
                                                                         
        String crown = parameters[0];

        GameWorld world = game.getWorld();
        Area currentArea = world.getCurrentArea();
        Bag bag= world.getBag();
        
        if(crown.equals("korunu")&&currentArea.containsItem("oltář")){
           if(bag.containsItem("zlomek_koruny")){
            game.setGameOver(true);
            return "Kus koruny který jsi použil je nesprávný, zůstaneš již navždy vězněm.";
            }
                
             if(bag.containsItem("kousek_koruny")&&bag.containsItem("část_koruny")&&bag.containsItem("kus_koruny")){
                    bag.removeItem("kus_koruny");
                   bag.removeItem("kousek_koruny");
                   bag.removeItem("část_koruny");
                   Item koruna = new Item("koruna", "magická koruna, nasaď si ji a jsi volný");
                   bag.addItem(koruna);
                    return "Kusy koruny se vznesly nad oltář a složily dohromady, jsi majitelem magické koruny.";
            
             }
            
        }
        
        if(crown.equals("korunu")&&!currentArea.containsItem("oltář")){
        return "Nejsi u oltáře, nemůžeš skládat korunu.";
        }
        
        if(!crown.equals("korunu")){
        return "Můžeš složit pouze korunu u oltáře.";
        }
        

        return "Nemáš všechny části koruny, potřebuješ tři části koruny.";
    }
}
