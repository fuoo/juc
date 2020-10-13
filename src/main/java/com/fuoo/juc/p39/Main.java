package com.fuoo.juc.p39;

/**
 * @author fuoo
 * @description
 * @date 2020/10/13 14:01
 */
public class Main {
    public static void main(String[] args) throws Exception{
        Ticket ticket = new Ticket();


        // 线程状态 new、runnable、blocked、waiting、time_waiting、terminated
        // LamdbaExpress
        new Thread(()-> {
            for (int i = 0; i < 12; i++) {
                ticket.buyTicket2();
            }
        }).start();

        new Thread(()-> {
            for (int i = 0; i < 12; i++) {
                ticket.buyTicket2();
            }
        }).start();

        new Thread(()-> {
            for (int i = 0; i < 12; i++) {
                ticket.buyTicket2();
            }
        }).start();
    }
}
