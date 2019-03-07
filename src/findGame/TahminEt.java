package findGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

//---------- OYUNUN TERCIH KISMI ---------- 
//Kullanicidan yazilimin tuttugu sayiyi tahmin etmesi istenecek, her adimda ipucu verilecek, kac adimda buldugu en son ekrana yazilacak 
public class TahminEt {
	
	//Okuyucumuz -> s
	static Scanner s = new Scanner(System.in);

	//TAHMIN KISMI
	public static void TahminEt() {

		int number = 0; // Yazilimin tutacagi random sayi -> number
		int girilen_sayi; // Kullanicinin girecegi sayi -> girilen_sayi
		
		int dogru_basamak, yanlis_basamak; // Kullanicinin basamagini dogru tahmin ettiği sayilarin sayaci -> dogru_basamak , Kullanicinin basamagini yanlis tahmin ettiği fakat tutulan sayinin icerisinde bulunan sayilarin sayaci -> yanlis_basamak
		List<Integer> numbers = new ArrayList(); // 'number' in icerisindeki sayilarin tutuldugu dizi ---- Ornegin -> number: 1708 ise numbers = {1,7,0,8}
		number = randomNumberGenerator(number);
		numbers = Ayristir(number); // Ayristir fonksiyonu ile list elde edilir
		int adim=0; // Tahmin sayisi -> adim
		
		//System.out.println(number); // TEST icin tutulan sayinin ekrana yazilmasi
		
		//Dogru sayi bulunana dek dongu devam edecek
		while (true) {
			adim++; //her seferde 1 adim artirir
			dogru_basamak = 0; // her tahminde dogru_basamak i sifirla
			yanlis_basamak = 0; // her tahminde yanlis_basamak i sifirla
			System.out.println("--Tahmin Et--");
			girilen_sayi = s.nextInt(); // girilen_sayi -> KULLANICI TAHMINI
			List<Integer> girilen_sayilar = new ArrayList(); //
			girilen_sayilar = Ayristir(girilen_sayi); // 'girilen_sayi' nin icerisindeki sayilarin tutuldugu dizi ---- Ornegin -> girilen_sayi: 1708 ise girilen_sayilar = {1,7,0,8}

			// KONTROL KISMI
			// Kullanicinin girdigi sayi ile yazilimin tuttugu sayinin kac tane basamagi eslesiyorsa dogru_basamak o kadar artirilir
			// Kullanicinin girdigi sayi ile yazilimin tuttugu sayilar eslesmiyorsa yazilimin tuttugu sayilarin icerisinde Kullanicinin girdigi sayilar varmi bakilir ve yanlis_basamak o kadar artirilir
			for (int i = 0; i < 4; i++) {
				if (girilen_sayilar.get(i) == numbers.get(i)) // Basamaklar ayni dogru_basamak i 1 artir
					dogru_basamak++;
				else { // Basamaklar ayni degil --- girilen sayi da girilmesi istenen sayilardaki sayilar var mi kontrol et varsa yanlis_basamak i 1 artir
					for (int j = 0; j < 4; j++) {
						if (i == j) // i==j ye bakmamali cunku bu dogru_basamak olurdu biz yanlis_basamak sayisini ariyoruz
							continue;
						if (numbers.get(i) == girilen_sayilar.get(j))
							yanlis_basamak++;
					}
				}
			}

			if (dogru_basamak == 4) { // dogru_basamak == 4  -> SONUC BULUNDU
				System.out.println();
				System.out.println("Tebrikler sayiyi " + adim +" adimda buldunuz!");
				break;
			}

			System.out.println("Dogru basamak tahmin sayisi : " + dogru_basamak);
			System.out.println("Yanlis basamak tahmin sayisi : " + yanlis_basamak);
			System.out.println();

		}

	}
	
	
	//GONDERILEN SAYIYI PARCALARA AYIRIR LIST OLARAK YOLLAR
	public static List<Integer> Ayristir(int number) {
		List<Integer> list = new ArrayList();
		for (int i = 0; i < 4; i++) {
			list.add(number % 10);
			number /= 10;
		}
		return list;
	}

	//4 BASAMAKLI BIRBIRINDEN FARKLI RANDOM SAYI URET
	public static int randomNumberGenerator(int sayi) {
		int carpim_sayisi = 1; //
		List<Integer> sayilar = new ArrayList(); // 0,1,2,3,4,5,6,7,8,9 bu liste eklenecek
		for (int i = 0; i < 10; i++) {
			sayilar.add(i);
		}
		Collections.shuffle(sayilar); // list i karistir  
		for (int i = 0; i < 4; i++) { 
			if (carpim_sayisi == 1000 && sayilar.get(i) == 0) { // Random fonksiyonumuz bu sistemde 0768 ya da 0918 gibi bir sayi uretebilir bu kisimda bunu onluyoruz
				sayilar.remove(i);
			}
			sayi += sayilar.get(i) * carpim_sayisi; //list den herhangi bir sayi al carpim_sayisi ile carpip sayiya ekle ---- Ornegin -> 4 gelsin sonra bunu simdilik 1 ile carp ve sayimiza ekle dongu sonrasinda 3 gelsin bunu 10 ile carp sayiya ekle ...
			carpim_sayisi *= 10; // her seferinde 10 kat artmasi gerek
		}
		return sayi; //sayimiz olustu gonderiyoruz
	}
	
}
