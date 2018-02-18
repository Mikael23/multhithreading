public class Basics {

    static long var; //64 bit, comp: 32 bit

    public static void main(String[] args) {
        var = 10; //thread 1
        System.out.println(var); //thread 2
        System.out.println(var); //thread 2
        //sequential consistency
    }

}
