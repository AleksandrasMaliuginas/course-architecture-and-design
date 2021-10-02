# course-architecture-and-design

Aleksandras Maliuginas I gr. 1 pogr.

Ar buvo aiškus ir patogus unit testai, ar kodas aiškus.
-----------------------------------------------------------

Daugmaž testai buvo suprantami ir aiškūs. Tačiau buvo keli tūkumai:
  
- Testams prirašyta per daug komentarų (po komentarą kiekvienam assert'ui). Apsunkina kodo skaitymą.
- EmailValidator turėjo kelis neteisingus bei nepilnus testus.
- Visi metodai buvo padaryti kaip statiniai, tad implementacijoje irgi naudoju statinius metodus.
- Testuojami privatūs metodai, nors mano nuomone reikėtų testuoti vieną public metodą, kuris jau kviestų privačius.
  (Bet gal tai priklauso nuo realizacijos, tai nesu 100% tikras dėl šito).
	
Kaip jus galėtumete juos pagerinti
-----------------------------------------------------------

  + Ištaisyčiau kalidingus testus (pvz., kad # yra invalid char email'e).
  + Pridėčiau kelis papildomus test case'us.
	
Kokius unit testus jus galėtumėte pridėti (jei tokių yra)
-----------------------------------------------------------

**Email local path:**
```java 
assertFalse(emailValidator.validate("Abc..123@example.com"));
assertFalse(emailValidator.validate("1234567890123456789012345678901234567890123456789012345678901234+x@example.com"));
assertFalse(emailValidator.validate("email.@example.com"));
assertFalse(emailValidator.validate("@example.com"));
assertFalse(emailValidator.validate("Joe Smith <email@example.com>"));
assertFalse(emailValidator.validate("simple,email@example.com"));
assertFalse(emailValidator.validate("simple email@example.com"));
assertFalse(emailValidator.validate("simple:email@example.com"));
assertFalse(emailValidator.validate("simple\\email@example.com"));
```
**Email domain:**
```java
assertFalse(emailValidator.validate("email@example.com (Joe Smith)"));
assertFalse(emailValidator.validate("email@-example.com"));
assertFalse(emailValidator.validate("email@e_x_a_m_p_l_e.web"));
assertFalse(emailValidator.validate("email@111.222.333.44444"));
assertFalse(emailValidator.validate("email@example@example.com"));
assertFalse(emailValidator.validate("email@example..com"));
```

	
Rezultatai:
-------------------------
[Implementacija Gintarės testams](https://github.com/AleksandrasMaliuginas/course-architecture-and-design/tree/master/src/com/alemal/gintare/validation)

[Implementacija mano testams](https://github.com/AleksandrasMaliuginas/course-architecture-and-design/tree/master/src/com/alemal/validation)

Savo implementaciją parašiau, nes nebuvo kaip pakeisti statinių metodų. Tačiau pati logika nelabai skiriasi, tik savo implementaciją labiau suskirsciau bandžiau labiau _decouplinti_.

Taip pat, savo implementacijoje ištaisiau aukščiau nurodytas klaidas.

**PS:**  mano testai buvo pakeisti, tačiau pačių test case'ų nekeičiau pridėjau tik assertAll() ir užkomentavau kelis sudėtingesnius email variantus, kurie nebuvo aprašyti emokymų užduotyje, tačiau jie yra padaryti pagal tikras email validavimo taisykles.
