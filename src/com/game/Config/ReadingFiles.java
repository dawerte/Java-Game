package com.game.Config;

import java.io.File;
/**
 * Class manages reading files (gives path)
 */
public class ReadingFiles {
    /**
     * Defines path to the file
     */
    public String path;
    private File file;

    public ReadingFiles(){
        path= System.getProperty("user.dir")+File.separator+"Config";
    }

    /**
     * Finds path to the file
     * @param subfolder
     */
    public ReadingFiles(String subfolder){
        path=System.getProperty("user.dir")+ File.separator + subfolder;
    }

    /**
     * Make object where path is parameter
     * Return this object to variable
     * @param file
     * @return
     */
    public File MaKeFile(String file){
        this.file= new File(path+ File.separator + file);
        return this.file;
    }

    public String getPath() {
        return path;
    }
}
