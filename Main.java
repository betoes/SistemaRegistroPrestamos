/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fechasiguiente;

import java.util.InputMismatchException;
import java.util.Scanner;



/**
 *
 * @author dell
 */
public class Main {

    static final int AUMENTO = 12;
    static final int NUMMESES = 12;
    public static void main(String[] args) {
        int meses[]={0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    
        Scanner sc = new Scanner(System.in);
        
        String errores = "";
        String resultado = "";
        int mes = 0, dia = 0, aaaa = 0;
        String op = "y";
        boolean rango = true;

        while("y".equals(op)){

            System.out.println("Por favor ingresa el mes, dia y año" + "\n");
            try {
                System.out.println("Mes(mm):" + "\n");
                mes = sc.nextInt();
                
                System.out.println("Dia(dd):" + "\n");
                dia = sc.nextInt();
                
                System.out.println("Anio(aaaa):" + "\n");
                aaaa = sc.nextInt();
            } catch (InputMismatchException ex) {
                errores += "Por favor solo ingresa numeros enteros";
            }
            
            
            
            if(aaaa % 4 == 0 && (aaaa % 100 != 0 || aaaa % 400 == 0)){
                meses[2] = 29;
            }
            
            if(mes < 1 || mes > 12){
                errores += "El valor del mes no esta en el rango del 1 al 12" + "\n";
                
            }else{
                    if(dia < 1 || dia > 31){
                    errores += "El valor del mes no esta en el rango del 1 al 31" + "\n";
                    }else if(dia > meses[mes] ){
                        errores += "Fecha de entrada invalida";
                    }else if(aaaa < 1812 || aaaa > 2020){
                        errores += "El valor del año no esta en el rango del 1812 al 2020" + "\n";
                        rango = false;
                    }
            }
                    
            if(!errores.isEmpty()){
                System.out.println(errores + "\n");
            }else{
                if(meses[mes] >= (dia + AUMENTO)){
                    resultado = mes + " - " + ++dia + " - " + aaaa;
                }else if(mes == NUMMESES && meses[mes] < (dia + AUMENTO)){
                    resultado = "1 - 1 - " + ++aaaa; 
                }else if(meses[mes] < (dia + AUMENTO)){
                    resultado = ++mes + " - 1 - " + aaaa; 
                } 
                
                System.out.println(resultado);
            }
                
            
            
            

            System.out.println("Desea continuar y/n");
            op = sc.nextLine();
            op = sc.nextLine();
            
            errores = "";
            resultado = "";
        }
    }
    
}
