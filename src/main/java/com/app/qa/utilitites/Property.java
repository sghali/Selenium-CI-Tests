package com.app.qa.utilitites;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class Property {

    static Properties props=new Properties();
    String strFileName;
    String strValue;
    
    /*
     * @method - to retrieve the property
     * @method strKey
     */
    public String getProperty(String strKey) {
                    try{
                                    File f = new File(strFileName);
                                    if(f.exists()){
                                                    FileInputStream in=new FileInputStream(f);
                                                    props.load(in);
                                                    strValue=props.getProperty(strKey);
                                                    in.close();
                                    }
                                    else
                                                    System.out.println("File not found!");
                    }
                    catch (Exception e) {
                                    System.out.println(e);
                    }
                    return strValue;
    }
    
    /*
     * @method - to set the property
     * @param - strKey,strValue
     */
    public void setProperty(String strKey,String strValue) throws Throwable {
                    try{
                                    File f = new File(strFileName);
                                    if(f.exists()){
                                                    FileInputStream in=new FileInputStream(f);
                                                    props.load(in);
                                                    props.setProperty(strKey, strValue);
                                                    props.store(new FileOutputStream(strFileName),null);
                                                    in.close();
                                    }
                                    else{
                                                    System.out.println("File not found!");
                                    }
                    }
                    catch (Exception e) {
                                    System.out.println(e);
                    }
    }
    
    /*
     * @method - to remove  the property
     * @param - strKey
     */
    public void removeProperty(String strKey){
                    try{
                                    File f = new File(strFileName);
                                    if(f.exists()){
                                                    FileInputStream in=new FileInputStream(f);
                                                    props.load(in);
                                                    props.remove(strKey);
                                                    props.store(new FileOutputStream(strFileName),null);
                                                    in.close();
                                    }
                                    else
                                                    System.out.println("File not found!");
                    }
                    catch (Exception e) {
                                    System.out.println(e);
                    }

    }
    public Property(String strFileName){
                    this.strFileName=strFileName;
    }
  
}

