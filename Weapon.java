
/**
 * Player's weapon.
 * 
 * @authors Andrei Cruceru
 * @version 1.0.12
 */
public class Weapon extends Item
{
    
    /**
     * Initialising the weapon.
     */
    public Weapon(String name, int baseStats, int enchantLevel, int bonusPerLevel, int cost)
    {
        super(name, baseStats, enchantLevel, bonusPerLevel, cost);
        
    }
    
    /**
     * Print the weapon's stats.
     */
    public void print()
    {
        System.out.println(displayName + " + " + displayLevel + "\tAttack: " + enchantStats);
    }
    
}
