package tk.gjz010.machinebukkit.events;

import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
/**
 * Called on a machine is created.
 * @author gjz010
 */
public class MachineCreateEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private String type;
    private Block sign;
    private Block base;
 
    public MachineCreateEvent(String type,Block base,Block sign) {
        this.sign=sign;
        this.base=base;
        this.type=type;
    }

    /**
     * The type of the machine.It's often written on the second line of the sign.
     * @return The type of the machine.
     * @see tk.gjz010.machinebukkit.api.Machine#getType() 
     */
    public String getType() {
        return type;
    }
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
     
    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * The sign block of the machine.
     * @return The sign block of the machine.
     * @see tk.gjz010.machinebukkit.api.Machine#getSignBlock() 
     */
    public Block getSign() {
        return sign;
    }

    /**
     * Sets the sign block of the machine.
     * @param  sign The sign block of the machine.
     * @see tk.gjz010.machinebukkit.api.Machine#setBaseAndSign(org.bukkit.block.Block, org.bukkit.block.Block)
     */
    public void setSign(Block sign) {
        this.sign = sign;
    }

    /**
     * The base block of the machine.
     * @return The base block of the machine.
     * @see tk.gjz010.machinebukkit.api.Machine#getBaseBlock() 
     */
    public Block getBase() {
        return base;
    }

    /**
     * Sets the base block of the machine.
     * @param  base The sign block of the machine.
     * @see tk.gjz010.machinebukkit.api.Machine#setBaseAndSign(org.bukkit.block.Block, org.bukkit.block.Block)
     */
    public void setBase(Block base) {
        this.base = base;
    }
}