package Percobaan_2;

class Pegawai {
  public String nama;
  public double gaji;
}

public class Manajer extends Pegawai{
    public String departemen;
  
    public void isiData(String n, String d){
      nama = n;
      departemen = d;
    }
  }