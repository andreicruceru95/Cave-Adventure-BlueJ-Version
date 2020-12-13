import java.util.*;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, console based game.  
 *  It's a world of monsters, where a young warior must fight to grow 
 *  so he can defeat tougher and tougher evils.
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 * 
 * Modified and extended by Your name
 */

public class Game 
{
    public static final char CLEAR = '\u000c';
    public static final String HELP = "help";
    public static final String QUIT = "quit";
    public static final String POTION = "potion";
    public static final String SEE_DATABASE = "database";
    public static final String SEE_MONSTERS = "seemonsters";
    public static final String SEE_LOCATION = "seelocation";
    public static final String SUCCESS = "\t\tYou have successfully purchased: ";   
    public static final String SHOW_STATS = "showstats";
    public static final String HIDE_STATS = "hidestats";
    public static final String SHOW_ITEMS = "showitems";
    public static final String HIDE_ITEMS = "hideitems";
    public static final String INVENTORY = "inventory";
    public static final String QUESTS = "seequests";
    
    public static final Shop SHOP = new Shop();
    public static final int PLAYER_INITIAL_ROW = 7;
    public static final int PLAYER_INITIAL_COL = 7;
    public static final int PLAYER_VISUAL = 3;
    public static final String TOWN = "town";
    public static final String DESSERT = "dessert";
    public static final String SPIDER_CAVE = "spidercave";
    public static final int LEVEL_1 = 1;
    public static final int LEVEL_5 = 5;
    public static final int LEVEL_10 = 10;
    public static final int LEVEL_15 = 15;
    public static final int LEVEL_20 = 20;
    public static final int LEVEL_25 = 25;
    public static final int LEVEL_30 = 30;
    public static final int LEVEL_35 = 35;
    public static final int LEVEL_40 = 40;
    public static final int LEVEL_45 = 45;
    public static final int LEVEL_50 = 50;
    public static final int LEVEL_55 = 55;
    public static final int LEVEL_9 = 9;
    public static final int LEVEL_14 = 14;
    public static final int LEVEL_19 = 19;
    public static final int LEVEL_34 = 34;
    public static final int LEVEL_49 = 49;
    public static final int LEVEL_60 = 60;
    public static final int LEVEL_70 = 70;
    public static final String UP = "w";
    public static final String DOWN = "s";
    public static final String LEFT = "a";
    public static final String RIGHT = "d";
        
    private String square = "   ";
    private int monsterLevel = 1;
    
    
    private ArrayList <Monster> monsters = new ArrayList<>();
    private ArrayList <String> misk = new ArrayList<>(); 
    //interactions with game objects
    private Interactions interaction = new Interactions();
    private Database database = new Database();
    private Display display = new Display();
    private Storyline story = new Storyline();
    private Blacksmith blacksmith = new Blacksmith();
    
    private Characters character;
    private Commands command;
    
    private Actor monster;
    private Player player;
    private Item weapon;
    private Item armour;
    private Item potion;
    private Item amulet;
    private Item ring;
    private Item bracelet;
    
    private World world = new World();
    
    private boolean shopDescription =  false;
    private boolean blacksmithDescription =  false;
    private boolean guardDescription =  false;
    private boolean stableDescription =  false;
    private boolean teleporterDescription =  false;
    private boolean dessertDescription =  false;
    private boolean spiderCaveDescription =  false;
    private boolean firstDeathDescription =  false;
    private boolean firstBiologistInteraction = false;
    private boolean firstLadyInteraction = false;
    private boolean stats = false;
    private boolean items = false;
    private boolean ringExists = false;
    private boolean braceletExists = false;
    private boolean biologistQuest1 = false;
    private boolean biologistQuest2 = false;
    private boolean biologistQuest3 = false;
    private boolean biologistQuest4 = false;
    private boolean biologistQuest5 = false;
    private boolean ladyQuest = false;
    
    private Random rand = new Random();
    private Input reader = new Input();
    
    private int pickUpGold = 0;
    
