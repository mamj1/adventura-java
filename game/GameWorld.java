package game;

/**
 * Třída představující mapu oblastí herního světa. V datovém atributu
 * {@link #currentArea} uchovává odkaz na aktuální oblast, ve které
 * se hráč právě nachází. Z aktuální oblasti je možné se prostřednictvím
 * jejích sousedů dostat ke všem přístupným oblastem ve hře.
 * <p>
 * Veškeré informace o stavu hry <i>(mapa oblastí, inventář, vlastnosti
 * hlavní postavy, informace o plnění úkolů apod.)</i> by měly být uložené
 * zde v podobě datových atributů.
 *
 * @author Jan Říha
 * @version ZS-2022, 2022-12-14
 */
public class GameWorld
{
    private Area currentArea;
    private Bag bag;
    private boolean win= false;
    /**
     * Konstruktor třídy, vytvoří jednotlivé oblasti, propojí je pomocí východů a vloží do nich předměty.
     */
    public GameWorld()
    {
        bag= new Bag(3);
        Area kosti = new Area("místnost_s_kostmi", "Kamená místnost s hromadou kostí.");
        Area brneni = new Area("místnost_s_brněním", "Kamená místnost s brněním na stojanu.");
        Area kusKoruny = new Area("místnost_s_kusem_koruny", "Kamená místnost s kusem koruny.");
        Area oltar = new Area("brána_s_oltářem", "Kamená místnost s oltářem a bránou.");
        Area studna = new Area("místnost_se_studnou", "Kamená místnost se studnou.");
        Area hlava= new Area("místnost_s_hlavou", "Kamená místnost s létající hlavou.");
        Area truhla= new Area("místnost_s_truhlama", "Kamená místnost s dvěma truhlama, železnou po pravici a zlatou po levici.");
        Area drevo= new Area("místnost_s_dřevem", "Kamená místnost s hromadou dřeva.");
        Area ohen= new Area("místnost_s_ohništěm", "Kamená místnost s pohaslím ohništěm.");
        
        
        
        kosti.addExit(oltar);
        kosti.addExit(brneni);
        
        brneni.addExit(kosti);
        brneni.addExit(kusKoruny);
        brneni.addExit(studna);
        
        kusKoruny.addExit(brneni);
        kusKoruny.addExit(hlava);
        
        oltar.addExit(kosti);
        oltar.addExit(studna);
        oltar.addExit(truhla);
        
        studna.addExit(oltar);
        studna.addExit(brneni);
        studna.addExit(drevo);
        studna.addExit(hlava);
        
        hlava.addExit(kusKoruny);
        hlava.addExit(studna);
        hlava.addExit(ohen);
        
        truhla.addExit(oltar);
        truhla.addExit(drevo);
        
        drevo.addExit(truhla);
        drevo.addExit(drevo);
        drevo.addExit(ohen);
        
        ohen.addExit(hlava);
        ohen.addExit(drevo);
        
        Npc head= new Npc("hlava",2);
        head.addAnswer("Hlava na tebe pohledí a spustí: Jsi uvězněn v magickém vězení, \n aby jsi unikl musíš najít tři kusy magické koruny a složit je na oltáři.\n K nalezení kusů koruny ti pomohou tři následující nápovědy. \n Ta pravá truhla je ta pravá. \n Povstane jako fénix. \n Pozor, nezakopni o ni.");
        head.addAnswer("Hlava na tebe pohledí a spustí: K nalezení kusů koruny ti pomohou tři následující nápovědy. \n Ta pravá truhla je ta pravá. \n Povstane jako fénix. \n Pozor, nezakopni o ni.");
        hlava.setNpc(head);
        
        Item koruna1 = new Item("kus_koruny"," kus koruny ");
        Item koruna2= new Item("kousek_koruny"," kus koruny ");
        Item koruna3= new Item("část_koruny"," kus koruny ");
        Item koruna4= new Item("zlomek_koruny"," kus koruny ");
        Item bone= new Item("kosti","Hromada kostí ležících na zemi.",false);
        Item armour= new Item("brnění"," Brnění rytíře na stojanu.",false);
        Item altar= new Item("oltář","Oltář pro magické rituály.",false);
        Item well= new Item("studna","Studna na jejíž dno není vidět.");
        Item chest1= new Item("zlatá_truhla","Zlatá truhla ležící na levo od železné truhly.",false);
        Item chest2= new Item("železná_truhla","Železná truhla ležící napravo od zlaté truhly.",false);
        Item wood= new Item("hromada_dřeva"," Hromada starého dřeva položené na podlaze.",false);
        Item fire= new Item("ohniště","Ohniště nehoří je tu už jenom popel.",false);
        
        chest1.addItem(koruna4);
        chest2.addItem(koruna1);
        fire.addItem(koruna2);
        
        kusKoruny.addItem(koruna3);
        kosti.addItem(bone);
        brneni.addItem(armour);
        oltar.addItem(altar);
        studna.addItem(well);
        truhla.addItem(chest1);
        truhla.addItem(chest2);
        drevo.addItem(wood);
        ohen.addItem(fire);
        
        
        currentArea = hlava;
        
    }

    /**
     * Metoda vrací odkaz na aktuální oblast, ve které se hráč právě nachází.
     *
     * @return aktuální oblast
     */
    public Area getCurrentArea()
    {
        return currentArea;
    }

    /**
     * Metoda nastaví aktuální oblast. Používá ji příkaz {@link CommandMove}
     * při přechodu mezi oblastmi.
     *
     * @param currentArea oblast, která bude nastavena jako aktuální
     */
    public void setCurrentArea(Area currentArea)
    {
        this.currentArea = currentArea;
    }

    /**
     * Metoda vrací informaci, zda hráč vyhrál <i>(zda jsou splněné všechny
     * úkoly a podmínky nutné pro výhru)</i>.
     *
     * @return {@code true}, pokud hráč vyhrál; jinak {@code false}
     */
    public boolean isVictorious()
    {
        return win;
    }
    
    public void setVictorious()
    {
        win= true;
    }
    
    
    /**
     * Metoda vrací odkaz na aktuální inventář.
     *
     * @return aktuální inventář
     */
    public Bag getBag()
    {
        return bag;
    }
}
