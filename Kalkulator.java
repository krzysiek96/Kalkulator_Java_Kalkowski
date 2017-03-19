import com.sun.deploy.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Kalkulator
{
    private String dzialanie;
    private double wynik;

    public Kalkulator()
    {
        this.wynik=0;
    }

    public void oblicz()
    {
        StringTokenizer tokenizer = new StringTokenizer(this.dzialanie,"+-/*",true);
        String token="";
        String znak="";
        double temp=0;
        boolean pierwsza = true;

        while(tokenizer.hasMoreTokens())
        {
            token = tokenizer.nextToken();
            if(!token.equals("+") && !token.equals("-") && !token.equals("*") && !token.equals("/"))
            {
                temp = Double.parseDouble(token);
                if(znak.equals("+"))
                    this.wynik+=temp;
                else if(znak.equals("-"))
                    this.wynik-=temp;
                else if(znak.equals("*"))
                    this.wynik*=temp;
                else if(znak.equals("/"))
                    this.wynik/=temp;
            }
            else
            {
                znak = token;
            }
            if(pierwsza)
            {
                this.wynik=temp;
                pierwsza=false;
            }
        }
    }

    public void wczytajZKonsoli()
    {
        Scanner in = new Scanner(System.in);

        while(true)
        {
            System.out.println("Podaj dzialanie: ");
            this.dzialanie = in.nextLine();
            if(this.dzialanie.equals("koniec"))
                break;
            else
            {
                this.wynik=0;
                oblicz();
            }
            System.out.println(this.dzialanie+"="+this.wynik);
        }

    }

    public void wczytajZPliku() throws FileNotFoundException
    {
        File plik = new File("dzialania.txt");
        Scanner in = new Scanner(plik);

        while(true)
        {
            this.dzialanie = in.nextLine();
            if(this.dzialanie.equals("koniec"))
                break;
            else
            {
                this.wynik=0;
                oblicz();
            }

            System.out.println(this.dzialanie+"="+this.wynik);
        }
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        Kalkulator calc = new Kalkulator();
        int wybor=0;

        System.out.println("KALKULATOR.");
        while(true)
        {
            System.out.println("Czytanie z konsoli - wcisnij 1");
            System.out.println("Czytanie z pliku - wcisnij 2");
            System.out.println("Koniec programu - wcisnij 3");
            wybor = in.nextInt();
            if(wybor==1)
            {
                calc.wczytajZKonsoli();
            }
            else if(wybor==2)
            {
                try
                {
                    calc.wczytajZPliku();
                }
                catch (FileNotFoundException e)
                {
                    System.out.println("Problem z plikiem");
                }
            }
            else if(wybor==3)
                break;
            else
                System.out.println("Nieprawidlowy znak!!!");
        }
    }
}