    public Game()
    {
        display.runStory(story.getPartOne());
        
        System.out.println(CLEAR);
        
        System.out.println("\n\n\n\t\tPlease enter the player's name:");
        
        String playerName = reader.getString();
        player = new Player(playerName, 1);
        
        weapon = player.getWeapon();
        armour = player.getArmour();
        potion = player.getPotion();
        amulet = player.getAmulet();
        
        display.runStory(story.getPartTwo(playerName));
        
        misk.add(character.FLOWER_RED.getCharacter());
        misk.add(character.FLOWER_BLUE.getCharacter());        
        misk.add(character.FLOWER_YELLOW.getCharacter());
        misk.add(character.FLOWER_PURPLE.getCharacter());
        misk.add(character.FLOWER_WHITE.getCharacter());
        misk.add(character.SNOW_FRAG.getCharacter());
        misk.add(character.STAR_FRAG.getCharacter());
        misk.add(character.SPIDER_KEY.getCharacter());
        misk.add(character.TOWER_KEY.getCharacter());
        misk.add(character.FOX_KEY.getCharacter());
        misk.add(character.CHEST_KEY.getCharacter());
        
        monsters.add(new Monster (character.BLACK_BEAR.getCharacter(), LEVEL_1, character.FLOWER_RED.getCharacter()));
        monsters.add(new Monster (character.WHITE_TIGER.getCharacter(), LEVEL_5, character.FLOWER_RED.getCharacter()));
        monsters.add(new Monster (character.APE_THROWER.getCharacter(), LEVEL_10, character.FLOWER_BLUE.getCharacter()));
        monsters.add(new Monster (character.POISON_SPIDER.getCharacter(), LEVEL_15, character.FLOWER_YELLOW.getCharacter()));
        monsters.add(new Monster (character.RED_SCORPION.getCharacter(),LEVEL_20, character.FLOWER_YELLOW.getCharacter()));
        monsters.add(new Monster (character.ALBINO_SNAKE.getCharacter(),LEVEL_25, character.FLOWER_PURPLE.getCharacter()));
        monsters.add(new Monster (character.POLAR_BEAR.getCharacter(),LEVEL_30, character.FLOWER_WHITE.getCharacter()));
        monsters.add(new Monster (character.YETI.getCharacter(),LEVEL_35, character.SNOW_FRAG.getCharacter()));
        monsters.add(new Monster (character.ABOMINABLE_SNOWMAN.getCharacter(),LEVEL_40, character.SNOW_FRAG.getCharacter()));
        monsters.add(new Monster (character.DEMON.getCharacter(),LEVEL_45, character.STAR_FRAG.getCharacter()));
        monsters.add(new Monster (character.CURSED_VAMPIRE.getCharacter(),LEVEL_50, character.STAR_FRAG.getCharacter()));
        monsters.add(new Monster (character.WITCH.getCharacter(),LEVEL_55, character.STAR_FRAG.getCharacter()));
        monsters.add(new Monster (character.BERA.getCharacter(),LEVEL_9, character.SPIDER_KEY.getCharacter()));
        monsters.add(new Monster (character.TIGRIS.getCharacter(),LEVEL_14, character.SPIDER_KEY.getCharacter()));
        monsters.add(new Monster (character.APE_KING.getCharacter(),LEVEL_19, character.SPIDER_KEY.getCharacter()));
        monsters.add(new Monster (character.SPIDER_QUEEN.getCharacter(),LEVEL_34, character.FOX_KEY.getCharacter()));
        monsters.add(new Monster (character.NINE_TAILS.getCharacter(),LEVEL_49, character.TOWER_KEY.getCharacter()));
        //drop to be changed
        monsters.add(new Monster (character.DEATH.getCharacter(),LEVEL_60, character.CHEST_KEY.getCharacter()));
        monsters.add(new Monster (character.RED_DRAGON.getCharacter(),70, character.CHEST_KEY.getCharacter()));
         
        run();
                
    }
    
