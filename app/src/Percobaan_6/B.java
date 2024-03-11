package Percobaan_6;


class A {
    String var_a = "Variable A";
    String var_b = "Variable B";
    String var_c = "Variable C";
    String var_d = "Variable D";

    A(){
    System.out.println("Konstruktor A dijalankan");
}
}

class B extends A {
    B(){
        System.out.println("Konstruktor B dijalankan");
        var_a = "Var_a dari class B";
        var_b = "Var_a dari class B";
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Objek A dibuat");
        A aa = new A();
        System.out.println("Menampilkan nama variabel dari object aa");
        System.out.println(aa.var_a);
        System.out.println(aa.var_b);
        System.out.println(aa.var_c);
        System.out.println(aa.var_d);
        System.out.println("");

        System.out.println("Objek B dibuat");
        B bb = new B();
        System.out.println("Menampilkan nama variabel dari object bb");
        System.out.println(bb.var_a);
        System.out.println(bb.var_b);
        System.out.println(bb.var_c);
        System.out.println(bb.var_d);
        System.out.println("");

    }
}