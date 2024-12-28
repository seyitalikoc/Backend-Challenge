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
/users/{username} isteği, UserController'daki getUser metoduna yönlendirilir.
/users/create isteği, createUser metodunu çağırır.
Controller sadece gelen istekleri alıp uygun servislere yönlendirir. HTTP yanıtları ise burada döndürülür.

#### Employee ####


### Service Katmanı ###

Service sınıfı, iş mantığını barındırır. Controller'dan gelen verileri işler ve gerekli model verilerini repository aracılığıyla alır. İşlemler sırasında doğrulamalar, hesaplamalar veya ek mantık burada gerçekleştirilir.

#### Company ####
getUserByUsername: Bu metod, UserRepository'yi kullanarak veritabanında kullanıcıyı arar.
createUser: Bu metod, yeni bir kullanıcıyı veritabanına ekler.


#### Employee ####


### Model Katmanı ###
Model, veritabanı ile doğrudan etkileşimde bulunur. Bu katman, genellikle JPA (Java Persistence API) kullanılarak veritabanındaki tablolara karşılık gelen sınıfları içerir. User sınıfı, veritabanındaki users tablosunun yapısını temsil eder.































