El sistema cuenta con un GestorDeSoporte que lee las propiedades (application.properties) de colas de atencion de empleados para dar soporte y las configura con la prioridad primero operadores de nivel 1, luego supervisores, luego gerentes. Si todos están ocupados, se le avisa al cliente que todos los operadores están ocupados y que se puede comunicar nuevamente más tarde.
A su vez, puede asignar empleados a pedido y desasignarlos una vez concluido el soporte
Cada NivelDeSoporte modela una cola de atencion.
A su vez NivelDeSoporteEncadenado representa un nivel de soporte y conoce la de nivel superior a quien derivar en caso de excepcion.
Finalmente el empleado conoce su cargo y puede manisfestarlo al presentarse al cliente.

-PROXIMOS PASOS-
1) hacer unit tests por cada clase pero definiendo los estados esperados con assertions
2) crear controllers para definir API Rest sobre el GestorDeSoporte
3) crear testing de integracion con SpringBoot sobre la API Rest
