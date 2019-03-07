package findGame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

//---------- OYUNUN TERCIH ETTIRME KISMI ---------- 
//Kullanicidan bir sayi tutmasi istenecek ve yazilimin her tahmininde ipucu istenecek buna gore yazilim dogru sayiyi bulmaya calisacak --- AMAC: PERFORMANS ANLAMINDA KULLANICIYI GECMEK  
public class TahminEttir {
	
	//Okuyucumuz -> s
	static Scanner s = new Scanner(System.in);
	
	//TAHMIN ETTIRME KISMI
	// Genel olarak programin isleyisi su sekilde ; ilk tahminimiz random bir sayi olacak (random sayi listinden gelen - 1023), kullanicinin verdigi dogru ve yanlis basamaklari tutacagiz sonra daha onceden hazirladigimiz random sayilar list imizden tahmin yaptigimiz sayi ile dogru ve yanlis basamaklari eslesen ilk sayi diger tahminimiz olacak eslesmeyen sayilar ise list den silinecek
	// Ornegin kullanici 1234 tutsun biz ise 1076 dedik 1 dogru basamagimiz 0 yanlis basamagimiz var simdi listden 1076 ya gore bu kaliba uyan(1 dogru, 0 yanlis) sayiyi tahmin olarak verecegiz ornegin bu sayi 2084 olabilir ve eslesmeyenlerde silindigi icin list her seferinde daralacak en sonunda listde sadece dogru tahmin kalacak
	public static void TahminEttir() {
	
		List<Integer> sayilar = randomNumbersGenerator(); // random sayilarimizi tutan list -> sayilar
        int adim = 0; // Tahmin sayisi -> adim
        System.out.println("----Aklindan 4 basamakli rakamlari farkli bir sayi tut----");
        
      //Dogru sayi bulunana dek dongu devam edecek ve her seferinde Kontrol fonksiyonu ile eslestirme islemi yapilacak eslesmeye uymayan list elemanlari silinecek
        while (true) {
            adim++; //her seferde 1 adim artirir
            Iterator<Integer> it = sayilar.iterator(); // sayilar listimizin iteratoru -> it
            int tahmin = it.next(); // Her seferinde yaptigimiz tahmin -> tahmin
            System.out.println("Tahminim: " + tahmin);
            System.out.print("Dogru basamak sayim:");
            int dogru_basamak = s.nextInt();
            System.out.print("Yanlis basamak sayim:");
            int yanlis_basamak = s.nextInt();
            //System.out.println(sayilar); // TEST icin tutulan sayilarin ekrana yazilmasi
            Kontrol(dogru_basamak, yanlis_basamak, tahmin, sayilar); // kontrol islemi 
            if (dogru_basamak == 4) { // dogru basamak 4 ise kac tahmin yapildigini soyle ve donguyu sonlandir
                System.out.println();
            	System.out.println("Sayiyi " + adim + " adimda buldum!");
                break;
            }
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

	//4 BASAMAKLI BIRBIRINDEN FARKLI 1000 den 10000e KADAR TUM SAYILARIN TUTULDUGU LIST I GONDER
    public static List<Integer> randomNumbersGenerator() {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 1000; i < 10000; i++) {
            list.add(i); // 1000 den 10000 e kadar tum sayilari liste ekle
        }
        Iterator<Integer> it = list.iterator(); //listimizin iteratoru -> it
        while (it.hasNext()) {
            int number = it.next(); //iteratorun tuttugu sayi -> number
            List<Integer> list2 = Ayristir(number); // numberin icerisindeki sayilarin ayrismis halinin tutuldugu list Ornegin/ number == 1894 ise list2 = {1,8,9,4}
            List<Integer> list3 = new ArrayList<Integer>(); // rakamlari ayni sayilari test edecegimiz list
            for (int c : list2) { // bu kisimda rakamlari ayni sayilar list den siliniyor Ornegin / number 1020 ise list3 e 1,0,2 eklenecek tekrar 0 eklenmesi gerekecegi icin sayi silinecek ama 1024 sayisinda bir problem cikmayacak
                if (list3.contains(c)) { // silinme isleminin yapildigi kisim
                    it.remove();
                    break;
                }
                list3.add(c); // ekleme isleminin yapildigi kisim
            }
        }
        //System.out.println(list);
        return list;
    }

    //KONTROL KISMI
    //Kullanicinin girdigi ipuclari (dogru_basamak , yanlis_basamak) , yaptigimiz tahmin, sayilarimizi tutan list alinir 
    //Listin iteratoru ile bir sonraki eleman ile yaptigimiz tahmin arasinda bir kontrol islemi gerceklestirilir
    //Buna gore eger tahminimiz ile bir sonraki eleman arasinda (dogru_basamak,yanlis_basamak) baglantisi var ise yeni tahminimiz bir sonraki elemanimiz olur yok ise list den bu eleman silinir
    public static void Kontrol(int dogru_basamak, int yanlis_basamak, int tahmin,
            List<Integer> sayilar) {
    	
        Iterator<Integer> it = sayilar.iterator(); //sayilar listimizin iteratoru -> it
        while (it.hasNext()) {
        	int dogru=0;
        	int yanlis=0;
            int ikinci_tahmin = it.next();
            List<Integer> list = Ayristir(ikinci_tahmin); //bir sonraki elemanimizin sayilarinin tutuldugu list Ornegin / 1708 ise list-> 1,7,0,8
            List<Integer> list2 = Ayristir(tahmin); //tahminimizin sayilarinin tutuldugu list Ornegin / 1706 ise list-> 1,7,0,6
            for (int i = 0; i < list.size(); i++) {
                if (list2.get(i) == list.get(i)) { // Eger tahminimizdeki sayilarin basamaklari ile bir sonraki elemanimizin basamaklari eslesiyorsa 'dogru' yu 1 artir
                    dogru++;
                } else if (list2.contains(list.get(i))) { // Eger tahminimizdeki sayilarin basamaklari ile bir sonraki elemanimizin basamaklari eslesmiyorsa tahminin icindeki sayilarda bir sonraki elemandaki sayilar var mi kontrol et varsa 'yanlis' i 1 artir
                    yanlis++;
                }
            }
            if (dogru_basamak != dogru || yanlis_basamak != yanlis) { // Eger tahminimiz ile bir sonraki elemanimiz dogru ve yanlis basamak acisindan eslesiyorsa artik yeni tahminimiz -> bir sonraki elemanimiz , eslesmiyorsa sil
                it.remove();
                }
            }
        }
    }


