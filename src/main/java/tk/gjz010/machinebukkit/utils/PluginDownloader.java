/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tk.gjz010.machinebukkit.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import org.bukkit.Bukkit;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.UnknownDependencyException;

/**
 *
 * @author Administrator
 */
public class PluginDownloader {
        public static boolean downloadPlugin(Plugin plugin, String id) {
        InputStreamReader in = null;
        try {
            URL url = new URL("https://www.google.com/?projectIds=" + id);
            //URL url = new URL("https://api.curseforge.com/servermods/files?projectIds=" + id);
            URLConnection urlConnection = (HttpsURLConnection)url.openConnection();
            in = new InputStreamReader(urlConnection.getInputStream());
            int numCharsRead;
            char[] charArray = new char[1024];
            StringBuilder sb = new StringBuilder();
            while ((numCharsRead = in.read(charArray)) > 0) {
                sb.append(charArray, 0, numCharsRead);
            }
            String result = sb.toString();
            result = result.replace("\\/", "/").replaceAll(".*\"downloadUrl\":\"", "").split("\",\"")[0];
            String[] split = result.split("/");
            url = new URL(result);
            final String path = plugin.getDataFolder().getParentFile().getAbsoluteFile() + "/" + split[split.length-1];
            ReadableByteChannel rbc = Channels.newChannel(url.openStream());
            FileOutputStream fos = new FileOutputStream(path);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            Bukkit.getServer().getLogger().log(Level.INFO, "Finished downloading " + split[split.length-1] + ". Loading dependecy");
            Bukkit.getServer().getPluginManager().loadPlugin(new File(path));
            return true;
        } catch (MalformedURLException ex) {
            Logger.getLogger(PluginDownloader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PluginDownloader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidPluginException ex) {
            Logger.getLogger(PluginDownloader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidDescriptionException ex) {
            Logger.getLogger(PluginDownloader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownDependencyException ex) {
            Logger.getLogger(PluginDownloader.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(PluginDownloader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
}
