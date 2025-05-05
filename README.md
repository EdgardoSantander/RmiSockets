## Diagrama de secuencia


![Diagrama del sistema]()


| Componente             | Capa                    | Tecnología         | Rol funcional                    |
|------------------------|-------------------------|--------------------|----------------------------------|
| Interfaz JavaFX        | Presentación            | JavaFX GUI         | Entrada de votos / visualización |
| Receptor de Votos      | Comunicación/entrada    | Sockets            | Envía votos al servidor          |
| Servidor RMI           | Distribución            | Java RMI           | Gestiona votos y resultados      |
| Base de Datos          | Persistencia            | H2                 | Guarda votos                     |
| Emisor de Votos        | Comunicación/salida     | Sockets + RMI      | Consulta resultados y muestra    |
