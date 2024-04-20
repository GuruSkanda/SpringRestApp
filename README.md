# TDD With SpringBoot JPA Application

### The essential idea is to break down writing a scenario (or test) into three sections:

- The given part describes the state of the world before you begin the behavior you're specifying in this scenario. You can think of it as the pre-conditions to the test.
- The when section is that behavior that you're specifying.
- Finally the then section describes the changes you expect due to the specified behavior.

### spring-boot-starter-test

This Dependency internally using 3 dependencies. That are
- junit 
- aspectj
- mockito

No need to install the above dependencies manually in spring boot application becuase it is alreay present inside the spring-boot-starter-test

 ### Annotation Used
- @SpringBootTest
- @Test
- @Mock
- @BeforeEach
- @AfterEach
- 
  

  