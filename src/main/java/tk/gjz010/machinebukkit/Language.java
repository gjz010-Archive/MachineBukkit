/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.gjz010.machinebukkit;

/**
 *
 * @author Administrator
 */
public enum Language {
    ONENABLED,
    ONDISABLED,
    ONMACHINECREATED,
    ONMACHINEDESTROYED,
    ONPLUGINHOOKED,
    ONMACHINESLOADED,
    MACHINENOTEXIST,
    NOPERMISSION;
    public static String getByLocale(String locale,Language str,String[] args){
        return str.toString();
        
    }
}
