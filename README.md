# Stores Info

Stores Info es una app de una única pantalla con una interfaz simple, la que nos muestra una tabla que se compone de un título y un reciclreView. En este último, estaremos viendo los datos de las distintas tiendas que se obtienen desde el endpoint provisto, con una carga inicial y luego mediante el scroll en pantalla se logrará cargar las siguientes 10.

## Visualización

<p align="center">
  <img src="https://github.com/OelNooc/stores_info/assets/91097475/a60f2219-4597-4743-8720-0213e9f4134f" width="250" alt="GIF">
</p>

Aquí podemos observar desde la apertura de la aplicación hasta los diversos scroll que se hacen a modo de comprobar el funcionamiento de la paginación/llamados posteriores.

## Instrucciones App
> [!IMPORTANT]
> Lo primero es colocar el token y el companyUUID como $\color{blue}{strings}$ en un archivo que deberá tener por nombre $\color{green}{secrets.xml}$ . Este archivo deberá ser colocado dentro de la sub-carpeta $\color{red}{values}$ que encontraremos en la carpeta $\color{yellow}{res}$

![Imagen centrada](https://github.com/OelNooc/stores_info/assets/91097475/ab7ea724-fbb0-4e9f-b251-998124c876aa)

Aquí puedes encontrar un ejemplo de cómo quedaría el archivo secrets.xml, y de como acceder a las carpetas correspondientes.

| secrets.xml | acceder a carpeta res | sub-carpeta values |
|----------|----------|----------|
| ![Imagen 1](https://github.com/OelNooc/stores_info/assets/91097475/fa175561-7911-4f6a-8cb4-50366f0f68d4)|![carpeta-res](https://github.com/OelNooc/stores_info/assets/91097475/afc1a949-8e6d-43cd-987e-d909ecf58817)| ![carpeta-values](https://github.com/OelNooc/stores_info/assets/91097475/c5fc2f5a-e4b9-47cd-a242-fa665adf6ba5)|

## Instrucciones Testing

Para lo que a testing refiere, se hicieron únicamente pruebas unitarias, las cuales puedes encontrar en la carpeta $\color{yellow}{test/java/com/oelnooc/storesinfo}$

![image](https://github.com/OelNooc/stores_info/assets/91097475/b11d345f-4b77-4d97-93ef-575a4442a4bc)

Las pruebas en concreto se centran en probar los llamados y los casos de error a modo de poder prevenir comportamiento erróneo. En ambos casos es suficiente con ingresar al archivo de test y ejecutarlo
| ResponseHandleErrorTest | FrogmiClientTest |
|----------|----------|
|![image](https://github.com/OelNooc/stores_info/assets/91097475/3711c28e-49c8-4c16-980a-808e7fadaea3) | ![image](https://github.com/OelNooc/stores_info/assets/91097475/6982bdce-4a3c-4c99-9483-899847c209a1)|
