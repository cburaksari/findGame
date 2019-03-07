package findGame;

import java.util.Scanner;

//---------- OYUNUN BASLANGIC KISMI ---------- 
// Kullaniciya tahmin edecegi ya da tahmin ettirecegi iki secenek verilecek 
public class App {
	
	//Okuyucumuz -> s
	static Scanner s = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		//Tercih secim sayisi -> selection_number
		int selection_number;
		
		
		// TERCIH KISMI
		System.out.println("------------------------------------------");
		System.out.println("------------OYUNA HOSGELDINIZ-------------");
		System.out.println("------------------------------------------");
		System.out.println("Tahmin Etmek icin 1'e Tahmin Ettirmek icin 2'e basin!");
		System.out.println("------------------------------------------");
		
		
		

		
		//Tahmin Etme ya da Tahmin Ettirme seceneklerinden birini secmedigi surece kullanicidan 'selection_number' istememiz gerek
		while (true) {
			selection_number = s.nextInt();
			
			if (selection_number == 1) { // TERCIH = TahminEt , TahminEt sinifina git
				TahminEt.TahminEt();
				break;
			} 
			else if (selection_number == 2) { // TERCIH = TahminEttir , TahminEttir sinifina git
				TahminEttir.TahminEttir();
				break;
			} 
			else { // TERCIH = yanlis , Tekrar dene!
				System.out.println("---Sanirim yanlis tusa bastin tekrar dene---");
			}
		}
	}

	

}
