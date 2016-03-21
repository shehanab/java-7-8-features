package com.learn;

/**
 * Created by Shehan on 6/3/15.
 */
public class ComboDrive implements CdDrive, DvdDrive {

    public void write(String s) {
        CdDrive.super.write(s);
        DvdDrive.super.write(s);
        System.out.println("com.learn.ComboDrive : " + s);
    }

}
