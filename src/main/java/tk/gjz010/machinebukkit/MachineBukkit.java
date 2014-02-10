/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.gjz010.machinebukkit;
import tk.gjz010.machinebukkit.listener.MachineListener;
import tk.gjz010.machinebukkit.api.Machine;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
/**
 *
 * @author Administrator
 */
public final class MachineBukkit extends JavaPlugin{
    public static Server s;
    public static Plugin plugin;
    
    @Override
    public void onEnable(){
        loggedmachines=new ArrayList<>();
        s=this.getServer();
        plugin=this;
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        s.getPluginManager().registerEvents(new MachineListener(), plugin);
        registerMachine(new TestMachine(),this);
        System.out.println("[MachineBukkit] "+java.util.ResourceBundle.getBundle("Bundle").getString("enable"));
    }
    @Override
    public void onDisable(){
        System.out.println("[MachineBukkit] "+java.util.ResourceBundle.getBundle("Bundle").getString("disable"));
    }
    private static ArrayList<Machine> loggedmachines;
    /**
     * Register machine as a listener.
     * @param m The machine listener.
     * @param p The base plugin.
     */
    public void registerMachine(Machine m,Plugin p){
        m.setPlugin(p);
        getServer().getPluginManager().registerEvents(m,p);
    }
    /**
     * Log machine.
     * @param m The machine instance.
     */
    public static int logMachine(Machine m){
        if(!loggedmachines.contains(m)){
        m.init();
        loggedmachines.add(m);//Avoid register for times.
        }
        return loggedmachines.indexOf(m);
    }
    /**
     * Unlog machine.
     * @param id The machine's ID.
     */
    public static void unlogMachine(int id){
        unlogMachine(loggedmachines.get(id));
    }
    /**
     * Unlog machine.
     * @param m The machine's instance.
     */
    public static void unlogMachine(Machine m){
        m.onDestoryed();
        loggedmachines.remove(m);
    }
    /**
     * Get logged machines.
     * @return The logged machines.
     */
    public static Machine[] getLoggedMachines(){
        Machine[] mlist = new Machine[loggedmachines.size()];
        return loggedmachines.toArray(mlist);
        
    }
    /**
     * Save logged machines.
     */
    public void saveLoggedMachines(){
        
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String commandName = command.getName().toLowerCase(); // Only Players
        if (!(sender instanceof Player)) {
            sender.sendMessage("/" + commandName + " can only be run from in game.");
            return true;
        }
        if (commandName.equals("gettestmachine")) {
            Player player = (Player) sender;
            return true;
        }
        return false;
    }
}
