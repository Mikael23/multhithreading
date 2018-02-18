public class SynchronizedCreateObject {

    static MyObject obj = null;

    public static void main(String[] args) {
        obj = new MyObject();

        new Thread(() -> {
            MyObject localObj;
            while ((localObj = obj) == null);
            System.out.println(localObj.value); //0, 10
        }).start();

        new Thread(() -> {
            obj = new MyObject();
            System.out.println(obj.value); //10
        }).start();
    }

    static class MyObject {
        final int value;

        public MyObject() {
            value = 10;
        }
    }

    //constructor hb volatile write
    //volatile read hb field read
    //volatile write hb volatile read
    //=> constructor hb field read

    //initialization hb constructor hb usage - NOT
    //init hb constr
    //int hb usage

}
