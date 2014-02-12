/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.gjz010.machinebukkit;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import tk.gjz010.machinebukkit.listener.MachineListener;
import tk.gjz010.machinebukkit.api.Machine;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import static org.bukkit.craftbukkit.libs.jline.internal.Log.error;
import org.bukkit.entity.Player;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.UnknownDependencyException;
import org.bukkit.plugin.java.JavaPlugin;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
/**
 *
 * @author Administrator
 */
public final class MachineBukkit extends JavaPlugin{
    public static Server s;
    public static Plugin plugin;
    
    @Override
    public void onEnable(){
        if (!this.getServer().getPluginManager().isPluginEnabled("SQLibrary")) {
            try {
                info("Downloadind dependecy: SQLibrary");
                URL url = new URL("http://repo.dakanilabs.com/content/repositories/public/lib/PatPeter/SQLibrary/SQLibrary/maven-metadata.xml");
                URLConnection urlConnection = url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(in);
                final String version = doc.getElementsByTagName("latest").item(0).getTextContent();
                in.close();
                info(version);
                url = new URL("http://repo.dakanilabs.com/content/repositories/public/lib/PatPeter/SQLibrary/SQLibrary/" + version + "/SQLibrary-" + version + ".jar");
                String path = this.getDataFolder().getParentFile().getAbsoluteFile() + "/SQLibrary-" + version + ".jar";
                info(path);
                ReadableByteChannel rbc = Channels.newChannel(url.openStream());
                FileOutputStream fos = new FileOutputStream(path);
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                info("Finished downloading SQLibrary-" + version + ". Loading dependecy");
                Plugin sql=this.getServer().getPluginManager().loadPlugin(new File(path));
                this.getServer().getPluginManager().enablePlugin(sql);
                if(this.getServer().getPluginManager().isPluginEnabled("SQLibrary")){
                info("Loaded SQLibrary");
                }
            } catch (MalformedURLException ex) {
                error("Failed to download dependency", ex);
            } catch (IOException ex) {
                error("Failed to download dependency", ex);
            } catch (ParserConfigurationException ex) {
                error("Failed to download dependency", ex);
            } catch (SAXException ex) {
                error("Failed to download dependency", ex);
            } catch (InvalidPluginException ex) {
                error("Failed to load dependency", ex);
            } catch (InvalidDescriptionException ex) {
                error("Failed to load dependency", ex);
            } catch (UnknownDependencyException ex) {
                error("Failed to load dependency", ex);
            }
        }
        loggedmachines=new ArrayList<>();
        s=this.getServer();
        plugin=this;
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        s.getPluginManager().registerEvents(new MachineListener(), plugin);
        registerMachine(new TestMachine(),this);
        System.out.println("[MachineBukkit] "+java.util.ResourceBundle.getBundle("lang").getString("enable"));

    }
    @Override
    public void onDisable(){
        System.out.println("[MachineBukkit] "+java.util.ResourceBundle.getBundle("lang").getString("disable"));
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

    private void info(String downloadind_dependecy_SQLibrary) {
        Logger.getLogger("Minecraft").info(downloadind_dependecy_SQLibrary);
    }
}
