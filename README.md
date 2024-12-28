# ENOCA™ Backend-Challenge

### SORU-1 ###
#### MVC (Model-View-Controller) Kavramı ####

MVC, yazılım geliştirme süreçlerinde uygulama mantığını ayrıştırmak için kullanılan bir mimari desendir. Bu desen, uygulamayı üç temel katmana ayırır:
- **Model :** Veritabanı ile etkileşime giren, iş mantığını içeren ve verilerin temsilini yapan sınıflardır. Genellikle Entity sınıfları olarak tanımlanır.
- **View :** Modelden gelen verileri kullanıcıya sunar ve kullanıcıdan alınan verileri kontrol katmanına iletir. 
- **Controller :** Model ve View arasında bir köprü görevi görür. Kullanıcıdan gelen girdileri alır, iş mantığını model ile işleyerek sonucu View'e gönderir.


#### Neden İhtiyaç Duyulur ? ####

- Uygulama mantığı, veri ve kullanıcı arayüzü ayrı katmanlarda tanımlandığı için daha düzenli bir yapı sağlar.
- Ayrılmış katmanlar sayesinde, her katmanı bağımsız olarak test etmek mümkündür.
- Değişiklikler yalnızca ilgili katmanda yapılabilir. Örneğin, kullanıcı arayüzünde bir değişiklik, veri katmanını veya iş mantığını etkilemez.
- Geliştirme ekibinin farklı uzmanlık alanlarına göre (örneğin UI tasarımı, iş mantığı, veri yönetimi) katmanları geliştirmesi kolaylaşır.

  
#### Java'da Nasıl Kurgulanır ? ####
- Model katmanında ***@Entity*** ile kurgulanır. 
- View katmanında ***JSP, Thymeleaf*** veya herhangi bir şablon motoru kullanılarak kurgulanır.
- Controller katmanında ***@Controller*** kullanılarak kurgulanır.


#### Object Oriented Katmanları ####
1.  **Entity Layer :** Veri tabanındaki nesneleri temsil eder. JPA ile tanımlanan ***@Entity*** sınıfları buna örnektir.
2.  **Service Layer :** İş mantığını barındırır. Veriyi işlemeye veya birden fazla kaynağı koordine etmeye odaklanır.
3.  **Repository Layer :** Veri tabanıyla etkileşim kurar. Spring Data JPA ile genellikle ***@Repository*** veya otomatik CRUD yöntemleri kullanılır.
4.  **Controller Layer :** HTTP isteklerini işler ve iş mantığını çağırır.

<br></br>
### SORU-2 ###
Birbirinden bağımsız iki platformun iletişim kurması için platformdan bağımsız protokoller ve veri formatları kullanılır. 
Bu yapılar, farklı dillerde ve teknolojilerde yazılmış sistemlerin birbiriyle etkili bir şekilde haberleşmesini sağlar.
RESTful Web Servisleri bunlara örnek gösterilebilir.
- [x]  X platformu (Java) bir REST API sunar.
- [x]  Y platformu (C#), bu API'ye HTTP istekleri gönderir ve JSON/XML yanıtlarını işler. 

<br></br>
### SORU-3 ###









<br></br>
### SORU-4 ###
```
for (int i = 0; i <= 5; i++) {
  int j=0;
  do{
    System.out.print("*");
    j++;
  }while (j < 2*i);
  System.out.println();
}
```
[İlgili Dosya](https://github.com/seyitalikoc/Backend-Challenge/blob/main/q4/Main.java)

<br></br>
### SORU-5 ###

#### Server'a Erişim Testi ####
1. **ping** konsol komutu ile suncuya ping gönderip yanıt alıyorsam sunucunun çalışır vaziyette olduğunu öğrenirim.
```
ping sunucu_ip_adresi
```

2. Yanıt aldıktan sonra sunucuya erişmek için aşağıdaki komutları kullanırım.
```
ssh kullanici_adi@sunucu_ip_adresi
```
```
sftp kullanici_adi@sunucu_ip_adresi
```
Verilen kullanıcı adını burada kullnadıktan sonra her iki konsol komutunda da şifre sorulacak ve verilen şifreyi girip sunucuya erişmiş olacağız.



#### Server'a Dosya Aktarma ve Çekme ####
Öncelikle aşağıdaki komutu kullanarak sunucunun **FTP (File Transfer Protocol)**'e bağlanırım.
```
sftp kullanici_adi@sunucu_ip_adresi
```

1. Server'a dosya aktarmak için aşağıdaki komutu kullanırım.
```
put yerel_dosya_yolu
```

2. Server'dan dosya çekmek için aşağıdkai komutu kullanırım.
```
get uzak_dosya_yolu
```














<br></br>
### SORU-6 ###

[Cevaba gitmek için tıklayınız.](https://github.com/seyitalikoc/Backend-Challenge/blob/main/challenge-q6)



<br></br>
### SORU-7 ###
***updateAt*** alanı **long** tipinde tutulduu için burada bir dönüşüm yapılması gerekmektedir.<br>
**"2020-01-01T00:00:00Z"** tarihi long tipinde **"1577836800000"** değerine eşittir.
```
http://example?query=updatedAt:[1577836800000 TO *]
```


















