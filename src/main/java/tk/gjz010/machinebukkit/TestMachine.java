/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.gjz010.machinebukkit;
import java.io.File;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.ItemStack;
import tk.gjz010.machinebukkit.api.*;
/**
 *
 * @author Administrator
 */
public class TestMachine extends BaseMachine{

    @Override
    public void doWorking() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setToHopper(ItemStack i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ItemStack getFromHopper() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onDestoryed() {
    }


    @Override
    public String getType() {
        return "test";
    }

    @Override
    public void getRecipe() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void save(File file) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void load(File file) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Material getBaseMaterial() {
        return Material.IRON_BLOCK;
    }

    @Override
    public void init() {
        Sign s=(Sign)this.getSignBlock().getState();
        s.setLine(2,"Working");
        s.update();
    }
    @Override
    public boolean isBreakable(Event e){
        if(e instanceof BlockBreakEvent){
        if(((BlockBreakEvent)e).getPlayer() == null){
            return false;
          }
        }
        if(e instanceof EntityExplodeEvent){
        return false;
        }
        return true;
    }
}
