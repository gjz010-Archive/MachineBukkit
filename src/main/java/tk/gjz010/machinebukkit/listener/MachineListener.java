/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.gjz010.machinebukkit.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.*;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.material.Attachable;
import tk.gjz010.machinebukkit.MachineBukkit;
import tk.gjz010.machinebukkit.api.Machine;
import tk.gjz010.machinebukkit.events.MachineCreateEvent;

/**
 * The listener that listens to machine create and break event.
 * @author gjz010
 */
public class MachineListener implements Listener{
    @EventHandler
    public void onSignChange(final SignChangeEvent event){
        Sign s=(Sign) event.getBlock().getState();
        if(s.getType().equals(Material.WALL_SIGN) & event.getLine(0).equalsIgnoreCase("[Machine]")){
        Attachable data = (Attachable) s.getData();
        Block b=event.getBlock().getRelative(data.getAttachedFace());
        for(Machine m:MachineBukkit.getLoggedMachines()){
            if(m.getBaseBlock().equals(b)) {return;}
        }
          MachineCreateEvent mce=new MachineCreateEvent(event.getLine(1),b,event.getBlock());
          Bukkit.getServer().getPluginManager().callEvent(mce);
        for(Machine m:MachineBukkit.getLoggedMachines()){
            if(m.getBaseBlock().equals(b)) {
                event.setLine(2,ChatColor.RED+"Building");
                 event.getPlayer().sendMessage("[MachineBukkit] Machine Created!");
            }
        }
        }
    }
    @EventHandler
    public void onBlockBreak(final BlockBreakEvent event){
        for(Machine m:MachineBukkit.getLoggedMachines()){
            if(event.getBlock().equals(m.getBaseBlock()) | event.getBlock().equals(m.getSignBlock())){
                if(m.isBreakable(event)){
                event.getPlayer().sendMessage("[MachineBukkit] Machine Destoryed!");
                MachineBukkit.unlogMachine(m);
                }
                else{
                    event.getPlayer().sendMessage("[MachineBukkit] You can't destory it!");
                    event.setCancelled(true);
                }
            }
        }
    }
    @EventHandler
    public void onBreak(final BlockBreakEvent event){
        for(Machine m:MachineBukkit.getLoggedMachines()){
            if(event.getBlock().equals(m.getBaseBlock()) | event.getBlock().equals(m.getSignBlock())){
                if(m.isBreakable(event)){
                event.getPlayer().sendMessage("[MachineBukkit] Machine Destoryed!");
                MachineBukkit.unlogMachine(m);
                }
                else{
                    event.getPlayer().sendMessage("[MachineBukkit] You can't destory it!");
                    event.setCancelled(true);
                }
            }
        }
    }
    @EventHandler
    public void onExplode(final EntityExplodeEvent event){
        for(Machine m:MachineBukkit.getLoggedMachines()){
            if(event.blockList().contains(m.getBaseBlock()) | event.blockList().contains(m.getSignBlock())){
                if(!m.isBreakable(event)){
                event.blockList().remove(m.getBaseBlock());
                event.blockList().remove(m.getSignBlock());
                }
                else{
                    MachineBukkit.unlogMachine(m);
                }
        }
      }
    }
}