    /**
     * Main options.
     */
    public void run()
    {
        System.out.println(CLEAR);
        
        boolean finished = false;
        while(!finished)
        {
            System.out.println(CLEAR);
                
            changeImage();
            showInfo();
            updateVisualField();
            
            String choice = reader.getAny();
            
            if (choice.toLowerCase().replaceAll("\\s+","").equals(QUIT))
                finished = true;
            
            else if(choice.toLowerCase().replaceAll("\\s+","").equals(POTION))
            {
                player.drinkPotion();
            }    
            else if(choice.toLowerCase().replaceAll("\\s","").contains(HELP))
            {
                display.listOptions(story.getHelp());
                pressAny();
            }
            else if(choice.toLowerCase().replaceAll("\\s","").contains(SEE_DATABASE))
            {
                database.printAll();
                pressAny();
            }
                
            else if(choice.toLowerCase().replaceAll("\\s","").contains(SEE_MONSTERS))
            {
                database.getMonsterList(monsters);
                pressAny();
            }
            
            else if(choice.toLowerCase().replaceAll("\\s","").contains(SEE_LOCATION))
            {
                world.printHelpMap(player.getRowCoord(), player.getColCoord());
                
                pressAny();
            }
            else if(choice.toLowerCase().replaceAll("\\s","").contains(SHOW_STATS))
                stats = true;
                
            else if(choice.toLowerCase().replaceAll("\\s","").contains(HIDE_STATS))
                stats = false;
                
            else if(choice.toLowerCase().replaceAll("\\s","").contains(SHOW_ITEMS))    
                items = true;
                
            else if(choice.toLowerCase().replaceAll("\\s","").contains(HIDE_ITEMS))
                items = false;
            
            else if(choice.toLowerCase().replaceAll("\\s","").contains(INVENTORY))
            {
                System.out.println(CLEAR);
                player.printInventory(); 
                pressAny();
            }
            else if(choice.toLowerCase().replaceAll("\\s","").contains(QUESTS))
            {
                System.out.println(CLEAR);
                player.printQuestList(); 
                pressAny();
            }
            else
                runMenu(choice);
        }
    }
            
    /**
     * Move the player.
     */
    public void runMenu(String choice)
    {
        System.out.println("Chose direction");
        movePlayer(choice);
    }
    
    public void pressAny()
    {
        System.out.println("Press any to continue");
        
        reader.getAny();
    }
    
    /**
     * Update and print the player's visual field.
     */
    private void updateVisualField()
    {
        player.updateVisualField(world.getMapAsArray());
        
    }
    
    /**
     * Move player on the map.
     */
    public void movePlayer(String direction)
    {
        if(direction.replaceAll("\\s+","").equals(UP))
        {
            
            if(checkNextSquare((player.getRowCoord() - 1), player.getColCoord()))
            {
                updateHelpMap(UP);
                
                world.setObject(player.getRowCoord() ,player.getColCoord(),"   ");
                
                player.setCoordinates((player.getRowCoord() - 1), player.getColCoord());
                      
            }
                       
        }
        else if(direction.replaceAll("\\s+","").equals(DOWN))
        {
            
            if(checkNextSquare((player.getRowCoord() + 1), player.getColCoord()))
            {
                updateHelpMap(DOWN);
                
                world.setObject(player.getRowCoord() ,player.getColCoord(),"   ");
                
                player.setCoordinates((player.getRowCoord() + 1), player.getColCoord());
                
            }
               
        }
        else if(direction.replaceAll("\\s+","").equals(LEFT))
        {
            
            if(checkNextSquare(player.getRowCoord(), (player.getColCoord() - 1)))
            {
                updateHelpMap(LEFT);
                
                world.setObject(player.getRowCoord() ,player.getColCoord(),"   ");
                
                player.setCoordinates(player.getRowCoord(), (player.getColCoord() - 1));
                
            }
               
        }
        else if(direction.replaceAll("\\s+","").equals(RIGHT))
        {
            
            if(checkNextSquare(player.getRowCoord(), (player.getColCoord() + 1)))
            {
                updateHelpMap(RIGHT);
                
                world.setObject(player.getRowCoord() ,player.getColCoord(),"   ");
                
                player.setCoordinates(player.getRowCoord(), (player.getColCoord() + 1));
                
            }
            
        }
        
        System.out.println(CLEAR);
        changeImage();
        showInfo();
        updateVisualField();
        
        checkFirstInteraction();       
    }
    
