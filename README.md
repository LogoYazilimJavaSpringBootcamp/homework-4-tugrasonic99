# 1. Mysql veya PostgreSQL ile controller katmanlarının çalışabilmesi için gerekli repository katmanlarını yazın. Ayrıca isbasi-email-service kuyruktan veriyi okuduktan sonra gerekli model class’ını oluşturup tabloya kaydedin.(60 Puan)

İşbaşı projesinde UserController üzerinden RabbitMQ'ya gönderilecek dummy Email değer endpoint'i hazırlandı: localhost:8080/users/dummies (POST)
İşbaşı Email projesinde Email modeli table entity haline getirildi. EmailListener'dan alınan değerler direkt olarak emails tablosuna kaydedilecek.
EmailRepository ve EmailService kullanılarak emails tablosu EmailController üzerinden görüntülenebilecek: localhost:8080/emails/ (GET)




# 2. JDBC, JdbcTemplate ve Hibernate ile bir DAO katmanını yazın ve avantajlarını ve dezavantajlarını kendi görüşlerinizle beraber yazın. OOP’nin polimorfizm’den yararlanarak aynı tabloya üç yöntem ile CRUD işlemlerini yapan kodu yazınız. (30 Puan)

Benzerlik anlamında JDBC ve JdbcTemplate, nasıl kullanılacağı anlamında birbirine benzeyen iki arayüzdür. JdbcTemplate, JDBC teknolojileri kullanılarak geliştirildiği için kullanım mantığı olarak JDBC'ye benzerdir. Büyük farkları nasıl DB'ye bağlandıkları, query üzerinden çekilen datanın nasıl tutulduğu ve genel anlamda ne kadar çok kod yazıldığıdır. JDBC, biraz ilkel olup, değerleri tekerli bloklar şeklinde depolar. JdbcTemplate ise daha pratik fonksiyonlarla dataya sahip çıkar veya manipule eder. Bağlantı durumunda da JDBC, büyük bir kod bloğu ister. JdbcTemplate bağlantı değerleri ise configuration içerisinde depolanır. Kısaca, çoğu durumda JdbcTemplate, JDBC'ye üstündür fakat projelerin ne beklediğine göre ikisi de kullanılabilir. Ana ortak özellikleri ise, data manipulasyonu için raw query kullanmalarıdır. Benzer teknolojileri kullandıklarından, her ne kadar JdbcTemplate daha pratik olsa da, ikisi de benzer görevlerde benzer performans gösterebilir.

Hibernate ise modellemeyi ve işlemlerini Entity bağlantılı annotation'lar üzerinden yaptığı için JDBC ve JdbcTemplate'den farklıdır. Raw query kullanmaz ve kendi transaction methodlarıyla tablo manipulasyonu sağlar. Maalesef kendi annotation'larını kullanması her ne kadar pratik olsa da, JDBC ve JdbcTemplate ile senkronize çalışmasını engelleyebilir. Örnek olarak(Projemdeki hatalardan biri) Hibernate'in yarattığı bir Primary Key'in yaratılan değerlerine JDBC ve JdbcTemplate tarafından erişim sağlıklı olmuyor. Bir tabloya Hibernate tarafından eklenen Primary Key'li bir değer üzerine bahsedilen diğer iki arayüz kuralları bozmadan ekleme yapsa bile Hibernate, sadece kendi Primary Key Sequence'ni tanıdığından diğer girilen değerlere aldırış yapmıyor. Fakat bu, Kendi sequence'inin bu yeni atanan fakat tanınmayan Primary key değerine ulaşmasının ardından, değerlerin aynı olmasından dolayı hata vermesini sağlıyor. O yüzden kendi açımdan Hibernate'in diğer iki arayüzle tek bir tablo üzerine işlem yapmaması görüşündedir.

İşbaşı Projesinde Product üzerine çalışıldı. Product, persistence kullanılarak Hibernate ile çalışabilmesi için bir Entity haline getirildi. ProductDAO ve üst DAO katmanları yazıldı, ProductService üzerinden DAO katmanlarıyla JDBC, JdbcTemplate ve Hibernate fonksiyonları yazıldı. ProductController'a arayüz endpointleri yazıldı(arayuz_ismi={jdbc, jdbctemplate, hibernate}).


* Yeni product ekleme: localhost:8080/products/{arayuz_ismi}/saveproduct (POST)
* Tüm product'ları listeleme: localhost:8080/products/{arayuz_ismi}/allproducts (GET)
* id ile product silme: localhost:8080/products/{arayuz_ismi}/deleteproduct (POST)
* id ile product arama: localhost:8080/products/{arayuz_ismi}/findproduct (GET)

NOT: ProductController'ın çalışabilmesi için öncelikle ya hibernate üzerinden bir product eklemek gerek ya da localhost:8080/products/hibernate/dummy (POST) endpoint'ini çalıştırmak gerekiyor. Bunun ana sebebi JDBC ve JdbcTemplate'in, Hibernate'in Entity'si tarafından tanımlanması gereken Primary Key'i arttırıp yeni değer girebilmesi için ihtiyaç duymasıdır. Bu olaydan sonra da ekleme işlemi ya hep Hibernate ile ya da sadece JDBC ve JdbcTemplate kullanılarak yapılması gerekiyor. Bunun sebebi Hibernate'in sequence'inin tabloya JDBC ve JdbcTemplate'den gönderilen değerleri tanımaması ve potansiyel olarak Primary Key'i oluşturduğu bir kayıt açıp, tüm projeyi durdurmasıdır. Ekleme işlemi dışına tüm işlemler sorunsuz şekilde çalışıyor durumdadır.


# 3. Aşağıdaki kavramları örneklerle açıklayın ve hangi problemi nasıl çözdüklerini anlatan bir makale yazın.(MongoDB, Couchbase, Redis).

https://medium.com/@tugracakici/mongodb-redis-ve-couchbase-k%C4%B1yaslamal%C4%B1-bir-%C3%B6zetleme-f532cc183f4
