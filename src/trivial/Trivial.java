/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trivial;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Félix Dupont
 */
public class Trivial {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        HostPlayer hPlayer=new HostPlayer("1");
        System.out.println(hPlayer.getIp());
        ClientPlayer cPlayer1=new ClientPlayer("2","localhost",7000);
        ClientPlayer cPlayer2=new ClientPlayer("3","localhost",7000);
        ClientPlayer cPlayer3=new ClientPlayer("4","localhost",7000);
    }

}