    private void updateHelpMap(String direction)
    {
        if(direction.equals(UP))
        {
            //center
            world.addToHelpMap((player.getRowCoord() - 1),player.getColCoord(), "   ");
            //left
            world.addToHelpMap((player.getRowCoord() - 1), (player.getColCoord() - 1),
                                world.getSquareValue((player.getRowCoord() - 1), (player.getColCoord() - 1)));
            //right         
            world.addToHelpMap((player.getRowCoord() - 1), (player.getColCoord() + 1),
                                world.getSquareValue((player.getRowCoord() - 1), (player.getColCoord() + 1)));
        }
        else if(direction.equals(DOWN))
        {
            //center
            world.addToHelpMap((player.getRowCoord() + 1),player.getColCoord(), "   ");
            //left
            world.addToHelpMap((player.getRowCoord() + 1), (player.getColCoord() - 1),
                                world.getSquareValue((player.getRowCoord() + 1), (player.getColCoord() - 1)));
            //right         
            world.addToHelpMap((player.getRowCoord() + 1), (player.getColCoord() + 1),
                                world.getSquareValue((player.getRowCoord() + 1), (player.getColCoord() + 1)));
        }
        else if(direction.equals(LEFT))
        {
            //center
            world.addToHelpMap((player.getRowCoord()),player.getColCoord() - 1, "   ");
            //down
            world.addToHelpMap((player.getRowCoord() - 1), (player.getColCoord() - 1),
                                world.getSquareValue((player.getRowCoord() - 1), (player.getColCoord() - 1)));
            //up         
            world.addToHelpMap((player.getRowCoord() + 1), (player.getColCoord() - 1),
                                world.getSquareValue((player.getRowCoord() + 1), (player.getColCoord() - 1)));
        }
        else if(direction.equals(RIGHT))
        {
            //center
            world.addToHelpMap((player.getRowCoord()),player.getColCoord() + 1, "   ");
            //down
            world.addToHelpMap((player.getRowCoord() + 1), (player.getColCoord() + 1),
                                world.getSquareValue((player.getRowCoord() + 1), (player.getColCoord() + 1)));
            //up         
            world.addToHelpMap((player.getRowCoord() - 1), (player.getColCoord() + 1),
                                world.getSquareValue((player.getRowCoord() - 1), (player.getColCoord() + 1)));
        }
        
    } 
     
    /**
     * Display information about the player and map.
     */
    public void showInfo()
    {
        System.out.println("\tMap: " + world.getCurrentMapName().toUpperCase() + "\n");
        System.out.println("\tPlayer: " + player.getName() + "\tScore: " + player.getScore() + "\n" + player.getHealthInfo());
        
        if(stats)
        {
            System.out.println(player.getStats()); 
            getGold();
            
        }
        
        createRing();
        createBracelet();
            
        if(items)
        {
            ((Weapon) weapon).print();
            ((Armour) armour).print();
            ((Potion) potion).print();
            ((Amulet) amulet).print();
            
            if(ringExists)
                ((Ring) ring).print();
            
            if(braceletExists)
                ((Bracelet) bracelet).print();
                
        }   
            
    }
    
    public void createRing()
    {
        
        if(((Player) player).ringExists())
        {
            ring = player.getRing();
            
            ringExists = true;
        }
        
    }
    
    public void createBracelet()
    {
        
        if(((Player) player).braceletExists())   
        {
            bracelet = player.getBracelet();
            
            braceletExists = true;
        }  
        
    }
    
