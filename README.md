# rpc-rmi

### Componentes
- ATM
- BANK

Para correr la aplicación se necesita un JDK v11 o mayor.

#### Bank
1. Para correr el servidor se debe abrir una consola de comandos en la carpeta de Bank y compilar las clases con el siguiente comando:
```
javac *.java
```
2. Luego se debe correr el servicio de RMI con el siguiente comando:
```
start rmiregistry
```
3. Posteriormente se debe correr el servidor mediante el comando:
```
java OperationServer
```

#### ATM
1. Para correr el cliente se debe abrir una consola de comandos en la carpeta de ATM y compilar las clases con el siguiente comando:
```
javac *.java
```
2. Posteriormente se debe correr el cliente mediante el comando:
```
java Main
```
