# BACKEND UYGULAMASI #

## Proje Yapısı #

Bu projede MVC (Model-View-Controller) mimarisi kullanılmaktadır. Ancak, görsel bir arayüz (View) kullanmadan, yalnızca Model ve Controller katmanları üzerinden iş akışı sağlanmaktadır. İş mantığı, Service katmanı aracılığıyla yönetilmektedir.<br>

Proje yapısı şu şekilde organize edilmiştir:

- **Model:** Veritabanı ile etkileşim sağlayan sınıflar.
- **Controller:** HTTP isteklerini alır ve uygun servisleri çağırarak yanıt döner.
- **Service:** İş mantığını barındıran ve Controller'dan gelen istekleri işleyerek Model'e yönlendiren sınıflar.


<br></br>
## Model ve Controller Akışı ##
Model ve Controller arasındaki akış, aşağıdaki adımlarla gerçekleşir:

### Modeller ###
Model sınıfı, veritabanı tablosunun yapısını temsil eder. Bu sınıf, veritabanı ile etkileşimi sağlayan gerekli alanları içerir.

- #### Company Model ####
```
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_address")
    private String companyAddress;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Employee> employees;

    // Getter ve Setter'lar
```

- #### Employee Model ####

```
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "employee_name")
    private String employeeName;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    // Getter ve Setter'lar
```

### Repository ###
Repository sınıfı, Model sınıfı ile veritabanı arasındaki bağlantıyı sağlar. Bu sınıf, JPA kullanılarak veritabanı işlemleri için CRUD (Create, Read, Update, Delete) fonksiyonlarını yönetir.

- #### Company ####
```
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findById(long id);
}
```

- #### Employee ####
```
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findById(long id);
}
```

### Service ###
Service sınıfı, iş mantığını içerir ve Controller'dan gelen talepleri işler. Repository üzerinden veri alır ve uygun işlemleri gerçekleştirir.

- #### Company ####
```
@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company getCompanyById(int id) {
        return companyRepository.findById(id);
    }

    public List<Employee> getEmployeesByCompanyId(int id) {
        return companyRepository.findById(id).getEmployees();
    }
    ...
```

- #### Employee ####
```
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final CompanyService companyService;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, CompanyService companyService) {
        this.employeeRepository = employeeRepository;
        this.companyService = companyService;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id);
    }
    ...
```

### Controller ###
Controller sınıfı, HTTP isteklerini alır ve uygun servis metodlarını çağırarak iş mantığını yönetir. Kullanıcıya geri dönüş sağlar.

- #### Company ####
```
@RestController
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/list")
    public List<Company> listCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/getCompanyInfo/id={id}")
    public Company getCompanyById(@PathVariable Integer id) {
        return companyService.getCompanyById(id);
    }
    ...
```

- #### Employee ####
```
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public List<Employee> listEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/getEmployeeInfo/id={id}")
    public Employee getEmployeeById(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id);
    }
    ...
```

## Akışın Detaylı Açıklaması ##
### Controller Katmanı ###
Controller sınıfı, kullanıcının HTTP isteklerini alır. Her bir HTTP isteği, ilgili servisin metodunu çağırır. Örneğin:

#### Company ####
- [x] **"/company/list"** isteği, CompanyController'daki listCompanies metoduna yönlendirilir.
- [x] **"/company/getCompanyInfo/id={id}"** isteği, getCompanyById metodunu çağırır.
- [x] **"/company/getCompanyEmployees/id={id}"** isteği, getEmployeesByCompanyId metodunu çağırır.
- [x] **"/company/saveCompany"** isteği, saveCompany metodunucu çağırır.
- [x] **"/company/updateCompany/id={id}"** isteği, updateCompany metodunu çağırır.
- [x] **"/company/delete/id={id}"** isteği, deleteCompany metodunu çağırır.

#### Employee ####
- [x] **"/employee/list"** isteği, EmployeeController'daki listEmployees metodunu çağırır.
- [x] **"/employee/getEmployeeInfo/id={id}"** isteği, getEmployeeById metodunu çağırır.
- [x] **"/employee/getEmployeeCompany/id={id}"** isteği, getEmployeesCompanyId metodunu çağırır.
- [x] **"/employee/saveEmployee"** isteği, saveEmployee metodunu çağırır.
- [x] **"/employee/updateEmployee/id={id}"** isteği, updateEmployee metodunu çağırır.
- [x] **"/employee/deleteEmployee/id={id}"** isteği, deleteEmployee metodunu çağırır.

### Service Katmanı ###
Service sınıfı, iş mantığını barındırır. Controller'dan gelen verileri işler ve gerekli model verilerini repository aracılığıyla alır. İşlemler sırasında doğrulamalar, hesaplamalar veya ek mantık burada gerçekleştirilir.

#### Company ####
- [x] **getCompanyById :** Bu method gelen id'ye sahip company'yi ***CompanyRepository***'yi kullanarak veri tabanında arar.
- [x] **getEmployeesByCompnayId :** Bu method gelen id'ye sahip company'nin ilişikili olduğu employee'leri ***CompanyRepository*** kullanarak veri tabanınında arar.
- [x] **getAllCompanies :** Bu method veri tabanındaki tüm company'leri ***CompanyRepository*** kullanarak veri tabanında arar.
- [x] **saveCompany :** Bu method yeni bir company'yi veri tabanına ekler.
- [x] **updateCompany :** Bu method gelen id'ye sahip company'yi veri tabanında günceller.
- [x] **deleteCompany :** Bu method gelen id'ye sahip company'yi veri tabanından silmek için kullanılır.

#### Employee ####
- [x] **getEmployeeById :** Bu method gelen id'ye sahip employee'yi ***EmployeeRepository***'yi kullanarak veri tabanında arar.
- [x] **getCompanyByEmployeeId :** Bu method gelen id'ye sahip employee'nin ilişikili olduğu compnay'yi ***EmployeeRepository*** kullanarak veri tabanınında arar.
- [x] **getAllEmployees :** Bu method veri tabanındaki tüm company'leri ***EmployeeRepository*** kullanarak veri tabanında arar.
- [x] **saveEmployee :** Bu method yeni bir employee'yi veri tabanına ekler.
- [x] **updateEmployee :** Bu method gelen id'ye sahip employee'yi veri tabanında günceller.
- [x] **deleteEmployee :** Bu method gelen id'ye sahip employee'yi veri tabanından silmek için kullanılır.

### Model Katmanı ###
Model, veritabanı ile doğrudan etkileşimde bulunur. Bu katman, genellikle JPA (Java Persistence API) kullanılarak veritabanındaki tablolara karşılık gelen sınıfları içerir. 
**[Company](https://github.com/seyitalikoc/Backend-Challenge/blob/main/challenge-q6/src/main/java/com/example/challenge/entity/Company.java)** sınıfı, veritabanındaki ***company*** tablosunun yapısını; 
**[Employee](https://github.com/seyitalikoc/Backend-Challenge/blob/main/challenge-q6/src/main/java/com/example/challenge/entity/Employee.java)** sınıfı veri tabanındaki ***employee*** tablosunun yapısını temsil eder.




## Proje Kurulumu ##

1. Projeyi klonlayın.
```
gh repo clone seyitalikoc/Backend-Challenge
```
2. Maven kullanarak bağımlılıkları yükleyin.
3. Veritabanı bağlantı bilgilerini [application.properties](https://github.com/seyitalikoc/Backend-Challenge/blob/main/challenge-q6/src/main/resources/application.properties) dosyasına ekleyin.
4. Uygulamayı çalıştırın ve belirtilen endpoint'leri test edin.






















