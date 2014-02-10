/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.gjz010.machinebukkit.api;

import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.plugin.Plugin;

/**
 * The super class of the machines with an inventory.
 * @author gjz010
 */
public abstract class ContainerMachine extends BaseMachine implements InventoryHolder{
    private Inventory i;

    /**
     * @return the inventory
     */
    @Override
    public Inventory getInventory() {
        return i;
    }

    /**
     * @param inventory the inventory to set
     */
    public void setInventory(Inventory inventory) {
        this.i = inventory;
    }
}
