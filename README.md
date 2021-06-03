# Aplicacion Spring Boot demo que expone endpoint GET /api/price?brandId=<brandId>&productId=[productId]&date=[date] para la consulta de precios
(ejemplo GET /api/price?brandId=35455&productId=1&date=2020-12-30 23:58:58)

-- Hay dos posibles implementaciones para la persistencia, con sus PROS y CONTRAS.

Ambas implementaciones devoleran un Optional que contendrá el precio (en caso de haberlo con los paremetros obtenidos)

Como la consulta puede devolver varios registros y solo nos interesa la información del primero, hay una aproximación sencilla en la rama main

https://github.com/gabicasas/price-list/blob/c43cb7bebe3a5b13e781cdf171f717a53fa8a43f/src/main/java/com/zara/prices/repository/PriceRepository.java#L32

PROS: Todo queda en la interfaz de repository y es de facil mantenimiento

CONTRAS: Todos los registros que se han obtenido de la consulta se han instanciado como una entity Java aunque solo se vaya a usar el primero (problemas de rendimiento si son muchos)


La segunda aproximación mitiga el efecto no deseado de la primera en la rama feature-custom-repository

https://github.com/gabicasas/price-list/blob/81bc512d55273cf694601dcd121a3fd63806be24/src/main/java/com/zara/prices/repository/PriceRepositoryCustomImpl.java#L13

PROS: Solo se instancia la entity que se usará 

CONTRAS: El código es mas complejo, en caso de que no suponga un problema de rendimiento esta solución pueder ser sobreingenieria

  
  ![image](https://user-images.githubusercontent.com/924807/119991972-52e57300-bfca-11eb-8602-db3e130a091f.png)
