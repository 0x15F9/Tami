import java.util.Random;

class rdice {
    public static void main(String[] args) {
        Random dices = new Random();

        int dice1 = dices.nextInt(6);
        int dice2 = dices.nextInt(6);

        dice1 += 1;
        dice2 += 1;

        System.out.println("Dice 1 gives : "+dice1);
        System.out.println("Dice 2 gives : "+dice2);
    }
}