    public void getGold()
    {
        System.out.println("\tGold: " + player.getGold() + "" + character.GOLD.getCharacter());
    }
    
    /**
     * Check the next square
     */
    public boolean checkNextSquare(int nextRow, int nextCol)
    { 
        if (world.getSquareValue(nextRow,nextCol).equals(square))
        {
            return true;
        }
        else if(world.getSquareValue(nextRow,nextCol).equals(character.WALL.getCharacter()) || world.getSquareValue(nextRow,nextCol).equals(character.ROCK.getCharacter()))
        {
            System.out.println("Cannot go through walls");
            
            return false;
        }
        else if(world.getSquareValue(nextRow,nextCol).equals(character.SHOP.getCharacter()))
        {
            runShop();
            
            return false;
        }
        else if(world.getSquareValue(nextRow,nextCol).equals(character.BLACKSMITH.getCharacter()))
        {
            runBlacksmith();
            
            return false;
        }
        else if(world.getSquareValue(nextRow,nextCol).equals(character.CHEST.getCharacter()))
        {
            openChest();
            
            return true;
        }
        else if(world.getSquareValue(nextRow,nextCol).equals(character.GOLD.getCharacter()))
        {
            player.addGold(pickUpGold);
            
            return true;
        }
        else if(checkItem(world.getSquareValue(nextRow,nextCol)))
        {
            player.addToInventory(world.getSquareValue(nextRow,nextCol), 1);
            
            return true;
        }
        else if(world.getSquareValue(nextRow,nextCol).equals(character.TELEPORT.getCharacter()))
        {
            teleport();
            
            return false;
        }
        else if(world.getSquareValue(nextRow,nextCol).equals(character.CORPSE.getCharacter()))
        {
            System.out.println(CLEAR);
            
            interaction.getInteraction(character.CORPSE.getCharacter());
            
            pressAny();
            
            return false;
        }
        else if(world.getSquareValue(nextRow,nextCol).equals(character.BIOLOGIST.getCharacter()))
        {
            runBiologist();
            
            pressAny();
            
            return false;
        }
        else if(world.getSquareValue(nextRow,nextCol).equals(character.PERSON_1.getCharacter()))
        {
            runLadyQuest();
            pressAny();
            
            return false;
        }
        else if(world.getSquareValue(nextRow,nextCol).equals(character.POTION.getCharacter()))
        {
            int potionAmount = 5;
            
            player.increasePotionAmount(potionAmount);
            
            return true;
        }
        else 
        {
            String character = (world.getSquareValue(nextRow,nextCol));
            monster = findMonster(character);
            
            if(fight(character, monster))
            {
                world.addAnother(character);
                
                pickUpGold = dropGold();
                player.addScore(pickUpGold);
                player.addScore(monster.getLevel());
                
                dropItem();
                dropChest();
                
                return true;
            }
            
        }
        return false;
    }
    
    public void dropChest()
    {
        if(((Monster) monster).dropChest())
        {
            world.addObjects(((Player) player).getColCoord() - 1 , ((Player) player).getColCoord() + 1,
            ((Player) player).getRowCoord() - 1, ((Player) player).getRowCoord() + 1, character.CHEST.getCharacter()) ;  
        }
    }
    
    /**
     * 50% chance to obtain (1, 5) x monster level items or gold.
     */
    private void openChest()
    {
        if((rand.nextInt(1 - 0) +0) == 0)
        {
            player.addToInventory(((Monster) monster).getDrop(), ((Monster) monster).getDropAmount());
            
            System.err.println("Recieved " + ((Monster) monster).getDropAmount() + " " + ((Monster) monster).getDrop());
        }
        else
        {
            player.addGold(((Monster) monster).getDropAmount() * monster.getLevel());
            
            System.err.println("Recieved " + ((Monster) monster).getDropAmount() * monster.getLevel() + character.GOLD.getCharacter());
        }
        
    }
    
    private boolean fight(String character, Actor monster)
    {
        if (monster != null)
        {
            return checkResult(action(monster), monster, character);
        }
        
        return false;
    }
    
