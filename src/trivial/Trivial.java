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
        Scanner input=new Scanner(System.in);
        int a=input.nextInt();
        if(a==0)
        new HostPlayer("1");
       if(a==1)
        new ClientPlayer("2",input.next(),input.nextInt());
    }

}
