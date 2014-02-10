/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.gjz010.machinebukkit.api;

import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import tk.gjz010.machinebukkit.MachineBukkit;
import tk.gjz010.machinebukkit.events.MachineCreateEvent;
/**
 * The base class of all the default machines.
 * @author gjz010
 */
public abstract class BaseMachine implements Machine{
    private Plugin plugin;//The plugin instance.
    private Block baseblock; //The block that machine based on.
    private Block signblock;//The sign block.
    //Constructor.
    public BaseMachine(){
    }

    /**
     * @return the plugin
     */
    @Override
    public Plugin getPlugin() {
        return plugin;
    }

    /**
     * @return the baseblock
     */
    @Override
    public Block getBaseBlock() {
        return baseblock;
    }

    /**
     * @return the signblock
     */
    @Override
    public Block getSignBlock() {
        return signblock;
    }
    @Override
    public Machine getInstance(Block base,Block sign){
        Machine ins=null;
        try{
        ins=this.getClass().newInstance();
        ins.setPlugin(getPlugin());
        ins.setBaseAndSign(base,sign);
        }catch(InstantiationException | IllegalAccessException e){
            e.printStackTrace();
        }
        return ins;
    }
    @Override
    public void setPlugin(Plugin plugin){
        this.plugin=plugin;
    }
    @Override
    public void setBaseAndSign(Block base,Block sign){
        this.baseblock=base;
        this.signblock=sign;
    }
    @EventHandler
    @Override
    public void onMachineCreated(MachineCreateEvent e){
        if(e.getType().equalsIgnoreCase(this.getType())&e.getBase().getType().equals(getBaseMaterial())){
            MachineBukkit.logMachine(getInstance(e.getBase(),e.getSign()));
        }
    }
}
