package game;

/**
 * Třída implementující příkaz pro zobrazení nápovědy ke hře.
 *
 * @author Jan Říha
 * @author Jakub Mamica
 * @version ZS-2022, 2023-1-10
 */
public class CommandHelp implements ICommand
{
    /**
     * Metoda vrací název příkazu tj.&nbsp; slovo <b>napoveda</b>.
     *
     * @return název příkazu
     */
    @Override
    public String getName()
    {
        return "napoveda";
    }

    /**
     * Metoda vrací obecnou nápovědu ke hře. 
     *
     * @param parameters parametry příkazu <i>(aktuálně se ignorují)</i>
     * @return nápověda, kterou hra vypíše na konzoli
     */
    @Override
    public String execute(String[] parameters)
    {
        return "Toto je hra o útěku z vězění\n\n"
                + "Tvým úkolem je najít tři správné kusy koruny\n"
                + "a složit je u magického oltáře.\n Používáš k tomu následující příkazy\n jdi lokace\n rozhledni_se\n nasaď koruna\n "
                +"slož korunu\n prozkoumej item\n oslov npc\n vezmi item\n plož item\n nápověda\n konec \n pro více informací náhlédni do manuálu.";
    }
}
