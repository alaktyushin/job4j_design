package ru.job4j.hash;

public class AutoNumber {

    private String number;
    private short regionCode;
    private Colour colour;

    private enum Colour {
        WHITE,
        YELLOW, RED, BLUE,
        BLACK
    }

    public AutoNumber(String number, short regionCode, Colour color) {
        this.number = number;
        this.regionCode = regionCode;
        this.colour = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AutoNumber that = (AutoNumber) o;
        return regionCode == that.regionCode && number.equals(that.number) && colour == that.colour;
    }

    @Override
    public int hashCode() {
        return number.chars().sum() * regionCode * colour.toString().chars().sum();
    }

    //@Override
    //public int hashCode() {
    //    return Objects.hash(number, regionCode, colour);
    //}

    public static void main(String[] args) {
        AutoNumber number1 = new AutoNumber("x555mk", (short) 77, Colour.WHITE);
        AutoNumber number2 = new AutoNumber("x5555", (short) 77, Colour.BLUE);
        AutoNumber number3 = new AutoNumber("x555mm", (short) 77, Colour.YELLOW);
        AutoNumber number4 = new AutoNumber("x5555", (short) 77, Colour.BLACK);
        AutoNumber number5 = new AutoNumber("x5555", (short) 77, Colour.BLUE);
        System.out.println("hashCode: " + number1.hashCode());
        System.out.println("hashCode: " + number2.hashCode());
        System.out.println("hashCode: " + number3.hashCode());
        System.out.println("hashCode: " + number4.hashCode());
        System.out.println("hashCode: " + number5.hashCode());
        System.out.println(number1.equals(number2));
        System.out.println(number2.equals(number4));
        System.out.println(number4.equals(number5));
        System.out.println(number2.equals(number5));
        System.out.println(number2 == number5);
    }
}