    private boolean checkItem(String value)
    {
        for(String string : misk)
        {
            if(string.equals(value))
                return true;
        }
        
        return false;
    }
    
    private int dropGold()
    {
        if(((Monster) monster).dropGold() != 0)
        {
            world.addObjects(((Player) player).getColCoord() - 1 , ((Player) player).getColCoord() + 1,
            ((Player) player).getRowCoord() - 1, ((Player) player).getRowCoord() + 1, character.GOLD.getCharacter()) ;           
        }
        
        return monster.getLevel();
    }
    
    private void dropItem()
    {
        if(((Monster) monster).dropItem())
        {
            world.addObjects(((Player) player).getColCoord() - 1 , ((Player) player).getColCoord() + 1,
            ((Player) player).getRowCoord() - 1, ((Player) player).getRowCoord() + 1, ((Monster) monster).getDrop()) ;           
        }
           
    }
    
    /**
     * Spawn another monster on the map if you won the fight.
     * If you die, you will be set back to the initial position.
     */
    private boolean checkResult(boolean result, Actor monster, String character)
    {
        if(result)
        {
            //System.err.println("\t\t\t\tYou Win!");
            
            return true;
        }
        else
        {
            checkFirstDeath();
            
           // System.err.println("\t\t\t\tYou Lost!");
                    
            player.setCoordinates(PLAYER_INITIAL_ROW, PLAYER_INITIAL_COL);
            player.setFullHealth();
                    
            //guardQuest();
        }
        return false;
    }
    
    public void checkFirstDeath()
    {
        if(firstDeathDescription == false)
        {
            System.out.println("You died! You will be sent back in town where someone will take care of you.");
            System.out.println("\n\n\n\n\n\n\n\n\n\n\t\tPress any to continue..");
            reader.getAny();
            
            firstDeathDescription = true;
        }
    }
    
    /**
     * Find which monster we are dealing with.
     */
    private Actor findMonster(String character)
    {
        for (Monster monster : monsters)     
        {
            if (monster.getName().equals(character))
            {
                return monster;
                
            }
        }
        
        return null;
    }
        
    /**
     * Fast-forward fight
     */
    public boolean action(Actor monster)
    {
        do
        {
            
            monster.receiveDmg(player.attack());
            //System.out.println("Monster received " + monster.receiveDmg(player.attack()));
                        
            player.receiveDmg(monster.attack());
            //System.out.println("Player received " + player.receiveDmg(monster.attack()));    
            
        }
        while(player.checkHealth() && monster.checkHealth());
        
        return player.getHealthPoints();
    }
    
    /**
     * 
     */
    public void changeImage()
    {
        if(player.getCurrentHealth() <= player.getFullHealth() / 2)
            player.changeImage(character.PLAYER2.getCharacter());
            
        else
            player.changeImage(character.PLAYER.getCharacter()); 
    }
     
    
    /**
     * 30% chance to recieve a 2x monster's level amount of gold.
     */
    private int goldChance()
    {
        int min = 5;
        int max = 15;
                
        int dropChance = rand.nextInt(max - min) + min;
        int multiplier = rand.nextInt(max - (min + 1)) + min;
        
        if(dropChance == min || dropChance == 1 || dropChance == max)
        {
            return monsterLevel * multiplier;
        }
        
        return 0;
    }
    
