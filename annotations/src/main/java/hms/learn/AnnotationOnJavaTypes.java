package hms.learn;


import hms.learn.custom.Critical;
import hms.learn.custom.Mandatory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shehan on 6/3/15.
 */
public class AnnotationOnJavaTypes implements @Mandatory Serializable {

    public static void main(String[] args) {

        try {
            String @Mandatory [] values = args;

            @Mandatory List<@Mandatory String> arr = new ArrayList<String>();

            String name = new @Mandatory String("shehan");

            String no = (@Mandatory String) "1234";

            throw new RuntimeException();
        } catch (@Critical Exception e) {
            e.printStackTrace();
        }
    }

}
