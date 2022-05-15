public class MetodyStatyczne {

    public static void main(String[] args) {


        OperacjeMatematyczne.dodaj(4, 5);
        OperacjeMatematyczne op1=new OperacjeMatematyczne();
        OperacjeMatematyczne op2=new OperacjeMatematyczne();

        System.out.println(OperacjeMatematyczne.CounterOperacji);
        System.out.println(op1.CounterOperacji);
        System.out.println(op2.CounterOperacji);
        op2.CounterOperacji= 10;

        System.out.println(OperacjeMatematyczne.CounterOperacji);


    }
}

class OperacjeMatematyczne {
    public static int CounterOperacji=0;

    public static int dodaj(int a, int b) {
        CounterOperacji++;
        return a + b;
    }

    public static int pomnoz(int a, int b) {
        return a * b;
    }
}

