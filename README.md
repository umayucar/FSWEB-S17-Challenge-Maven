#  Java Spring REST API

### Proje Kurulumu

Projeyi öncelikle forklayın ve clone edin.
Daha sonra projeyi IntellijIDEA kullanarak açınız. README.md dosyasını dikkatli bir şekilde okuyarak istenenleri yapmaya çalışın.
Proje sayımız ilerledikçe proje yönetimimizi kolaylaştırmak adına projelerimizi belli klasör kalıplarında saklamak işimizi kolaylaştırmak adına iyi bir alışkanlıktır.
Örnek bir Lokasyon: Workintech/Sprint_1/Etud.

### Hedeflerimiz:

### Course Rest Api

 ### Başlangıç
 * Uygulamanızı  ```9000``` portundan ayağa kaldırın.
 * Bir öğrenci için rest api dizayn etmeniz istenmektedir.

### Amaç
 * Amacımız Alex isimli öğrencinin derslerini ve derslerden aldığı notları ekleyebileceğimiz bir rest api oluşturmak ve Alex'in her dersi için totalGpa hesabı yapabilmek.
 * Bu Api'yi oluştururken error handling ve validation kurallarına uymalıyız.
 * Dependency Injection kurallarına uymalıyız.
 
 ### Görev 1
 * main metodunuzun olduğu paket altında ```controller```, ```entity```, ```exceptions``` isminde 3 adet daha paket oluşturunuz.
 * Project Lombok'u dependency olarak uygulamanıza ekleyin.
 * ```model``` paketinin altına ```CourseGpa``` adında bir interface tanımlayınız. ```int getGpa()``` adında bir metod tanımlayınız.
 *  ```CourseGpa``` interface kullananan 3 tane daha sınıf tanımlayınız.  ```LowCourseGpa```,  ```MediumCourseGpa```,  ```HighCourseGpa```
 *  ```LowCourseGpa``` sınıfı getGpa() metodu 3, ```MediumCourseGpa``` sınıfı için getGpa() metodu 5,  ```HighCourseGpa``` için getGpa() metodu 10 değerini return etmeli.
 * ```model``` paketi altına ```Course``` sınıfını tanımlayınız. ```String name, int credit, Grade grade``` isimli 2 fielda sahip olmalı.
 * ```model``` paketi altına ```Grade``` sınıfını tanımlayınız. ```int coefficient, String note``` bu iki değişkenide set eden bir adet constructor tanımlayınız.

 ### Görev 2
 * ```controller``` paketi altında ```CourseController``` adında 1 tane controller yazmalısınız.
 * ```CourseController``` içerisinde course objelerini tutacak bir adet ```courses``` isminde List tutmalısınız. Controller bean ilk oluştuğunda bu listi tanımlamalı.
 * ```CourseController``` dependency injection yöntemi ile  ```LowCourseGpa```,  ```MediumCourseGpa```,  ```HighCourseGpa``` sınıflarının hepsini tanımlamalı.
 * Amacımız CRUD işlemlerini tanımlayan endpointler yazmak. 
 * Aynı isimde birden fazla course ekleyemeyiz.
 * credit değeri hiçbir şekilde 0'dan küçük olamaz. 4'ten büyük olamaz.
 * [GET]/workintech/courses => tüm course listini dönmeli.
 * [GET]/workintech/courses/{name} => İlgili isimdeki deki course objesini dönmeli.
 * [POST]/workintech/courses => Bir adet course objesini courses listesine ekler. Dönüş değeri olarak course objesi ve totalGpa değerini dönmeli. totalGpa şu şekilde hesaplanır. 
    eğer dersin credit değeri 2 veya 2 den düşükse ```course.getGrade().getCoefficient() * course.getCredit() * lowCourseGpa.getGpa()```
    eğer dersin credit değeri 3 ise ```course.getGrade().getCoefficient() * course.getCredit() * mediumCourseGpa.getGpa()```
    eğer dersin credit değeri 4 ise ```course.getGrade().getCoefficient() * course.getCredit() * highCourse.getGpa()``` 
 * [POST] requestlerin response code değeri ```201``` olmalıdır.
 * [PUT]/workintech/courses/{id} => İlgili id deki course objesinin değerlerini yeni gelen data ile değiştirir. Course objesi ve totalGpa değerlerini dönmelidir.
 * [DELETE]/workintech/courses/{id} => İlgili id değerindeki course objesini listeden siler.


 ### Görev 3
 * Her endpointin hata fırlatabileceği senaryolar düşünülmeli ```exceptions``` paketi altına bu Error sınıfları oluşturulmalı.
 * ```exceptions``` paketi altına hata mesajlarını dönmek için ```ApiErrorResponse``` sınıfını ekleyiniz.
 * ```exceptions``` paketi altına hata hataları yakalayamabilmek için ```ApiException``` sınıfını ekleyiniz.
 * ```exceptions``` paketi altına ```GlobalExceptionHandler``` adında tüm hataları yöneten bir sınıf ekleyiniz. 
 * Error Handling Global bir merkezden yönetilmeli. Controller sınıflarının altında olmamalı.
 * ```@Slf4j``` ile ilgili işaretlenme yapılmalı. Endpoint bir şekilde hata döndüğünde ```error logu``` basılmalı.
 * validation işlemleri controller sınıfı içinde kalmamalı.

### Görev 4
 * Codepen üzerinden veya bir React uygulaması oluşturarak Spring Boot ile yazdığımız projeye request atmayı deneyiniz.
  cors hatasını nasıl çözebiliriz.

 

### ⚠️ Skorun NextGen'e Kaydedilmediyse

Eğer testleri çalıştırdığın halde skorun NextGen'e kaydedilmediyse, önce fork'unun güncel olup olmadığını kontrol et:

1. GitHub reponu aç.
2. Repo **X commit ahead** ve **X commits behind** şeklinde bir uyarı gösteriyorsa, branch'in güncel değildir.
3. **Sync fork → Update branch** adımlarını uygula.
4. Ardından localinde şu komutu çalıştır:

```bash
   git pull
```

5. Testleri tekrar çalıştır. Bu adımdan sonra skorun güncellenmiş olacaktır.

> **Not:** Bu kontrolü yapmadan tekrar tekrar test çalıştırmak sorunu çözmez; sorunun kaynağı genellikle fork'un upstream repository ile senkron olmamasıdır.