    /**
     * The first interaction with some of the game elements.
     */
    public void checkFirstInteraction()
    {
        if(player.checkVisualField(character.SHOP.getCharacter()) && !shopDescription)
        {
            shopDescription = interaction.getInteraction(character.SHOP.getCharacter());    
            pressAny();
        }    
        else if(player.checkVisualField(character.BLACKSMITH.getCharacter()) && !blacksmithDescription)
        {
            blacksmithDescription = interaction.getInteraction(character.BLACKSMITH.getCharacter()); 
            pressAny();
        }    
        else if(player.checkVisualField(character.STABLE.getCharacter()) && !stableDescription)
        {
            stableDescription = interaction.getInteraction(character.STABLE.getCharacter());
            pressAny();
        }          
        else if(player.checkVisualField(character.GUARD.getCharacter()) && !guardDescription)
        {
            guardDescription = interaction.getInteraction(character.GUARD.getCharacter());
            pressAny();
        }    
        else if(player.checkVisualField(character.TELEPORT.getCharacter()) && !teleporterDescription)
        {
            teleporterDescription = interaction.getInteraction(character.TELEPORT.getCharacter());
            pressAny();
        }    
        else if(world.getCurrentMapName().toLowerCase().equals(DESSERT) && !dessertDescription)
        {
            dessertDescription = interaction.getInteraction(DESSERT);
            pressAny();
        }                
        else if(world.getCurrentMapName().toLowerCase().equals(SPIDER_CAVE) && !spiderCaveDescription)
        {
            spiderCaveDescription = interaction.getInteraction(SPIDER_CAVE);
            pressAny();
        }    
    }
    
    public void runBiologist()
    {
        if(!firstBiologistInteraction)
        {
            firstBiologistInteraction = interaction.getInteraction(character.BIOLOGIST.getCharacter());
            
            player.startQuest("biologist1");
            player.startQuest("biologist2");
            player.startQuest("biologist3");
            player.startQuest("biologist4");
            player.startQuest("biologist5");
        }
        else
        {
            checkBiologistQuests();
            
        }
        
    }
    
    public void runLadyQuest()
    {
        if(!firstLadyInteraction)
        {
            firstLadyInteraction = interaction.getInteraction(character.PERSON_1.getCharacter());
            
            player.startQuest("Main[1][2]");
        }
        else
            if(!ladyQuest)
                runQuest("Main[1][2]");
    }
    
    public void checkBiologistQuests()
    {
        if(!biologistQuest1)
        {
            runQuest("biologist1");
        }
        
        if(!biologistQuest2)
        {
            runQuest("biologist2");
        }
        
        if(!biologistQuest3)
        {
            runQuest("biologist3");
        }
        
        if(!biologistQuest4)
        {
            runQuest("biologist4");
        }
        
        if(!biologistQuest5)
        {
            runQuest("biologist5");
        }
        
    }
    
    private void runQuest(String questName)
    {
        if(player.checkInventory(player.getRequirement(questName), player.getRequirementAmount(questName)))
        {
            player.addGold(player.getRewardAmount(questName));
            
        }
        
    }
    
    /**
     * Enchance an item.
     */
    private void runBlacksmith()
    {
        boolean finished = false;
            
        while(!finished)
        {
            blacksmith.createList(ringExists, braceletExists);
            
            System.out.println("\n\n\t\tWhat can I do for you?\n\n");
                
            blacksmith.openBlacksmithShop();
                
            String choice = reader.getString();
                
            if(choice.toLowerCase().replaceAll("\\s+","").equals("quit"))
            {
                finished = true;
            }
            else if(choice.toLowerCase().replaceAll("\\s+","").equals("enchanceweapon"))
            {
                                 
                if(blacksmith.enchance(weapon, player.getGold()))
                    player.pay(blacksmith.getCost(weapon));
                
            }
            else if(choice.toLowerCase().replaceAll("\\s+","").equals("enchancearmour"))
            {
                
                if(blacksmith.enchance(armour,player.getGold()))
                    player.pay(blacksmith.getCost(armour));
                    
            }
            else if(choice.toLowerCase().replaceAll("\\s+","").equals("enchancepotion"))
            {
                
                if(blacksmith.enchance(potion, player.getGold()))
                    player.pay(blacksmith.getCost(potion));
                    
            }
            else if(choice.toLowerCase().replaceAll("\\s+","").equals("enchanceamulet"))
            {
                
                if(blacksmith.enchance(amulet, player.getGold()))
                    player.pay(blacksmith.getCost(amulet));
                
            }
            else if(choice.toLowerCase().replaceAll("\\s+","").equals("enchancering"))
            {
                
                if(blacksmith.enchance(ring, player.getGold()))
                    player.pay(blacksmith.getCost(ring));
                
            }
            else if(choice.toLowerCase().replaceAll("\\s+","").equals("enchancebracelet"))
            {
                
                if(blacksmith.enchance(bracelet, player.getGold()))
                    player.pay(blacksmith.getCost(bracelet));
                
            }
            else
                System.out.println("Not an option");
                
        }
        
    }
    
