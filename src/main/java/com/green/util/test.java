package com.green.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

public class test {
    public static void main(String[] args) {
        JavaMailer javaMailer = new JavaMailer();

        javaMailer.sendMail("Daily Report", "");

//        ExecutorService es = Executors.newCachedThreadPool();
//        TreadTest test = new TreadTest();
//        Future<Integer> integerFuture = es.submit(test);
//        int
//        try {
//            int result = integerFuture.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            System.out.println(e.getCause());
//        } finally {
//            es.shutdown();
//        }

    }


}

class TreadTest implements Callable {


    @Override
    public Object call() throws Exception {
        int result = 1;
        for (int i = 1; i<15; i++){
            result = result * i;
        }
        return result;
    }
}