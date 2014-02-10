package tk.gjz010.machinebukkit.api;

import java.io.File;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import tk.gjz010.machinebukkit.events.MachineCreateEvent;

/**
 * The super interface of all the machines.
 * @author gjz010
 */
public interface Machine extends Listener{
    /**
     *Called when the machine is created.
     */
    public void init();
    /**
     *Called when the machine is working.
     */
    public void doWorking();
    /**
     *Tells the server how to deal with the item from the hopper.Not implemented yet.
     * @param i The item to the Inventory.
     */
    public void setToHopper(ItemStack i);
    /**
     *Tells the server what to get from the inventory to hopper.Not implemented yet.
     * @return The item from the Inventory.
     */
    public ItemStack getFromHopper();
    /**
     *Called when the machine is destoryed.
     */
    public void onDestoryed();
    /**
     *Tells how to build the machine.
     */
    public void getRecipe();
    /**
     *Returns the type of the machine.It's written on the second line of the sign.
     * @return The type of the machine.
     */
    public String getType();
    /**
     *Returns the base of the machine.
     * @return The base block of the machine.
     */
    public Block getBaseBlock();
    /**
     *Returns the sign of the machine.
     * @return The sign of the machine.
     */
    public Block getSignBlock();
    /**
     *Returns material of the base block.
     * @return The material of the base block.
     */
    public Material getBaseMaterial();
    /**
     * Returns a instance of the machine.It creates a instance and call setBaseAndSign().
     * @param base The base block of the machine.
     * @param sign The sign of the machine.
     * @return A new instance of the registered machine.
     * @see #setBaseAndSign(Block base,Block sign)
     */
    public Machine getInstance(Block base,Block sign);
    /**
     * Gets the base Plugin.
     * @return The machine's plugin.
     */
    public Plugin getPlugin();
    /**
     * Sets the machine location.
     * @param base The base block of the machine.
     * @param sign The sign of the machine.
     * @see #getInstance(Block base,Block sign)
     */
    public void setBaseAndSign(Block base,Block sign);
    /**
     * Sets the base Plugin.
     */
    public void setPlugin(Plugin plugin);
    /**
     * Saves the machine state to a file.
     */
    public void save(File file);
    /**
     * Loads the machine state from a file.
     */
    public void load(File file);
    /**
     * Called when MachineListener calls a MachineCreateEvent.
     * @param e Machine create event.Contains the block pair and the machine type;
     */
    @EventHandler
    public void onMachineCreated(MachineCreateEvent e);
    /**
     * Judge if the machine can be broken by one event.
     */
    public boolean isBreakable(Event e);
}