    /**
     * Buy from shop.
     */
    private void runShop()
    {
        boolean finished = false;
            
        while(!finished)
        {
            System.out.println("\t\tHave a look at what I got!\n\n\n");
               
            SHOP.openShop();
                
            String choice = reader.getString();
            
            if(choice.toLowerCase().replaceAll("\\s+","").equals("quit"))
            {
                finished = true;
            }
            else if(choice.toLowerCase().replaceAll("\\s+","").equals("buypotion"))
            {
                System.out.println("\n\nHow many do you want to buy?\n\n");
                int amount = reader.getInteger();
                    
                if (player.getGold() >= (SHOP.getPotionPrice() * amount))
                {
                    player.pay(SHOP.getPotionPrice() * amount);
                    
                    player.increasePotionAmount(amount);
                    
                    System.out.println(SUCCESS + amount + " Health Potion(s)");
                }
                
            }
            else if(choice.toLowerCase().replaceAll("\\s+","").equals("buyattack"))
            {
                
                if (player.getGold() >= (SHOP.getAttackPrice()))
                {
                    player.pay(SHOP.getAttackPrice());
                    
                    player.increaseAttackForce(SHOP.getAtackValue());
                    
                    System.out.println(SUCCESS + " Attack value");
                }
                
            }
            else if(choice.toLowerCase().replaceAll("\\s+","").equals("buyshield"))
            {
                
                if (player.getGold() >= (SHOP.getShieldPrice()))
                {
                    player.pay(SHOP.getShieldPrice());
                    
                    player.increaseShield(SHOP.getShieldValue());
                    
                    System.out.println(SUCCESS + " Deffence value");
                }
                    
            }
            else if(choice.toLowerCase().replaceAll("\\s+","").equals("buyhealth"))
            {
                
                if (player.getGold() >= (SHOP.getHealthPrice()))
                {
                    player.pay(SHOP.getHealthPrice());
                    
                    player.increaseHealthPoints(SHOP.getHealthValue());
                    
                    System.out.println(SUCCESS + " Extra health points");
                }
                
            }
            else
                System.out.println("Not an option");
        }
        
    }
    
    /**
     * Teleport to a different map
     */
    private void teleport()
    {
        if(world.getCurrentMapName().toLowerCase().equals("town") && world.goTest(player.getColCoord()))
        {
            player.setCoordinates(PLAYER_INITIAL_ROW,PLAYER_INITIAL_COL);
            
            world.setCurrentMap("test");
        }
        else if(world.getCurrentMapName().toLowerCase().equals("town"))
        {
            player.setCoordinates(PLAYER_INITIAL_ROW,PLAYER_INITIAL_COL);
            
            world.setCurrentMap("dessert");
        }
        else if(world.getCurrentMapName().toLowerCase().equals("test"))
        {
            player.setCoordinates(PLAYER_INITIAL_ROW,PLAYER_INITIAL_COL);
            
            world.setCurrentMap("town");
        }
        else if(world.getCurrentMapName().toLowerCase().equals("dessert"))
        {
            player.setCoordinates(PLAYER_INITIAL_ROW,PLAYER_INITIAL_COL);
            
            world.setCurrentMap("spidercave");
        }
        else if(world.getCurrentMapName().toLowerCase().equals("spidercave"))
        {
            player.setCoordinates(PLAYER_INITIAL_ROW,PLAYER_INITIAL_COL);
            
            world.setCurrentMap("town");
        }
    }
}
