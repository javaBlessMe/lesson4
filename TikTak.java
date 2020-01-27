import java.util.Random;
import java.util.Scanner;

public class TikTak {
    private static char[][] field;
    public static void main(String[] args) {

        Scanner dataFromUser = new Scanner(System.in);
        System.out.println("Введите размер массива");
        int arraySize = dataFromUser.nextInt();
        field = new char[arraySize][arraySize];
        for (int i = 0; i <arraySize ; i++) {

            for (int j = 0; j <arraySize ; j++) {
                field[i][j] = 46;
            }
        }
         int times=0; //кол-во ходов
        while(true){
            System.out.println("Введите координату Х");
            int coordinateX = dataFromUser.nextInt()-1;
            System.out.println("Ввведите координату У");
            int coordinateY = dataFromUser.nextInt()-1;

            if(!checkCoordinate(coordinateX,coordinateY)){
                System.out.println("Ошибка! ячейка занята!");
                continue;
            }
            else{
                times++;
                field[coordinateY][coordinateX] = 88;
            }

            //если кол-во ходов равно размеру поля, значит больше нет вариантов
            if(times==field.length*field.length) {
                System.out.println("ничья...");
                break;
            }

            System.out.println("Ход компьютера...");
            computerPlay();
            times++;

            printField();

            if(checkResult('X')) {
                System.out.println("Вы выиграли!");
                break;
            }

            if(checkResult('O')) {
                System.out.println("проиграли!");
                break;
            }



        }

    }

    private static boolean checkCoordinate(int x, int y){
        return field[y][x]==46;

    }

    private static boolean checkResult(char X){
        int sum=0,sum2=0;
        //Проверяем, что диагонали заполнены
        for (int i = 0; i <field.length ; i++) {
            if(field[i][i]==X) {
                sum++;//Если в диагонали нужный нам символ, значит увеличиваем на 1
                //Если сумма будет равна размерности диагонали, значит победа
            }
            if (sum==field.length) return true;


            if (X==field[i][field.length-i-1]){
                sum2++;
            }
            if (sum2==field.length) return true;



        }

        //Проверяем, что  линии заполнены
        sum=0;
        sum2=0;
        for (int i = 0; i <field.length ; i++) {

            for (int j = 0; j <field.length ; j++) {
                if(field[i][j]==X){
                    sum++;
                }

                if (field[j][i]==X){
                    sum2++;
                }
            }
            if(sum==field.length || sum2== field.length ) return true;

            sum=0;
            sum2=0;
        }

        //Проверяем, что вертикальные линии заполнены
        return false;
    }
    private static void printField(){
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j <field.length ; j++) {
                System.out.print(field[i][j]+" ");
            }
            System.out.println("\n");
        }
    }
    private static void computerPlay(){
        Random rand = new Random();

        int x,y;
        do {
            x=rand.nextInt(field.length);
            y=rand.nextInt(field.length);
        }
        while (!checkCoordinate(x,y));
        field[y][x]=79;
    }
}

