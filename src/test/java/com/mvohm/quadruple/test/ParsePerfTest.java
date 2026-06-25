package com.mvohm.quadruple.test;

import com.mvohm.quadruple.Quadruple;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParsePerfTest {

    private static String PI = "3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679" ;

    public void runner(int times, int loops, Runnable r){
        List<Long> data = new ArrayList<>();
        for ( int i=0; i < times; i++ ){
            final long startTime = System.currentTimeMillis();
            for ( int j=0; j< loops; j++  ){
                r.run();
            }
            final long delta = System.currentTimeMillis()  - startTime;
            data.add(delta);
        }
        Collections.sort(data);
        String funcName = new Throwable().getStackTrace()[1].getMethodName() ;
        System.out.println( funcName +  " - 90% is : " + data.get( (int) (times * 0.9) ) );
    }

    @Test
    public void bigDecimalParsing(){
        runner( 20, 100000, ()-> {
            BigDecimal bd = new BigDecimal(PI);
        } )  ;
    }

    @Test
    public void quadrupleParsing(){
        runner( 20, 100000, ()-> {
            Quadruple bd = new Quadruple(PI);
        } )  ;
    }
}
